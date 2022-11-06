package org.lanit.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthPage {
    WebDriver driver;

    public AuthPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "input[type='text']")
    WebElement userInput;

    @FindBy(css = "input[type='password']")
    WebElement passwordInput;

    @FindBy(css = ".v-btn__content")
    WebElement loginButton;
    @FindBy(css = "#details-button")
    WebElement details;
    @FindBy(css = "#proceed-link")
    WebElement proceed;

    public void loginApp(String userName, String userPassword) {
        goTo();
//        details.click();
//        proceed.click();
        userInput.sendKeys(userName);
        passwordInput.sendKeys(userPassword);
        loginButton.click();

    }

    public void goTo() {
        driver.get("https://docshouse-test.k8s.lan.lanit.ru/auth");
    }
}
