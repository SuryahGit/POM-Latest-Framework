package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import com.qa.opencart.base.BaseTest;

public class productPageInfoTest extends BaseTest {

	@BeforeClass
	public void productPageInfoSetup() {
		Accpage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}

	@DataProvider
	public Object[][] getProductTestData() {
		return new Object[][] { { "Macbook", "MacBook Pro", 4 }, { "IMac", "iMac", 3 },
				{ "Apple", "Apple Cinema 30\"", 6 }, { "Samsung", "Samsung SyncMaster 941BW", 1 } };
	}

	@Test(dataProvider = "getProductTestData")
	public void productImagesCountTest(String searchKey, String productName, int excpImagesCount) {
		searchPage = Accpage.performSearch(searchKey);
		productInfoPage = searchPage.selectProduct(productName);
		int actImagesCount = productInfoPage.getProductImageCount();
		Assert.assertEquals(actImagesCount, excpImagesCount);
	}

	@Test
	public void productInfoTest() {
		searchPage = Accpage.performSearch("Macbook");
		productInfoPage = searchPage.selectProduct("MacBook Pro");
		Map<String, String> actProductInfoMap = productInfoPage.getProductInfo();
		softAssert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actProductInfoMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(actProductInfoMap.get("productname"), "MacBook Pro");
		softAssert.assertEquals(actProductInfoMap.get("Reward Points"), "800");
		softAssert.assertEquals(actProductInfoMap.get("productprice"), "$2,000.00");

		softAssert.assertAll();
	}

	@DataProvider
	public Object[][] getAddToCartProductTestData() {
		return new Object[][] { { "Macbook", "MacBook Pro" }, { "IMac", "iMac" }, { "Macbook", "MacBook Air" },
				{ "Samsung", "Samsung SyncMaster 941BW" } };
	}

	@Test(dataProvider = "getAddToCartProductTestData")
	public void addToCartTest(String searchKey, String productName) {
		searchPage = Accpage.performSearch(searchKey);
		productInfoPage = searchPage.selectProduct(productName);
		productInfoPage.enterQuantity(2);
		String actCartMesg = productInfoPage.addProductToCart();
		// Success: You have added MacBook Pro to your shopping cart!
		softAssert.assertTrue(actCartMesg.contains("Success"));
		softAssert.assertTrue(actCartMesg.contains(productName));

		softAssert.assertEquals(actCartMesg, "Success: You have added " + productName + " to your shopping cart!");
		softAssert.assertAll();

	}
}
