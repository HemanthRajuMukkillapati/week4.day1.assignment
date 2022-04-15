package week4.day1.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContact 
{
	

		public static void main(String[] args) 
		{
		
		
		WebDriverManager.chromedriver().setup();
	
		ChromeDriver driver =new ChromeDriver(); 	
		
		
		//launch and login leaftaps
		driver.get("http://leaftaps.com/opentaps/control/login");
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.id("username")).sendKeys("DemoSalesManager");
		
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		
		driver.findElement(By.className("decorativeSubmit")).click();
		
		driver.findElement(By.partialLinkText("CRM/SFA")).click();
		
		//click on contacts and then merge contacts
		driver.findElement(By.linkText("Contacts")).click();
		
		driver.findElement(By.xpath("//a[contains(text(),'Merge Contacts')]")).click();
		
		//click on from contact widget
		driver.findElement(By.xpath("//input[@id='partyIdFrom']/following-sibling::a")).click();
		
		//storing window references in string set
		Set<String> frmCntWinSet = driver.getWindowHandles();
		
		//converting to list to access through index
		List<String> frmCntWinList = new ArrayList<String>(frmCntWinSet);
		
		//switching to newly opened window
		driver.switchTo().window(frmCntWinList.get(1));
		
		driver.findElement(By.xpath("//a[@class='linktext']")).click();
		
		//switching back to original window
		driver.switchTo().window(frmCntWinList.get(0));
		
		driver.findElement(By.xpath("//input[@id='partyIdTo']/following-sibling::a")).click();
		
		//storing window references in string set
		Set<String> ToCntWinSet = driver.getWindowHandles();
		
		List<String> ToCntWinList = new ArrayList<String>(ToCntWinSet);
		
		//switching to newly opened window
		driver.switchTo().window(ToCntWinList.get(1));
		
		driver.findElement(By.xpath("//a[@class='linktext']/following::a[5]")).click();
		
		//switching back to original window
		driver.switchTo().window(ToCntWinList.get(0));
		
		driver.findElement(By.xpath("//a[text()='Merge']")).click();
		
		//object for Alert class and accepting alert
		Alert cnfmAlrt = driver.switchTo().alert();
		
		cnfmAlrt.accept();
		
		//verifying page title and printing confirmation
		if(driver.getTitle().contains("View Contact | opentaps CRM")) 
		{
			System.out.println("Merging contacts is successful");
		}
		else 
		{
			System.out.println("Merging contacts is not successful");
		}
		
		
		
		
	}

}	



