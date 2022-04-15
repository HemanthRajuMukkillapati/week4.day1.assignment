package week4.day1.assignments;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LeafGroundFrames 
{

		public static void main(String[] args) throws IOException 
		{
		
		
		WebDriverManager.chromedriver().setup();
		
		ChromeDriver driver =new ChromeDriver(); 	
		
		//launching url in chrome
		driver.get("http://leafground.com/pages/frame.html");
		
		//maximize window
		driver.manage().window().maximize();
		
		//storing frames in list
		List<WebElement> noOfFrames = driver.findElements(By.tagName("iframe"));
		
		//switching to 1st frame
		driver.switchTo().frame(0);
		
		//clicking on the click me button under 1st frame
		driver.findElement(By.xpath("//button[@id='Click']")).click();
		
		// take the screenshot after clicking on click Me
		File source = driver.getScreenshotAs(OutputType.FILE);
		// Creating physical file
		File destination = new File("./ScreenShots/screenshotFrame1.png");
		// Copy source to destination
		FileUtils.copyFile(source, destination);
		
		//printing no of frames visible to main page
		//2 children frames will be eliminated here since they are under parent frames
		
		System.out.println("The no of frames in page are: "+ noOfFrames.size());
		
		
		
		
		
	}

}
