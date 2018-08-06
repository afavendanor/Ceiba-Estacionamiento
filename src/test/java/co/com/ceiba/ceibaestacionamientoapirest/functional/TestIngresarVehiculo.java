package co.com.ceiba.ceibaestacionamientoapirest.functional;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource(locations = "classpath:application-test.properties")
public class TestIngresarVehiculo {

	private static WebDriver driver;

	public TestIngresarVehiculo() {
	}

	@BeforeClass
	public static void startDriver() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\andres.avendano\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe"); // Here mention the
																									// path to run
																									// chromedriver as
																									// property
		driver = new ChromeDriver(); // Here you need to tell which driver you want to use
	}

	@AfterClass
	public static void cerrarDriver() {
		driver.quit();
	}

	@Test
	public void comprobarIngresoVehiculo() {
		driver.get("http://localhost:4200/vehiculos/form");

		String placaVehiculo = "DES534";

		WebElement placa = driver.findElement(By.name("placa"));
		placa.sendKeys(placaVehiculo);

		WebElement tipo = driver.findElement(By.name("tipo"));
		tipo.sendKeys("MOTO");

		WebElement cilindraje = driver.findElement(By.name("cilindraje"));
		cilindraje.sendKeys("1200");

		WebElement botonAceptar = driver.findElement(By.id("registrar"));
		botonAceptar.click();

		WebDriverWait wait = new WebDriverWait(driver, 3);

		driver.get("http://localhost:4200/vehiculos");

		WebElement check = driver.findElement(By.xpath("//*[contains(text(),'" + placaVehiculo + "')]"));

		wait.until(ExpectedConditions.visibilityOf(check));

		String msj = check.getText();

		assertEquals(placaVehiculo, msj);

	}

}
