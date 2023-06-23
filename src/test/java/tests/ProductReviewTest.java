package tests;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.*;
import pages.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class ProductReviewTest {

    ///////////////////Variables\\\\\\\\\\\\\\\\\\\
    SHAFT.GUI.WebDriver driver;
    SHAFT.TestData.JSON testData;
    HomePage homePage;
    ProductDetailsPage productDetailsPage;
    ProductReviewPage productReviewPage;
    LoginPage loginPage;
    RegisterPage registerPage;
    String email;
    String currentTime;

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

    @Test(dependsOnMethods = "registerWithValidEmailAndPw",description = "Validate Login with registered email and password")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Login")
    public void loginWithValidEmailAndPassword(){
        loginPage       .openLoginPage();
        loginPage       .login(email, testData.getTestData("UserInfo.Password"));
        driver          .assertThat().element(homePage.getMyAccountLinkLocator()).isVisible().perform();
    }

    @Test(description = "Verify Searching for specific product and viewing it's product page")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Search for product")
    public void searchForProductByName(){
        String ProductName = testData.getTestData("ProductName");

        homePage            .searchForProduct(ProductName);
        productDetailsPage  .openProductDetails(ProductName);
        driver              .assertThat().element(productDetailsPage.getProductTitleLocator()).text().contains(ProductName).perform();
    }

    @Test(dependsOnMethods = "searchForProductByName", description ="Verify adding product review for a registered user")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Add product Review")
    public void addProductReviewByRegisteredUser(){
        productDetailsPage  .openProductReviewsPage();
        driver              .assertThat().element(productReviewPage.getProductReviewTitleLocator()).text().contains(testData.getTestData("ProductReviewTitle")).perform();
        productReviewPage   .addProductReview(testData.getTestData("ProductReviewTitle"),testData.getTestData("ProductReviewText"),testData.getTestData("ProductRating"));
        driver              .assertThat().element(productReviewPage.getProductReviewAddMsgLocator()).text().contains(testData.getTestData("ProductReviewAddedMsg")).perform();
    }

    /////////////////Configuration\\\\\\\\\\\\\\\\\\
    @BeforeClass
    public void setUp(){
        driver = new SHAFT.GUI.WebDriver();
        testData = new SHAFT.TestData.JSON("ProductReviewTestData.json");
        homePage = new HomePage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
        productReviewPage = new ProductReviewPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        currentTime = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(Calendar.getInstance().getTime());
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

}