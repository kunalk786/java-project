import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
//import org.openqa.selenium.devtools.v118.network.Network;
//import org.openqa.selenium.devtools.v118.network.model.Response;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PublishedPost {

	public static void main(String[] args) throws InterruptedException {
		 WebDriverManager.chromedriver().setup();
	        
	        ChromeOptions options = new ChromeOptions();
//	        options.addArguments("--disable-notifications");
	        WebDriver driver = new ChromeDriver(options);
	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        driver.get("https://testbusiness.practina.com");
	        Thread.sleep(3000);
	        String businessSubscriptionPurchased = "";
	        String errorMessage = "";

	        List<WebElement> langNames = driver.findElements(By.xpath("//ul[@class='language-list list-inline text-center d-md-block d-none']/li"));
	        String lanToBeSelected = "English US";
	        boolean languageFound = false;
	        System.out.println(langNames.size());

	        for (WebElement homeLanguages : langNames) {
	            System.out.println(homeLanguages.getText());
	            if (homeLanguages.getText().equalsIgnoreCase(lanToBeSelected)) {
	                homeLanguages.click();
	                languageFound = true;
	                break;
	            }
	        }

	        if (!languageFound) {
	            driver.findElement(By.xpath("//span[@class='mat-menu-trigger cursor']")).click();
	            List<WebElement> matLanguages = driver.findElements(By.xpath("//div[@class='overflow-auto more_menu--innr ng-tns-c145-1']"));
	            for (WebElement currentLanguages : matLanguages) {
	                System.out.println(currentLanguages.getText());
	            }
	        }
	        Thread.sleep(2000);
	        driver.findElement(By.xpath("//button[contains(@class, 'signup')]")).click();
	        Thread.sleep(2000);
	        driver.findElement(By.xpath("//input[@formcontrolname='email']")).sendKeys("kunal.kumar+dummy76zcxsds22@bridgingtech.com");
	        driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys("qwerty@123@");
	        driver.findElement(By.xpath("//button[@class='generate-content']")).click();
	        Thread.sleep(15000);
//	        wait.until(ExpectedConditions.jsReturnsValue("return window.localStorage.getItem('user');"));
	        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	        String localStorageData = (String) jsExecutor.executeScript("return window.localStorage.getItem('user');");
	        System.out.println(localStorageData);
	        String userObject = (String) jsExecutor.executeScript("return window.localStorage.getItem('user');");
	        Object userJsonObject = new org.json.JSONObject(userObject);
	        
	        String bussName = ((org.json.JSONObject) userJsonObject).getString("buss_name");
	        System.out.println(bussName);
	        
//	        double creditBalance = ((org.json.JSONObject) userJsonObject).getDouble("credit_balance");
//	        System.out.println(creditBalance);
	        
	        double wallet = ((org.json.JSONObject) userJsonObject).getDouble("wallet");
	        System.out.println(wallet);
//	        driver.findElement(By.xpath("//span[@class='menu-close-icon bg-white position-absolute rounded-pill cursor']")).click();
	        String SelectedOption= "AI Posts";
	        List <WebElement> menuList= driver.findElements(By.xpath("//ul[@class='menu__list']/li"));
	        
	        for(WebElement AiPosts :menuList) {
	        	if (AiPosts.getText().contains(SelectedOption)) {
	        		AiPosts.click();
	        		driver.findElement(By.xpath("(//a[@class='fs-14 fw-300 menu-innr-link d-flex'])[2]")).click();
	        		break;
	        		
	        	}
	        }
	        Thread.sleep(25000);
	      //  clicking on the create post button
	        driver.findElement(By.xpath("//button[@class='btn btn-primary px-3']")).click();
	        driver.findElement(By.xpath("//span[@id='manual-switch-case']")).click();
	        driver.findElement(By.xpath("//span[@class='image-options']")).click();
	        String UploadOptionSelected="Upload image";
	        List<WebElement> UploadOptions= driver.findElements(By.xpath("//div[@id='cdk-overlay-1']/div/div/button"));
	        for (WebElement CurrentOption: UploadOptions) {
	        	System.out.println(CurrentOption.getText());
	        	if(CurrentOption.getText().contains("Upload image")) {
//	        		CurrentOption.click();
	        		String filePath = "C:/Users/BHTPL_USR73/Downloads/helo.jpeg";
	        		System.out.println("Clicked on Upload image option");
	        		CurrentOption.sendKeys("C:/Users/BHTPL_USR73/Downloads/helo.jpeg");
	        		WebElement fileInput = driver.findElement(By.xpath("(//input[@id='imageFile'])[2]"));
	        		 fileInput.sendKeys(filePath);
	        		System.out.println("File uploaded successfully");
	        		break;
	        		
	        	}
	        }
	        Thread.sleep(5000);
	        List<WebElement> platformNames= driver.findElements(By.xpath("//div[@class='social-list cursor']/div[2]//img"));
	        System.out.println("Number of elements in platformNames list: " + platformNames.size());
	        System.out.println(platformNames.get(0).getAttribute("src"));
	        if (platformNames.size() >= 1 && !platformNames.get(0).getAttribute("src").contains("fb")) {
	            System.out.println("Platform is not connected --> Facebook");
	        } else {
		        String platformCondition= "web-fb.svg";
		        for(WebElement CurrentImagePlatformName:platformNames) {
		        	if(!CurrentImagePlatformName.getAttribute("src").contains(platformCondition)) {
		    	        System.out.println(driver.findElement(By.xpath("//ul[@class='connected']/li")));
		    	        System.out.println("The value of platformCondition is"+ platformNames);
		    	        System.out.println("The value of CurrentImagePlatformName is" +CurrentImagePlatformName);
		        		driver.findElement(By.xpath("//ul[@class='connected']/li")).click();
		        		}
		        	else {
		        		System.out.println("The item is not clickable");
		        		driver.findElement(By.xpath("//*[contains(@class, 'textarea-contentbox')]/textarea[1]")).sendKeys("Smile Bright, Smile Right");
		        		driver.findElement(By.xpath("//*[contains(@class, 'Next')][1]")).click();
		        	}
		        	}
		        }
		        
		        		
		     	        
		        	}
		        }
	        
	

	        	
	       
	        
	        
//	        WebElement fileInput = driver.findElement(By.id(""));
//	        DevTools devTools = ((ChromeDriver) driver).getDevTools();
//	        devTools.createSession();
//	        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
//
//	        devTools.addListener(Network.responseReceived(), response ->
//	        {
//	            Response res = response.getResponse();
//	            System.out.println(res.getUrl());
//	            System.out.println(res.getStatus());
//
//	        });