package pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ChangePasswordPage {

    /////////////////Variables\\\\\\\\\\\\\\\\\\
    SHAFT.GUI.WebDriver driver;
    String ChangePasswordPageUrl = System.getProperty("nopCommerce.baseuri")+"customer/changepassword";

    public ChangePasswordPage(SHAFT.GUI.WebDriver driver){
        this.driver = driver;
    }

    /////////////////Locators\\\\\\\\\\\\\\\\\\
    By OldPasswordLocator = By.id("OldPassword");
    By NewPasswordLinkLocator = By.id("NewPassword");
    By ConfirmPasswordLinkLocator = By.id("ConfirmNewPassword");
    By ChangePasswordBtn = By.className("change-password-button");
    By PasswordChangedSucessMsg = By.className("success");
    By ChangePasswordTitleLocator = By.className("page-title");

    /////////////////Actions\\\\\\\\\\\\\\\\\\\
    @Step("Open change password page")
    public void openChangePasswordPage(){
        driver.browser().navigateToURL(ChangePasswordPageUrl);
    }

    @Step("Change password from currentPassword:[{CurrentPassword}] to New password: [{NewPassword}]")
    public void changePassword(String CurrentPassword, String NewPassword){
        driver.element()
                .type(OldPasswordLocator, CurrentPassword)
                .type(NewPasswordLinkLocator, NewPassword)
                .type(ConfirmPasswordLinkLocator, NewPassword)
                .click(ChangePasswordBtn);
    }

    @Step("get Password changed message locator")
    public By getPasswordChangedMsgLocator(){
        return PasswordChangedSucessMsg;
    }

    public By getChagePasswordTitleLocator(){
        return ChangePasswordTitleLocator;
    }

}
