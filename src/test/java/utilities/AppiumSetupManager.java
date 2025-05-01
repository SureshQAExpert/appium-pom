package utilities;

import java.io.BufferedReader;
import java.io.File;
import java.net.URL;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import java.io.IOException;
import java.io.InputStreamReader;

public class AppiumSetupManager {

	public static AndroidDriver driver;
	public static AppiumDriverLocalService service;
	public static String emulatorName;
	public static String serverURL = TestDataManager.getProperty("serverURL");
	public static String uiAutomator2 = TestDataManager.getProperty("automationName");
	public static String android = TestDataManager.getProperty("platformName");
	public static String deviceName = TestDataManager.getProperty("deviceName");
	public static String trueValue = TestDataManager.getProperty("trueValue");
	public static String chromedriverExecutablePath = TestDataManager.getProperty("chromedriverExecutablePath");
	public static String chromeBrowser = TestDataManager.getProperty("browserName");
	public static String applicationURL = TestDataManager.getProperty("applicationURL");

	// Server initialization and server related things
	@BeforeSuite
	public static void startServer() {
		AppiumServiceBuilder builder = new AppiumServiceBuilder()
				.withAppiumJS(new File("C:/Users/Admin/AppData/Roaming/npm/node_modules/appium/build/lib/main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).withLogFile(new File("appium_logs.txt"))
				.withArgument(() -> "--allow-insecure", "chromedriver_autodownload");
		service = AppiumDriverLocalService.buildService(builder);
		service.start();
		System.out.println("Appium Server started!");

	}

	@AfterSuite
	public static void stopServer() {
		quitDriver();
		if (service != null) {
			service.stop();
			System.out.println("Appium Server stopped!");
		}
	}

	// Driver initialization and driver related
	@BeforeMethod
	@SuppressWarnings("deprecation")
	public static <WebDriver> void launchThePageByURLs() throws IOException, InterruptedException {
		DesiredCapabilities options = new DesiredCapabilities();

		emulatorName = System.getProperty("emulatorName"); // Get emulator name from Maven command line

		if (emulatorName == null || emulatorName.isEmpty()) {
			System.out.println("No emulatorName provided. Assuming real device connected via USB.");
		} else {
			startEmulator(emulatorName); // Start emulator if emulatorName is provided
		}

		String browserName = System.getProperty("browser"); // Fetch browserName from command line
		if (browserName == null) {
			browserName = "chrome"; // default fallback
		}

		options.setCapability("appium:automationName", uiAutomator2);
		options.setCapability("platformName", android);
		options.setCapability("appium:newCommandTimeout", 3600);
		options.setCapability("appium:connectHardwareKeyboard", trueValue);
		options.setCapability("appium:browserName", browserName);

		// Device setup
		if (emulatorName == null || emulatorName.isEmpty()) {
			String udid = getConnectedDeviceUDID(); // Real device setup
			options.setCapability("appium:udid", udid);
			options.setCapability("appium:deviceName", "Android Device");
		} else {
			options.setCapability("appium:deviceName", emulatorName); // Emulator setup
		}

		ChromeOptions notification = new ChromeOptions();
		notification.addArguments("--incognito");
		notification.addArguments("--disable-notifications");
		options.setCapability(ChromeOptions.CAPABILITY, notification);
		driver = new AndroidDriver(new URL(serverURL), options);
	}

	// Mock method — implement this to fetch UDID of connected real device
	public static String getConnectedDeviceUDID() throws IOException {
		@SuppressWarnings("deprecation")
		Process process = Runtime.getRuntime().exec("adb devices");
		java.io.BufferedReader reader = new java.io.BufferedReader(
				new java.io.InputStreamReader(process.getInputStream()));
		String line;
		while ((line = reader.readLine()) != null) {
			if (line.endsWith("device") && !line.startsWith("List")) {
				return line.split("\\s+")[0];
			}
		}
		throw new RuntimeException("No real device connected.");
	}

	public static void startEmulator(String emulatorName) throws IOException, InterruptedException {
		System.out.println("Starting emulator: " + emulatorName);
		String emulatorPath = "C:\\Users\\Admin\\AppData\\Local\\Android\\Sdk\\emulator\\emulator.exe";
		ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "\"" + emulatorPath + "\" -avd " + emulatorName);
		pb.start();
		Thread.sleep(5000); // wait a bit for it to start
		waitForDeviceBoot();
		Thread.sleep(5000); // wait after a bit starting
	}

	public static void waitForDeviceBoot() throws IOException, InterruptedException {
		String bootStatus = "";
		while (!bootStatus.contains("1")) {
			@SuppressWarnings("deprecation")
			Process process = Runtime.getRuntime().exec("adb shell getprop sys.boot_completed");
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			bootStatus = reader.readLine();
			process.waitFor();
			System.out.println("Boot status: " + bootStatus);
			Thread.sleep(5000);
		}
		System.out.println("Emulator booted successfully.");
	}

	public static void launchThePageByURL(String endPoint) throws IOException, InterruptedException {
		String applicationURLFromCommandLine = System.getProperty("url");
		if (applicationURLFromCommandLine == null) {
			applicationURLFromCommandLine = applicationURL; // default fallback
		}
		if (endPoint == null) {
			endPoint = "/"; // default fallback
		}
		driver.get(applicationURL + endPoint);
		System.out.println("Mobile web app launched successfully: " + applicationURL + endPoint);
	}

	public static AndroidDriver getDriver() {
		if (driver == null) {
			System.out.println("Driver is null");
		}
		return driver;
	}

	public static void quitDriver() {
		if (driver != null) {
			driver.quit();
		}
	}

}
