package tests;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.*;
import pages.ChangePasswordPage;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class ChangePasswordTest {

    ///////////////////Variables\\\\\\\\\\\\\\\\\\\
    SHAFT.GUI.WebDriver driver;
    SHAFT.TestData.JSON testData;
    RegisterPage registerPage;
    LoginPage loginPage;
    HomePage homePage;
    ChangePasswordPage changePasswordPage;
    String email;
    String currentTime;

    /////////////////////Tests\\\\\\\\\\\\\\\\\\\\\\
    @Test(description = "Validate registering a user with valid email and password")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Register")
    public void registerWithValidEmailAndPw(){
        email = testData.getTestData("UserInfo.Email")+"_"+currentTime+testData.getTestData("UserInfo.Domain");

        registerPage    .openRegisterPage();
        registerPage    .RegisterUser(testData.getTestData("UserInfo.FirstName")
                                     ,testData.getTestData("UserInfo.LastName")
                                     ,email,testData.getTestData("UserInfo.Password"));

        checkSuccessfullRegistration();
        registerPage    .clickContinueBtn();
    }

    @Test(dependsOnMethods = "registerWithValidEmailAndPw",description = "Validate Login with registered email and password")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Login")
    public void loginWithValidEmailAndPassword(){
        loginPage   .openLoginPage();
        loginPage   .login(email, testData.getTestData("UserInfo.Password"));

        checkSuccessfullLogin();
    }

    @Test(dependsOnMethods = "loginWithValidEmailAndPassword" , description = "Validate changing account password to a new one")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Change Password")
    public void VerifyChangingPasswordSuccessfully(){
        changePasswordPage  .openChangePasswordPage();
        changePasswordPage  .changePassword(testData.getTestData("UserInfo.Password"),testData.getTestData("UserInfo.NewPassword"));

        checkSuccessfullChangingPassword();
    }

    /////////////////Configuration\\\\\\\\\\\\\\\\\\
    @BeforeClass
    public void setUp(){
        driver = new SHAFT.GUI.WebDriver();
        registerPage = new RegisterPage(driver);
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        changePasswordPage = new ChangePasswordPage(driver);
        testData = new SHAFT.TestData.JSON("ChangePasswordTestData.json");
        currentTime = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(Calendar.getInstance().getTime());
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    /////////////////Assertions\\\\\\\\\\\\\\\\\\

    public void checkSuccessfullRegistration(){
        driver.assertThat()
                .element(registerPage.getRegisterConfirmationLocator())
                .text()
                .contains(testData.getTestData("messages.ConfirmRegister"))
                .withCustomReportMessage("check if the desired registration message exists")
                .perform();
    }

    public void checkSuccessfullLogin(){
        driver.assertThat()
                .element(homePage.getMyAccountLinkLocator())
                .isVisible()
                .withCustomReportMessage("Check if My account link appears to verify login")
                .perform();
    }

    public void checkSuccessfullChangingPassword(){
        driver.assertThat()
                .element(changePasswordPage.getPasswordChangedMsgLocator())
                .text()
                .contains(testData.getTestData("messages.PasswordChangedMsg"))
                .withCustomReportMessage("Check that 'Password was changed' message appear")
                .perform();
    }
}
