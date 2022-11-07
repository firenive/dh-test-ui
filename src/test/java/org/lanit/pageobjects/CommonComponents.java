package org.lanit.pageobjects;

import org.lanit.components.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class CommonComponents {

    WebDriver driver;

    public CommonComponents(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".mdi-content-save-outline")
     private WebElement saveButton;

    @FindBy (css = ".mdi-delete-outline")
    private WebElement deleteButton;
    @FindBy(css = ".v-icon.notranslate.mdi.mdi-plus.theme--light")
    private WebElement createButton;

    @FindBy(css = ".v-snack__content")
    WebElement popup;

    @FindBy(css = ".v-btn.error--text")
    WebElement confirm;

    @FindBy(css = ".app-bar__tab-item__title__text.dh-text-subtitle2")
    public WebElement list;

    public  void saveObject() {
        saveButton.click();
    }

    public  void deleteObject() {
        deleteButton.click();
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", confirm);

    }

    public  void createObject() {
        createButton.click();
    }

    public void changeLocale() {
        driver.findElement(By.cssSelector(".mdi-menu")).click();
        driver.findElement(By.xpath("//span[normalize-space()='en']")).click();

    }

    public String getNotificationText() {
        Wait.waitForElementAppear(driver, By.cssSelector(".v-snack__content"), Duration.ofSeconds(20));
        return popup.getText();
    }

    public String getNotificationText(String message) {
        Wait.waitForElementAppear(driver,
                By.xpath("//div[contains(text(),'" + message + "')]"), Duration.ofSeconds(20));
        return popup.getText();
    }
    public void goToList() {
        list.click();
    }


}
