package pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class HomePage {

    /////////////////Variables\\\\\\\\\\\\\\\\\\
    SHAFT.GUI.WebDriver driver;
    String homePageUrl = System.getProperty("nopCommerce.baseuri");

    public HomePage(SHAFT.GUI.WebDriver driver){
        this.driver = driver;
    }

    /////////////////Locators\\\\\\\\\\\\\\\\\\
    By SearchBarLocator = By.id("small-searchterms");
    By SubmitSearchBtn = By.className("search-box-button");
    By MyAccountLinkLocator = By.className("ico-account");

    /////////////////Actions\\\\\\\\\\\\\\\\\\\
    @Step("Navigate to home page")
    public void navigateToHomePage(){
        driver.browser().navigateToURL(homePageUrl);
    }

    @Step("Search for specific product using the first 4 characters")
    public void searchForProduct(String ProductName){
        driver.element().type(SearchBarLocator,ProductName)
                        .click(SubmitSearchBtn);
    }

    @Step("return My account link locator")
    public By getMyAccountLinkLocator(){
        return MyAccountLinkLocator;
    }

    @Step("open my account page")
    public void openMyAccountPage(){
        driver.element().click(MyAccountLinkLocator);
    }

}
