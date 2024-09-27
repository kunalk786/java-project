package Test_Scenarios;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NewLogin {
    private static WebDriverWait wait;

    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://testbusiness.practina.com");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//button[contains(text(), 'Sign Up or Start a Free Trial')]")).click();
        driver.findElement(By.xpath("//input[@formcontrolname='email']")).sendKeys("Kunal.kumar+dummy1mk@bridgingtech.com");
        driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys("qwerty@123@");
        driver.findElement(By.xpath("//div[@class='button-box']")).click();
       
        
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.jsReturnsValue("return window.localStorage.getItem('user');"));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String localStorageData = (String) jsExecutor.executeScript("return window.localStorage.getItem('user');");
        System.out.println(localStorageData);
        String userObject = (String) jsExecutor.executeScript("return window.localStorage.getItem('user');");
        Object userJsonObject = new org.json.JSONObject(userObject);
        if (((JSONObject) userJsonObject).isNull("user")) {
            driver.findElement(By.xpath("//span[@class='fw-700']")).click();
            driver.findElement(By.xpath("//button[@class='generate-content']")).click();
        }
        else {
        String token = ((org.json.JSONObject) userJsonObject).getString("token");
        System.out.println(token);
        String redirection_url = ((org.json.JSONObject) userJsonObject).getString("redirection_url");
        System.out.println(redirection_url);
        
        if (!((JSONObject) userJsonObject).isNull("category_data") && redirection_url.contains("PLAN"))
        {
        org.json.JSONObject categoryDataObject = ((JSONObject) userJsonObject).getJSONObject("category_data");
        String categoryType = categoryDataObject.getString("type");
        String categoryName = categoryDataObject.getString("category_name");
        System.out.println("Category Type: " + categoryType);
        System.out.println("Category Name: " + categoryName);
        driver.findElement(By.xpath("//button[@class='w-100 start-btn fs-16 fw-600 d-flex align-items-center justify-content-center ng-star-inserted']")).click();
        }
        else if (redirection_url.equalsIgnoreCase("Dashboard")) {
            return;
        }
        else {
            System.out.println("category_data is null");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='col-6 industry-block ng-star-inserted']")));
            List<WebElement> industry = driver.findElements(By.xpath("//div[@class='col-6 industry-block ng-star-inserted']"));
            String industryToBeSelected = "Dental Care";

            for (WebElement selectedIndustry : industry) {
                if (selectedIndustry.getText().equalsIgnoreCase(industryToBeSelected)) {
                    System.out.println(selectedIndustry.getText());
                    selectedIndustry.click();
                    driver.findElement(By.xpath("//button[@class='generate-content']")).click();
                    break;
                    
                }
                Thread.sleep(2000);
                
            }
            driver.findElement(By.xpath("//button[@class='w-100 start-btn fs-16 fw-600 d-flex align-items-center justify-content-center ng-star-inserted']")).click();
            
        }
        


//        System.out.println(localStorageData);
    }
    
}
}