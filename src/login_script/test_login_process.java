//1) Write a script that checks the login process in bongobd.com and assert that it works.
//before run the code please change the phone number. 
// choose any one option's code of "use sms" and "use whatsApp" , because here i used "use whatsApp" button .
//for this reason "use sms" button's code is written before  the code and kept it under commenting.
//please write the verification code manually from your phone and wait for automate the system it will take some because i used thread since the page need more time for loading.
// We have to enter the verification code within 30 seconds and also automate the further action after 30 seconds so please keep patience
//test status will show in console.

package login_script;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class test_login_process {
	public static void main(String[] args) throws InterruptedException{
		System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();       
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);

		
		driver.get("https://www.bongobd.com/");
		 System.out.println("ID of both windows");
		String homepage = driver.getWindowHandle();
        System.out.println(homepage); //print homePage id
        
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/nav/div/div/div[2]/div[4]/ul/li[1]/a")).click(); /// click on login button
        driver.findElement(By.xpath("//*[@id=\"regNext\"]/span[2]")).click(); // click on phone number button
        
        String popPage = driver.getWindowHandle();
	    System.out.println(popPage); ///print phone number popUp window id
	    
	    Set<String> windows = driver.getWindowHandles();
	    System.out.println(windows.size());
	    
	    Iterator iterator = windows.iterator();
	    String currentWindowId;
	    while(iterator.hasNext()) {
	    	  currentWindowId = iterator.next().toString();
	    	  System.out.println(currentWindowId);
	    	  
	    	  if(!currentWindowId.equals(homepage)) {
	    		  driver.switchTo().window(currentWindowId);
	    		 //please change the phone number for getting verification code.
	    		  driver.findElement(By.name("phone_number")).sendKeys("1790000051"); // sending phone number 
	    		  Thread.sleep(1000);
	    			
	    		  //driver.findElement(By.xpath("//*[@id=\"u_0_6r\"]")).click(); // click on "use sms" button
	    		 
	    		  driver.findElement(By.xpath("//*[@id=\"u_0_6s\"]")).click(); // click on use whatsApp  button. i used this one because delay of verification code of "send sms" button 
	    		  
	    		 //since we have to take  code from the phone we will enter the code manually after that automation will begin again
	    		 Thread.sleep(30000); // using high thread time for time out issue because need time to get verification code
	    			
	    		 driver.findElement(By.xpath("//*[@id=\"u_0_3\"]")).click(); // click on  continue button
	    		    
	    		 Thread.sleep(3000);
	   
	    		
	    		    	
	    	  }
	    	  
	    	  if(currentWindowId.equals(homepage)) {
	    		  String at = driver.getTitle();	// for getting the title of the page 
	    			Thread.sleep(3000);
	    			System.out.println(at);
	    			
	    			String et = "BongoBD - Login";
	    			
	    			Thread.sleep(1000);
	    			
	    			
	    			if(at.equalsIgnoreCase(et)) {
	    				
	    				System.out.println("Test successful");
	    			}
	    			else {
	    				
	    				System.out.println("Test unsuccessful");
	    				
	    			}
	    			
	    			System.out.println("end");
	    			Thread.sleep(1000);
	    			
	    			//driver.close(); 
	    	  }
	    }//
	    
	    
	}
}
