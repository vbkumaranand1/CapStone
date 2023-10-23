package com.virtusa;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Scenario1 extends BaseClass {


    String url="https://automationpanda.com/2021/12/29/want-to-practice-test-automation-try-these-demo-sites/";
    String browser="edge"; //edge,chrome
    public static WebDriver driver;
    public static String pageTitle="Want to practice test automation? Try these demo sites! | Automation Panda";

    @BeforeSuite
    public void intializeTest() throws IOException {
        driver= super.initializeDriver("edge");
    }

    @Test
    public void launchBrowserVerifyLogoDisplayedTestCase(){
        try{

            System.out.println("browser :- "+browser);
            System.out.println("url :- "+url);

            switch(browser){
                case "chrome":
                    ChromeOptions chromeOptions=new ChromeOptions();
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(chromeOptions);
                    break;
                case "edge":
                    EdgeOptions options = new EdgeOptions();
                    options.addArguments("--remote-allow-origins=*");
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver(options);
                    break;
                default:
                    Assert.fail("Provide correct Browser name(chrome/edge) to execute, currently provided browser name is :- "+browser);
            }



            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(190));
            driver.get(url);
            System.out.println(driver.getTitle());
            Assert.assertEquals(driver.getTitle(),"Want to practice test automation? Try these demo sites! | Automation Panda");

            driver.findElement(By.xpath("//a[contains(text(),'Contact')]")).click();
            System.out.println(driver.getTitle());
            Assert.assertEquals(driver.getTitle(),"Contact | Automation Panda");


            driver.findElement(By.xpath("//input[@id='g3-name']")).sendKeys("Test Name");
            driver.findElement(By.xpath("//input[@id='g3-email']")).sendKeys("test@gmail.com");
            driver.findElement(By.xpath("//textarea[@name='g3-message']")).sendKeys("Message sent");
            driver.findElement(By.xpath("//button/strong[contains(text(),'Contact Me')]")).click();

            String message=driver.findElement(By.xpath("//h4[@id='contact-form-success-header']")).getText();
System.out.println(message);
            Assert.assertEquals(message,"Your message has been sent");

//            driver.quit();

        }catch (Exception e){
            Assert.fail("launchBrowserVerifyLogoDisplayedTestCase :_ Exception :- "+e);
        }

    }

    @Test
    public void methodFlipCartScenario(){

        driver.get("https://www.flipkart.com/");

        driver.findElement(By.xpath("//input[@name='q']")).sendKeys("iphone 14");
        driver.findElement(By.xpath("//input[@name='q']")).sendKeys(Keys.ENTER);

        String stext=driver.findElement(By.xpath("//span//span[contains(text(),'iphone 14')]/parent::span")).getText();
System.out.println(stext);

        List<WebElement> lstObj=driver.findElements(By.xpath("//a[@rel='noopener noreferrer']//div[contains(text(),'APPLE iPhone 14')]"));
        System.out.println(lstObj.get(0).getText());
        lstObj.get(0).click();
        // Get all Open Tabs
        ArrayList<String> tabHandles = new ArrayList<String>(driver.getWindowHandles());

        String pageTitle = "APPLE iPhone 14";
        boolean myNewTabFound = false;

        for(String eachHandle : tabHandles)
        {
            driver.switchTo().window(eachHandle);
            // Check Your Page Title
            if(driver.getTitle().equalsIgnoreCase(pageTitle))
            {
                System.out.println(driver.getTitle());
                // Report ur new tab is found with appropriate title

                //Close the current tab
                driver.close(); // Note driver.quit() will close all tabs

                //Swithc focus to Old tab
//                driver.switchTo().window(currentPageHandle);
                myNewTabFound = true;
            }
        }

        System.out.println(driver.getTitle());


//        driver.quit();

    }

}
