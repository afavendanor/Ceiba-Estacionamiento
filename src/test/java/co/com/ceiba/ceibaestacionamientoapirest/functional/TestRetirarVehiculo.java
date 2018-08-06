package co.com.ceiba.ceibaestacionamientoapirest.functional;

import static org.junit.Assert.assertNull;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource(locations = "classpath:application-test.properties")
public class TestRetirarVehiculo {

	private static WebDriver driver;

	public TestRetirarVehiculo() {
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
	public void facturarVehiculo() {

		String msj = null;

		driver.get("http://localhost:4200/vehiculos");

		WebElement placa = driver.findElement(By.xpath(".//table/tbody/tr[1]/td[1]"));

		String placaVehiculo = placa.getText();

		WebElement retirar = driver.findElement(By.xpath(".//table/tbody/tr[1]/td[6]/button"));

		retirar.click();

		WebElement confirmar = driver.findElement(By.className("btn-success"));

		confirmar.click();

		driver.get("http://localhost:4200/vehiculos");

		try {
			driver.findElement(By.xpath("//*[contains(text(),'" + placaVehiculo + "')]"));
		} catch (Exception e) {
			msj = null;
		}

		assertNull(msj);

	}

}
