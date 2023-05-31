package tests;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductDetailsPage;
import pages.WishListPage;

public class WishListTest {

    ///////////////////Variables\\\\\\\\\\\\\\\\\\\
    SHAFT.GUI.WebDriver driver;
    SHAFT.TestData.JSON testData;
    HomePage homePage;
    ProductDetailsPage productDetailsPage;
    WishListPage wishListPage;
    String ProductName;

    /////////////////////Tests\\\\\\\\\\\\\\\\\\\\\\
    @Test(description = "Verify Searching for specific product and viewing it's product page")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Search for product")
    public void searchForProductByName(){
        ProductName = testData.getTestData(testData.getTestData("info.ProductName"));

        homePage            .searchForProduct(ProductName);
        productDetailsPage  .openProductDetails(ProductName);
        driver              .assertThat().element(productDetailsPage.getProductTitleLocator()).text().contains(ProductName);
    }

    @Test(dependsOnMethods = "searchForProductByName")
    public void verifyAddingProductToWishlist(){
        productDetailsPage  .addProductToWishList();
        driver              .assertThat().element(productDetailsPage.getProductAddedConfirmationLocator()).text().contains(testData.getTestData("messages.prdouctAdded")).perform();
        productDetailsPage  .openWishList();
        driver              .assertThat().element(wishListPage.getProductLinkLocator(ProductName)).text().contains(testData.getTestData("info.ProductName")).perform();
    }

    /////////////////Configuration\\\\\\\\\\\\\\\\\\
    @BeforeClass
    public void setUp(){
        driver = new SHAFT.GUI.WebDriver();
        testData = new SHAFT.TestData.JSON("WishListTestData.json");
        homePage = new HomePage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
        wishListPage = new WishListPage(driver);
        homePage.navigateToHomePage();
    }

    @AfterClass
    public void tearDowm(){
        driver.quit();
    }

}
