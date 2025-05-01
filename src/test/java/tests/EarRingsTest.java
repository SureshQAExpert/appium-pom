package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.BaseClass;
import pages.EarRingsPage;
import org.testng.annotations.DataProvider;
import utilities.AppiumSetupManager;
import utilities.ExcelUtil;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class EarRingsTest extends AppiumSetupManager {

	BaseClass baseClass;
	EarRingsPage earRingsPage;
	ExcelUtil excelUtil;

	@DataProvider(name = "productData")
	public Object[][] orderData() {
		String sheetName = "Sheet1";
		String filePath = new File(getClass().getClassLoader().getResource("test-data/Earrings.xlsx").getFile())
				.getAbsolutePath();
		return ExcelUtil.getDataFromFile(filePath, sheetName);
	}

	@Test(dataProvider = "productData")
	public void earRings(Map<String, String> testData) throws InterruptedException, IOException {

		String baseURL = AppiumSetupManager.applicationURL;
		String productEndpoint = testData.get("Product Link");
		String productTitle = testData.get("Product Title");
		String productPrice = testData.get("Default Product Price");
		String defaultGemstoneQuality = testData.get("Default selected Gemstone Quality");
		String defaultCaratWeight = testData.get("Default selected Total Carat Weight");
		String defaultMetalType = testData.get("Default selected Metal Type");
		String changedMetalType = testData.get("Change metal type to");
		SoftAssert softAssert = new SoftAssert();

		String fullURL = baseURL + productEndpoint;
		System.out.println(fullURL);

		// AppiumSetupManager.launchThePageByURL(baseURL, productLink);
		AppiumSetupManager.launchThePageByURL(productEndpoint);

		baseClass = new BaseClass();
		earRingsPage = new EarRingsPage();
		earRingsPage.closeTheDiscountPopUP();

		// verifying default values
		earRingsPage.verifyTheProductPageUrl(softAssert, productEndpoint);
		earRingsPage.verifyTheProductTitle(softAssert, productTitle);
		earRingsPage.verifyTheActualPrice(softAssert, productPrice);
		earRingsPage.verifyTheGemstoneQuality(softAssert, defaultGemstoneQuality);
		earRingsPage.verifyTheCaratWeight(softAssert, defaultCaratWeight);
		earRingsPage.verifyTheMetalType(softAssert, defaultMetalType);

		// addToTheBag
		earRingsPage.addToTheBag();
		earRingsPage.verifyTheBagSectionIsDisplayed();
		earRingsPage.clickOnDetails();
		earRingsPage.verifyTheGemstoneQualityInDetails(softAssert, defaultGemstoneQuality);
		earRingsPage.verifyTheTotalCaratWeightInInDetails(softAssert, defaultCaratWeight);
		earRingsPage.verifyTheMetalTypeInInDetails(softAssert, defaultMetalType);

		// Changed values
		earRingsPage.clickOnTheEdit();
		earRingsPage.closeTheDiscountPopUP();
		earRingsPage.selectMetalType(softAssert, changedMetalType);
		earRingsPage.updateTheBag();
		earRingsPage.clickOnDetails();
		earRingsPage.verifyTheMetalTypeInInDetails(softAssert, changedMetalType);

		softAssert.assertAll();

	}

}
