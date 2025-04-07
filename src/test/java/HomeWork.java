import com.paulhammant.ngwebdriver.ByAngular;
import com.paulhammant.ngwebdriver.NgWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class HomeWork {
    public static WebDriver driver;
    private static NgWebDriver ngWebDriver;


    @BeforeClass
    public void before_class() {
        driver = DriverSingleton.getDriverInstance();
//        System.setProperty("webdriver.chrome.driver", Constants.CHROMEDRIVER_PATH);
//        driver = new ChromeDriver();
        ngWebDriver = new NgWebDriver((JavascriptExecutor) driver);

    }

//1

    /**
     * 1.
     * <p>
     *  Enter https://dgotlieb.github.io/Selenium/synchronization.html
     *  Press on “remove checkbox” button and find the new element
     * that will show up under the CheckBox - use Implicit Wait.
     *  Press on “Show hidden” button and find the new element that
     * will replace the “Show hidden” button - use Thread.sleep().
     *  Press on “render” button and find the new element that will
     * show under “render” button – use Explicit Wait.
     */

    @Test
    public void test_01_implicitly_wait() {
        driver.get("https://dgotlieb.github.io/Selenium/synchronization.html");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println(driver.findElement(By.id("checkbox")).isDisplayed());
        driver.findElement(By.id("btn")).click();
        driver.findElement(By.id("message")).isDisplayed();
    }

    @Test
    public void test_02_implicitly_wait() throws InterruptedException {
        driver.get("https://dgotlieb.github.io/Selenium/synchronization.html");
        driver.findElement(By.id("hidden")).click();
        Thread.sleep(10000);
        driver.findElement(By.cssSelector("div[style='']")).isDisplayed();
    }

    @Test
    public void test_03_explicit_wait() {
        driver.get("https://dgotlieb.github.io/Selenium/synchronization.html");
        driver.findElement(By.id("rendered")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish2")));
        String output = driver.findElement(By.id("finish2")).getText();
        assertEquals(output, "This is a new element");
    }


    //2

    /**
     * Using ngWebDriver
     * (https://github.com/paul-hammant/ngWebDriver)
     *  Enter https://dgotlieb.github.io/AngularJS/main.html
     *  Clear first name.
     *  Enter your first name instead.
     *  Assert result.
     */

    @Test
    public void test_04_angular_test() {

        driver.navigate().to("https://dgotlieb.github.io/AngularJS/main.html");
        ngWebDriver.waitForAngularRequestsToFinish();
        WebElement firstname = driver.findElement(ByAngular.model("firstName"));
        firstname.clear();
        firstname.sendKeys("Serzh");
        assertEquals(driver.findElement(By.xpath("//input")).getAttribute("value"), "Serzh");
    }

    //3

    /**
     * What PageLoadTimeOut is used for?
     */
//    defines the amount of time that Selenium will wait for a page to load
//    in Selenium WebDriver is used to set the maximum amount of time the driver should wait for a page to load before throwing an error
    @AfterClass

    //4
    /** Create a TestNG test using tests efficiency techniques:
     1. Constants.
     2. POM (Page Object Model).

      Enter https://dgotlieb.github.io/WebCalculator/
      Print “7” button dimensions (using Constant).
      Check if “6” button is displayed
      Prepare an int variable with any number
      Calculate a mathematical formula that will give the result you
     choose in the int variable earlier (using POM)
      Use assert to check if you got the expected result
      Use a Singleton class to create and return your driver. */

    @Test
    public void test_05_POM() throws InterruptedException {

        driver.get("https://dgotlieb.github.io/WebCalculator");
        System.out.println(driver.findElement(By.id(Constants.SEVEN)).getSize());
        System.out.println(driver.findElement(By.id("six")).isDisplayed());
        CalculatorPage.pressEight();
        CalculatorPage.pressPlus();
        CalculatorPage.pressEight();
        CalculatorPage.pressEquals();
        Thread.sleep(1000);
        String expectedResult = "16";
        assertEquals(expectedResult, CalculatorPage.getResult());

    }


    public void after_all() {
        driver.quit();
    }


}
