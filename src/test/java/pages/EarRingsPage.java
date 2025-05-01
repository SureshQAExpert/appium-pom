package pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

public class EarRingsPage extends BaseClass {

	// Default values of product page
	By closePopup = By.xpath("//div[@class='hide-large hide-medium']/descendant::div[@class='close-icon-btn']");
	By productTitle = By.xpath("//h1[@class='product-title']");
	By mainOrActualPrice = By
			.xpath("//div[@class='price-section']/child::div/child::span[contains(@class,'main-price')]");
	By GemstoneQualitySectionText = By.xpath("//div[text()='Gemstone Quality']");
	By TotalCaratWeightSectionText = By.xpath("//div[text()='Total Carat Weight']");
	By MetalTypeSectionText = By.xpath("//div[text()='Metal Type']");
	By gemstoneQualityValue = By.xpath(
			"//div[@id='Gemstone-Quality']/descendant::div[@class='option selected']/following-sibling::div/child::div[contains(@class,'option-label')]");
	By totalCaratWeightValue = By.xpath(
			"//div[@id='Total-Carat-Weight']/descendant::div[@class='option selected']/following-sibling::div/child::div[contains(@class,'option-label')]");
	By metalTypeValue = By.xpath(
			"//div[@id='Metal-Type']/descendant::div[@class='option selected']/following-sibling::div/child::div[contains(@class,'option-label')]");

	// Add To Bag
	By addToBag = By.xpath("//span[text()='Add To Bag']");
	By addToBagHomePage = By.id("cartHeading");

	// Details section in Add To Bag page
	By detailsInAddToBagSection = By.xpath("(//span[contains(@class,'jtLoer icon')])[1]");

	By gemstoneQualityTextInDetails = By.xpath("(//span[text()='Gemstone Quality'])[1]"); // getText if
	By gemstoneQualityValueInDetails = By.xpath("(//span[text()='Gemstone Quality']//following-sibling::span)[1]"); // getText

	By totalCaratWeightTextInBagSection = By.xpath("(//span[text()='Total Carat Weight'])[1]"); // getText if
	By totalCaratWeightValueInBagSection = By
			.xpath("(//span[text()='Total Carat Weight']//following-sibling::span)[1]"); // getText

	By metalTypeTextInBagSection = By.xpath("(//span[text()='Metal Type'])[1]"); // getText if
	By metalTypeValueInBagSection = By.xpath("(//span[text()='Metal Type']//following-sibling::span)[1]"); // getText

	// Edit
	By EditTextInAddToCart = By.xpath("//a[text()='Edit']");

	// UpdateBag the in product page
	By updateBag = By.xpath("//span[text()='Update Bag']");
	By selectMetalType = By.xpath("//div[@class='icon metalType']/child::img");

	public void closeTheDiscountPopUP() throws InterruptedException {
		Thread.sleep(10000);
		click(closePopup);
	}

	public void verifyTheProductPageUrl(SoftAssert softAssert, String expectedProductPageURL) {
		if (expectedProductPageURL != null && !expectedProductPageURL.isEmpty()) {
			System.out.println("Verifying values for productLink ");
			String currentUrl = driver.getCurrentUrl();
			System.out.println("Current URL: " + currentUrl);
			softAssert.assertTrue(currentUrl.contains(expectedProductPageURL),
					"Product page URL does not contain expected text: '" + expectedProductPageURL + "'");
		} else {
			System.out.println("Product link data is empty. Skipping verification.");
		}
	}

	public void verifyTheProductTitle(SoftAssert softAssert, String expectedProductTitle) throws InterruptedException {
		if (expectedProductTitle != null && !expectedProductTitle.isEmpty()) {
			System.out.println("Verifying values for productTitle ");

			String actualProductTitle = getText(productTitle).trim();
			System.out.println("Actual product title is: " + actualProductTitle + " and " + "Expected title is: "
					+ expectedProductTitle);
			softAssert.assertEquals(actualProductTitle, expectedProductTitle);

		} else {
			System.out.println("Product title data is empty. Skipping verification.");
		}
	}

	public void verifyTheActualPrice(SoftAssert softAssert, String expectedActualPrice) throws InterruptedException {

		if (expectedActualPrice != null && !expectedActualPrice.isEmpty()) {
			System.out.println("Verifying values for product price ");

			String mainOrActPrice = getText(mainOrActualPrice).trim();
			System.out.println("Main or actual price is: " + mainOrActPrice + " and " + "Expected actual price is : "
					+ expectedActualPrice);
			String mainOrActPrice1 = mainOrActPrice.replace("$", "");
			String expectedMainPrice1 = expectedActualPrice.replace(".0", "");
			softAssert.assertTrue(expectedMainPrice1.contains(mainOrActPrice1));

		} else {
			System.out.println("Product price data is empty. Skipping verification.");
		}
	}

	public void verifyTheGemstoneQuality(SoftAssert softAssert, String expectedGemstoneQuality)
			throws InterruptedException {
		if (expectedGemstoneQuality != null && !expectedGemstoneQuality.isEmpty()) {
			String actualGemstoneQuality = getText(gemstoneQualityValue).trim();
			System.out.println("Actual gemstone quality is :" + actualGemstoneQuality + " and "
					+ "Expected gemstone quality is :" + expectedGemstoneQuality);
			softAssert.assertEquals(actualGemstoneQuality, expectedGemstoneQuality);
		} else {
			System.out.println("Gemstone quality data is empty. Skipping verification.");
		}
	}

