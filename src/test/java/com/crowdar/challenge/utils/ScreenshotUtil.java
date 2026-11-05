
package com.crowdar.challenge.utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ScreenshotUtil {

    public static void capturar(WebDriver driver, String nombreArchivo) {

        if (driver == null) {
            System.out.println(
                    "No se puede capturar la pantalla: WebDriver es nulo"
            );
            return;
        }

        try {
            // Tomar captura de pantalla
            Path captura = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE)
                    .toPath();

            // Crear carpeta si no existe
            Path carpeta = Paths.get("screenshots");
            Files.createDirectories(carpeta);

            // Guardar captura en el proyecto
            Path destino = carpeta.resolve(nombreArchivo + ".png");

            Files.copy(
                    captura,
                    destino,
                    StandardCopyOption.REPLACE_EXISTING
            );

            System.out.println(
                    "Captura guardada: " + destino.toAbsolutePath()
            );

            // Adjuntar captura al reporte Allure
            byte[] imagen = Files.readAllBytes(destino);

            Allure.addAttachment(
                    "Captura de pantalla - " + nombreArchivo,
                    "image/png",
                    new ByteArrayInputStream(imagen),
                    ".png"
            );

            System.out.println("Captura adjuntada a Allure");

        } catch (IOException | ClassCastException e) {

            System.out.println(
                    "No se pudo guardar o adjuntar la captura: "
                            + e.getMessage()
            );
        }
    }
}
