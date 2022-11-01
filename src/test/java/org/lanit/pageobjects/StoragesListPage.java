package org.lanit.pageobjects;

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

    @FindBy(css = ".dh-workspace-area-actions div:nth-of-type(1) > div")
    WebElement createButton;

    @FindBy(css = ".storage-panel")
    WebElement elementOfList;

    public void goTo() {
        driver.get("http://docshouse-test.k8s.lan.lanit.ru/storages-service/storages");
    }
}
