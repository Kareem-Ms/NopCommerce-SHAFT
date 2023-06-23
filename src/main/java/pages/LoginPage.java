package pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class LoginPage {

    /////////////////Variables\\\\\\\\\\\\\\\\\\
    SHAFT.GUI.WebDriver driver;
    String LoginPageUrl = System.getProperty("nopCommerce.baseuri")+"login";

    public LoginPage(SHAFT.GUI.WebDriver driver){
        this.driver = driver;
    }

    /////////////////Locators\\\\\\\\\\\\\\\\\\
    By EmailInputLocator = By.id("Email");
    By PasswordInputLocator = By.id("Password");
    By LoginButton = By.className("login-button");

    /////////////////Actions\\\\\\\\\\\\\\\\\\
    @Step("Navigate to Login page")
    public void openLoginPage(){
        driver.browser().navigateToURL(LoginPageUrl);
    }

    @Step("Login with Email:[{email}] ,Password [{password}]")
    public void login(String email, String password){
        driver.element()
                .type(EmailInputLocator, email)
                .type(PasswordInputLocator, password)
                .click(LoginButton);
    }

}
