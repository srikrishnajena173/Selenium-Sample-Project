package Generic;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.asserts.SoftAssert;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

abstract public class BaseClass extends UtilityClass {

	protected SoftAssert sAssert = new SoftAssert();
	protected static Logger Log = Logger.getLogger(BaseClass.class);
	public static WebDriver driver;

	protected void setBrowser(String URL) {
		if (driver == null) {
			String btype = samplePropertiesFile("Browsertype", "code.properties").trim();
			try {
				if (btype.equals("ff")) {
					setFireFoxDriver();
				} else if (btype.equals("ch")) {
					setChormeDriver();
				} else if (btype.equals("ie")) {
					setIEBrowser();
				}
			} catch (WebDriverException wd) {
				Log.info("Error in intailizing the Webdriver " + wd.getStackTrace());
			}
		}
		driver.get(URL);
		Log.info("Successfully Lunch the Application with the URL " + URL.trim());
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		Log.info("Successfully maximize the window and delete all the cookies");
	}

	protected void setFireFoxDriver() {
		String fireFoxpath = null;
		if (System.getProperty("os.name").contains("Window")) {
			fireFoxpath = ClassLoader.getSystemResource("geckodriver.exe").getFile();
		} else if (System.getProperty("os.name").contains("Linux")) {
			fireFoxpath = ClassLoader.getSystemResource("geckodriver").getFile();
		} else if (System.getProperty("os.name").contains("Mac")) {
			fireFoxpath = ClassLoader.getSystemResource("geckodriver").getFile();
		}
		System.setProperty("webdriver.gecko.driver", fireFoxpath);
		driver = new FirefoxDriver();
		Log.info("Launch FireFox Browser");
	}

	protected void setChormeDriver() {
		String chromePath = null;
		if (System.getProperty("os.name").contains("Window")) {
			chromePath = ClassLoader.getSystemResource("chromedriver.exe").getFile();
		} else if (System.getProperty("os.name").contains("Linux")) {
			chromePath = ClassLoader.getSystemResource("chromedriver").getFile();
		} else if (System.getProperty("os.name").contains("Mac")) {
			chromePath = ClassLoader.getSystemResource("chromedriver").getFile();
		}
		System.setProperty("webdriver.chrome.driver", chromePath);
		driver = new ChromeDriver();
		Log.info("Launch Chrome Browser");
	}

	protected void setIEBrowser() {
		String iePath = null;
		if (System.getProperty("os.name").contains("Window")) {
			iePath = ClassLoader.getSystemResource("IEDriverServer.exe").getFile();
		} else if (System.getProperty("os.name").contains("Linux")) {
			iePath = ClassLoader.getSystemResource("IEDriverServer").getFile();
		} else if (System.getProperty("os.name").contains("Mac")) {
			iePath = ClassLoader.getSystemResource("IEDriverServer").getFile();
		}
		System.setProperty("webdriver.ie.driver", iePath);
		driver = new InternetExplorerDriver();
		Log.info("Launch IE Browser");
	}

	protected static void closeBrowser() {
		if (null != driver) {
			driver.close();
			WebDriver d = driver;
			driver = null;
			d.quit();
			Log.info("Closed the browser");
		}
	}

	protected void waitForAnElement(WebElement ele, long t1) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, t1);
			wait.until(ExpectedConditions.visibilityOf(ele));
		} catch (TimeoutException t) {
			Log.info(t.getStackTrace());
		} catch (ElementNotFoundException e) {
			Log.info(e.getStackTrace());
		} catch (StaleElementReferenceException s) {
			Log.info(s.getStackTrace());
		} catch (WebDriverException w) {
			Log.info(w.getStackTrace());
		}
	}

	public void click(WebElement[] ele) {
		for (int i = 0; i < ele.length; i++) {
			click(ele[i]);
		}
	}

	protected void click(WebElement ele) {
		try {
			waitForAnElement(ele, 30);
			ele.click();
		} catch (ElementNotFoundException e) {
			Log.info(e.getStackTrace());
		} catch (StaleElementReferenceException s) {
			Log.info(s.getStackTrace());
		} catch (WebDriverException w) {
			Log.info(w.getStackTrace());
		}
	}

	protected void setText(WebElement ele, String val) {
		try {
			waitForAnElement(ele, 30);
			ele.clear();
			ele.sendKeys(val);
		} catch (ElementNotFoundException e) {
			Log.info(e.getStackTrace());
		} catch (StaleElementReferenceException s) {
			Log.info(s.getStackTrace());
		} catch (WebDriverException w) {
			Log.info(w.getStackTrace());
		}
	}

	protected String getText(WebElement ele) {
		String val = null;
		try {
			waitForAnElement(ele, 30);
			val = ele.getText();
		} catch (ElementNotFoundException e) {
			Log.info(e.getStackTrace());
		} catch (StaleElementReferenceException s) {
			Log.info(s.getStackTrace());
		} catch (WebDriverException w) {
			Log.info(w.getStackTrace());
		}
		return val;
	}

	protected void switchToFrame(WebElement ele) {
		try {
			waitForAnElement(ele, 30);
			driver.switchTo().defaultContent();
			driver.switchTo().frame(ele);
		} catch (ElementNotFoundException e) {
			Log.info(e.getStackTrace());
		} catch (StaleElementReferenceException s) {
			Log.info(s.getStackTrace());
		} catch (WebDriverException w) {
			Log.info(w.getStackTrace());
		}
	}

	protected void selectDropDownByText(WebElement ele, String val) {
		try {
			Select sel = new Select(ele);
			sel.selectByVisibleText(val);
		} catch (ElementNotFoundException e) {
			Log.info(e.getStackTrace());
		} catch (StaleElementReferenceException s) {
			Log.info(s.getStackTrace());
		} catch (WebDriverException w) {
			Log.info(w.getStackTrace());
		}
	}

	protected void selectAnElementFromList(WebElement parentEle, String tagName, String val) {
		List<WebElement> tags = parentEle.findElements(By.tagName(tagName));
		for (WebElement ele : tags) {
			if (ele.getText().equals(val)) {
				click(ele);
				break;
			}
		}
	}
	
	 protected boolean isElementPresent(By by) {
	        try {
	            driver.findElement(by);
	            return true;
	        } catch (NoSuchElementException e) {
	            return false;
	        }
	    }
}
