

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DashboardTextAssertion {
	private static WebDriverWait wait;
	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://testbusiness.practina.com");
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
//        driver.findElement(By.xpath("//span[@class='cursor']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@formcontrolname='email']")).sendKeys("kunal.kumar+dummy76sdsss22SaaaSSS@bridgingtech.com");
        driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys("qwerty@123@");
        driver.findElement(By.xpath("//button[@class='generate-content']")).click();

        driver.findElement(By.xpath("//span[@class='menu-close-icon bg-white position-absolute rounded-pill cursor']")).click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='menu__list']/li")));
        driver.findElement(By.xpath("(//div[@class='row card-container']/div/div/p)[1]")).click();
        String text= driver.findElement(By.xpath("//div[@class='top-headings pr-0 ng-star-inserted']")).getText();
        System.out.println(text);
        Assert.assertTrue(text.contains("Published Posts\n"
        		+ "Your posts are looking good, keep it up!"), "Element is not in the expected state");
//        Assert.assertTrue(("active"), "Element is not in the expected state");
      
	}

}