package in.til.cms.driver;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.AfterTest;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;


import io.github.bonigarcia.wdm.WebDriverManager;

@Listeners({ in.til.cms.driver.TestNGCustomReport.class,
	in.til.cms.propfile.ExtentReporterNG.class,	
	in.til.cms.propfile.TestInvocation.class})



public class BaseClass {


	public static File directory = new File(".");
	public static ChromeDriver driver;
	public static Properties CONFIGOBJ = null;

	@BeforeTest
	public static void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();

	}

	@BeforeTest
	public void initConfigurations() {

		if (CONFIGOBJ == null) {

			CONFIGOBJ = new Properties();
			try {
				FileInputStream fs = new FileInputStream(System.getProperty("user.dir")
						+ "//src//test//java//in//til//cms//propfile//config.properties");
				CONFIGOBJ.load(fs);

			} catch (Exception e) {
			}
		}
	}


	

	@AfterTest
	public void endReport() {
		driver.quit();
		
	}

}
