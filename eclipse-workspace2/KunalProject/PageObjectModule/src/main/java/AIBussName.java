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

public class AIBussName {

    private static WebDriver driver;
    private static WebDriverWait wait;

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        initializeDriver();
        navigateToLoginPage();
        selectLanguage("English US");
        login("kunal.kumar+dummy76sdsss22SaaaSSS@bridgingtech.com", "qwerty@123@");
        validateWelcomeText();
        validateCreditAmount();
        closeBrowser();
    }

    private static void initializeDriver() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        // options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        wait = new WebDriverWait(driver, 15);
        Thread.sleep(15000);
    }

    private static void navigateToLoginPage() {
        driver.get("https://testbusiness.practina.com");
    }

    private static void selectLanguage(String language) {
        List<WebElement> langNames = driver.findElements(By.xpath("//ul[@class='language-list list-inline text-center d-md-block d-none']/li"));

        for (WebElement homeLanguages : langNames) {
            if (homeLanguages.getText().equalsIgnoreCase(language)) {
                homeLanguages.click();
                return;
            }
        }

        // Handle the case when the language is not found in the home languages
        driver.findElement(By.xpath("//span[@class='mat-menu-trigger cursor']")).click();
        List<WebElement> matLanguages = driver.findElements(By.xpath("//div[@class='overflow-auto more_menu--innr ng-tns-c145-1']"));
        for (WebElement currentLanguages : matLanguages) {
            System.out.println(currentLanguages.getText());
        }
    }

    private static void login(String username, String password) {
        driver.findElement(By.xpath("//input[@formcontrolname='email']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@class='generate-content']")).click();
        wait.until(ExpectedConditions.jsReturnsValue("return window.localStorage.getItem('user');"));
    }

    private static void validateWelcomeText() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String userObject = (String) jsExecutor.executeScript("return window.localStorage.getItem('user');");
        Object userJsonObject = new org.json.JSONObject(userObject);

        String bussName = ((org.json.JSONObject) userJsonObject).getString("buss_name");
        String welcomeText = driver.findElement(By.xpath("//h4[@class='fw-800 text-left text-nowrap mb-3']")).getText().trim();
        String expectedWelcomeText = bussName.isEmpty() ? "Welcome" : "Welcome, " + bussName;
        Assert.assertEquals(welcomeText, expectedWelcomeText, "Welcome text does not match the expected value");
        System.out.println("Welcome name and business name are matched");
    }

    private static void validateCreditAmount() {
        String creditAvailable = driver.findElement(By.xpath("(//span[@class='notranslate'])[3]")).getText();

        // Check if the string is not empty before parsing
        if (!creditAvailable.isEmpty()) {
            double actualCreditAmount = Double.parseDouble(creditAvailable);
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            String userObject = (String) jsExecutor.executeScript("return window.localStorage.getItem('user');");
            Object userJsonObject = new org.json.JSONObject(userObject);

            double creditBalance = ((org.json.JSONObject) userJsonObject).getDouble("credit_balance");
            double wallet = ((org.json.JSONObject) userJsonObject).getDouble("wallet");

            Assert.assertTrue(actualCreditAmount == creditBalance || actualCreditAmount == wallet,
                    "Credit amount isn't correct");
        } else {
            System.out.println("Amount is not displayed or present");
        }
    }

    private static void closeBrowser() {
        driver.quit();
    }
}
