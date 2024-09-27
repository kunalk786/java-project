package Test_Scenarios;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NewChromeDemo {
    private static WebDriverWait wait;

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--disable-notifications");
        driver.get("https://testbusiness.practina.com/welcome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Thread.sleep(4000);
//        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String businessSubscriptionPurchased="";
        String errorMessage="";

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
        driver.findElement(By.xpath("//input[@formcontrolname='email']")).sendKeys("kunal.kumar+dummy54@bridgingtech.com");
        driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys("qwerty@123@");
        driver.findElement(By.xpath("//div[@class='button-box']")).click();
       
        
        
        errorMessage = driver.findElement(By.xpath("//div[@class='overlay-container']")).getText();
        
        if (errorMessage.contains("already exist")) {
        	Thread.sleep(4000);//            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='fw-700']")));
            driver.findElement(By.xpath("//span[@class='fw-700']")).click();
            driver.findElement(By.xpath("//button[@class='generate-content']")).click();
            Thread.sleep(4000);
//            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@class='fw-700 text-capitalize mb-2 text-black']")));
            String businessIndustryNotSetup = driver.findElement(By.xpath("//h3[@class='fw-700 text-capitalize mb-2 text-black']")).getText();

            if (businessIndustryNotSetup.contains("Choose Your Industry")) {
//                Assert.assertTrue(true);
                List<WebElement> industry = driver.findElements(By.xpath("//div[@class='col-6 industry-block ng-star-inserted']"));
                String industryToBeSelected = "Dental Care";

                for (WebElement selectedIndustry : industry) {
                    if (selectedIndustry.getText().equalsIgnoreCase(industryToBeSelected)) {
                        System.out.println(selectedIndustry.getText());
                        selectedIndustry.click();
                        driver.findElement(By.xpath("//button[@class='generate-content']")).click();
                        break;
                    }
                }
               
               

            }
            businessSubscriptionPurchased = driver.findElement(By.xpath("//h3[@class='fw-800']")).getText().trim();
            System.out.println(businessSubscriptionPurchased);
            if (businessSubscriptionPurchased.contains("Not Only Practina AI Is Amazing")) {
//                Assert.assertTrue(true);
                driver.findElement(By.xpath("//button[@class='w-100 start-btn fs-16 fw-600 d-flex align-items-center justify-content-center ng-star-inserted']")).click();
            } else {
//                Assert.assertFalse(false);
            }
//            else if(businessSubscriptionPurchased)
//            System.out.println(businessSubscriptionPurchased);

            if (businessSubscriptionPurchased.contains("Not Only Practina AI Is Amazing")) {
//                Assert.assertTrue(true);
                driver.findElement(By.xpath("//button[@class='w-100 start-btn fs-16 fw-600 d-flex align-items-center justify-content-center ng-star-inserted']")).click();
            } else {
//                Assert.assertFalse(false);
            }

        } else {
        	Thread.sleep(4000);
//               wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@class='fw-700 text-capitalize mb-2 text-black']")));
               String businessIndustryNotSetup = driver.findElement(By.xpath("//h3[@class='fw-700 text-capitalize mb-2 text-black']")).getText();
               if (businessIndustryNotSetup.contains("Choose Your Industry")) {
//                   Assert.assertTrue(true);
                   List<WebElement> industry = driver.findElements(By.xpath("//div[@class='col-6 industry-block ng-star-inserted']"));
                   String industryToBeSelected = "Dental Care";

                   for (WebElement selectedIndustry : industry) {
                       if (selectedIndustry.getText().equalsIgnoreCase(industryToBeSelected)) {
                           System.out.println(selectedIndustry.getText());
                           selectedIndustry.click();
                           driver.findElement(By.xpath("//button[@class='generate-content']")).click();
                           break;
                       }
                   }
               }
        }


     
        }


    }