package org.lanit.pageobjects;

import org.lanit.components.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class StoragesCardPage {
    WebDriver driver;
    Map<String, String> values;
    public StoragesCardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy (xpath = "//label[contains(text(),'Наименование хранилища')]/following::input[1]")
     WebElement storageName;

    @FindBy (xpath = "//label[contains(text(),'Код хранилища')]/following::input[1]")
    WebElement storageCode;

    @FindBy (xpath = "//label[contains(text(),'Описание хранилища')]/following::textarea[1]")
    public WebElement storageDescription;

    @FindBy (xpath = "//label[normalize-space()='URI']/following::input[1]")
    WebElement storageUri;

    public void fillDataStorage() throws IOException {
        Random randomInt = new Random();
        int limit = 1000;
        values = new HashMap<>();
        values.put("name", "Storage_ " + randomInt.nextInt(limit));
        values.put("code", String.valueOf(randomInt.nextInt(limit)));
        values.put("description", "Created by Selenium");
        values.put("uri", Utilities.readProperties("mongo"));
        storageName.sendKeys(values.get("name"));
        storageCode.sendKeys(values.get("code"));
        storageDescription.sendKeys(values.get("description"));
        storageUri.sendKeys(values.get("uri"));
    }
    public Map getValues() {
        return values;
    }

    public void updateData(String description) {
        Random randomInt = new Random();
        int limit = 1000;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        values.put("description", description);
        storageDescription.clear();
        storageDescription.sendKeys(values.get("description"));
    }

}
