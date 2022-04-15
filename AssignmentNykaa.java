package week4.day1.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AssignmentNykaa 
{

	public static void main(String[] args) throws InterruptedException 
	{

		WebDriverManager.chromedriver().setup();

		ChromeDriver driver = new ChromeDriver();

		// launch bwlow URL
		driver.get("https://www.nykaa.com/");

		// maximize window
		driver.manage().window().maximize();

		// implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		// Object for action class
		Actions builder = new Actions(driver);

		// using builder object mouseover on brands
		builder.moveToElement(driver.findElement(By.xpath("//a[text()='brands']"))).perform();

		// clicking on search-box
		builder.moveToElement(driver.findElement(By.xpath("//input[@id='brandSearchBox']"))).click();

		// entering L'oreal paris in searchbox
		driver.findElement(By.xpath("//input[@id='brandSearchBox']")).sendKeys("L\'Oreal Paris");

		// clicking on L'oreal paris in results
		builder.click(driver.findElement(By.xpath("//a[contains(text(),'Oreal Paris')]"))).perform();

		// verifying title
		if (driver.getTitle().contains("L'Oreal Paris")) 
		{
			// if title matches,print below and execute below code
			System.out.println("L\'Oreal Paris products page is displayed");

			// click on sort by
			driver.findElement(By.xpath("//span[@class='sort-name']/parent::button")).click();

			// click on customer top rated
			driver.findElement(By.xpath("//span[text()='customer top rated']")).click();

			// click on category
			driver.findElement(By.xpath("//span[text()='Category']")).click();
			Thread.sleep(3000);
			
			// click on Hair
			builder.click(driver.findElement(By.xpath("//span[text()='Hair']/following-sibling::span"))).perform();

			// Click Haircare
			builder.click(driver.findElement(By.xpath("//span[text()='Hair Care']/following-sibling::span"))).perform();
				
			// select shampoo
			builder.click(driver.findElement(By.xpath("//span[text()='Shampoo']/following-sibling::span"))).perform();

			Thread.sleep(3000);
			builder.click(driver.findElement(By.xpath("//span[text()='Concern']/following-sibling::div/div"))).perform();

			Thread.sleep(3000);
			builder.click(driver.findElement(By.xpath("//label[contains(@for,'checkbox_Color Protection')]")))
					.perform();

			// serach for shampoo in filter
			if (driver.findElement(By.xpath("//span[@class='filter-value' and text()='Shampoo']")).getText()
					.contains("Shampoo")) 
			{

				// if shampoo is available prints below and executes below code
				System.out.println("Shampoo is available in filter ");

				// condition to check shampoo name
				if (driver.findElement(By.xpath("//div[contains(text(),'Oreal Paris Colour Protect Shampoo')]"))
						.getText().equals("L'Oreal Paris Colour Protect Shampoo")) 
				{

					// if shampoo name matches prints below and execute below code
					System.out.println("L'Oreal Paris Colour Protect Shampoo is displayed in products");

					// clicking on quick link
					driver.findElement(By.xpath("//div[contains(text(),'Oreal Paris Colour Protect Shampoo')]"))
							.click();

					// putting window handles into set
					Set<String> winHdlsS = driver.getWindowHandles();

					// converting window handles from set to list
					List<String> WinHdlsL = new ArrayList<String>(winHdlsS);

					// switching to window no 2
					driver.switchTo().window(WinHdlsL.get(1));

					// size drop down web element
					WebElement sizeDr = driver.findElement(By.xpath("//select[@title='SIZE']"));

					// providing webelement to the select class
					Select sizeDD = new Select(sizeDr);

					// selecting based on value attribute
					sizeDD.selectByValue("0");

					// ommiting rupee symbol in MRP
					String Mrp = driver.findElement(By.xpath("//span[text()='MRP:']/following-sibling::span")).getText()
							.substring(1);

					// Printing Mrp
					System.out.println("The MRP of Product is: " + Mrp);

					// clicking on add to bag
					driver.findElement(By.xpath("//span[text()='ADD TO BAG']/parent::button")).click();

					// clicking on cart button
					driver.findElement(By.xpath("//span[@class='cart-count']/parent::button")).click();

					// switching to frame
					driver.switchTo().frame(0);

					// getting grand total and ommiting rupee symbol
					String price = driver.findElement(By.xpath("//div[@class='payment-tbl-data']//div[4]/div[2]"))
							.getText().substring(1);

					// printing Grand Total
					System.out.println("The Grand Total is : " + price);

					// clicking on proceed
					driver.findElement(By.xpath("//button[contains(@class,'proceed')]")).click();

					// switching out of frame
					driver.switchTo().defaultContent();

					// clicking on continue as guest
					driver.findElement(By.xpath("//button[@class='btn full big']")).click();

					// condition to check grandtotal in checkout page and shopping cart
					if (driver.findElement(By.xpath("//div[text()='Grand Total']/following-sibling::div/span"))
							.getText().contains(price)) 
					{
						// if grand totatl is correct prints below
						System.out.println("The Grand Total matches in checkout page and shopping cart");
						driver.quit();
					} else 
					{
						// if grand total is wrong, prints below
						System.out.println("The Grand Total does not matches");
						driver.quit();
					}
				 }

					// if shampoo is not available prints below text
				else 
				{
					System.out.println("Shampoo is not part of filter");
					driver.quit();
				}

				// if wrong product is selected prints below text
			} else 
			{
				System.out.println("Wrong product page is displayed");
				driver.quit();
			}
		  }

	}
}
