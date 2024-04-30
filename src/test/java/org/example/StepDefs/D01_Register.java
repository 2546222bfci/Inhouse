package org.example.StepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.Pages.P01_Register;
import org.example.Pages.P02_SignIn;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import java.time.Duration;
import static org.example.StepDefs.Hooks.driver;

public class D01_Register {
    P01_Register p01_register=new P01_Register();
    P02_SignIn p02_SignIn=new P02_SignIn();

    @Given("The user is on the signup page")
    public void theUserIsOnTheWebsiteSHomepage() {
        p01_register.RegisterPage.click();
    }

    @When("The user enters {string}, {string}, {string},{string},{string}, and {string}")
    public void theUserEntersAnd(String firstname , String lastname, String email, String mobile, String pass, String confirmPass) {

        p01_register.firstNameField.sendKeys(firstname);
        p01_register.lastNameField.sendKeys(lastname);

        p01_register.emailField.sendKeys(email);
        p01_register.mobileField.sendKeys(mobile);

        p01_register.passwordField.sendKeys(pass);
        p01_register.confirmPasswordField.sendKeys(confirmPass);

    }

    @And("click the create account button")
    public void clickTheCreateAccountButton() {
        WebElement button = p01_register.createAccountButton;
        Actions actions = new Actions(driver);
        actions.moveToElement(button, 10, 10).click().perform();
    }

    @Then("signup must be successful.")
    public void signupMustBeSuccessful() {
        System.out.println("enter the fun");
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(p01_register.RegisterSuccessfulMessage));

        boolean Display=p01_register.RegisterSuccessfulMessage.isDisplayed();
        Assert.assertTrue(Display,"شكرا لك للتسجيل في Inhouse KSA.");
        System.out.println("Is Registeration Successful MessageDisplayed"+Display);
    }
    @Then("An error message should be displayed")
    public void anErrorMessageShouldBeDisplayed() {

        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(p01_register.ErrorMessage));

        boolean IsDisplay=p01_register.ErrorMessage.isDisplayed();
        Assert.assertTrue(IsDisplay,"");

        System.out.println("account is already existing"+ IsDisplay);
    }
    @Then("An validation messages should be displayed")
    public void anValidationMessagesShouldBeDisplayed() {

        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(p01_register.validationFirstname));

        boolean IsDisplay=p01_register.validationFirstname.isDisplayed();
        Assert.assertTrue(IsDisplay,"yes it is displayed");

        System.out.println(IsDisplay);
        System.out.println(p01_register.validationFirstname);
    }

    @When("the user click on Signup button")
    public void theUserClickOnSignupButton() {
         p01_register.createAccountLink.click();
    }

    @Then("SignUp page is opened")
    public void signupPageIsOpened() {
        String actualTitle=p01_register.signUp_pageTitle.getText();
        System.out.println(actualTitle);
        String expectedTitle="إنشاء حساب عميل جديد";
        Assert.assertEquals(actualTitle,expectedTitle);
    }
}
