package dd_util;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;

import dd_core.TestCore;

public class TestUtil extends TestCore{
	
	
	public static String mailscreenshotpath;
	
	public static void captureScreenshot(String name) {
		
		 Calendar cal = new GregorianCalendar();
		  int month = cal.get(Calendar.MONTH); //4
		  int year = cal.get(Calendar.YEAR); //2013
		  int sec =cal.get(Calendar.SECOND);
		  int min =cal.get(Calendar.MINUTE);
		  int date = cal.get(Calendar.DATE);
		  int day =cal.get(Calendar.HOUR_OF_DAY);
		
		
	
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    try {
	    	mailscreenshotpath = System.getProperty("user.dir")+"\\screenshots\\"+name+"_"+year+"_"+date+"_"+(month+1)+"_"+day+"_"+min+"_" +sec+".jpeg";
			FileUtils.copyFile(scrFile, new File(mailscreenshotpath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	   
	}

	    
	//Validating if the Run mode in Excel file is set as Yes or No
	public static boolean isExecutable(String tcid){
			
			for(int rowNum=2; rowNum<=excel.getRowCount("TestSuite"); rowNum++){
				if(excel.getCellData("TestSuite","TCID", rowNum).equals(tcid)){
					if(excel.getCellData("TestSuite","Runmode", rowNum).equalsIgnoreCase("Y")){
						{
							log.debug("Test Case "+tcid+ " : Y");
							return true;
						}
					}
					else{
						return false;
					}
						
				}
			}
			return false;
		}
	
		
	// This method is used for providing parameterized inputs to various method
	@DataProvider
	public static Object[][] getData(String sheetName){
		 
		 int rows = excel.getRowCount(sheetName);
		 int cols = excel.getColumnCount(sheetName);
		 
		 Object[][] data = new Object[rows-1][cols];
		
		 for(int rowNum = 2 ; rowNum <= rows ; rowNum++){ 
				
				for(int colNum=0 ; colNum< cols; colNum++){
					data[rowNum-2][colNum]=excel.getCellData(sheetName, colNum, rowNum); 
				}
			}
		
		 return data;
	}

	
	public static void closePopup() throws InterruptedException
	{
			
		//Getting all windows handle
		log.debug("Getting all windows handle");
		Set<String> WinIDs = driver.getWindowHandles();
		Iterator<String> iterator = WinIDs.iterator();
		
		try
		{
			String MainWindow = iterator.next();
			String PopupWindow = iterator.next();
		
			driver.switchTo().window(PopupWindow);
			driver.close();
			driver.switchTo().window(MainWindow);
		}
		
		catch(Exception e)
		{
			log.debug(e.getMessage());
		}
	}
	
		
		
	
}

