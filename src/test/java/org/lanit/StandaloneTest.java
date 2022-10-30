package org.lanit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

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
    }
}
