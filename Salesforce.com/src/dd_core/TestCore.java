package dd_core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import dd_util.DbManager;
import dd_util.ExcelReader;
import dd_util.TestConfig;
import dd_util.TestUtil;

public class TestCore {
	
	/*
	 * Excel files
	 * Properties files
	 * Log files
	 * WebDriver browsers, testsiteurl,
	 * timeouts
	 * database connect
	 * mail conn
	 */
	
	public static WebDriver driver=null;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir")+"//src//dd_properties//testdata.xlsx");
	public static Properties OR;
	public static Properties Config;
	
	
	
	@BeforeSuite
	public static void setUp() throws IOException, ClassNotFoundException, SQLException{
		
		
		if(driver ==null){
			
			//Working On Properties initialization
			OR = new Properties();
			Config = new Properties();
			
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\dd_properties\\OR.Properties");
			OR.load(fis);
			log.debug("loading OR Properties file");
	
			fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\dd_properties\\Config.Properties");
			Config.load(fis);
			log.debug("loading Config Properties file");
			
			
			
			//Working on Web driver initialization based on browser type
			if(Config.getProperty("browser").equals("firefox")){
				
				driver = new FirefoxDriver();
				log.debug("loading Firefox");
			}else if(Config.getProperty("browser").equals("chrome")){
				
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\ChromeDriver\\chromedriver.exe");
				driver = new ChromeDriver();
				
			}else if(Config.getProperty("browser").equals("ie")){
				
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\IEDriver\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				
			}
			
			
			//Opening URL and Setting Timeout
			driver.get(Config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
			//DbManager.setMysqlDbConnection();
				
		}
		
	}
	
	
	//This method will check all the webelement in each test class
	//and will capture screenshot in case of any error
	public static WebElement findElement(String xpath){
		
		try
		{
			log.debug(xpath);
			return driver.findElement(By.xpath(OR.getProperty(xpath)));
		}
		
		catch(Throwable t)
		{
			
			log.debug("Could not find the element \"" + xpath + "\"");
			TestUtil.captureScreenshot("Exp_findElement_");
			return null;
			
		}
	}
	
	
	@AfterSuite
	public static void tearDown(){
		
		//driver.quit();
		
	}

}
