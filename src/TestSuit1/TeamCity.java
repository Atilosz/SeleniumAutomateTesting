package TestSuit1;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TeamCity
{

	 public static WebDriver driver;
	 public static Actions ac;
	 
	 public TeamCity()
	 {
	 }
	    

	 @BeforeClass
	 public static void setUpClass() throws Exception
	 {
		 System.setProperty("webdriver.chrome.driver", "C:\\Users\\Attila\\source\\repos\\FirstTest\\FirstTest\\bin\\Debug\\chromedriver.exe");
		 driver = new ChromeDriver();
		 driver.manage().window().maximize();
	 }

	 @AfterClass
	 public static void tearDownClass() 
	 {
		//driver.quit();
	 }
	
	 @Test
	 public void asd() throws Exception
	 {
		 TeamCityLogin();
	 }
	 
	 
	 
	 
	 
	 
	 public static void TeamCityLogin() throws InterruptedException
	 {
	        driver.navigate().to("http://masterbuilder/profile.html");
	        if (driver.findElements(By.name("username")).size() > 0)
	        {
	            driver.findElement(By.name("username")).sendKeys("asl");
	            driver.findElement(By.name("password")).sendKeys("bogancs1956");
	            driver.findElement(By.name("submitLogin")).click();
	        }
	        //Thread.sleep(1000);
	        WebDriverWait wait = new WebDriverWait(driver, 1);
	        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@class='headerLogoImg']")));
	        driver.findElement(By.xpath("//i[@class='headerLogoImg']")).click();
	        System.out.println("1");
	        driver.findElement(By.className("buildTypeName")).getText();
	        System.out.println("2");
	        System.out.println(""+ driver.findElement(By.className("buildTypeName")).getText());
	        System.out.println(""+ driver.findElement(By.className("buildTypeName")).getTagName());
	        System.out.println(""+ driver.findElement(By.className("buildTypeName")).toString());
	        System.out.println(""+ driver.findElement(By.className("buildTypeName")).isDisplayed());
	        System.out.println(""+ driver.findElement(By.className("buildTypeName")).getLocation());
	        System.out.println("3");
	 }
	 
	 
	 
	 
}
