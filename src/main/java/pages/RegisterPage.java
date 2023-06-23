package pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class RegisterPage {

    /////////////////Variables\\\\\\\\\\\\\\\\\\
    SHAFT.GUI.WebDriver driver;
    String RegisterPageUrl = "https://demo.nopcommerce.com/register?returnUrl=%2F";

    public RegisterPage(SHAFT.GUI.WebDriver driver){
        this.driver = driver;
    }

    /////////////////Locators\\\\\\\\\\\\\\\\\\
    By FirstNameInputLocator = By.id("FirstName");
    By LastNameInputLocator = By.id("LastName");
    By EmailInputLocator = By.id("Email");
    By PasswordInputLocator = By.id("Password");
    By ConfirmPasswordInputLocator = By.id("ConfirmPassword");
    By RegisterButtonLocator = By.id("register-button");
    By RegisterTitleLocator = By.className("page-title");
    By RegisterConfirmationMsg = By.className("result");
    By RegisterContinueBtnLocator = By.className("register-continue-button");
    By RegisteredEmailErrorMsg = By.className("validation-summary-errors");


    /////////////////Actions\\\\\\\\\\\\\\\\\\
    @Step("Navigate to register page")
    public void openRegisterPage(){
        driver.browser().navigateToURL(RegisterPageUrl);
    }
    @Step("Register user with First Name: [{FirstName}] , Last Name: [{LastName}], Email: [{Email}], Password: [{Password}]")
    public void RegisterUser(String FirstName, String LastName, String Email, String Password){
        driver.element()
                .type(FirstNameInputLocator, FirstName)
                .type(LastNameInputLocator, LastName)
                .type(EmailInputLocator, Email)
                .type(PasswordInputLocator, Password)
                .type(ConfirmPasswordInputLocator, Password)
                .click(RegisterButtonLocator);
    }

    @Step("Get register title locator")
    public By getRegisterTitleLocator(){
        return RegisterTitleLocator;
    }

    @Step("Return Register confirmation message")
    public By getRegisterConfirmationLocator(){
        return RegisterConfirmationMsg;
    }

    @Step("Click continue button")
    public void clickContinueBtn(){
        driver.element().click(RegisterContinueBtnLocator);
    }

    @Step("get RegisteredEmailErrorMsg locator")
    public By getRegisteredEmailErrorMsg(){
        return RegisteredEmailErrorMsg;
    }



}
