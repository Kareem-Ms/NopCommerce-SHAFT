package tests;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.*;
import pages.RegisterPage;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterUserTest {

    ///////////////////Variables\\\\\\\\\\\\\\\\\\\
    SHAFT.GUI.WebDriver driver;
    SHAFT.TestData.JSON testData;
    RegisterPage registerPage;
    String email;
    String currentTime = new SimpleDateFormat("ddMMyyyyHHmmssSSS").format(new Date());



    /////////////////////Tests\\\\\\\\\\\\\\\\\\\\\\
    @Test(description = "Validate registering a user with valid email and password")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Register")
    public void registerWithValidEmailAndPw(){
        email = testData.getTestData("UserInfo.Email")+"_"+currentTime+testData.getTestData("UserInfo.Domain");
        registerPage    .openRegisterPage();
        driver          .assertThat().element(registerPage.getRegisterTitleLocator()).text().contains("Register").perform();
        registerPage    .RegisterUser(testData.getTestData("UserInfo.FirstName"),testData.getTestData("UserInfo.LastName"),email,testData.getTestData("UserInfo.Password"));
        driver          .assertThat().element(registerPage.getRegisterConfirmationLocator()).text().contains(testData.getTestData("messages.ConfirmRegister")).perform();
        registerPage    .clickContinueBtn();
    }

    @Test(dependsOnMethods = "registerWithValidEmailAndPw", description = "Validate registering with already registered email")
    @Severity(SeverityLevel.MINOR)
    @Story("Register")
    public void registerWithExistingEmailAndPw(){
        registerPage    .openRegisterPage();
        driver          .assertThat().element(registerPage.getRegisterTitleLocator()).text().contains("Register");
        registerPage    .RegisterUser(testData.getTestData("UserInfo.FirstName"),testData.getTestData("UserInfo.LastName"),email,testData.getTestData("UserInfo.Password"));
        driver          .assertThat().element(registerPage.getRegisteredEmailErrorMsg()).text().contains(testData.getTestData("messages.RegisteredEmail")).perform();
    }


    /////////////////Configuration\\\\\\\\\\\\\\\\\\
    @BeforeTest
    public void classSetUp(){
        testData = new SHAFT.TestData.JSON("RegisterUserTestData.json");
    }

    @BeforeMethod
    public void methodSetup(){
        driver = new SHAFT.GUI.WebDriver();
        registerPage = new RegisterPage(driver);
    }

    @AfterMethod
    public void methodTearDown(){
        driver.quit();
    }
}
