
package tests;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pruebas de integración API - MercadoLibre")
public class ApiTest {

    @Test
    @DisplayName("Consultar departamentos de MercadoLibre")
    @Description("Verificar que el endpoint de departamentos de MercadoLibre responda correctamente con código HTTP 200 y devuelva una lista de departamentos que contengan un nombre válido.")

    public void consultarDepartamentosMercadoLibre() {

        Response respuesta = given()
                .header("User-Agent", "Mozilla/5.0")
                .header("Accept", "application/json")
                .header("Referer", "https://www.mercadolibre.com.ar/")
                .when()
                .get("https://www.mercadolibre.com.ar/menu/departments");

        // Validar código de respuesta HTTP
        assertEquals(
                200,
                respuesta.statusCode(),
                "El endpoint no respondió con código HTTP 200"
        );

        // Obtener lista de departamentos
        List<Map<String, Object>> departamentos =
                respuesta.jsonPath().getList("departments");

        // Verificar que la lista exista y contenga elementos
        assertNotNull(
                departamentos,
                "La lista de departamentos es nula"
        );

        assertFalse(
                departamentos.isEmpty(),
                "No existen departamentos"
        );

        // Verificar que cada departamento tenga un nombre válido
        for (Map<String, Object> departamento : departamentos) {

            Object nombre = departamento.get("name");

            assertNotNull(
                    nombre,
                    "Existe un departamento sin nombre"
            );

            assertInstanceOf(
                    String.class,
                    nombre,
                    "El nombre del departamento no es un texto"
            );

            assertFalse(
                    ((String) nombre).isBlank(),
                    "Existe un departamento con nombre vacío"
            );
        }

        System.out.println(
                "Departamentos encontrados: " + departamentos.size()
        );
    }
}
