package org.example.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class P04_PlaceOrder_WithExistingEmail {
    private WebDriver driver;
    private Wait<WebDriver> wait;

    public P04_PlaceOrder_WithExistingEmail(WebDriver driver) {
        this.driver = driver;
        this.wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(50))
                .pollingEvery(Duration.ofMillis(2000))
                .ignoring(org.openqa.selenium.NoSuchElementException.class);
    }



}
