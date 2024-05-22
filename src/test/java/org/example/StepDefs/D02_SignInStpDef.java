package org.example.StepDefs;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.Pages.P02_SignIn;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static org.example.StepDefs.Hooks.driver;
import static org.testng.Assert.assertEquals;
public class D02_SignInStpDef {
    P02_SignIn p2_signIn = new P02_SignIn(driver);
    @Given("User is on the login page")
    public void userIsOnTheLoginPage() {
        p2_signIn.navigateToSignInPage();
    }

    @When("User enters valid {string} and {string}")
    public void userEntersValidEmailAndPassword(String email, String password) {
        p2_signIn.signIn_isSuccessful(email,password);
    }

    @And("clicks on the login button")
    public void clicksOnTheLoginButton() {
        p2_signIn.clickSignInButton();
    }

    @Then("user should be redirected to the home page")
    public void userShouldBeRedirectedToTheMyAccountPage() {
        p2_signIn.verifyRedirecingMyAccountPage();
    }

    @When("User enters invalid email {string} and  enters valid password {string}")
    public void userEntersInvalidEmail(String email, String pass) {
        p2_signIn.enteringInvalidEmail(email,pass);
    }

    @Then("user should see an error message")
    public void userShouldSeeAnErrorMessage() {
        p2_signIn.login_isFailed();
    }

    @When("User enters valid email {string} and  enters invalid password {string}")
    public void userEntersValidEmail(String email, String pass) {
        p2_signIn.enteringInvalidPass(email,pass);
    }

    @When("User enters empty email {string} and password {string}")
    public void userEntersEmptyEmailAndPassword(String email, String pass) {
        p2_signIn.enteringEmptyData(email,pass);
    }

    @Then("user should see validation errors for email and password fields")
    public void userShouldSeeValidationErrorsForEmailAndPasswordFields() {
       p2_signIn.verify_validationMessage_isDisplayed();

    }


}
