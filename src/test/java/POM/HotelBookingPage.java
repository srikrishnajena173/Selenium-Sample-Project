package POM;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Generic.BaseClass;

public class HotelBookingPage extends BaseClass {

	@FindBy(linkText = "Hotels")
	private WebElement hotelLink;

	@FindBy(id = "Tags")
	private WebElement localityTextBox;

	@FindBy(id = "ui-id-1")
	private WebElement whereListParentEle;

	@FindBy(id = "SearchHotelsButton")
	private WebElement searchButton;

	@FindBy(id = "travellersOnhome")
	private WebElement travellerSelection;

	public HotelBookingPage() {
		PageFactory.initElements(driver, this);
	}

	public void shouldBeAbleToSearchForHotels() {
		click(hotelLink);
		setText(localityTextBox, "Indiranagar, Bangalore");
		selectAnElementFromList(whereListParentEle, "li", "bloom Boutique | Indiranagar, Bangalore, Karnataka, India");
		selectDropDownByText(travellerSelection, "1 room, 2 adults");
		click(searchButton);
	}
}
