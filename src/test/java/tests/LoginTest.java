
package tests;

import com.crowdar.challenge.driver.DriverFactory;
import com.crowdar.challenge.utils.ScreenshotUtil;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Pruebas de inicio de sesión")
public class LoginTest {

    @Test
    @DisplayName("Inicio de sesión exitoso")
    @Description("Verificar que un usuario registrado pueda iniciar sesión con credenciales válidas y acceder al catálogo de productos.")
    public void loginExitoso() {

        DriverFactory factory = new DriverFactory();
        WebDriver driver = factory.createDriver();

        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );

        try {
            driver.get("https://www.saucedemo.com/");

            // Ingresar credenciales válidas
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.id("user-name")
            )).sendKeys("standard_user");

            driver.findElement(By.id("password"))
                    .sendKeys("secret_sauce");

            driver.findElement(By.id("login-button"))
                    .click();

            // Validar acceso al catálogo
            wait.until(ExpectedConditions.urlContains("inventory.html"));

            assertTrue(
                    driver.getCurrentUrl().contains("inventory.html"),
                    "El login no fue exitoso"
            );

        } catch (Throwable e) {

            ScreenshotUtil.capturar(driver, "login_exitoso_error");
            throw e;

        } finally {
            factory.quitDriver(driver);
        }
    }

    @Test
    @DisplayName("Inicio de sesión con credenciales incorrectas")
    @Description("Verificar que el sistema muestre un mensaje de error cuando se ingresan credenciales inválidas.")
    public void loginCredencialesIncorrectas() {

        DriverFactory factory = new DriverFactory();
        WebDriver driver = factory.createDriver();

        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );

        try {
            driver.get("https://www.saucedemo.com/");

            // Ingresar credenciales incorrectas
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.id("user-name")
            )).sendKeys("usuario_incorrecto");

            driver.findElement(By.id("password"))
                    .sendKeys("clave_incorrecta");

            driver.findElement(By.id("login-button"))
                    .click();

            // Esperar mensaje de error
            String mensajeError = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.cssSelector("[data-test='error']")
                    )
            ).getText();

            // Validar mensaje
            assertTrue(
                    !mensajeError.isEmpty(),
                    "El mensaje de error está vacío"
            );

        } catch (Throwable e) {

            ScreenshotUtil.capturar(driver, "login_credenciales_error");
            throw e;

        } finally {
            factory.quitDriver(driver);
        }
    }
}
