package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("EPIC - 100: design Accounts pages for open cart app")
@Story("US-Login: 101: design login page features for open cart")
public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accPage() {
		Accpage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}

	@Severity(SeverityLevel.TRIVIAL)
	@Description("... Getting the My Account page title.... tester: Suriya")
	@Test
	public void accPageTitleTest() {
		String accTitle = Accpage.getAccPageTitle();
		Assert.assertEquals(accTitle, AppConstants.ACCOUNTS_PAGE_TITLE_VALUE);
	}

	@Severity(SeverityLevel.TRIVIAL)
	@Description("... Getting the My Account page URL.... tester: Suriya")
	@Test
	public void accPageURLTest() {
		String accUrl = Accpage.getAccPageURL();
		Assert.assertTrue(accUrl.contains(AppConstants.ACCOUNTS_PAGE_URL_FRACTION_VALUE));
	}

	@Severity(SeverityLevel.TRIVIAL)
	@Description("... checking the logout Link is exist in My Account page title....")
	@Test
	public void isLogoutLinkExistTest() {
		Assert.assertTrue(Accpage.isLogoutLinkExist());
	}

	@Severity(SeverityLevel.TRIVIAL)
	@Description("... checking the My Account page Headers Count.... tester: Suriya")
	@Test
	public void accPageHeadersTest() {
		List<String> actualaccPageHeadersList = Accpage.getAccountsPageHeadersList();
		System.out.println("Accounts page Headers List : " + actualaccPageHeadersList);
		Assert.assertEquals(actualaccPageHeadersList.size(), AppConstants.ACCOUNTS_PAGE_HEADERS_COUNT);
	}

	@Severity(SeverityLevel.TRIVIAL)
	@Description("... checking the My Account page Headers Value.... tester: Suriya")
	@Test
	public void accPageHeadersValueTest() {
		List<String> actualaccPageHeadersList = Accpage.getAccountsPageHeadersList();
		System.out.println("Actual Acc page Headers List : " + actualaccPageHeadersList);
		System.out.println("Expected Acc page Headers List : " + AppConstants.EXPECTED_ACCOUNTS_PAGE_HEADERS_VALUE);
		Assert.assertEquals(actualaccPageHeadersList, AppConstants.EXPECTED_ACCOUNTS_PAGE_HEADERS_VALUE);
	}

	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] { { "Macbook" }, { "IMac" }, { "Apple" }, { "Samsung" }, { "Dummy" } };
	}

	@Severity(SeverityLevel.NORMAL)
	@Description("... Searching the product in search box")
	@Test(dataProvider = "getProductData")
	public void searchProductCountTest(String searchKey) {
		searchPage = Accpage.performSearch(searchKey);
		Assert.assertTrue(searchPage.getSearchProductCount() > 0);
	}

	@DataProvider
	public Object[][] getProductTestData() {
		return new Object[][] { { "Macbook", "MacBook Pro" }, { "Macbook", "MacBook Air" }, { "IMac", "iMac" },
				{ "Apple", "Apple Cinema 30\"" }, { "Samsung", "Samsung Galaxy Tab 10.1" } };
	}

	@Severity(SeverityLevel.NORMAL)
	@Description("... Searching the product in search box and Selecting the product")
	@Test(dataProvider = "getProductTestData")
	public void searchProductTest(String searchKey, String productName) {
		searchPage = Accpage.performSearch(searchKey);
		Assert.assertTrue(searchPage.getSearchProductCount() > 0);
		{
			productInfoPage = searchPage.selectProduct(productName);
			String actProductHeader = productInfoPage.getProductHeaderValue();
			Assert.assertEquals(actProductHeader, productName);
		}
	}

}
