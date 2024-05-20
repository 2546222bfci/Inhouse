package org.example.StepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.Pages.P01_Register;
import org.example.Pages.P02_SignIn;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import static org.example.StepDefs.Hooks.driver;
import org.apache.commons.io.FileUtils;
public class D01_Register {
    P01_Register p01_register=new P01_Register();
    P02_SignIn p02_SignIn=new P02_SignIn();

    @Given("The user is on the signup page")
    public void theUserIsOnTheWebsiteSHomepage() {
        p01_register.RegisterPage.click();
    }

    @When("The user enters the data")
    public void UserData() {

        String firstname="test";
        String lastname="test";

        String timeStampEmail= String.valueOf(System.currentTimeMillis());
        String email="test"+timeStampEmail+"@test.com";

        String timeStampMobile= String.valueOf(System.currentTimeMillis());
        String mobile="05"+timeStampMobile;

        String pass="test123";
        String confirmPass="test123";

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
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(p01_register.RegisterSuccessfulMessage));

        boolean Display=p01_register.RegisterSuccessfulMessage.isDisplayed();
        Assert.assertTrue(Display,"شكرا لك للتسجيل في Inhouse KSA.");
        System.out.println("Is Registeration Successful MessageDisplayed "+Display);
        System.out.println(p01_register.RegisterSuccessfulMessage.getText());
    }

    @Then("An error message should be displayed")
    public void anErrorMessageShouldBeDisplayed() {

        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@data-bind=\"html: $parent.prepareMessageForHtml(message.text)\" and contains(text(), 'هناك بالفعل حساب مع عنوان البريد الإلكتروني هذا')]")));
        wait.until(ExpectedConditions.visibilityOf(p01_register.ErrorMessage));

        boolean IsDisplay=p01_register.ErrorMessage.isDisplayed();
        Assert.assertTrue(IsDisplay,"");

        System.out.println("account is already existing"+ IsDisplay);
        System.out.println("The Error message is: "+p01_register.ErrorMessage.getText());

    }
    @Then("validation messages should be displayed")
    public void ValidationMessagesShouldBeDisplayed() {

        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(p01_register.validationFirstname));
        boolean IsDisplay=p01_register.validationFirstname.isDisplayed();
        Assert.assertTrue(IsDisplay,"yes it is displayed");
        System.out.println(p01_register.validationFirstname.getText());
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

    @When("The user enters the data with existing email")

        public void UserDataWithExistingEmail() {

            String firstname="test";
            String lastname="test";

            String email="test.m@test.com";

            String timeStampMobile= String.valueOf(System.currentTimeMillis());
            String mobile="05"+timeStampMobile;

            String pass="test123";
            String confirmPass="test123";

            p01_register.firstNameField.sendKeys(firstname);
            p01_register.lastNameField.sendKeys(lastname);

            p01_register.emailField.sendKeys(email);
            p01_register.mobileField.sendKeys(mobile);

            p01_register.passwordField.sendKeys(pass);
            p01_register.confirmPasswordField.sendKeys(confirmPass);

        }

    @When("The user doesn't enter the data")
    public void theUserDoesnTEnterTheData() {
        String firstname="";
        String lastname="test";

        String timeStampEmail=String.valueOf(System.currentTimeMillis());
        String  email="test"+timeStampEmail+".m@test.com";

        String timeStampMobile= String.valueOf(System.currentTimeMillis());
        String mobile="05"+timeStampMobile;

        String pass="test123";
        String confirmPass="test123";

        p01_register.firstNameField.sendKeys(firstname);
        p01_register.lastNameField.sendKeys(lastname);

        p01_register.emailField.sendKeys(email);
        p01_register.mobileField.sendKeys(mobile);

        p01_register.passwordField.sendKeys(pass);
        p01_register.confirmPasswordField.sendKeys(confirmPass);

    }

    @When("The user enters the data with invalid confirmation pass")
    public void theUserEntersTheDataWithInvalidConfirmationPass() {
        String firstname="test";
        String lastname="test";
        String timeStampEmail=String.valueOf(System.currentTimeMillis());
        String email="test"+timeStampEmail+".m@test.com";
        String timeStampMobile= String.valueOf(System.currentTimeMillis());
        String mobile="05"+timeStampMobile;
        String pass="test123";
        String confirmPass="test12";
        p01_register.firstNameField.sendKeys(firstname);
        p01_register.lastNameField.sendKeys(lastname);
        p01_register.emailField.sendKeys(email);
        p01_register.mobileField.sendKeys(mobile);
        p01_register.passwordField.sendKeys(pass);
        p01_register.confirmPasswordField.sendKeys(confirmPass);

    }
    @Then("validation message for confirm pass field should be displayed")
    public void validationMessageForPassFieldShouldBeDisplayed() throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Simplified XPath for the element
        String xpath = "//div[@id='password-confirmation-error']";

        try {
            // Wait for the element to be present in the DOM
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            // Wait for the element to be visible
            wait.until(ExpectedConditions.visibilityOf(element));

            boolean isDisplayed = element.isDisplayed();
            Assert.assertTrue(isDisplayed, "Validation message is displayed");
            System.out.println("Validation message: " + element.getText());
        } catch (Exception e) {
            // Capture a screenshot on failure
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File("screenshot.png"));

            System.out.println("Debug: Element could not be found or was not visible within the timeout period.");
            System.err.println("Exception occurred: " + e.getMessage());
            Assert.fail("Validation message was not displayed");
        }
    }
    }


