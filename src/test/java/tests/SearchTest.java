package tests;

import com.shaft.driver.SHAFT;
import io.qameta.allure.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductDetailsPage;

@Epic("NopCommerce")
@Feature("Search for product")
public class SearchTest {

    ///////////////////Variables\\\\\\\\\\\\\\\\\\\
    SHAFT.GUI.WebDriver driver;
    SHAFT.TestData.JSON testData;
    HomePage homePage;
    ProductDetailsPage productDetailsPage;

    /////////////////////Tests\\\\\\\\\\\\\\\\\\\\\\
    @Test(description = "Verify Searching for specific product and viewing it's product page")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Search for product")
    public void searchForProductByName(){
        String ProductName = testData.getTestData("ProductName");

        homePage            .searchForProduct(ProductName);
        productDetailsPage  .openProductDetails(ProductName);
        driver              .assertThat().element(productDetailsPage.getProductTitleLocator()).text().contains(ProductName);
    }


    /////////////////Configuration\\\\\\\\\\\\\\\\\\
    @BeforeClass
    public void setUp(){
        driver = new SHAFT.GUI.WebDriver();
        testData = new SHAFT.TestData.JSON("SearchTestData.json");
        homePage = new HomePage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
        homePage.navigateToHomePage();
    }

}
