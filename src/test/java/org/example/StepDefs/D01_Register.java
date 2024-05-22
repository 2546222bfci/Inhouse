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
    P01_Register p01_register=new P01_Register(driver);
    P02_SignIn p02_SignIn=new P02_SignIn(driver);
    @Given("The user is on the signup page")
    public void theUserIsOnTheWebsiteSHomepage() {
        p01_register.navigateToSignUp();
    }
    @When("the user click on Signup button")
    public void theUserClickOnSignupButton() {
        p01_register.createAccount();
    }
    @When("The user enters the data")
    public void UserData() {
              p01_register.userData();
            }
    @And("click the create account button")
    public void clickTheCreateAccountButton() {
       p01_register.clickOn_createAccountButton();
        }
    @Then("signup must be successful.")
    public void signupMustBeSuccessful() {
        p01_register.verifySignUp_isSuccessfull();
    }
    @Then("An error message should be displayed")
    public void anErrorMessageShouldBeDisplayed() {
        p01_register.verifySignUp_isFailed();
    }
    @Then("validation messages should be displayed")
    public void ValidationMessagesShouldBeDisplayed() {
        p01_register.verifyFirstName_validation();
    }


    @Then("SignUp page is opened")
    public void signupPageIsOpened() {
        p01_register.verfiySignUp_isOpened();
    }

    @When("The user enters the data with existing email")
        public void UserDataWithExistingEmail() {
            p01_register.signUP_withExistingEmail();
        }

    @When("The user doesn't enter the data")
    public void theUserDoesnTEnterTheData() {
        p01_register.signUP_withEmptyFirstnameField();
    }

    @When("The user enters the data with invalid confirmation pass")
    public void theUserEntersTheDataWithInvalidConfirmationPass() {
        p01_register.signUP_withInvalidConfirmationPass();

    }
    @Then("validation message for confirm pass field should be displayed")
    public void validationMessageForPassFieldShouldBeDisplayed() throws IOException {
        p01_register.verifyConfirmPassword_validation();
    }
    }


