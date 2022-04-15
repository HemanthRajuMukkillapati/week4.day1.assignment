package week4.day1.assignments;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


import io.github.bonigarcia.wdm.WebDriverManager;

public class ServiceNow 
{

	public static void main(String[] args) throws InterruptedException 
	{
		WebDriverManager.chromedriver().setup();

		ChromeDriver driver = new ChromeDriver();

		// launch bwlow URL
		driver.get("https://dev122476.service-now.com/");

		// maximize window
		driver.manage().window().maximize();

		// implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		//switch to frame
		driver.switchTo().frame(0);
		
		//enter username
		driver.findElement(By.id("user_name")).sendKeys("admin");
		
		//enter password
		driver.findElement(By.id("user_password")).sendKeys("Testleaf@123");
		
		//click on login button 
		driver.findElement(By.id("sysverb_login")).click();
		
		//search for incidents
		//driver.findElement(By.xpath("//input[@id='filter']")).sendKeys("incidents");
		Actions builder = new Actions(driver);
		
		//click on Incident
		builder.click(driver.findElement(By.xpath("//div[text()='Incidents']"))).perform();
		
		//switch to frame
		driver.switchTo().frame(0);
		
		//click on all using builder object
		builder.click(driver.findElement(By.xpath("//span[@id='incident_breadcrumb']/a/b"))).perform();
		
		//click on id
		driver.findElement(By.id("sysverb_new")).click();
		
		//get incident number to an variable
		String incNo = driver.findElement(By.id("incident.number")).getAttribute("value");
		
		//click on caller input box and enter data
		driver.findElement(By.id("sys_display.incident.caller_id")).sendKeys("Abraham Lincoln");
		
		Thread.sleep(3000);
		
		//click on description box and enter data
		driver.findElement(By.id("incident.short_description")).sendKeys("issues with windows");
		
		//click on submit
		driver.findElement(By.xpath("//button[text()='Submit']")).click();
		
		Thread.sleep(3000);
		
		//search with the stored incident number
		driver.findElement(By.xpath("//label[text()='Search']/following-sibling::input[@placeholder='Search']")).sendKeys(incNo,Keys.ENTER);
		
		//compare incident we search vs created
		if(driver.findElement(By.xpath("//a[@class='linked formlink']")).getText().equals(incNo))
		{
			//print success message with incident no
			System.out.println("The expected incident is displayed: "+incNo);
		}
		else 
		{
			//print wrong incident message
			System.out.println("wrong incident is dispalyed");
		}
		
		
	}

}
