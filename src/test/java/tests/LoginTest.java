package tests;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginTest {

    ///////////////////Variables\\\\\\\\\\\\\\\\\\\
    ThreadLocal<SHAFT.GUI.WebDriver> driver = new ThreadLocal<>();
    SHAFT.TestData.JSON testData;
    RegisterPage registerPage;
    LoginPage loginPage;
    HomePage homePage;
    String email;
   String currentTime = new SimpleDateFormat("ddMMyyyyHHmmssSSS").format(new Date());


    /////////////////////Tests\\\\\\\\\\\\\\\\\\\\\\
    @Test(description = "Validate registering a user with valid email and password")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Register")
    public void registerWithValidEmailAndPw(){
        email = testData.getTestData("UserInfo.Email")+"_"+currentTime+testData.getTestData("UserInfo.Domain");

        registerPage    .openRegisterPage();
        driver.get()    .assertThat().element(registerPage.getRegisterTitleLocator()).text().contains("Register").perform();
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


    /////////////////Configuration\\\\\\\\\\\\\\\\\\
    @BeforeTest
    public void setUp(){
        driver.set(new SHAFT.GUI.WebDriver());
        registerPage = new RegisterPage(driver.get());
        homePage = new HomePage(driver.get());
        loginPage = new LoginPage(driver.get());
        testData = new SHAFT.TestData.JSON("LoginTestData.json");

    }

    @AfterTest
    public void tearDown(){
        driver.get().quit();
    }
}