	public void verifyTheCaratWeight(SoftAssert softAssert, String expectedCaratWeight) throws InterruptedException {
		if (expectedCaratWeight != null && !expectedCaratWeight.isEmpty()) {
			System.out.println("Verifying values for caratWeight ");
			String actualCaratWeight = getText(totalCaratWeightValue).trim();
			System.out.println("Actual total carat weight is : " + actualCaratWeight + " and "
					+ "Expected total carat weight is : " + expectedCaratWeight);
			softAssert.assertTrue(actualCaratWeight.contains(expectedCaratWeight));

		} else {
			System.out.println("Carat weight data is empty. Skipping verification.");
		}

	}

	public void verifyTheMetalType(SoftAssert softAssert, String expectedMetalType) throws InterruptedException {
		if (expectedMetalType != null && !expectedMetalType.isEmpty()) {
			System.out.println("Verifying values for metalType");
			String actualMetalType = getText(metalTypeValue).trim();
			System.out.println("Actual metal type is : " + actualMetalType + " and" + " Expected metal type is : "
					+ expectedMetalType);
			softAssert.assertEquals(actualMetalType, expectedMetalType);
		} else {
			System.out.println("Metal type data is empty. Skipping verification.");
		}
	}

	public void addToTheBag() throws InterruptedException {
		Thread.sleep(15000);
		// scrollToElement(addToBag);
		// scrollByPixels(0,800);
		click(addToBag);
	}

	public void verifyTheBagSectionIsDisplayed() throws InterruptedException {
		Thread.sleep(10000);
		isElementDisplayed(addToBagHomePage);
	}

	public void clickOnDetails() throws InterruptedException {
		Thread.sleep(10000);
		click(detailsInAddToBagSection);
		Thread.sleep(10000);
	}

	public void clickOnTheEdit() throws InterruptedException {
		click(EditTextInAddToCart);
	}

	public void updateTheBag() throws InterruptedException {
		Thread.sleep(10000);
		click(updateBag);
	}

	public void verifyTheGemstoneQualityInDetails(SoftAssert softAssert, String expectedGemstoneQuality)
			throws InterruptedException {

		if (expectedGemstoneQuality != null && !expectedGemstoneQuality.isEmpty()) {
			System.out.println("Verifying values for gemstone quality in details sectionn");
			String gemstoneQualityValue = getText(gemstoneQualityValueInDetails).trim();
			System.out.println("Actual gemstone quality is :" + gemstoneQualityValue + " and "
					+ "Expected gemstone quality is :" + expectedGemstoneQuality);
			softAssert.assertEquals(gemstoneQualityValue, expectedGemstoneQuality);
		}

		else {
			System.out.println("Gem stone data is empty in details section. Skipping verification.");
		}

	}

	public void verifyTheTotalCaratWeightInInDetails(SoftAssert softAssert, String expectedGemstoneQuality)
			throws InterruptedException {

		if (expectedGemstoneQuality != null && !expectedGemstoneQuality.isEmpty()) {
			System.out.println("Verifying values for carat weight in bag section");
			String gemstoneQualityValue = getText(totalCaratWeightValueInBagSection).trim();
			System.out.println("Actual carat weight is :" + gemstoneQualityValue + " and " + "carat weight is :"
					+ expectedGemstoneQuality);
			gemstoneQualityValue = gemstoneQualityValue.replace("carats", "ct");
			softAssert.assertEquals(gemstoneQualityValue, expectedGemstoneQuality);
		}

		else {
			System.out.println("Carat weight empty in details section. Skipping verification.");
		}

	}

	public void verifyTheMetalTypeInInDetails(SoftAssert softAssert, String expectedGemstoneQuality)
			throws InterruptedException {

		if (expectedGemstoneQuality != null && !expectedGemstoneQuality.isEmpty()) {
			System.out.println("Verifying values for metal type in bag section");
			String gemstoneQualityValue = getText(metalTypeValueInBagSection).trim();
			System.out.println("Actual metal type is :" + gemstoneQualityValue + " and " + "Expected metal type is :"
					+ expectedGemstoneQuality);

			softAssert.assertTrue(gemstoneQualityValue.contains(expectedGemstoneQuality));

			// softAssert.assertEquals(gemstoneQualityValue, expectedGemstoneQuality);
		}

		else {
			System.out.println("metal type data is empty in details section. Skipping verification.");
		}

	}

	public void selectMetalType(SoftAssert softAssert, String expectedChangedMetalType) throws InterruptedException {
		Thread.sleep(10000); // Consider replacing this with a WebDriverWait for better reliability
		List<WebElement> imageElements = findElements(selectMetalType);
		System.out.println("Total elements found: " + imageElements.size());

		boolean isFound = false;

		if (!imageElements.isEmpty()) {
			for (WebElement imageElement : imageElements) {
				@SuppressWarnings("deprecation")
				String imageUrl = imageElement.getAttribute("src");

				// Extract the filename without query parameters
				String fileNameWithExtension = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
				String fileNameWithoutParams = fileNameWithExtension.split("\\?")[0];

				// Remove extension and replace hyphens with spaces
				String fileNameWithoutExtension = fileNameWithoutParams.replace(".png", "").replace("-", " ");
				System.out.println("Found: " + fileNameWithoutExtension);

				// Check if the image filename matches the expected one
				if (fileNameWithoutExtension.equalsIgnoreCase(expectedChangedMetalType)) {
					System.out.println("Match found: " + fileNameWithoutExtension);
					isFound = true;
					imageElement.click();
					break;
				}
			}
		}

		if (!isFound) {
			System.out.println("No match found for: " + expectedChangedMetalType);
		}
	}

}
