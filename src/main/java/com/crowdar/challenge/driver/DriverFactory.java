
package com.crowdar.challenge.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    public WebDriver createDriver() {

        String navegador = System.getProperty("browser", "chrome")
                .toLowerCase();

        System.out.println("NAVEGADOR CONFIGURADO: " + navegador);

        WebDriver driver;

        switch (navegador) {

            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                driver = new ChromeDriver(chromeOptions);
                break;

            case "edge":
                System.setProperty(
                        "webdriver.edge.driver",
                        "C:\\WebDrivers\\Edge\\msedgedriver.exe"
                );

                driver = new EdgeDriver();
                break;

            default:
                throw new IllegalArgumentException(
                        "Navegador no soportado: " + navegador
                );
        }

        driver.manage().window().maximize();

        return driver;
    }

    public void quitDriver(WebDriver driver) {

        if (driver != null) {
            driver.quit();
        }
    }
}
