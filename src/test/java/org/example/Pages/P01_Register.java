package org.example.Pages;

import org.apache.commons.io.FileUtils;
import org.example.StepDefs.Hooks;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.function.Function;

import static org.example.StepDefs.Hooks.driver;

public class P01_Register {

   private final WebDriver driver;
   private final Wait<WebDriver> wait;

    public P01_Register(WebDriver driver){
          this.driver = driver;
          this.wait=new FluentWait<>(driver)
                  .withTimeout(Duration.ofSeconds(30))
                  .pollingEvery(Duration.ofMillis(2000))
                  .ignoring(org.openqa.selenium.NoSuchElementException.class);
           }

    public void navigateToSignUp(){

      WebElement RegisterPage=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='https://staging.inhouse.sa/ar/customer/account/create/']")));
      RegisterPage.click();

    }

    public void userData(){
        WebElement firstNameField=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='firstname']")));

        WebElement lastNameField=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name=\"lastname\"]")));

        WebElement emailField=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='email' and @id='email_address']")));

        WebElement mobileField=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='mobile_number_input']")));

        WebElement passwordField=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='password']")));

        WebElement confirmPasswordField=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='password_confirmation']")));

        String firstname="test";
        String lastname="test";

        String timeStampEmail= String.valueOf(System.currentTimeMillis());
        String email="test"+timeStampEmail+"@test.com";

        String timeStampMobile= String.valueOf(System.currentTimeMillis());
        String mobile="05"+timeStampMobile;

        String pass="test123";
        String confirmPass="test123";

        firstNameField.sendKeys(firstname);
        lastNameField.sendKeys(lastname);

        emailField.sendKeys(email);
        mobileField.sendKeys(mobile);

        passwordField.sendKeys(pass);
        confirmPasswordField.sendKeys(confirmPass);


    }


    public void createAccount(){

        WebElement createAccountLink=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='https://staging.inhouse.sa/ar/customer/account/create/' and  @class='action create primary']")));
        createAccountLink.click();
    }

    public void verfiySignUp_isOpened(){

        WebElement signUp_pageTitle=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='base' and @data-ui-id='page-title-wrapper']")));
        String actualTitle=signUp_pageTitle.getText();
        System.out.println(actualTitle);
        String expectedTitle="إنشاء حساب عميل جديد";
        Assert.assertEquals(actualTitle,expectedTitle);

    }

    public void  clickOn_createAccountButton(){
        WebElement createAccountButton=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='primary']//button[@class='action submit primary' and @type='submit' and @title='انشاء حساب']")));
        Actions actions = new Actions(driver);
        actions.moveToElement(createAccountButton, 10, 10).click().perform();
    }


    public void verifySignUp_isSuccessfull(){
        WebElement RegisterSuccessfulMessage=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-bind=\"html: $parent.prepareMessageForHtml(message.text)\" and contains(text(), 'شكرا لك للتسجيل في Inhouse KSA.')]")));
        boolean Display=RegisterSuccessfulMessage.isDisplayed();
        Assert.assertTrue(Display,"شكرا لك للتسجيل في Inhouse KSA.");
        System.out.println("Is Registeration Successful MessageDisplayed "+Display);
        System.out.println(RegisterSuccessfulMessage.getText());
    }



   // @FindBy(xpath = "//input[@type='checkbox' and @name='show-password' and @id='show-password']")
    //public WebElement showwPass;


    public void signUP_withExistingEmail(){

        WebElement firstNameField=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='firstname']")));

        WebElement lastNameField=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name=\"lastname\"]")));

        WebElement emailField=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='email' and @id='email_address']")));

        WebElement mobileField=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='mobile_number_input']")));

        WebElement passwordField=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='password']")));

        WebElement confirmPasswordField=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='password_confirmation']")));

        String firstname="test";
        String lastname="test";

        String email="test@test.com";

        String timeStampMobile= String.valueOf(System.currentTimeMillis());
        String mobile="05"+timeStampMobile;

        String pass="test123";
        String confirmPass="test123";

        firstNameField.sendKeys(firstname);
        lastNameField.sendKeys(lastname);

        emailField.sendKeys(email);
        mobileField.sendKeys(mobile);

        passwordField.sendKeys(pass);
        confirmPasswordField.sendKeys(confirmPass);


    }

    public void signUP_withEmptyFirstnameField(){

        WebElement firstNameField=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='firstname']")));

        WebElement lastNameField=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name=\"lastname\"]")));

        WebElement emailField=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='email' and @id='email_address']")));

        WebElement mobileField=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='mobile_number_input']")));

        WebElement passwordField=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='password']")));

        WebElement confirmPasswordField=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='password_confirmation']")));

        String firstname="";
        String lastname="test";

        String email="test@test.com";

        String timeStampMobile= String.valueOf(System.currentTimeMillis());
        String mobile="05"+timeStampMobile;

        String pass="test123";
        String confirmPass="test123";

        firstNameField.sendKeys(firstname);
        lastNameField.sendKeys(lastname);

        emailField.sendKeys(email);
        mobileField.sendKeys(mobile);

        passwordField.sendKeys(pass);
        confirmPasswordField.sendKeys(confirmPass);


    }

    public void signUP_withInvalidConfirmationPass(){

        WebElement firstNameField=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='firstname']")));

        WebElement lastNameField=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name=\"lastname\"]")));

        WebElement emailField=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='email' and @id='email_address']")));

        WebElement mobileField=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='mobile_number_input']")));

        WebElement passwordField=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='password']")));

        WebElement confirmPasswordField=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='password_confirmation']")));

        String firstname="test";
        String lastname="test";

        String email="test@test.com";

        String timeStampMobile= String.valueOf(System.currentTimeMillis());
        String mobile="05"+timeStampMobile;

        String pass="test123";
        String confirmPass="test12";

        firstNameField.sendKeys(firstname);
        lastNameField.sendKeys(lastname);

        emailField.sendKeys(email);
        mobileField.sendKeys(mobile);

        passwordField.sendKeys(pass);
        confirmPasswordField.sendKeys(confirmPass);


    }
    public void verifySignUp_isFailed(){
        WebElement ErrorMessage=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-bind=\"html: $parent.prepareMessageForHtml(message.text)\" and contains(text(), 'هناك بالفعل حساب مع عنوان البريد الإلكتروني هذا')]")));
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@data-bind=\"html: $parent.prepareMessageForHtml(message.text)\" and contains(text(), 'هناك بالفعل حساب مع عنوان البريد الإلكتروني هذا')]")));
        wait.until(ExpectedConditions.visibilityOf(ErrorMessage));

        boolean IsDisplay=ErrorMessage.isDisplayed();
        Assert.assertTrue(IsDisplay,"");

        System.out.println("account is already existing"+ IsDisplay);
        System.out.println("The Error message is: "+ErrorMessage.getText());
    }

    public void verifyConfirmPassword_validation() throws IOException {
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


    public void verifyFirstName_validation()  {
        WebElement validationFirstname=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@for='firstname' and @generated='true' and @class='mage-error' and @id='firstname-error']")));
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(validationFirstname));
        boolean IsDisplay=validationFirstname.isDisplayed();
        Assert.assertTrue(IsDisplay,"yes it is displayed");
        System.out.println(validationFirstname.getText());
    }




  //  @FindBy(xpath = "//div[@id='password-confirmation-error']")
    //public WebElement mismatchPassword_validationMassege;

}
