package org.lanit.tests.storages_service;

import org.lanit.components.BaseTest;
import org.lanit.pageobjects.CommonComponents;
import org.lanit.pageobjects.PoliciesCardPage;
import org.lanit.pageobjects.StoragesCardPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class PoliciesTest extends BaseTest {
    PoliciesCardPage policyCard;
    CommonComponents components;
    @Test
    public void createPolicy() throws IOException {
        components.goToList();
        policyCard = new PoliciesCardPage(driver);
        policyCard.section.click();
        components.createObject();
        StoragesCardPage storage = new StoragesCardPage(driver);
        policyCard.fillDataPolicy(storage.getValues().get("name").toString());
        components.saveObject();

        Assert.assertTrue(components.getNotificationText().contains("Сохранение данных выполнено успешно"));

    }

//    @Test (dependsOnMethods = {"createStorage"})
//    public void checkStorageInList() {
//        components.goToList();
//        String actual = findStorage().getText();
//        String expected = card.getValues().get("name").toString();
//        Assert.assertEquals(actual, expected);
//
//    }
//
//    @Test (dependsOnMethods = {"createStorage", "checkStorageInList"} )
//    public void updateStorage() {
//        WebElement storage = findStorage();
//        new Actions(driver)
//                .doubleClick(storage)
//                .perform();
//        String newDesc = "Updated by Selenium";
//        card.updateData(newDesc);
//        components.saveObject();
//        Assert.assertTrue(components.getNotificationText().contains("Сохранение данных выполнено успешно"));
//
//    }
//
//    @Test (dependsOnMethods = {"createStorage", "updateStorage", "checkStorageInList"})
//    public void deleteStorage() {
//        components.deleteObject();
//        // System.out.println(components.getNotificationText());
//        Assert.assertNull(findStorage());
//    }
//
//
//    public WebElement findStorage() {
//        List<WebElement> elements = driver.findElements(By.cssSelector(".storage-panel__name"));
//        for (WebElement element : elements) {
//            if (element.getText().contains(card.getValues().get("name").toString())) {
//                return element;
//            }
//        }
//        return null;
//    }
}
