package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class SearchPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By searchProductRResults = By.cssSelector("div#content div.product-layout");

	public SearchPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	@Step(".... getting the search product count")
	public int getSearchProductCount() {
		int productCount = eleUtil.waitForElementsVisible(searchProductRResults, AppConstants.DEFAULT_MEDIUM_TIME_OUT)
				.size();
		System.out.println("Product Count : " + productCount);
		return productCount;
	}

	@Step(".... seleting the product : {0} : in Search result page...")
	public ProductInfoPage selectProduct(String productName) {
		By productLocator = By.linkText(productName);
		eleUtil.waitForElementVisible(productLocator, AppConstants.DEFAULT_MEDIUM_TIME_OUT).click();
		return new ProductInfoPage(driver);
	}

}
