import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BussName {

	
		private static WebDriverWait wait;

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
//	        driver.findElement(By.xpath("//span[@class='cursor']")).click();
	        Thread.sleep(2000);
	        driver.findElement(By.xpath("//input[@formcontrolname='email']")).sendKeys("kunal.kumar+dummy76sdsss22SaaaSSS@bridgingtech.com");
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
	        
	        String welcomeText= driver.findElement(By.xpath("//h4[@class='fw-800 text-left text-nowrap mb-3']")).getText().trim();
	        String expectedWelcomeText = bussName.isEmpty() ? "Welcome" : "Welcome, " + bussName;
	        Assert.assertEquals(welcomeText, expectedWelcomeText, "Welcome text does not match the expected value");
	        System.out.println("Welcome name and buss name is matched");
	        Thread.sleep(5000);
	        
	        //String creditAvailable = driver.findElement(By.xpath("(//span[@class='notranslate'])[3]")).getText();
	        String creditAvailable = driver.findElement(By.xpath("//span[@id='credit-balance']")).getText();
	        System.out.println("Fetch Value :"+creditAvailable);
	        double actualCreditAmount;

	            // Check if the string is not empty before parsing
	            if (!creditAvailable.isEmpty()) {
	                actualCreditAmount = Double.parseDouble(creditAvailable);
	                Assert.assertTrue(actualCreditAmount == wallet,
	                        "Credit amount isn't correct");
	                System.out.println("Same Amount is displayed");
	            } else {
	                // Handle the case when the string is empty
	            	System.out.println("Amount is not displayed or present");
	            }
	        }
	    public static double round(double value) {
	        return Math.round(value * 100.0) / 100.0;
	    }
}
