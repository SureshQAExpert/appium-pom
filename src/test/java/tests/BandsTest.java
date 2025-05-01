package tests;

import org.testng.asserts.SoftAssert;
import pages.BandsPage;
import pages.BaseClass;
import utilities.AppiumSetupManager;

import java.io.IOException;
import java.util.Map;

public class BandsTest extends AppiumSetupManager {

	BaseClass baseClass;
	BandsPage bandsPage;

	// @Test(dataProvider = "excelData", dataProviderClass = ExcelUtil.class)
	public void bands(Map<String, String> testData) throws InterruptedException, IOException {
		String baseURL = AppiumSetupManager.applicationURL;
		String endPoint = testData.get("Product Link");
		String bandWidth = testData.get("Default selected Band Widths");
		String metalType = testData.get("Default selected Metal Type");
		String ringSizeText = testData.get("Default selected ring size");
		SoftAssert softAssert = new SoftAssert();
		System.out.println(endPoint + "  " + bandWidth + "  " + metalType + "  " + ringSizeText);
		String fullURL = baseURL + endPoint;
		System.out.println(fullURL);
		Thread.sleep(10000);

		AppiumSetupManager.launchThePageByURL(endPoint);

		baseClass = new BaseClass();
		bandsPage = new BandsPage();

		if (bandWidth != null && !bandWidth.isEmpty()) {
			System.out.println("Verifying values... bandWidth ");
			bandsPage.verifyTheBandWidth(softAssert, bandWidth);
		} else {
			System.out.println("bandWidth Data is empty. Skipping verification.");
		}

		if (metalType != null && !metalType.isEmpty()) {
			// Verification logic
			System.out.println("Verifying values...metalType");
			bandsPage.verifyTheMetalType(softAssert, metalType);
		} else {
			System.out.println("metalType Data is empty. Skipping verification.");
		}

		if (ringSizeText != null && !ringSizeText.isEmpty()) {
			// Verification logic
			System.out.println("Verifying values...ringSizeText ");
			bandsPage.verifyTheRingSizeText(softAssert, ringSizeText);
		} else {
			System.out.println("ringSizeText Data is empty. Skipping verification.");
		}

		bandsPage.verifyTheBandWidth(softAssert, bandWidth);
		bandsPage.verifyTheMetalType(softAssert, metalType);
		bandsPage.verifyTheRingSizeText(softAssert, ringSizeText);

		softAssert.assertAll();
	}

}
