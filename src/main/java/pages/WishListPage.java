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

}
