package com.games.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class Driver {
    //create a private constructor to remove access to this object

    private Driver(){
        /*
        we made the WebDriver private,because we want to close access from outside the class
        We are making it static,because we will use it in a static method
         */

    }

    private static WebDriver driver;
    public static WebDriver getDriver(){
        if(driver == null){
            String browserType = ConfigurationReader.getProperty("browser");
            switch(browserType){
                case "chrome":

                    driver = new ChromeDriver();
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                    break;
                case "firefox":

                    driver = new FirefoxDriver();
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                    break;
            }
        }
        return driver;
    }

    public static void closeDriver(){
        if(driver != null){
            driver.quit();
            driver = null;
        }
    }
}
