
package tests;

import com.crowdar.challenge.driver.DriverFactory;
import com.crowdar.challenge.utils.ScreenshotUtil;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FalloIntencionalDemo {

    @Test
    @DisplayName("Demostración de fallo intencional")
    @Description("Provocar un fallo controlado para comprobar que el framework capture la pantalla y adjunte la evidencia al reporte Allure.")
    public void pruebaFalloIntencional() {

        DriverFactory factory = new DriverFactory();
        WebDriver driver = factory.createDriver();

        try {
            driver.get("https://www.saucedemo.com/");

            driver.findElement(By.id("user-name"))
                    .sendKeys("standard_user");

            driver.findElement(By.id("password"))
                    .sendKeys("secret_sauce");

            driver.findElement(By.id("login-button"))
                    .click();

            // Fallo intencional para demostrar la captura
            assertTrue(false,
                    "Fallo intencional para validar screenshot");

        } catch (Throwable e) {

            ScreenshotUtil.capturar(driver, "fallo_intencional");
            throw e;

        } finally {
            factory.quitDriver(driver);
        }
    }
}
