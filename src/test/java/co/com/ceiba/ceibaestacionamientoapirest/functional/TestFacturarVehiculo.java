package co.com.ceiba.ceibaestacionamientoapirest.functional;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource(locations = "classpath:application-test.properties")
public class TestFacturarVehiculo {

	private static WebDriver driver;

	public TestFacturarVehiculo() {
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

		String placaVehiculo = "FRT75A";

		driver.get("http://localhost:4200/facturas/1");

		WebElement placaFactura = driver.findElement(By.xpath(".//table/tbody/tr[1]/td"));

		String pFactura = placaFactura.getText();

		assertEquals(placaVehiculo, pFactura);

	}

}
