package org.lanit.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Utilities {


    public static String readProperties(String property) throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("src/test/java/org/lanit/resources/globalData.properties");
        prop.load(fis);
        return prop.getProperty(property);
    }





}
