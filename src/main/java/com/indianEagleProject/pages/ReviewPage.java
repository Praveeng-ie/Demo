package com.indianEagleProject.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReviewPage extends PageBase {

	@FindBy(css = "div[onclick='signInFromCheckoutPage()']")
	private WebElement btnSignIn;

	@FindBy(id = "checkoutUserNameId")
	private WebElement txtBoxEmail;

	@FindBy(name = "password")
	private WebElement txtBoxPassword;

	@FindBy(css = "a[href*='/review/indianeagle.com'] div[class*='text-grey']")
	private WebElement txtReview;

	public ReviewPage(WebDriver driver) {
		super(driver);
		WebDriverWait reviewPageFullLoadWait = new WebDriverWait(driver, Duration.ofSeconds(110));
		reviewPageFullLoadWait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class*='chkSignin']")));
		System.out.println("Research results are fully loaded");
	}

	public TravelerInfoPage doSignIn() throws InterruptedException {
		txtBoxEmail.sendKeys("manojnani8106@gmail.com");
		txtBoxPassword.sendKeys("9618621848");
		btnSignIn.click();
		Thread.sleep(7000);
		return new TravelerInfoPage(driver);

	}

	public String getTrustPilotReview() {
		String liveReviewText = txtReview.getText();
		System.out.println(liveReviewText);
		return liveReviewText;
	}
}
