import java.time.Duration;
		import java.util.ArrayList;
		import java.util.Arrays;
		import java.util.List;
		import java.util.concurrent.TimeUnit;

		import org.openqa.selenium.By;
		import org.openqa.selenium.JavascriptExecutor;
		import org.openqa.selenium.StaleElementReferenceException;
		import org.openqa.selenium.WebDriver;
		import org.openqa.selenium.WebElement;
		import org.openqa.selenium.chrome.ChromeDriver;
		import org.openqa.selenium.chrome.ChromeOptions;
		import org.openqa.selenium.support.ui.ExpectedConditions;
		import org.openqa.selenium.support.ui.WebDriverWait;
		import org.testng.Assert;

		import io.github.bonigarcia.wdm.WebDriverManager;
public class dashboard 
{


		    private static WebDriverWait wait;

		    public static void main(String[] args) throws InterruptedException {
		        WebDriverManager.chromedriver().setup();
		        ChromeOptions options = new ChromeOptions();
//		        options.addArguments("--disable-notifications");
		        WebDriver driver = new ChromeDriver(options);
		        driver.manage().window().maximize();
		        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
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

//		        driver.findElement(By.xpath("//span[@class='cursor']")).click();
		        Thread.sleep(2000);
		        driver.findElement(By.xpath("//input[@formcontrolname='email']")).sendKeys("kunal.kumar+dummy76sdsss22SaaaSSS@bridgingtech.com");
		        driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys("qwerty@123@");
		        driver.findElement(By.xpath("//button[@class='generate-content']")).click();
		        Thread.sleep(15000);
//		        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//		        wait.until(ExpectedConditions.jsReturnsValue("return window.localStorage.getItem('user');"));
		        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		        String localStorageData = (String) jsExecutor.executeScript("return window.localStorage.getItem('user');");
		        System.out.println(localStorageData);
		        String userObject = (String) jsExecutor.executeScript("return window.localStorage.getItem('user');");
		        Object userJsonObject = new org.json.JSONObject(userObject);
		        String token = ((org.json.JSONObject) userJsonObject).getString("token");
		        System.out.println(token);
		        String redirection_url = ((org.json.JSONObject) userJsonObject).getString("redirection_url");
		        System.out.println(redirection_url);

		        if (redirection_url.contains("SUBSCRIPTION_EXPIRED")) {
		            driver.findElement(By.xpath("//button[@class='w-100 fs-16 fw-600 ng-star-inserted']")).click();
		            Thread.sleep(3000);
//		            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(), ' Credit/Debit Card ')]")));
		            driver.findElement(By.xpath("//img[@class='mr-2 fs-16 fw-600']")).click();
		            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='privacy_checkbox']")));
		            driver.findElement(By.xpath("//input[@id='privacy_checkbox']")).click();
		            Thread.sleep(3000);

		            driver.findElement(By.xpath("//input[@id='privacy_checkbox']")).click();
		        } else {
		            Thread.sleep(2000);
		            driver.findElement(By.xpath("//span[@class='menu-close-icon bg-white position-absolute rounded-pill cursor']")).click();
		            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='menu__list']/li")));

		            List<String> expectedUrls = new ArrayList<>(Arrays.asList(
		                    "https://testbusiness.practina.com/posts/published",
		                    "https://testbusiness.practina.com/ai-content/generate?ctype=2",
		                    "https://testbusiness.practina.com/ai-content/generate?ctype=3",
		                    "https://testbusiness.practina.com/ai-content/generate?ctype=1",
		                    "https://testbusiness.practina.com/reputation-management",
		                    "https://testbusiness.practina.com/settings/edit-language"
		            ));

		            for (int i = 0; i < expectedUrls.size(); i++) {
		                try {
		                    // Re-find the elements to avoid StaleElementReferenceException
		                    List<WebElement> updatedMenu = driver.findElements(By.xpath("//div[@class='row card-container']/div/div/p"));

		                    // Click on each element
		                    updatedMenu.get(i).click();

		                    // Wait for the page to load or handle any asynchronous behavior
		                    // Add appropriate wait conditions based on your application

		                    // Apply assertion on the current URL based on the corresponding expected URL
		                    String expectedUrl = expectedUrls.get(i);
		                    String currentUrl = driver.getCurrentUrl();
		                    System.out.println(currentUrl);
		                    Assert.assertEquals(currentUrl, expectedUrl, "URLs do not match");
		                    Thread.sleep(2000);

		                    // Navigate back to the original page for the next iteration
		                    driver.navigate().back();
		                    Thread.sleep(2000);

		                } catch (StaleElementReferenceException e) {
		                    // Handle StaleElementReferenceException if it occurs
		                    System.out.println("StaleElementReferenceException: Element is no longer attached to the DOM");
		                }
		            }
		        }
		    }
		
}


