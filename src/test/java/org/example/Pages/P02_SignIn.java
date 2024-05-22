package org.example.Pages;
import org.example.StepDefs.Hooks;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static org.example.StepDefs.Hooks.driver;

public class P02_SignIn {

    private final WebDriver driver;
    private final Wait<WebDriver> wait;

    public P02_SignIn(WebDriver driver){
        this.driver = driver;
        this.wait=new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(org.openqa.selenium.NoSuchElementException.class);
    }

    public void navigateToSignInPage(){
        WebElement loginPage=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[ @href='https://staging.inhouse.sa/ar/customer/account/login/' and @class='account-width' and @title='تسجيل الدخول']")));
        loginPage.click();
    }

   public void signIn_isSuccessful(String email, String password){
        WebElement emailField=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='login[username]' and @id='email']")));
        emailField.sendKeys(email);

        WebElement passwordField=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='login[password]' and @id='pass']")));
        passwordField.sendKeys(password);
    }

    public void clickSignInButton(){

        WebElement loginButton=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class=\"action login primary\"]/span[contains(text(), \"تسجيل الدخول\")]")));
        wait.until(ExpectedConditions.titleIs("تسجيل دخول العميل"));
        // Check if the button is overlaying other elements
        boolean isOverlaying = (boolean) ((JavascriptExecutor) driver).executeScript(
                "var element = arguments[0];" +
                        "var rect = element.getBoundingClientRect();" +
                        "var elementsBelow = document.elementsFromPoint(rect.left + rect.width / 2, rect.top + rect.height / 2);" +
                        "return elementsBelow.some(function(el) { return el !== element && el.contains(element); });",
                loginButton);
        if (isOverlaying) {
            System.out.println("The login button is overlaying other elements.");
        } else {
            System.out.println("The login button is not overlaying other elements.");
        }

        boolean isDisplayed = loginButton.isDisplayed();
        System.out.println("Is the login button displayed? " + isDisplayed);

        boolean isClickable = loginButton.isEnabled();
        System.out.println("Is the login button clickable? " + isClickable);

        // Get the value of the 'color' property for the login button
        String colorValue = loginButton.getCssValue("color");
        System.out.println("Color of the login button: " + colorValue);

        // Get the value of the 'font-size' property for the login button
        String fontSizeValue = loginButton.getCssValue("font-size");
        System.out.println("Font size of the login button: " + fontSizeValue);
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).submit();
    }

    public void verifyRedirecingMyAccountPage(){
        String expectedURL = "https://staging.inhouse.sa/ar/customer/account/";
        String redirectURL = driver.getCurrentUrl();
        Assert.assertEquals(redirectURL, expectedURL);
    }
    public void enteringInvalidEmail(String email, String password){
        WebElement emailField=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='login[username]' and @id='email']")));
        emailField.sendKeys(email);

        WebElement passwordField=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='login[password]' and @id='pass']")));
        passwordField.sendKeys(password);
    }

    public void login_isFailed(){
        WebElement ErrorMessage=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-bind=\"html: $parent.prepareMessageForHtml(message.text)\"]")));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@data-bind=\"html: $parent.prepareMessageForHtml(message.text)\"]")));
        wait.until(ExpectedConditions.visibilityOf(ErrorMessage));

        boolean isDisplayed = ErrorMessage.isDisplayed();

        Assert.assertTrue(isDisplayed, "Error message is displayed.");

        String expecteErrorMessage = "كان تسجيل الدخول إلى الحساب غير صحيح أو تم تعطيل حسابك مؤقتًا. يرجى الانتظار والمحاولة مرة أخرى في وقت لاحق.";
        String actualErrorMessage = ErrorMessage.getText().trim();

        Assert.assertEquals(actualErrorMessage, expecteErrorMessage);
        System.out.println( expecteErrorMessage);
        System.out.println( actualErrorMessage);
    }

    public void enteringInvalidPass(String email,String password){
        WebElement emailField=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='login[username]' and @id='email']")));
        emailField.sendKeys(email);

        WebElement passwordField=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='login[password]' and @id='pass']")));
        passwordField.sendKeys(password);
    }



    public void enteringEmptyData(String email,String password){
        WebElement emailField=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='login[username]' and @id='email']")));
        emailField.sendKeys(email);

        WebElement passwordField=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='login[password]' and @id='pass']")));
        passwordField.sendKeys(password);

    }

    public void verify_validationMessage_isDisplayed(){
        WebElement emailValidationMessage=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='email-error']")));
        WebElement passValidationMessage=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='pass-error']")));

        boolean isErrorMessageEmailDisplayed = emailValidationMessage.isDisplayed();
        boolean isErrorMessagePassDisplayed = passValidationMessage.isDisplayed();

        System.out.println("Is the error message Email  displayed? " + isErrorMessageEmailDisplayed);
        System.out.println("Is the error message Password  displayed? " + isErrorMessagePassDisplayed);

        Assert.assertTrue(emailValidationMessage.isDisplayed(), "Error message Email is displayed.");
        Assert.assertTrue(passValidationMessage.isDisplayed(), "Error message Password is displayed.");

        String expecteErrorEmailMessage = "هذا الحقل مطلوب.";
        String expecteErrorPassMessage = "هذا الحقل مطلوب.";

        String actualErrorEmailMessage = emailValidationMessage.getText().trim();
        String actualErrorPassMessage = passValidationMessage.getText().trim();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(emailValidationMessage));
        Assert.assertEquals(actualErrorEmailMessage, expecteErrorEmailMessage);
        Assert.assertEquals(actualErrorPassMessage, expecteErrorPassMessage);

        System.out.println( expecteErrorEmailMessage);
        System.out.println( expecteErrorPassMessage);

        System.out.println( actualErrorEmailMessage);
        System.out.println( actualErrorPassMessage);
    }
}

