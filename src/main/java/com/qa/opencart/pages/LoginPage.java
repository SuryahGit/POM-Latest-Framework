package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	// 1. private By Locators

	private By emailID = By.id("input-email");
	private By passWord = By.id("input-password");
	private By lgnBtn = By.xpath("//input[@value='Login']");
	private By forgorPwdLink = By.linkText("Forgotten Password");
	private By logo = By.xpath("//img[@title='naveenopencart']");
	private By registerLink = By.linkText("Register");

	// 2. page constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// 3. page actions/methods
	@Step(".... getting the login page title...")
	public String getLoginPageTitle() {

		String title = eleUtil.waitForTitleIsAndFetch(AppConstants.DEFAULT_SHORT_TIME_OUT,
				AppConstants.LOGIN_PAGE_TITLE_VALUE);
		System.out.println("Login page title is : " + title);
		return title;
	}

	@Step(".... getting the login page url...")
	public String getLoginPageUrl() {
		String url = eleUtil.waitForURLContainsAndFetch(AppConstants.DEFAULT_SHORT_TIME_OUT,
				AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE);
		System.out.println("Login page url is : " + url);
		return url;
	}

	@Step(".... getting the forgot pwd exit...")
	public boolean isForgotPasswordLinkExist() {
		return eleUtil.waitForElementVisible(forgorPwdLink, AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
	}

	@Step(".... getting the login page logo path...")
	public String getLogoUrl() {
		return eleUtil.getElementAttribute(logo, "src");

	}

	@Step(".... getting the login page logo...")
	public boolean isLogoPresent() {
		return eleUtil.doElementIsDisplayed(logo);
	}

	@Step(".... login with username : {0}: and password : {1}")
	public AccountsPage doLogin(String userName, String pwd) {

		System.out.println("App creds are :" + userName + " : " + pwd);
		eleUtil.waitForElementVisible(emailID, AppConstants.DEFAULT_MEDIUM_TIME_OUT).sendKeys(userName);
		eleUtil.doSendKeys(passWord, pwd);
		eleUtil.doClick(lgnBtn);
		return new AccountsPage(driver);
	}

	@Step(".... navigating to register page...")
	public RegistrationPage navigateToRegisterPage() {
		eleUtil.doClick(registerLink);
		return new RegistrationPage(driver);
	}
}
