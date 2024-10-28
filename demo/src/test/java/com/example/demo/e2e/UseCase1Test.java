package com.example.demo.e2e;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.aspectj.lang.annotation.Before;
import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.K;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import io.github.bonigarcia.wdm.WebDriverManager;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UseCase1Test  {

    private WebDriver driver;
    private WebDriverWait wait;

    private final String BASE_URL = "http://localhost:4200";

    @BeforeEach
    public void init() {
        
        WebDriverManager.chromedriver().setup();

        ChromeOptions chromeOptions = new ChromeOptions();

        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--disable-extensions");
        //chromeOptions.addArguments("--headless");

        this.driver = new ChromeDriver(chromeOptions);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
    

    @Test
    public void RegistroClienteMascotaTest() throws InterruptedException {
        driver.get(BASE_URL + "/veterinario/login");
        driver.manage().window().maximize();
        ((JavascriptExecutor) driver).executeScript("document.body.style.zoom='50%'");

        // Esperar a que los elementos de inicio de sesión estén presentes y visibles
        WebElement inputCedula = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cedula")));
        WebElement inputContrasena = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("contraseña")));

        // Credenciales incorrectas
        inputCedula.clear();
        inputCedula.sendKeys("V001");
        inputContrasena.clear();
        inputContrasena.sendKeys("incorrectPass");

        WebElement btnLogin = wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-vlogin")));
        btnLogin.click();

        // Segundo intento de inicio de sesión (con credenciales correctas)
        inputCedula.clear();
        inputCedula.sendKeys("V001");
        inputContrasena.clear();
        inputContrasena.sendKeys("pass123");

        wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
        btnLogin.click();

        // Espera para el próximo elemento
        WebElement regCliente = wait.until(ExpectedConditions.elementToBeClickable(By.className("opcionesRegister")));
        regCliente.click();

        // Esperar a que los campos del cliente sean visibles
        WebElement clieCedula = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cedulau")));
        WebElement clieNombre = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nombreu")));
        WebElement clieCorreo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("correou")));
        WebElement clieCelular = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("celularu")));
        
        clieCedula.sendKeys(Keys.BACK_SPACE);
        clieCedula.sendKeys("111222335");
        clieNombre.sendKeys("Rodolfo Aicardi");
        clieCorreo.sendKeys("rodolfoelrenos@gmail.com");
        clieCelular.sendKeys(Keys.BACK_SPACE);
        clieCelular.sendKeys("0987654321");
        
        WebElement btnRegistrar = wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-registrar")));
        btnRegistrar.click();
        
        // Repetir el registro de cliente para un caso con cédula diferente
        clieCedula.clear();
        clieCedula.sendKeys("111222348");
        clieNombre.sendKeys("Rodolfo Aicardi");
        clieCorreo.sendKeys("rodolfoelreno8@gmail.com");
        clieCelular.clear();
        clieCelular.sendKeys("098765");

        wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-registrar")));
        btnRegistrar.click();

        // Esperar a que el botón de agregar mascota esté presente y sea clicable
        By regMascotaLocator = By.xpath("//*[@id=\"agregarMascota\"]");
        WebElement regMascota = wait.until(ExpectedConditions.elementToBeClickable(regMascotaLocator));
        regMascota.click();

        // Esperar a que los campos de mascota sean visibles
        WebElement mascotaNombre = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nombrem")));
        WebElement mascotaRaza = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("razam")));
        WebElement mascotaEdad = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edadm")));
        WebElement mascotaPeso = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pesom")));
        WebElement mascotaEnf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("enfermedadm")));
        WebElement mascotaImagen = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("imagen")));
        WebElement btnRegistrarMascota = wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-registrarm")));

        mascotaNombre.sendKeys("Firulais");
        mascotaRaza.sendKeys("Pastor Aleman");
        mascotaEdad.sendKeys(Keys.BACK_SPACE);
        mascotaEdad.sendKeys("5");
        mascotaPeso.sendKeys(Keys.BACK_SPACE);
        mascotaPeso.sendKeys("30");
        mascotaEnf.sendKeys("Ninguna");
        mascotaImagen.sendKeys("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSg01anokV6hVN5FnDFWZMs4rzbsDdA-v3Frg&s");

        btnRegistrarMascota.click();


        WebElement nombreMascota = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nombre-mascota")));
        WebElement razaMascota = driver.findElement(By.id("raza-mascota"));
        WebElement edadMascota = driver.findElement(By.id("edad-mascota"));
        WebElement pesoMascota = driver.findElement(By.id("peso-mascota"));
        WebElement enfermedadMascota = driver.findElement(By.id("enfermedad-mascota"));
        WebElement estadoMascota = driver.findElement(By.id("estado-mascota"));

        // Comparar los valores
        assertEquals("Firulais", nombreMascota.getText());
        assertEquals("Pastor Aleman", razaMascota.getText());
        assertEquals("5", edadMascota.getText());
        assertEquals("30", pesoMascota.getText());
        assertEquals("Ninguna", enfermedadMascota.getText());
        assertEquals("Activo", estadoMascota.getText());
    }
    

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
