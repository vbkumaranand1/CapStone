package com.virtusa;

import com.pages.PageAutomationPanda;
import com.pages.PageFlipkart;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

public class SeleniumTests extends BaseClass {
    WebDriver driver;
    @BeforeSuite
    @Parameters({"browser"})
    public void setUp(String browser) throws IOException {
        driver=super.initializeDriver(browser);
    }

    @Test
    @Parameters({"urlAutomationPanda"})
    public void testAutomationPanda(String urlAutomationPanda){
        System.out.println("TestNg Suite Param:- urlAutomationPanda :-"+urlAutomationPanda);
//        String url="https://automationpanda.com/2021/12/29/want-to-practice-test-automation-try-these-demo-sites/";

        String strUser="Test User";
        String strEmail="test@gmail.com";
        String strMessage="Test Message";
        String expectedSuccessMessage="Your message has been sent";

        driver.get(urlAutomationPanda);
        //Page Title displayed
        Assert.assertEquals(driver.getTitle(),"Want to practice test automation? Try these demo sites! | Automation Panda","When Launched URL Page Title displayed correctly");

        PageAutomationPanda pageAutomationPanda=new PageAutomationPanda(driver);
        pageAutomationPanda.linkContact.click();
        Assert.assertEquals(driver.getTitle(),"Contact | Automation Panda","When clicked Contact URL Page Title displayed correctly");

        String actualSuccessMessage= pageAutomationPanda.performContactOperation(strUser,strEmail,strMessage);
        Assert.assertEquals(actualSuccessMessage,expectedSuccessMessage,"Contact Message Successful");

    }

    @Test
    @Parameters({"urlFlipkart"})
    public void testFlipCartSearchIPhone(String urlFlipkart){
        String strSearchText="iphone 14";
//        String URL="https://www.flipkart.com/";
        driver.get(urlFlipkart);

        PageFlipkart pageFlipCart=new PageFlipkart(driver);
        Assert.assertTrue(pageFlipCart.imgLogoFlipcart.isDisplayed(),"Flipkart image Logo is displayed in left or not");
        String returnText=pageFlipCart.searchFlipCart(strSearchText);
        System.out.println(returnText);
        String resultCount=pageFlipCart.returnNumberOfSearchResult(returnText);
        System.out.println("Total Result Count displayed:- "+resultCount);
        pageFlipCart.lstSearchResultElelemntDisplayed.get(0).click();

        String strCurrentWindowHanle=driver.getWindowHandle();
        Set<String> strWindows = driver.getWindowHandles();
        for(String win :strWindows){
            driver.switchTo().window(win);
            System.out.println("Title:- "+driver.getTitle());
        }
        System.out.println(driver.getTitle());

    }

    @AfterSuite
    public void tearDown()  {
        driver.quit();
    }

}
