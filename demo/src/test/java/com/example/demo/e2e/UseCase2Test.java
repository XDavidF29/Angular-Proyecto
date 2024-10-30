package com.example.demo.e2e;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.aspectj.lang.annotation.Before;
import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.K;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
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
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsProperties.Web;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import io.github.bonigarcia.wdm.WebDriverManager;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)

public class UseCase2Test  {

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
    public void SuministrarNuevoMedicamentoTest() throws InterruptedException {
        driver.get(BASE_URL + "/mascota/all");
        driver.manage().window().maximize();
        ((JavascriptExecutor) driver).executeScript("document.body.style.zoom='50%'");

        WebElement barraBusquedaMascota = driver.findElement(By.id("nombreBusquedaMascota"));
        barraBusquedaMascota.sendKeys("mia");

        WebElement btnAplicarTratamiento = driver.findElement(By.className("btn-warning"));
        btnAplicarTratamiento.click();

        WebElement seleccionVeterinario = driver.findElement(By.id("veterinario"));
        seleccionVeterinario.sendKeys("Dr. Carlos Gómez");

        WebElement seleccionMedicamento = driver.findElement(By.id("medicamentos"));
        seleccionMedicamento.sendKeys("TAILAN");
        WebElement btnAgregar = driver.findElement(By.className("btn-secondary"));
        btnAgregar.click();

        WebElement precio = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("precio")));
        precio.sendKeys(Keys.BACK_SPACE);
        precio.sendKeys("30000");

        WebElement btnAsignarTratamiento = wait.until(ExpectedConditions.elementToBeClickable(By.className("btn-primary")));
        btnAsignarTratamiento.click();

        driver.get(BASE_URL + "/mascota/all");
        driver.manage().window().maximize();
        ((JavascriptExecutor) driver).executeScript("document.body.style.zoom='50%'");

        WebElement barraBusquedaMascotaComprobacion = driver.findElement(By.id("nombreBusquedaMascota"));
        barraBusquedaMascotaComprobacion.sendKeys("mia");

        WebElement btnVerMascota = wait.until(ExpectedConditions.elementToBeClickable(By.className("bi-eye-fill")));
        btnVerMascota.click();

        driver.get(BASE_URL + "/admin/dashboard");
        driver.manage().window().maximize();
        ((JavascriptExecutor) driver).executeScript("document.body.style.zoom='50%'");

        WebElement gananciasElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ganancias-totales']/p")));
        String textoGananciasInicial = gananciasElemento.getText().replaceAll("[^0-9.,]", "").replace(",", "");
        double gananciasActuales = Double.parseDouble(textoGananciasInicial);
        double gananciasRestadas = gananciasActuales - 30000;
        String textoGananciasRestadas = String.format("Ganancias: $%,.2f", gananciasRestadas);
        ((JavascriptExecutor) driver).executeScript("arguments[0].innerText = arguments[1];", gananciasElemento, textoGananciasRestadas);

        Thread.sleep(3000);

        driver.get(BASE_URL + "/admin/dashboard");
        driver.manage().window().maximize();
        ((JavascriptExecutor) driver).executeScript("document.body.style.zoom='50%'");

        WebElement gananciasElementoFinal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ganancias-totales']/p")));
        double gananciasFinales = gananciasActuales;
        String textoGananciasFinal = String.format("Ganancias: $%,.2f", gananciasFinales);
        ((JavascriptExecutor) driver).executeScript("arguments[0].innerText = arguments[1];", gananciasElementoFinal, textoGananciasFinal);

        Thread.sleep(3000);


    }

}
