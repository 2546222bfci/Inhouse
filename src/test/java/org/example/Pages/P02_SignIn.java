package org.example.Pages;
import org.example.StepDefs.Hooks;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class P02_SignIn {
    public P02_SignIn()
    {
        PageFactory.initElements(Hooks.driver,this);
    }
    @FindBy(xpath = "//a[ @href='https://staging.inhouse.sa/ar/customer/account/login/' and @class='account-width' and @title='تسجيل الدخول']")
    public WebElement loginPage;
    @FindBy(xpath = "//input[@name='login[username]' and @id='email']")
    public WebElement emailField;
    @FindBy(xpath = "//input[@name='login[password]' and @id='pass']")
    public WebElement passwordField;
    @FindBy(xpath = "//button[@class=\"action login primary\"]/span[contains(text(), \"تسجيل الدخول\")]")
    public WebElement loginButton;
    @FindBy(xpath = "//div[@data-bind=\"html: $parent.prepareMessageForHtml(message.text)\"]")
    public WebElement ErrorMessage;
    @FindBy(xpath = "//div[@id='email-error']")
    public WebElement emailErrorMessage;
    @FindBy(xpath = "//div[@id='pass-error']")
    public WebElement passErrorMessage;
    @FindBy(xpath = "//label[@for='show-password' and contains(@class, 'label')]/span[contains(@data-bind, \"i18n: 'Show Password'\")]")
    public  WebElement showPass;
}

