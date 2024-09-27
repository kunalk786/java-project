package com.Kunal;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;  // Uncomment this line if you are using TestNG

import io.github.bonigarcia.wdm.WebDriverManager;
public class LocalStorage {
	private static WebDriverWait wait;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\BHTPL_USR73\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        driver.get("https://testbusiness.practina.com/welcome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

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

        driver.findElement(By.xpath("//button[contains(text(), 'Sign Up or Start a Free Trial')]")).click();
        driver.findElement(By.xpath("//input[@formcontrolname='email']")).sendKeys("Kunal.kumar+dummy6711p@bridgingtech.com");
        driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys("qwerty@123@");
        driver.findElement(By.xpath("//div[@class='button-box']")).click();

        if (isToasterErrorMessagePresent(driver)) {
            runIfLogic(driver);
        } else {
            runElseConditions(driver);
        }
    }

    public static void runIfLogic(WebDriver driver) {  // Change ChromeDriver to WebDriver
        String errorMessage = driver.findElement(By.xpath("//div[@id='toast-container']")).getText();
        System.out.println(errorMessage);
        
        if (errorMessage.contains("email already exist")) {
            driver.findElement(By.xpath("//a[text()='Log in']")).click();
            driver.findElement(By.xpath("//button[@class='generate-content']")).click();
        } 
        
        String currentUrl = driver.getCurrentUrl();
        System.out.println(currentUrl);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@class='fw-700 text-capitalize mb-2 text-black']")));
        String businessIndustryNotSetup = driver.findElement(By.xpath("//h3[@class='fw-700 text-capitalize mb-2 text-black']")).getText();

        if (businessIndustryNotSetup.contains("Choose your industry")) {
            Assert.assertTrue(true);
            List<WebElement> industry = driver.findElements(By.xpath("//div[@class='col-6 industry-block ng-star-inserted']"));
            String industryToBeSelected = "Dental Care";
            
            for (WebElement selectedIndustry : industry) {
                if (selectedIndustry.getText().equalsIgnoreCase(industryToBeSelected)) {
                    System.out.println(selectedIndustry.getText());
                    selectedIndustry.click();
                    driver.findElement(By.xpath("//button[@class='generate-content']")).click();
                    String businessSubscriptionPurchased = driver.findElement(By.xpath("//h3[@class='fw-800']")).getText().trim();
                    
                    if (businessSubscriptionPurchased.contains("Not only Practina AI is Amazing, but it's Affordable too!")) {
                        Assert.assertTrue(true);
                        driver.findElement(By.xpath("//button[@class='w-100 start-btn fs-16 fw-600 d-flex align-items-center justify-content-center ng-star-inserted']")).click();
                    }
                }
            }
        }
    }

    public static void runElseConditions(WebDriver driver) {  // Change ChromeDriver to WebDriver
        System.out.println("User can be eligible for SignUp");
        List<WebElement> industry = driver.findElements(By.xpath("//div[@class='col-6 industry-block ng-star-inserted']"));
        String industryToBeSelected = "Dental Care";
        
        for (WebElement selectedIndustry : industry) {
            System.out.println(selectedIndustry.getText());

            if (selectedIndustry.getText().equalsIgnoreCase(industryToBeSelected)) {
                selectedIndustry.click();
            }
        }
    }

    private static boolean isToasterErrorMessagePresent(WebDriver driver) {  // Change ChromeDriver to WebDriver
        try {
            // Check if the toaster error message is present by finding the element
            driver.findElement(By.xpath("//div[@id='toast-container']"));
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }
}

	}

}
