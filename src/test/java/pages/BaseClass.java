package pages;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.android.AndroidDriver;
import utilities.AppiumSetupManager;

public class BaseClass {

	public AndroidDriver driver;

	public BaseClass() {
		this.driver = AppiumSetupManager.getDriver();
	}

	int smallWait = 10000;
	int mediumWait = 20000;
	int logWait = 30000;

	public WebElement findElement(By locator) {
		return driver.findElement(locator);
	}

	public List<WebElement> findElements(By locator) {
		return driver.findElements(locator);
	}

	public void click(By locator) {
		driver.findElement(locator).click();
	}

	public void sendKeys(By locator, String text) {
		clear(locator);
		driver.findElement(locator).sendKeys(text);
	}

	public String getText(By locator) {
		isElementDisplayed(locator);
		return driver.findElement(locator).getText();
	}

	public void clear(By locator) {
		driver.findElement(locator).clear();
	}

	@SuppressWarnings("deprecation")
	public String getAttribute(By locator, String attribute) {
		return driver.findElement(locator).getAttribute(attribute);
	}

	public boolean isElementDisplayed(By locator) {
		try {
			waitForVisibility(locator, mediumWait);
			return driver.findElement(locator).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isElementEnabled(By locator) {
		try {
			return driver.findElement(locator).isEnabled();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isElementSelected(By locator) {
		try {
			return driver.findElement(locator).isSelected();
		} catch (Exception e) {
			return false;
		}
	}

	public void selectByVisibleText(By locator, String visibleText) {
		Select select = new Select(driver.findElement(locator));
		select.selectByVisibleText(visibleText);
	}

	public void selectByValue(By locator, String value) {
		Select select = new Select(driver.findElement(locator));
		select.selectByValue(value);
	}

	public void selectByIndex(By locator, int index) {
		Select select = new Select(driver.findElement(locator));
		select.selectByIndex(index);
	}

	// Explicit Wait (visibility)
	public WebElement waitForVisibility(By locator, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	// Implicit Wait
	public void setImplicitWait(int timeoutInSeconds) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeoutInSeconds));
	}

	public void waitForSeconds(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	public void isElementPresent(By locator) {

		List<WebElement> elements = driver.findElements(locator);
		if (elements.size() > 0) {
			System.out.println("Element exists in the DOM.");

		} else {
			System.out.println("Element not found.");
		}

	}

	public void scrollToElement(By locator) {
		WebElement element = driver.findElement(locator);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void scrollByAction(int startX, int startY, int endX, int endY) {
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence swipe = new Sequence(finger, 1);

		swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
		swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), endX, endY));
		swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		driver.perform(Arrays.asList(swipe));
	}

	public void scrollByPixels(int x, int y) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(arguments[0], arguments[1]);", x, y);
	}

}
