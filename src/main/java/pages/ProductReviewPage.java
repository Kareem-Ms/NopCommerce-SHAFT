package pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ProductReviewPage {

    /////////////////Variables\\\\\\\\\\\\\\\\\\
    SHAFT.GUI.WebDriver driver;

    public ProductReviewPage(SHAFT.GUI.WebDriver driver){
        this.driver = driver;
    }

    /////////////////Locators\\\\\\\\\\\\\\\\\\
    By ProductReviewTitleInputLocator = By.id("AddProductReview_Title");
    By ProductReviewTextInputLocator = By.id("AddProductReview_ReviewText");
    By ProductReviewTitleLocator = By.cssSelector("#review-form >div.title");
    By ProductReviewAddMsgLocator = By.cssSelector("div.page-body > div.result");
    By SubmitReviewBtnLocator = By.name("add-review");

    /////////////////Actions\\\\\\\\\\\\\\\\\\\
    @Step("Add product review using the following fields Review title: [{}], Review text: [{}], Rating value: [{}]")
    public void addProductReview(String ReviewTitle, String ReviewText, String RatingVlaue){
        By ratingLocator = By.cssSelector("input[value = '"+RatingVlaue+"']");

        driver.element().type(ProductReviewTitleInputLocator, ReviewTitle);
        driver.element().type(ProductReviewTextInputLocator, ReviewText);
        driver.element().click(ratingLocator);
        driver.element().click(SubmitReviewBtnLocator);
    }

    public By getProductReviewTitleLocator(){
        return ProductReviewTitleLocator;
    }

    public By getProductReviewAddMsgLocator(){
        return ProductReviewAddMsgLocator;
    }

}
