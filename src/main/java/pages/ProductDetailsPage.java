package pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ProductDetailsPage {

    /////////////////Variables\\\\\\\\\\\\\\\\\\
    SHAFT.GUI.WebDriver driver;

    public ProductDetailsPage(SHAFT.GUI.WebDriver driver){
        this.driver = driver;
    }

    /////////////////Locators\\\\\\\\\\\\\\\\\\
    By ProductTitleLocator = By.className("product-name");
    By AddProductToWishList = By.id("add-to-wishlist-button-4");
    By ProductAddedConfirmationLocator = By.className("success");
    By whishListLinkLocator = By.cssSelector("p.content > a");


    /////////////////Actions\\\\\\\\\\\\\\\\\\\

    @Step("Open product details page")
    public void openProductDetails(String ProductName){
        By ProductLinkLocator = By.xpath("//h2[@class = 'product-title']//a[contains (text() , '"+ProductName+"')]");
        driver.element().click(ProductLinkLocator);
    }

    public By getProductTitleLocator(){
        return ProductTitleLocator;
    }

    public void addProductToWishList(){
        driver.element().click(AddProductToWishList);
    }

    public By getProductAddedConfirmationLocator(){
        return ProductAddedConfirmationLocator;
    }

    public void openWishList(){
        driver.element().click(whishListLinkLocator);
    }


}
