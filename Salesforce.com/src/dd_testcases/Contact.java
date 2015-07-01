package dd_testcases;

import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dd_core.TestCore;
import dd_util.TestUtil;

public class Contact extends TestCore{
	
	
	@BeforeTest
	public void isExecutable()
	{
		if(!TestUtil.isExecutable("Contact"))
		{
			throw new SkipException("Test Execution Set To 'N'");
		}
	}
	
	
	@DataProvider
	public Object[][] getData(){
		 
		 
		 return TestUtil.getData("Contact");
		
	}
	
	
	//Test Case to create a new contact
	@Test (priority = 1, enabled = true)
	public void doSelectContact()
	{
		//Added a comment
		log.debug("Starting TC - Select Contact");
		findElement("mnuContact").click();
		Select select = new Select(findElement("ddView"));
		select.selectByVisibleText("My Contacts");
		findElement("btnGo").click();
		findElement("btnNewContact").click();
		
		Assert.assertEquals(findElement("lblNewContact").getText(), OR.getProperty("expectedLbl"));
		
	}
	
	//dependsOnMethods = { "doSelectContact" },
	@Test (priority = 2, dataProvider="getData")
	public void createNewContact(String ddName,String txtFirstName,String txtMiddleName, String txtLastName, String txtSuffix, String txtAccName, String txtTitle, String txtEmail,String txtPhone, String txtMobile, String txtReportsTo, String txtDept, String txtFax,String txtMStreet, String txtMCity, String txtMState, String txtMZip, String txtMCounty)
	{
		System.out.println("Testcase 21");
		
		log.debug("Filling new contact form");
		Select select = new Select(findElement("ddName"));
		select.selectByVisibleText(ddName);
		findElement("txtFirstName").sendKeys(txtFirstName);
		findElement("txtMiddleName").sendKeys(txtMiddleName);
		findElement("txtSuffix").sendKeys(txtSuffix);
		findElement("txtAccName").sendKeys(txtAccName);
		findElement("txtTitle").sendKeys(txtTitle);
		findElement("txtPhone").sendKeys(txtPhone);
		findElement("txtMobile").sendKeys(txtMobile);
		findElement("txtReportsTo").sendKeys(txtReportsTo);
		findElement("txtDept").sendKeys(txtDept);
		findElement("txtFax").sendKeys(txtFax);
		findElement("txtMStreet").sendKeys(txtMStreet);
		findElement("txtMCity").sendKeys(txtMCity);
		findElement("txtMState").sendKeys(txtMState);
		findElement("txtMZip").sendKeys(txtMZip);
		findElement("txtMCounty").sendKeys(txtMCounty);
		
	}

}
