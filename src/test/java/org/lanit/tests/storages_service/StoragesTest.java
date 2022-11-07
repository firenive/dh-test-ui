package org.lanit.tests.storages_service;

import org.lanit.components.BaseTest;
import org.lanit.components.Wait;
import org.lanit.pageobjects.StoragesCardPage;
import org.lanit.pageobjects.CommonComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class StoragesTest extends BaseTest {

    StoragesCardPage storageCard;
    CommonComponents components;

    @BeforeSuite
    public void start() throws IOException {
        try {
            launchApp();
            Wait.waitForElementAppear(driver, By.cssSelector(".mdi-menu"), Duration.ofMinutes(5));
            components = new CommonComponents(driver);
            components.changeLocale();
        } catch (Exception e) {
            e.printStackTrace();
            driver.quit();
        }
    }

    @AfterSuite
    public void finish() {
        driver.quit();
    }

    @Test
    public void createStorage() throws IOException {
        components.createObject();
        storageCard = new StoragesCardPage(driver);
        storageCard.fillDataStorage();
        String code = storageCard.getValues().get("code").toString();
        components.saveObject();
        Assert.assertNotNull(code);
        Assert.assertTrue(components.getNotificationText().contains("Сохранение данных выполнено успешно"));

    }

    @Test (dependsOnMethods = {"createStorage"})
    public void checkStorageInList() {
        components.goToList();
        String actual = findStorage().getText();
        String expected = storageCard.getValues().get("name").toString();
        Assert.assertEquals(actual, expected);

    }

    @Test (dependsOnMethods = {"createStorage", "checkStorageInList"} )
    public void updateStorage() {
        WebElement storage = findStorage();
        new Actions(driver)
                .doubleClick(storage)
                .perform();
        String newDesc = "Updated by Selenium";
        storageCard.updateData(newDesc);
        components.saveObject();
        Assert.assertTrue(components.getNotificationText().contains("Сохранение данных выполнено успешно"));

    }

//    @Test (dependsOnMethods = {"createStorage", "updateStorage", "checkStorageInList"})
//    public void deleteStorage() {
//        components.deleteObject();
//        // System.out.println(components.getNotificationText());
//        Assert.assertNull(findStorage());
//    }


    public WebElement findStorage() {
        List<WebElement> elements = driver.findElements(By.cssSelector(".storage-panel__name"));
        for (WebElement element : elements) {
            if (element.getText().contains(storageCard.getValues().get("name").toString())) {
                return element;
            }
        }
        return null;
    }
}
