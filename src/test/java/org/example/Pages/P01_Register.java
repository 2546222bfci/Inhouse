package org.example.Pages;

import org.example.StepDefs.Hooks;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class P01_Register {

    public P01_Register(){
        PageFactory.initElements(Hooks.driver,this);
    }

   // @FindBy(xpath ="//a[text()=\"انشاء حساب\"]")
   @FindBy(xpath = "//a[@href='https://staging.inhouse.sa/ar/customer/account/create/']")
   public WebElement RegisterPage;

    @FindBy(xpath ="//input[@id='firstname']")
    public WebElement firstNameField;

    @FindBy(xpath = "//input[@name=\"lastname\"]")
    public WebElement lastNameField;

    @FindBy(xpath = "//input[@name='email' and @id='email_address']")
    public WebElement emailField;

    @FindBy(xpath = "//input[@name='mobile_number_input']")
    public WebElement mobileField;

    @FindBy(xpath = " //input[@name='password']")
    public WebElement passwordField;

    @FindBy(xpath = " //input[@name='password_confirmation']")
    public WebElement confirmPasswordField;

    @FindBy(xpath = "//input[@type='checkbox' and @name='show-password' and @id='show-password']")
    public WebElement showwPass;

    @FindBy(xpath = "//a[@href='https://staging.inhouse.sa/ar/customer/account/create/' and  @class='action create primary']")
    public WebElement createAccountLink;

    @FindBy(xpath = "//div[@class='primary']//button[@class='action submit primary' and @type='submit' and @title='انشاء حساب']")
    public WebElement createAccountButton;



    @FindBy(xpath = "//div[@class='success-msg']//span[contains(text(),'شكرا لك للتسجيل في Inhouse KSA.')]")
    public WebElement RegisterSuccessfulMessage;
    @FindBy(xpath = "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")
    public WebElement ErrorMessage;
    @FindBy(xpath = "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")
    public WebElement validationFirstname;

    @FindBy(xpath = "//span[@class='base' and @data-ui-id='page-title-wrapper']")
    public WebElement signUp_pageTitle;

}
