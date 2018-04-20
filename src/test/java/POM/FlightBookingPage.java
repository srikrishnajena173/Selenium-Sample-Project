package POM;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Generic.BaseClass;

public class FlightBookingPage extends BaseClass {

	@FindBy(id = "OneWay")
	private WebElement oneWayRadioButton;

	@FindBy(id = "FromTag")
	private WebElement formInputBox;

	@FindBy(id = "ToTag")
	private WebElement toInputBox;

	@FindBy(id = "ui-id-1")
	private WebElement oneParentListEle;

	@FindBy(id = "ui-id-2")
	private WebElement toParentListEle;

	@FindBy(id = "DepartDate")
	private WebElement departDatePicker;

	@FindBy(css = ".monthBlock.first > table > tbody")
	private WebElement listOfDates;

	@FindBy(id = "SearchBtn")
	private WebElement searchButton;

	public FlightBookingPage() {
		PageFactory.initElements(driver, this);
	}

	public void testThatResultsAppearForAOneWayJourney() {
		int flag = 0;
		click(oneWayRadioButton);
		setText(formInputBox, "Bangalore");
		selectAnElementFromList(oneParentListEle, "li", "Bangalore, IN - Kempegowda International Airport (BLR)");
		setText(toInputBox, "Delhi");
		selectAnElementFromList(toParentListEle, "li", "New Delhi, IN - Indira Gandhi Airport (DEL)");
		click(departDatePicker);
		List<WebElement> trs = listOfDates.findElements(By.tagName("tr"));
		for (WebElement tr : trs) {
			List<WebElement> tds = tr.findElements(By.tagName("td"));
			for (WebElement td : tds) {
				List<WebElement> as = td.findElements(By.tagName("a"));
				for (WebElement a : as) {
					if (a.getText().equals("30")) {
						click(a);
						flag = 1;
						break;
					}
				}
				if (flag == 1) {
					break;
				}
			}
			if (flag == 1) {
				break;
			}
		}
		click(searchButton);
		Assert.assertTrue(isElementPresent(By.className("searchSummary")));
	}
}
