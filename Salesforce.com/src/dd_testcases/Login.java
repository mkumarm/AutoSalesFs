package dd_testcases;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;

import dd_core.*;
import dd_util.*;

public class Login extends TestCore{
	
	@BeforeTest
	public void isExecutable()
	{
		if(!TestUtil.isExecutable("Login"))
		{
			throw new SkipException("Test Execution Set To 'N'");
		}
	}
	
	@Test(dataProvider="getData", priority = 0, enabled = true)
	public void doLogin(String Usrname, String Passwd) throws InterruptedException
	{
		//driver.get(Config.getProperty("testsiteurl"));
		//findElement("btnLogin").click();
		
		log.debug("Logging for the user " + Usrname);
		findElement("username").sendKeys(Usrname);
		findElement("password").sendKeys(Passwd);
		findElement("signin").click();
		
		if(findElement("error") != null)
		{
			String errorTxt = findElement("error").getText();
			String expected = OR.getProperty("errormsg");
			Assert.assertEquals(errorTxt, expected);
		}
		
		//Thread.sleep(5000);
	}
	
	@DataProvider
	public Object[][] getData(){
		 
		 
		 return TestUtil.getData("Login");
		
	}

}
