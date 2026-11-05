
package tests;

import com.crowdar.challenge.driver.DriverFactory;
import com.crowdar.challenge.utils.ScreenshotUtil;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Pruebas de gestión del carrito de compras")
public class CartTest {


    @Test
    @DisplayName("Agregar un producto al carrito")
    @Description("Verificar que el usuario pueda agregar Sauce Labs Backpack al carrito y visualizar correctamente el producto seleccionado.")
    public void agregarUnProductoAlCarrito() {

        DriverFactory factory = new DriverFactory();
        WebDriver driver = factory.createDriver();

        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );

        try {
            // Ingresar a SauceDemo
            driver.get("https://www.saucedemo.com/");

            // Iniciar sesión
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.id("user-name")
            )).sendKeys("standard_user");

            driver.findElement(By.id("password"))
                    .sendKeys("secret_sauce");

            driver.findElement(By.id("login-button"))
                    .click();

            // Esperar a que cargue el catálogo
            wait.until(ExpectedConditions.urlContains("inventory.html"));

            // Agregar mochila al carrito
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("add-to-cart-sauce-labs-backpack")
            )).click();

            // Abrir carrito
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.className("shopping_cart_link")
            )).click();

            // Esperar a que cargue la página del carrito
            wait.until(ExpectedConditions.urlContains("cart.html"));

            // Localizar el nombre del producto dentro del carrito
            String nombreProducto = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.cssSelector(".cart_item .inventory_item_name")
                    )
            ).getText();

            // Validar producto
            assertEquals(
                    "Sauce Labs Backpack",
                    nombreProducto,
                    "El producto no aparece en el carrito"
            );

        } catch (Throwable e) {

            ScreenshotUtil.capturar(
                    driver,
                    "carrito_un_producto_error"
            );

            throw e;

        } finally {
            factory.quitDriver(driver);
        }
    }



    @Test
    @DisplayName("Agregar dos productos al carrito")
    @Description("Verificar que el usuario pueda agregar Sauce Labs Backpack y Sauce Labs Bike Light y que ambos productos aparezcan en el carrito.")
    public void agregarDosProductosAlCarrito() {

        DriverFactory factory = new DriverFactory();
        WebDriver driver = factory.createDriver();

        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );

        try {
            driver.get("https://www.saucedemo.com/");

            // Iniciar sesión
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.id("user-name")
            )).sendKeys("standard_user");

            driver.findElement(By.id("password"))
                    .sendKeys("secret_sauce");

            driver.findElement(By.id("login-button"))
                    .click();

            // Esperar a que cargue el catálogo
            wait.until(ExpectedConditions.urlContains("inventory.html"));

            // Esperar y agregar mochila
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("add-to-cart-sauce-labs-backpack")
            )).click();

            // Esperar y agregar luz de bicicleta
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("add-to-cart-sauce-labs-bike-light")
            )).click();

            // Abrir carrito
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.className("shopping_cart_link")
            )).click();

            // Esperar contenido del carrito
            String contenidoCarrito = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.className("cart_list")
                    )
            ).getText();

            // Validar presencia de ambos productos
            assertTrue(
                    contenidoCarrito.contains("Sauce Labs Backpack"),
                    "No se encontró la mochila en el carrito"
            );

            assertTrue(
                    contenidoCarrito.contains("Sauce Labs Bike Light"),
                    "No se encontró la luz de bicicleta en el carrito"
            );

        } catch (Throwable e) {

            ScreenshotUtil.capturar(
                    driver,
                    "carrito_dos_productos_error"
            );

            throw e;

        } finally {
            factory.quitDriver(driver);
        }
    }

}
