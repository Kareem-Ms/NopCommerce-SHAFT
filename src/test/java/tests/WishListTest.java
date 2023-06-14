package tests;

import com.shaft.driver.SHAFT;
import io.qameta.allure.*;
import org.testng.annotations.*;
import pages.HomePage;
import pages.ProductDetailsPage;
import pages.WishListPage;

@Epic("NopCommerce")
@Feature("Add and remove products from wishlists")
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
        ProductName = testData.getTestData("info.ProductName");

        homePage            .searchForProduct(ProductName);
        productDetailsPage  .openProductDetails(ProductName);
        driver              .assertThat().element(productDetailsPage.getProductTitleLocator()).text().contains(ProductName);
    }


    @Test(dependsOnMethods = "searchForProductByName", description = "Verify adding product for the wishlist")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Add product to wishlist")
    public void verifyAddingProductToWishlist(){
        productDetailsPage  .addProductToWishList();
        driver              .assertThat().element(productDetailsPage.getProductAddedConfirmationLocator()).text().contains(testData.getTestData("messages.prdouctAdded")).perform();
        wishListPage        .openWishListPage();
        driver              .assertThat().element(wishListPage.getProductLinkLocator(ProductName)).text().contains(testData.getTestData("info.ProductName")).perform();
    }

    @Test(dependsOnMethods = "verifyAddingProductToWishlist" , description = "Verify deleting product from wishlist after adding it")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Delete product from wishlist")
    public void verifyDeletingProductFromWishList(){
        wishListPage        .removeProductFromWishList(ProductName);
        driver              .assertThat().element(wishListPage.getProductLinkLocator(ProductName)).doesNotExist().perform();
    }

    /////////////////Configuration\\\\\\\\\\\\\\\\\\
    @BeforeTest
    public void setUp(){
        driver = new SHAFT.GUI.WebDriver();
        testData = new SHAFT.TestData.JSON("WishListTestData.json");
        homePage = new HomePage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
        wishListPage = new WishListPage(driver);
        homePage.navigateToHomePage();
    }

    @AfterTest
    public void tearDowm(){
        driver.quit();
    }

}
