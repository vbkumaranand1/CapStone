package com.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.List;

public class PageFlipkart {
WebDriver driver;
    public PageFlipkart(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 60),this);
    }
    @FindBy(xpath="//input[@name='q']") WebElement txtBoxSearch;
    @FindBy(xpath ="//span//span[contains(text(),'iphone 14')]/parent::span") WebElement searchResultCountDisplayedFrame;
    @FindBy(xpath = "//a[@rel='noopener noreferrer']//div[contains(text(),'APPLE iPhone 14')]") public List<WebElement> lstSearchResultElelemntDisplayed;
    @FindBy(xpath = "//a/img[@title='Flipkart']") public WebElement imgLogoFlipcart;

    public String searchFlipCart(String strSearchText){
        txtBoxSearch.sendKeys(strSearchText);
        txtBoxSearch.sendKeys(Keys.ENTER);
        String strSearchResult=searchResultCountDisplayedFrame.getText();
        return strSearchResult;
    }

    public String returnNumberOfSearchResult(String strSearchResult){
        String[] aSplitResult = strSearchResult.split(" of ");
        String[] aSplitTotalResultCount=aSplitResult[1].split(" ");
        return aSplitTotalResultCount[0];

    }
}
