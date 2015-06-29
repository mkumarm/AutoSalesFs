package dd_testcases;
import java.util.List;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;
import org.testng.SkipException;
import org.testng.annotations.*;

import com.thoughtworks.selenium.webdriven.commands.CaptureScreenshotToString;

import dd_core.TestCore;
import dd_util.TestUtil;

public class Signup extends TestCore{

	@BeforeTest
	public void isExecutable()
	{
		if(!TestUtil.isExecutable("Signup"))
		{
			throw new SkipException("Test Execution Set To 'N'");
		}
	}
	
	
	@Test(dataProvider="getData", enabled = false)
	public void doSignup(String firstname, String lastname, String jobtitle, String email, String phone, String company, String tncondition)
	{
		if(findElement("freertial")!=null)
			findElement("freertial").click();
		
		findElement("firstname").clear();
		findElement("firstname").sendKeys(firstname);
		
		findElement("lastname").clear();
		findElement("lastname").sendKeys(lastname);
		
		//Logic to select the job title form the dropdown
		driver.findElement(By.xpath(".//*[@id='form-container']/ul/li[10]/div/div[2]/a/span[2]")).click();
		Select job_titles = new Select(driver.findElement(By.className("selectBox")));
		List<WebElement> options = job_titles.getOptions();
		int i = 1;
		
		for(WebElement option : options)
		{
			if(option.getAttribute("title").trim().equalsIgnoreCase(jobtitle))
			{
				driver.findElement(By.xpath("html/body/ul/li["+i+"]/a")).click();;
				break;
			}
			i++;
		}
		//End of select logic
		
		findElement("email").clear();
		findElement("email").sendKeys(email);
		
		findElement("phone").clear();
		findElement("phone").sendKeys(phone);
		
		findElement("company").clear();
		findElement("company").sendKeys(company);
		
		if(tncondition.equalsIgnoreCase("Y"))
		{
			if(!findElement("tncondition").isSelected())
				findElement("tncondition").click();
			
		}
		else
		{
			if(findElement("tncondition").isSelected())
				findElement("tncondition").click();
		}
		
		TestUtil.captureScreenshot("Signup_");
	}
	
	
	@DataProvider
	public Object[][] getData(){
		 
		 
		 return TestUtil.getData("Signup");
		
	}
	
	@AfterTest
	public void ResetPage()
	{
		System.out.println("Test Completed.. So Resetting..");
		//driver.get("https://salesforce.com");
	}
}
