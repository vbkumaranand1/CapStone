package com.virtusa;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import static java.time.Duration.ofSeconds;

public class BaseClass {

    public WebDriver driver;
//    public LandingPage objLandingPage;
    public WebDriver initializeDriver(String browser) throws IOException {

        if(browser.contains("chrome")){
            ChromeOptions chromeOptions=new ChromeOptions();
            if(browser.contains("headless")){
                chromeOptions.addArguments("headless");
            }
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(chromeOptions);
        }else if(browser.equalsIgnoreCase("edge")) {

            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }else if(browser.equalsIgnoreCase("OtherBrowser")) {
            //Similarly update for Other Browser
        }else{
            System.out.println("provide correct browser type for Driver");
        }

//        driver.manage().timeouts().implicitlyWait(ofSeconds(60));
        driver.manage().window().maximize();
        return driver;
    }


//    @BeforeMethod(alwaysRun = true)
//    public WebDriver launchApplication() throws IOException {
//        driver=initializeDriver("edge");
//        return driver;
//    }

//    @BeforeMethod(alwaysRun = true)
//    public LandingPage launchApplication() throws IOException {
//        driver=initializeDriver();
////        LandingPage objLandingPage=new LandingPage(driver);
//        objLandingPage=new LandingPage(driver); // as marking this method with @BeforeMethod and this class object
//        // needs to be accessed by other class, making as class vriable
//        objLandingPage.launchURL();
//        return objLandingPage;
//    }

//    @AfterMethod(alwaysRun = true)
//    public void tearDown(){
//        driver.quit();
//    }




}
