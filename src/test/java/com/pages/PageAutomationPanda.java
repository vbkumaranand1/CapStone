package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class PageAutomationPanda {
    WebDriver driver;
    public PageAutomationPanda(WebDriver driver){
        this.driver=driver;
//        PageFactory.initElements(driver,this);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 60),this);

    }


//    @WithTimeout(timeOut = time, timeUnit = unit)
    @FindBy(xpath = "//a[contains(text(),'Contact')]")   public WebElement linkContact;
    @FindBy(xpath = "//input[@id='g3-name']")    WebElement txtBoxName;
    @FindBy(xpath = "//input[@id='g3-email']")    WebElement txtBoxEmail;
    @FindBy(xpath = "//textarea[@name='g3-message']")    WebElement txtAreaMessage;
    @FindBy(xpath = "//button/strong[contains(text(),'Contact Me')]")    WebElement btnContactMe;
    @FindBy(xpath = "//h4[@id='contact-form-success-header']")    WebElement hdrSuccessElement;

    public String performContactOperation(String strName,String strEmail,String strMessage){
//        linkContact.click();
        txtBoxName.sendKeys(strName);
        txtBoxEmail.sendKeys(strEmail);
        txtAreaMessage.sendKeys(strMessage);
        btnContactMe.click();
        String retSuccessMessage=hdrSuccessElement.getText();
        return retSuccessMessage;
    }
}
