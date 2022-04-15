package week4.day1.assignments;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FramesPractice 
{

	public static void main(String[] args) 
	{
			
		
		WebDriverManager.chromedriver().setup();
	
		ChromeDriver driver =new ChromeDriver(); 	
		
		
		//launch bwlow URL
		driver.get("https://chercher.tech/practice/frames-example-selenium-webdriver");
		
		//maximize window
		driver.manage().window().maximize();
		
		//implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//getting title
		
		System.out.println(driver.getTitle());

		//switching to frame 1 using frame id
		driver.switchTo().frame("frame1");
		
		//since no property for the attribute and this is first in the frame using directly attribute name here
		driver.findElement(By.xpath("//input")).sendKeys("No properties");
		
		//switching to inner frame
		driver.switchTo().frame("frame3");
		
		//selecting the checkbox
		driver.findElement(By.xpath("//input[@id='a']")).click();
		
		//switching back to default content
		driver.switchTo().defaultContent();
		
		//switching the frame2
		
		driver.switchTo().frame("frame2");
		
		//finding drop-down element using id.
		WebElement animal = driver.findElement(By.id("animals"));
		
		//creating an object for select class
		Select animalDD = new Select(animal);
		
		
		//select baby cat in drop-down
		animalDD.selectByValue("babycat");
		
		//switching to default window
		driver.switchTo().defaultContent();
		
		//close the browser
		driver.quit();
		
		
		
	}

}
