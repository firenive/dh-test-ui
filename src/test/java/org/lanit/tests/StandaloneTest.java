package org.lanit.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class StandaloneTest {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://docshouse-test.k8s.lan.lanit.ru/");
        //v-btn__content
        driver.findElement(By.cssSelector("input[type='text']")).sendKeys("user");
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys("password");

        driver.findElement(By.cssSelector(".v-btn__content")).click();

        // practise java stream
/*        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
        products.stream().filter(product -> product
                .findElement(By.cssSelector("b"))
                .getText().equals("ZaRA COAT 3"))
                .findFirst().orElse(null);*/

        WebDriverWait waitNotification = new WebDriverWait(driver, Duration.ofSeconds(5));
        // ждем пока не возникнет усплывающее уведомление
        waitNotification.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".v-snack__content")));
    }
}
