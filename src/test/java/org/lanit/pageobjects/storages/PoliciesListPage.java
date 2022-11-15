package org.lanit.pageobjects.storages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PoliciesListPage {

    WebDriver driver;

    public PoliciesListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy
    WebElement policy;

    public WebElement getPolicy(String name) {
        return driver.findElement(By.xpath("//td[normalize-space()='" + name + "']"));
    }
}
