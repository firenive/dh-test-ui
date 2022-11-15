package org.lanit.tests.storages;

import org.lanit.components.BaseTest;
import org.lanit.components.Wait;
import org.lanit.pageobjects.storages.PoliciesCardPage;
import org.lanit.pageobjects.storages.PoliciesListPage;
import org.lanit.pageobjects.storages.StoragesCardPage;
import org.lanit.pageobjects.base.CommonComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class StoragesServiceTests extends BaseTest {

    StoragesCardPage storageCard;
    PoliciesCardPage policyCard;
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

    @Test(dependsOnMethods = {"createStorage"})
    public void checkStorageInList() {
        components.goToList();
        String actual = findStorage().getText();
        String expected = storageCard.getValues().get("name").toString();
        Assert.assertEquals(actual, expected);

    }

    @Test(dependsOnMethods = {"checkStorageInList"})
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
    @BeforeTest
    public void beforeEachTest() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(dependsOnMethods = {"updateStorage"})
    public void createPolicy() throws IOException {
        components.goToList();
        policyCard = new PoliciesCardPage(driver);
        policyCard.section.click();
        components.createObject();
        policyCard.fillDataPolicy(storageCard.getValues().get("name").toString());
        components.saveObject();

        Assert.assertTrue(components.getNotificationText().contains("Сохранение данных выполнено успешно"));

    }

    @Test(dependsOnMethods = {"createPolicy"})
    public void checkPolicyInList() {
        components.goToList();
        PoliciesListPage page = new PoliciesListPage(driver);
        String actual = page.getPolicy(policyCard.getPolicyValues("name")).getText();
        String expected = policyCard.getPolicyValues("name");
        Assert.assertEquals(actual, expected);

    }

    @Test(dependsOnMethods = {"checkPolicyInList"})
    public void updatePolicy() {
        PoliciesListPage page = new PoliciesListPage(driver);
        new Actions(driver)
                .doubleClick(page.getPolicy(policyCard.getPolicyValues("name")))
                .perform();
        policyCard.updateData("Updated by Selenium");
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
