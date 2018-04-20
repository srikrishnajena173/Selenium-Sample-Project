package TestScenario;

import java.lang.reflect.Method;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Generic.BaseClass;
import POM.FlightBookingPage;

public class FlightBookingTest extends BaseClass {
	FlightBookingPage obj;
	/**
	 * Description:Launch Internet Login Reset Registration Application
	 */
	@BeforeMethod
	private void init(Method m) {
		setBrowser(samplePropertiesFile("URL" , "code.properties").trim());
	}

	@Test(priority = 1)
	private void BranchLocator1001() {
	obj	= new FlightBookingPage();
	obj.testThatResultsAppearForAOneWayJourney();	
	}
}
