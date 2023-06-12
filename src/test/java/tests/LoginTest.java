package tests;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;

import java.text.SimpleDateFormat;

public class LoginTest {

    ///////////////////Variables\\\\\\\\\\\\\\\\\\\
    ThreadLocal<SHAFT.GUI.WebDriver> driver = new ThreadLocal<>();
    ThreadLocal<SHAFT.TestData.JSON> testData = new ThreadLocal<>() ;
    RegisterPage registerPage;
    LoginPage loginPage;
    HomePage homePage;
    String email;
    String currentTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());


    /////////////////////Tests\\\\\\\\\\\\\\\\\\\\\\
    @Test(description = "Validate registering a user with valid email and password")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Register")
    public void registerWithValidEmailAndPw(){
        email = testData.get().getTestData("UserInfo.Email")+"_"+currentTime+testData.get().getTestData("UserInfo.Domain");

        registerPage    .openRegisterPage();
        driver.get()          .assertThat().element(registerPage.getRegisterTitleLocator()).text().contains("Register").perform();
        registerPage    .RegisterUser(testData.get().getTestData("UserInfo.FirstName"),testData.get().getTestData("UserInfo.LastName"),email,testData.get().getTestData("UserInfo.Password"));
        driver.get()          .assertThat().element(registerPage.getRegisterConfirmationLocator()).text().contains(testData.get().getTestData("messages.ConfirmRegister")).perform();
        registerPage    .clickContinueBtn();
    }

    @Test(dependsOnMethods = "registerWithValidEmailAndPw",description = "Validate Login with registered email and password")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Login")
    public void loginWithValidEmailAndPassword(){
        loginPage   .openLoginPage();
        loginPage   .login(email, testData.get().getTestData("UserInfo.Password"));
        driver.get()      .assertThat().element(homePage.getMyAccountLinkLocator()).isVisible().perform();
    }


    /////////////////Configuration\\\\\\\\\\\\\\\\\\
    @BeforeClass
    public void setUp(){
        driver.set(new SHAFT.GUI.WebDriver());
        registerPage = new RegisterPage(driver.get());
        homePage = new HomePage(driver.get());
        loginPage = new LoginPage(driver.get());
        testData.set(new SHAFT.TestData.JSON("LoginTestData.json"));

    }

    @AfterClass
    public void tearDown(){
        driver.get().quit();
    }
}
