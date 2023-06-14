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
import java.util.Date;

public class ChangePasswordTest {

    ///////////////////Variables\\\\\\\\\\\\\\\\\\\

    ThreadLocal<SHAFT.GUI.WebDriver> driver = new ThreadLocal<>();
    SHAFT.TestData.JSON testData;
    RegisterPage registerPage;
    LoginPage loginPage;
    HomePage homePage;
    ChangePasswordPage changePasswordPage;
    String email;
    String currentTime = new SimpleDateFormat("ddMMyyyyHHmmssSSS").format(new Date());

    /////////////////////Tests\\\\\\\\\\\\\\\\\\\\\\
    @Test(description = "Validate registering a user with valid email and password")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Register")
    public void registerWithValidEmailAndPw(){
        email = testData.getTestData("UserInfo.Email")+"_"+currentTime+testData.getTestData("UserInfo.Domain");

        registerPage    .openRegisterPage();
        driver.get()          .assertThat().element(registerPage.getRegisterTitleLocator()).text().contains("Register").perform();
        registerPage    .RegisterUser(testData.getTestData("UserInfo.FirstName"),testData.getTestData("UserInfo.LastName"),email,testData.getTestData("UserInfo.Password"));
        driver.get()          .assertThat().element(registerPage.getRegisterConfirmationLocator()).text().contains(testData.getTestData("messages.ConfirmRegister")).perform();
        registerPage    .clickContinueBtn();
    }

    @Test(dependsOnMethods = "registerWithValidEmailAndPw",description = "Validate Login with registered email and password")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Login")
    public void loginWithValidEmailAndPassword(){
        loginPage   .openLoginPage();
        loginPage   .login(email, testData.getTestData("UserInfo.Password"));
        driver.get()      .assertThat().element(homePage.getMyAccountLinkLocator()).isVisible().perform();
    }

    @Test(dependsOnMethods = "loginWithValidEmailAndPassword" , description = "Validate changing account password to a new one")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Change Password")
    public void VerifyChangingPasswordSuccessfully(){
        changePasswordPage  .openChangePasswordPage();
        driver.get()              .assertThat().element(changePasswordPage.getChagePasswordTitleLocator()).text().contains(testData.getTestData("UserInfo.ChangePasswordTitle"));
        changePasswordPage  .changePassword(testData.getTestData("UserInfo.Password"), testData.getTestData("UserInfo.NewPassword"));
        driver.get()              .assertThat().element(changePasswordPage.getPasswordChangedMsgLocator()).text().contains(testData.getTestData("messages.PasswordChangedMsg"));
    }

    /////////////////Configuration\\\\\\\\\\\\\\\\\\
    @BeforeTest
    public void setUp(){
        driver.set(new SHAFT.GUI.WebDriver());
        registerPage = new RegisterPage(driver.get());
        homePage = new HomePage(driver.get());
        loginPage = new LoginPage(driver.get());
        changePasswordPage = new ChangePasswordPage(driver.get());
        testData = new SHAFT.TestData.JSON("ChangePasswordTestData.json");
    }

    @AfterTest
    public void tearDown(){
        driver.get().quit();
    }
}
