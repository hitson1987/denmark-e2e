package in.til.cms.denmark_e2e;

import org.openqa.selenium.By;


import org.testng.annotations.Test;



import in.til.cms.driver.BaseClass;

public class NewTest extends BaseClass {

	@Test(priority =1)
	public void email() {
		
		
		driver.get("https://www.facebook.com");

		driver.findElement(By.id("email1")).sendKeys("adsnbhkj");
		
		
	}
	@Test(priority =2)
	public void pwd() {
		
		driver.findElement(By.id("pass")).sendKeys("adsnbhkj");

	}
}
