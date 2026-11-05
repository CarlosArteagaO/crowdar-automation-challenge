
# QA Automation Challenge – Crowdar

## 1. Descripción del proyecto

Framework de automatización de pruebas desarrollado como parte del desafío técnico de Crowdar para validar funcionalidades de inicio de sesión, gestión del carrito de compras e integración mediante API REST.

El proyecto utiliza Java, Selenium WebDriver, JUnit 5 y Rest Assured. Además, incorpora Allure para la generación de reportes y evidencia de ejecución.

## 2. Tecnologías utilizadas

| Tecnología | Utilización |
|---|---|
| Java 21 | Lenguaje de programación |
| Selenium WebDriver 4.27.0 | Automatización web |
| JUnit 5.11.4 | Ejecución y organización de pruebas |
| Rest Assured 5.5.0 | Automatización de pruebas API |
| Maven | Gestión de dependencias y ejecución |
| Allure 2.35.1 | Reportería de pruebas |
| IntelliJ IDEA | Entorno de desarrollo |

## 3. Funcionalidades automatizadas

### 3.1 Inicio de sesión – SauceDemo

Se implementaron dos escenarios de prueba:

- **Login exitoso:** verifica el acceso a la aplicación utilizando credenciales válidas.
- **Login fallido:** comprueba que el sistema rechace credenciales incorrectas y muestre el mensaje de error correspondiente.

### 3.2 Gestión del carrito de compras – SauceDemo

Se implementaron dos escenarios de prueba:

- **Agregar un producto:** verifica que un producto seleccionado se incorpore correctamente al carrito.
- **Agregar múltiples productos:** comprueba que dos productos se agreguen correctamente y permanezcan visibles en el carrito.

### 3.3 Integración API – MercadoLibre Argentina

Se implementó un escenario de prueba para:

- Consultar el endpoint mediante una petición HTTP GET.
- Validar que la respuesta tenga código HTTP 200.
- Comprobar que la lista de departamentos no esté vacía.
- Verificar que cada departamento contenga un nombre válido.

## 4. Estructura del proyecto

```text
crowdar-automation-challenge/
│
├── screenshots/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/crowdar/challenge/
│   │           ├── driver/
│   │           │   └── DriverFactory.java
│   │           └── utils/
│   │               └── ScreenshotUtil.java
│   │
│   └── test/
│       ├── java/
│       │   └── tests/
│       │       ├── LoginTest.java
│       │       ├── CartTest.java
│       │       ├── ApiTest.java
│       │       └── FalloIntencionalDemo.java
│       │
│       └── resources/
│           └── allure.properties
│
├── .gitignore
├── pom.xml
└── README.md
```

### Organización

- `driver`: configuración y creación de los navegadores.
- `utils`: utilidades reutilizables para capturas de pantalla.
- `tests`: casos automatizados de interfaz web e integración API.
- `resources`: configuración de Allure.
- `screenshots`: almacenamiento de evidencias de ejecución.

## 5. Evidencia y reportes

El framework incorpora:

- Capturas de pantalla ante fallos de ejecución.
- Adjuntos de evidencia en los reportes Allure.
- Reportes HTML con resultados detallados.
- Demostración independiente de un fallo intencional para validar el mecanismo de captura de evidencias.

Las capturas de pantalla se almacenan en el directorio `screenshots/`.

## 6. Resultados de ejecución

### 6.1 Suite funcional

| Funcionalidad | Casos ejecutados | Aprobados | Fallidos |
|---|---:|---:|---:|
| Inicio de sesión | 2 | 2 | 0 |
| Gestión del carrito | 2 | 2 | 0 |
| Integración API | 1 | 1 | 0 |
| **Total** | **5** | **5** | **0** |

**Resultado global: 100% de pruebas aprobadas.**

Las pruebas de interfaz web fueron ejecutadas y validadas en Google Chrome y Microsoft Edge.

### 6.2 Demostración de fallo intencional

Se ejecutó un escenario independiente con un fallo provocado deliberadamente para comprobar el mecanismo de captura de pantalla y su integración con Allure.

| Indicador | Resultado |
|---|---|
| Casos ejecutados | 1 |
| Fallidos intencionalmente | 1 |
| Captura de pantalla | Generada |
| Adjunto en Allure | Validado |

## 7. Requisitos previos

- Java JDK 21.
- Maven.
- Google Chrome para ejecutar las pruebas en Chrome.
- Microsoft Edge y su correspondiente WebDriver para ejecutar las pruebas en Edge.

**Configuración de Edge:** el proyecto utiliza actualmente el WebDriver ubicado en:

`C:\WebDrivers\Edge\msedgedriver.exe`

Si se utiliza otra ubicación, será necesario actualizar la ruta en `DriverFactory.java`.

## 8. Ejecución del proyecto

### 8.1 Clonar el repositorio

```bash
git git clone https://github.com/CarlosArteagaO/crowdar-automation-challenge.git
```

### 8.2 Ingresar al directorio

```bash
cd crowdar-automation-challenge
```

### 8.3 Ejecutar las pruebas funcionales en Chrome

```bash
mvn clean test -Dtest=LoginTest,CartTest,ApiTest
```

### 8.4 Ejecutar las pruebas funcionales en Edge

```bash
mvn clean test -Dtest=LoginTest,CartTest,ApiTest -Dbrowser=edge
```

### 8.5 Ejecutar la demostración de fallo intencional

```bash
mvn clean test -Dtest=FalloIntencionalDemo
```

### 8.6 Generar y visualizar el reporte Allure

```bash
mvn allure:serve
```

**Nota:** La prueba de fallo intencional se ejecuta de manera independiente y está excluida de la ejecución funcional estándar.

El comando `mvn clean` elimina los resultados de ejecuciones anteriores antes de ejecutar las pruebas. Por ello, se recomienda ejecutar `mvn allure:serve` después de la ejecución cuyos resultados se desean consultar.

## 9. Aplicaciones y servicios bajo prueba

**Aplicación web:**

https://www.saucedemo.com/

**Endpoint API:**

https://www.mercadolibre.com.ar/menu/departments

## 10. Autor

Desarrollado como parte del QA Automation Challenge de Crowdar.
