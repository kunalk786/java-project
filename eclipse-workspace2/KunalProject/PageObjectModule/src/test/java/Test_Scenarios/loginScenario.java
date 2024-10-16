package Test_Scenarios;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import io.opentelemetry.sdk.resources.Resource;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class loginScenario {

    public static void main(String[] args) throws IOException, InterruptedException {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://testbusiness.practina.com/");

        // Use Duration for implicit wait
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        WebElement emailInput = driver.findElement(By.xpath("//input[@formcontrolname='email']"));
        emailInput.sendKeys("kunal.kumar+dummy762dfgdsg2@bridgingtech.com");

        WebElement passInput = driver.findElement(By.xpath("//input[@id='mat-input-0']"));
        passInput.sendKeys("K0912u@123@");

        WebElement loginButton = driver.findElement(By.xpath("//span[@id='primaryButton']"));
        loginButton.click();
        
        WebElement arrowContainer = driver.findElement(By.xpath("//span[contains(@class, 'menu-close-icon')]"));

        
        WebElement rightArrow = arrowContainer.findElement(By.xpath(".//mat-icon[text()='keyboard_arrow_right']"));

        if (rightArrow != null) {
            rightArrow.click();
        }
        
        WebElement aiAds = driver.findElement(By.xpath("//div[@id='main']/ul/li[3]"));
        aiAds.click();

        WebElement recommendedAds = driver.findElement(By.xpath("//body[1]/app-root[1]/app-main-layout[1]/div[1]/div[1]/div[1]/app-main-menu[1]/div[1]/div[1]/div[2]/ul[1]/li[3]/mat-accordion[1]/mat-expansion-panel[1]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[1]/a[1]"));
        recommendedAds.click();

        WebElement normalFacebookAd = driver.findElement(By.xpath("//button[@class='fs-16 fw-600 border-0 cursor p-2']/span"));
        normalFacebookAd.click();
       
        Thread.sleep(1000);
   
        try {
            // Try to find and interact with the warning dialog
            WebElement warningDialog = driver.findElement(By.xpath("//div[@id='warning_dialog']"));
            // If the element is found, you can perform any required actions on it
            warningDialog.click();
            WebElement cancelButton = warningDialog.findElement(By.xpath("//button[@id='cancel_btn']"));
            cancelButton.click();
        }  catch (NoSuchElementException e) {
       

        WebElement postEngagementAd = driver.findElement(By.xpath("//div[@class=\"popup-body row gutter-16\"]/div[2]"));
        postEngagementAd.click();

        WebElement continueButton = driver.findElement(By.xpath("//app-submit-ad[@class=\"camp-continue-btn ng-star-inserted\"]"));
        continueButton.click();

        WebElement primaryText = driver.findElement(By.xpath("//div[@class='mat-mdc-form-field-infix ng-tns-c29-39']/textarea"));
        primaryText.sendKeys("PRIMARY TEXT ENTERED BY AUTOMATION ENGINEER");

        Thread.sleep(3000);

        WebElement uploadButton = driver.findElement(By.xpath("//input[@type='file']"));
        File file = new File("C:\\Users\\BHTPL_USR73\\Downloads\\Compatable.jpg");
        uploadButton.sendKeys(file.getAbsolutePath());

        WebElement saveButton = driver.findElement(By.xpath("//button[@class='fs-16 fw-600 text-center btn-primary btn px-4']"));
        saveButton.click();

        WebElement websiteUrl = driver.findElement(By.xpath("//input[@id='mat-input-2']"));
        websiteUrl.clear();
        websiteUrl.sendKeys("https://practina.com/");
        
        WebElement calltoActions = driver.findElement(By.xpath("//body/app-root[1]/app-single-page-fb-ad[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/app-fb-ad-details[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[1]/mat-form-field[1]"));
        calltoActions.click();

        Thread.sleep(2000);

        List<WebElement> calltoActionsList = driver.findElements(By.xpath("//div[@role='listbox']/mat-option"));

        String calltoActionsToBeSelected = "Download";

        for (WebElement selectedcalltoAction : calltoActionsList) {
            System.out.println(selectedcalltoAction.getText());

            if (selectedcalltoAction.getText().equalsIgnoreCase(calltoActionsToBeSelected)) {
                selectedcalltoAction.click();
                break;
            }
        }
        Thread.sleep(1000);
        WebElement publishButton = driver.findElement(By.xpath("//button[@id='publish_now']"));
        publishButton.click();

        WebElement doneButton = driver.findElement(By.xpath("//button[@class='btn-primary btn']"));
        doneButton.click();

        WebElement backButton = driver.findElement(By.xpath("//img[@class='mr-2' and @alt='back-icon']"));
        backButton.click();

        WebElement createAdButton = driver.findElement(By.xpath("//button[@class='px-3 d-flex align-items-center justify-content-center mx-auto btn btn-primary'][1]"));
        createAdButton.click();

        WebElement selectGoogleAd = driver.findElement(By.xpath("(//input[@type='radio' and @name='card'])[3]"));
        selectGoogleAd.click();

        WebElement clickOnContinueButton = driver.findElement(By.xpath("//span[@class='mx-2']"));
        clickOnContinueButton.click();
        
        WebElement finalUrl = driver.findElement(By.xpath("(//input[@placeholder='https://www.practina.com/dental'])[1]"));
        finalUrl.sendKeys("https://practina.com");
        
        WebElement headline1Input = driver.findElement(By.name("input_0"));
        headline1Input.sendKeys("Headline 1");

        // Headline 2
        WebElement headline2Input = driver.findElement(By.name("input_1"));
        headline2Input.sendKeys("Headline 2");

        // Headline 3
        WebElement headline3Input = driver.findElement(By.name("input_2"));
        headline3Input.sendKeys("Headline 3");

        // Description 1
        WebElement description1Input = driver.findElement(By.id("text_0"));
        description1Input.sendKeys("Description 1"); 

        WebElement description2Input = driver.findElement(By.id("text_1"));
        description2Input.sendKeys("Description 2");  
        
        WebElement description3Input = driver.findElement(By.id("text_2"));
        description3Input.sendKeys("Description 3");  
        
        WebElement calloutText1Input = driver.findElement(By.id("callout0"));
        calloutText1Input.sendKeys("Callout Text"); 
        
        WebElement contactNumberInput = driver.findElement(By.xpath("//input[@id='text_0']"));
        contactNumberInput.sendKeys("6283552808");
        
        WebElement saveButtonForGoogle= driver.findElement(By.xpath("//button[@class='fs-16 fw-600 text-center btn-primary btn px-4']"));
        saveButtonForGoogle.click();
        
        WebElement clickOnGoogleCalendar= driver.findElement(By.xpath("(//span[contains(@class, 'mat-ripple') and contains(@class, 'mat-mdc-button-ripple')])[2]"));
        clickOnGoogleCalendar.click();
        
        WebElement calendarYear= driver.findElement(By.xpath("(//tbody[@class='mat-calendar-body']/tr/td)[1]"));
        System.out.println(calendarYear.getText());
        clickOnGoogleCalendar.click();
        
        LocalDate today = LocalDate.now();
        LocalDate currentDate = null;
        String currentMonth = today.format(DateTimeFormatter.ofPattern("MMMM"));
        LocalDate twoDaysAhead = today.plusDays(2);
        System.out.println("Current Month: " + currentMonth);
        System.out.println("Date two days ahead: " + twoDaysAhead);
        
        
        
        }
    }
}
