package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("EPIC - 100: design login for open cart app")
@Story("US-Login: 101: design login page features for open cart")
public class LoginPageTest extends BaseTest {

	@Severity(SeverityLevel.TRIVIAL)
	@Description("... Getting the title of the page.... tester: Suriya")
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actualTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE_VALUE);
	}

	@Severity(SeverityLevel.NORMAL)
	@Description("... Gettingh the URL of the page.... tester: Suriya")
	@Test(priority = 2)
	public void loginPageURLTest() {
		String actualTitle = loginPage.getLoginPageUrl();
		Assert.assertTrue(actualTitle.contains(AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE));
	}

	@Severity(SeverityLevel.CRITICAL)
	@Description("... checking the forgot pwd link exist.... tester: Suriya")
	@Test(priority = 3)
	public void isforgotPwdLinkExistTest() {
		Assert.assertTrue(loginPage.isForgotPasswordLinkExist());
	}

	@Severity(SeverityLevel.MINOR)
	@Description("... checking the logo present.... tester: Suriya")
	@Test(priority = 4)
	public void isLogoPresentTest() {
		Assert.assertTrue(loginPage.isLogoPresent());
	}

	@Severity(SeverityLevel.MINOR)
	@Description("... checking the logo URL path.... tester: Suriya")
	@Test(priority = 5)
	public void LogoUrlTest() {
		String actualLogoUrl = loginPage.getLogoUrl();
		Assert.assertEquals(actualLogoUrl, AppConstants.LOGO_URL_PATH);
	}

	@Severity(SeverityLevel.CRITICAL)
	@Description("... checking user able to login to app with correct username and password....")
	@Test(priority = 6)
	public void loginTest() {
		loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}

}
