package org.lanit.pageobjects.storages;

import org.lanit.pageobjects.base.CommonComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PoliciesCardPage {

    WebDriver driver;
    Map<String, String> policyValues;

    public PoliciesCardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//label[contains(text(),'Наименование')]/following::input[1]")
    WebElement policyName;

    @FindBy (xpath = "//label[contains(text(),'Код')]/following::input[1]")
    WebElement policyCode;

    // Период удержания контента, мин

    @FindBy (xpath = "//label[contains(text(),'Период удержания контента, мин')]/following::input[1]")
    WebElement policyRetentionPeriod;

    @FindBy (xpath = "//label[contains(text(),'Описание')]/following::textarea[1]")
    WebElement policyDescription;

    @FindBy (xpath = "//span[contains(text(),'Выбрать')]")
    WebElement selectButton;

    @FindBy (xpath = "//div[contains(text(),'Политики размещения')]")
    public WebElement section;

    public void fillDataPolicy(String storageName) {
        Random randomInt = new Random();
        int limit = 1000;
        policyValues = new HashMap<>();
        policyValues.put("name", "Policy_ " + randomInt.nextInt(limit));
        policyValues.put("code", String.valueOf(randomInt.nextInt(limit)));
        policyValues.put("description", "Created by Selenium");
        policyValues.put("retentionPeriod", String.valueOf(0));
        policyName.sendKeys(policyValues.get("name"));
        policyCode.sendKeys(policyValues.get("code"));
        policyDescription.sendKeys(policyValues.get("description"));
        policyRetentionPeriod.sendKeys(policyValues.get("retentionPeriod"));
        linkStorage(storageName);
    }

    public void linkStorage(String storageName) {
        CommonComponents components = new CommonComponents(driver);
        components.createObject();
        driver.findElement(By.xpath("//td[contains(text(),'" + storageName + "')]/preceding::td[1]/div"))
                .click();
        selectButton.click();
    }

    public String getPolicyValues(String key) {
        return policyValues.get(key);
    }

    public void updateData(String description) {
        Random randomInt = new Random();
        int limit = 1000;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        policyValues.put("description", description);
        policyDescription.clear();
        policyDescription.sendKeys(policyValues.get("description"));
    }



}
