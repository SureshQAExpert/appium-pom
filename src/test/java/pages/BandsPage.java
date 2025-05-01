package pages;

import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;

public class BandsPage extends BaseClass {

	By ringsMenuItem = By.xpath("(//span[text()='Rings'])[2]");
	By closePopup = By.xpath("//div[@class=\"close-icon-btn\"])[1]");
	By firstProduct = By.xpath("//div[@class=\"products-card-list\"]/div[normalize-space(@class)='inView']");
	By previoesButton = By.xpath("(//button[text()='Previous'])[1]");
	By ringsizeOption = By.cssSelector(".ring-size-options");
	By addToBag = By.xpath("(//span[text()='Add To Bag'])[1]");
	By addToBagHomePage = By.id("cartHeading");

	By ringSize = By.id("ringsize");
	By quantity = By.id("qty");
	By detailsInAddToBagSection = By.cssSelector(".jtLoer.icon  ");
	By gemstoneQuality = By.xpath("//span[text()='H, SI2']");
	By totalCaratWeight = By.xpath("//span[text()='3/4 carat']");
	By metalType = By.xpath("//span[text()='14K White Gold']");

	By Edit = By.xpath("//a[text()='Edit'])[1]");
	By updateBag = By.xpath("//span[text()='Update Bag']");

	// Filters
	By filter = By.xpath("//span[text()='Filter (1)']");
	By metalTypeInfilterSection = By.xpath("(//div[text()='Metal Type'])[4]");
	By metalTypeSilver = By.xpath("//div[text()='Silver']");
	By apply = By.xpath("(//div[text()='Apply'])[2]");

	// Default values
	By bandWidthText = By.xpath("//div[text()='Band Widths']");
	By metalTypeText = By.xpath("//div[text()='Metal Type']");
	By ringSelectSizeText = By.xpath("//h3[text()='Select Size']");

	By defaultBandWidth = By.xpath("(//div[@class='option selected']/following-sibling::div)[1]");
	By defaultMetalType = By.xpath("(//div[@class='option selected']/following-sibling::div)[2]");
	By defaultRingSelectSize = By.cssSelector(".ring-size-options.cp.selected");

	// with end points
	SoftAssert softAssert = new SoftAssert();

	public void scrollToTheElemnet() throws InterruptedException {
		Thread.sleep(10000);
		scrollByPixels(0, 1000);
	}

	public void addToTheBag() throws InterruptedException {
		Thread.sleep(10000);
		scrollByPixels(0, 1000);
		for (int i = 0; i < 15; i++) {
			click(previoesButton);
			String classVaue = getAttribute(previoesButton, "class");
			if (classVaue.contains("slick-disabled")) {
				break;
			}
		}
		click(ringsizeOption);
		click(addToBag);
		isElementDisplayed(addToBagHomePage);
	}

	public void verifyTheBandWidth(SoftAssert softAssert, String expectedBandWidth) throws InterruptedException {
		Thread.sleep(10000);
		// scrollToElement(bandWidthText);
		String actualBandWidth = getText(defaultBandWidth).trim();
		System.out.println("Selected Ring Size: " + actualBandWidth);
		softAssert.assertEquals(actualBandWidth, expectedBandWidth);
	}

	public void verifyTheMetalType(SoftAssert softAssert, String expectedMetalType) throws InterruptedException {
		Thread.sleep(10000);
		scrollToElement(metalTypeText);
		String actualMetalType = getText(defaultMetalType).trim();
		System.out.println("Selected Ring Size: " + actualMetalType);
		softAssert.assertEquals(actualMetalType, expectedMetalType);
	}

	public void verifyTheRingSizeText(SoftAssert softAssert, String expectedRingSize) throws InterruptedException {
		Thread.sleep(10000);
		scrollToElement(ringSelectSizeText);
		String actualRingSize = getText(defaultRingSelectSize).trim();
		System.out.println("Selected Ring Size: " + actualRingSize);
		softAssert.assertEquals(actualRingSize, expectedRingSize);
	}

}
