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
    Map<String, String> storageValues;
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
        storageValues = new HashMap<>();
        storageValues.put("name", "Storage_ " + randomInt.nextInt(limit));
        storageValues.put("code", String.valueOf(randomInt.nextInt(limit)));
        storageValues.put("description", "Created by Selenium");
        storageValues.put("uri", Utilities.readProperties("mongo"));
        storageName.sendKeys(storageValues.get("name"));
        storageCode.sendKeys(storageValues.get("code"));
        storageDescription.sendKeys(storageValues.get("description"));
        storageUri.sendKeys(storageValues.get("uri"));
    }
    public Map getValues() {
        return storageValues;
    }

    public void updateData(String description) {
        Random randomInt = new Random();
        int limit = 1000;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        storageValues.put("description", description);
        storageDescription.clear();
        storageDescription.sendKeys(storageValues.get("description"));
    }

}
