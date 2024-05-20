package org.example.StepDefs;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.Pages.P02_SignIn;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static org.example.StepDefs.Hooks.driver;
import static org.testng.Assert.assertEquals;
public class D02_SignInStpDef {
    P02_SignIn p2_signIn = new P02_SignIn();
    @Given("User is on the login page")
    public void userIsOnTheLoginPage() {
        p2_signIn.loginPage.click();
    }

    @When("User enters valid {string} and {string}")
    public void userEntersValidEmailAndPassword(String email, String password) {
        p2_signIn.emailField.sendKeys(email);
        p2_signIn.passwordField.sendKeys(password);
    }

    @And("clicks on the login button")
    public void clicksOnTheLoginButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleIs("تسجيل دخول العميل"));
        // Check if the button is overlaying other elements
        boolean isOverlaying = (boolean) ((JavascriptExecutor) driver).executeScript(
                "var element = arguments[0];" +
                        "var rect = element.getBoundingClientRect();" +
                        "var elementsBelow = document.elementsFromPoint(rect.left + rect.width / 2, rect.top + rect.height / 2);" +
                        "return elementsBelow.some(function(el) { return el !== element && el.contains(element); });",
                p2_signIn.loginButton);
        if (isOverlaying) {
            System.out.println("The login button is overlaying other elements.");
        } else {
            System.out.println("The login button is not overlaying other elements.");
        }

        boolean isDisplayed = p2_signIn.loginButton.isDisplayed();
        System.out.println("Is the login button displayed? " + isDisplayed);

        boolean isClickable = p2_signIn.loginButton.isEnabled();
        System.out.println("Is the login button clickable? " + isClickable);

        // Get the value of the 'color' property for the login button
        String colorValue = p2_signIn.loginButton.getCssValue("color");
        System.out.println("Color of the login button: " + colorValue);

        // Get the value of the 'font-size' property for the login button
        String fontSizeValue = p2_signIn.loginButton.getCssValue("font-size");
        System.out.println("Font size of the login button: " + fontSizeValue);
        wait.until(ExpectedConditions.elementToBeClickable(p2_signIn.loginButton)).submit();
    }

    @Then("user should be redirected to the home page")
    public void userShouldBeRedirectedToTheMyAccountPage() {
            String expectedURL = "https://staging.inhouse.sa/ar/customer/account/";
            String redirectURL = driver.getCurrentUrl();
            Assert.assertEquals(redirectURL, expectedURL);
            //String pageTitle = driver.getTitle();
           // Assert.assertEquals("حسابي", pageTitle);
    }

    @When("User enters invalid email {string}")
    public void userEntersInvalidEmail(String email) {
        p2_signIn.emailField.sendKeys(email);
    }

    @And("enters valid password {string}")
    public void entersValidPassword(String pass) {
        p2_signIn.passwordField.sendKeys(pass);
    }

    @Then("user should see an error message")
    public void userShouldSeeAnErrorMessage() {

        boolean isDisplayed = p2_signIn.ErrorMessage.isDisplayed();
        System.out.println("Is the error message  displayed? " + isDisplayed);

        Assert.assertTrue(isDisplayed, "Error message is displayed.");

        String expecteErrorMessage = "كان تسجيل الدخول إلى الحساب غير صحيح أو تم تعطيل حسابك مؤقتًا. يرجى الانتظار والمحاولة مرة أخرى في وقت لاحق.";
        String actualErrorMessage = p2_signIn.ErrorMessage.getText().trim();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(p2_signIn.ErrorMessage));

        Assert.assertEquals(actualErrorMessage, expecteErrorMessage);
        System.out.println( expecteErrorMessage);
        System.out.println( actualErrorMessage);
    }
    @When("User enters valid email {string}")
    public void userEntersValidEmail(String email) {
        p2_signIn.emailField.sendKeys(email);
    }

    @And("enters incorrect password {string}")
    public void entersIncorrectPassword(String pass) {
        p2_signIn.passwordField.sendKeys(pass);
    }

    @When("User enters empty email and password")
    public void userEntersEmptyEmailAndPassword() {
        p2_signIn.emailField.sendKeys("");
        p2_signIn.passwordField.sendKeys("");
    }

    @Then("user should see validation errors for email and password fields")
    public void userShouldSeeValidationErrorsForEmailAndPasswordFields() {

        boolean isErrorMessageEmailDisplayed = p2_signIn.emailErrorMessage.isDisplayed();
        boolean isErrorMessagePassDisplayed = p2_signIn.emailErrorMessage.isDisplayed();

        System.out.println("Is the error message Email  displayed? " + isErrorMessageEmailDisplayed);
        System.out.println("Is the error message Password  displayed? " + isErrorMessagePassDisplayed);

        Assert.assertTrue(p2_signIn.emailErrorMessage.isDisplayed(), "Error message Email is displayed.");
        Assert.assertTrue(p2_signIn.passErrorMessage.isDisplayed(), "Error message Password is displayed.");

        String expecteErrorEmailMessage = "هذا الحقل مطلوب.";
        String expecteErrorPassMessage = "هذا الحقل مطلوب.";


        String actualErrorEmailMessage = p2_signIn.emailErrorMessage.getText().trim();
        String actualErrorPassMessage = p2_signIn.passErrorMessage.getText().trim();


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(p2_signIn.emailErrorMessage));
        Assert.assertEquals(actualErrorEmailMessage, expecteErrorEmailMessage);
        Assert.assertEquals(actualErrorPassMessage, expecteErrorPassMessage);

        System.out.println( expecteErrorEmailMessage);
        System.out.println( expecteErrorPassMessage);

        System.out.println( actualErrorEmailMessage);
        System.out.println( actualErrorPassMessage);

    }
}
