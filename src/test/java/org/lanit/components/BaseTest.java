package org.lanit.components;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.lanit.pageobjects.base.AuthPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.time.Duration;

public class BaseTest {
    public WebDriver driver;

    // инициализация драйвера. в зависимости от значения в globalData.properties запускается нужный браузер
    public WebDriver  initializeDriver() throws IOException {


        if (Utilities.readProperties("browser").equals("chrome")) {
            ChromeOptions handlingSSL = new ChromeOptions();
            handlingSSL.setAcceptInsecureCerts(true);
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(handlingSSL);
        } else if (Utilities.readProperties("browser").equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }

    public void launchApp() throws IOException {
        driver = initializeDriver();
        AuthPage auth = new AuthPage(driver);
        auth.loginApp("user", "password");
    }
}
