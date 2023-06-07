package pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class WishListPage {

    /////////////////Variables\\\\\\\\\\\\\\\\\\
    SHAFT.GUI.WebDriver driver;

    public WishListPage(SHAFT.GUI.WebDriver driver){
        this.driver = driver;
    }

    /////////////////Locators\\\\\\\\\\\\\\\\\\


    /////////////////Actions\\\\\\\\\\\\\\\\\\\
    public By getProductLinkLocator(String ProductName){
        return By.xpath("//a[@class = 'product-name' ] [contains(text() , '"+ProductName+"')]");
    }

    public void removeProductFromWishList(String ProductName){
        String removeProductBtnXpath = "//a[@class = 'product-name' ] [contains(text() , '"+ProductName+"')]/parent::td//following-sibling::td[@class = 'remove-from-cart']//button";
        driver.element().click(By.xpath(removeProductBtnXpath));
    }

    public boolean isProductExistInWishList(String ProductName){
        boolean flag;
        if(driver.element().isElementDisplayed(getProductLinkLocator(ProductName))){
            flag = true;
        }
        else {
            flag = false;
        }
        return flag;
    }


}
