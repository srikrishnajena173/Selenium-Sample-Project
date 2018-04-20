package POM;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Generic.BaseClass;

public class SignInPage extends BaseClass {

	@FindBy(linkText = "Your trips")
	private WebElement yourTripLink;

	@FindBy(id = "SignIn")
	private WebElement signInButton;

	@FindBy(id = "modal_window")
	private WebElement signInFrame;

	@FindBy(id = "email")
	private WebElement userNameInput;

	@FindBy(id = "password")
	private WebElement passwordInput;

	@FindBy(id = "signInButton")
	private WebElement insidePopUpSignInButton;

	@FindBy(css = "#errors1 > span")
	private WebElement errorMessage;

	public SignInPage() {
		PageFactory.initElements(driver, this);
	}

	public void validateSignInPage() {
		WebElement[] ele = { yourTripLink, signInButton };
		click(ele);
		switchToFrame(signInFrame);
		click(insidePopUpSignInButton);
		Assert.assertEquals(getText(errorMessage),
				samplePropertiesFile("signInActualErrorMessage", "code.properties").trim());
	}
}
