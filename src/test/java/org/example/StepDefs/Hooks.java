package org.example.StepDefs;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
public class Hooks {
    public static WebDriver driver=null;
    @Before
    public static void openBrowser(){


        ChromeOptions options=new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://staging.inhouse.sa/");
    }

    @After
    public static void closeBrowser() throws InterruptedException {

        Thread.sleep(10000);
        driver.quit();
    }

}