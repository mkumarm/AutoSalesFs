package dd_testcases;

import java.util.Iterator;
import java.util.Set;

import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import dd_core.TestCore;
import dd_util.TestUtil;

public class selectRole extends TestCore{
	
	
	@BeforeTest
	public void isExecutable()
	{
		if(!TestUtil.isExecutable("selectRole"))
		{
			throw new SkipException("Test Execution Set To 'N'");
		}
	}
	
	
	@Test (priority = 1, enabled = true)
	public void selectOrgRole()
	{
		log.debug("Selecting IT Role");
		if(findElement("role_it")!=null)
		{
			findElement("role_it").click();
		}
		else
			log.debug("Org Role Selection Page not found.. Move On..");	
		
		
	}
	
	@Test (priority = 2, enabled = true)
	public void closePopup() throws InterruptedException
	{
		log.debug("Taking a powernap for few sec");
		Thread.sleep(30000L);
		TestUtil.closePopup();
	}
	

}
