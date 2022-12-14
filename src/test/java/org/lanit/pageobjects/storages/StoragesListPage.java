package org.lanit.pageobjects.storages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StoragesListPage {
    WebDriver driver;

    public StoragesListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".storage-panel")
    WebElement elementOfList;

    public void goTo() {
        driver.get("http://docshouse-test.k8s.lan.lanit.ru/storages-service/storages");
    }
}
