package TestScenario;

import java.lang.reflect.Method;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Generic.BaseClass;
import POM.SignInPage;

public class SignInTest extends BaseClass {

	@BeforeMethod
	private void init(Method m) {
		setBrowser(samplePropertiesFile("URL", "code.properties").trim());
	}

	@Test(priority = 1)
	private void SingInTest() {
		SignInPage obj = new SignInPage();
		obj.validateSignInPage();
	}
	@AfterMethod
	private void quitBrowser( ) {
		closeBrowser();
	}
}
