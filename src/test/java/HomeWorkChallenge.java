import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;

import java.util.Set;

import org.openqa.selenium.chrome.ChromeDriver;


public class HomeWorkChallenge {
    /**
     * • Open Google in first tab
     * • Open YouTube on the second tab
     * • Open Google translate in the third tab.
     * • From translate go to Google and from Google go to YouTube.
     */
    private static WebDriver driver;

    @BeforeClass
    public void before_all() {

        driver = DriverSingleton.getDriverInstance();
    }


    @Test
    public void test_01_handling_tabs() throws InterruptedException {
    driver.get("https://www.google.com");
    String googleTab = driver.getWindowHandle();
    ((JavascriptExecutor)driver).executeScript("window.open('https://www.youtube.com','_blank');");
    String youtubeTab = driver.getWindowHandle();
    ((JavascriptExecutor)driver).executeScript("window.open('https://translate.google.com','_blank');");
    driver.switchTo().window(googleTab);
    driver.switchTo().window(youtubeTab);


//        // Open Google in the first tab
//        driver.get("https://www.google.com");
//        String googleTab = driver.getWindowHandle();
//
//        // Open YouTube in the second tab
//        ((JavascriptExecutor) driver).executeScript("window.open('https://www.youtube.com','_blank');");
//        Set<String> windowHandles = driver.getWindowHandles();
//        String youtubeTab = "";
//        for (String handle : windowHandles) {
//            if (!handle.equals(googleTab)) {
//                youtubeTab = handle;
//            }
//        }
//
//        // Open Google Translate in the third tab
//        ((JavascriptExecutor) driver).executeScript("window.open('https://translate.google.com','_blank');");
//        windowHandles = driver.getWindowHandles();
//        String translateTab = "";
//        for (String handle : windowHandles) {
//            if (!handle.equals(googleTab) && !handle.equals(youtubeTab)) {
//                translateTab = handle;
//            }
//        }

//        // Switch to Google Translate tab
//        Thread.sleep(1000);
//        driver.switchTo().window(translateTab);
//
//        Thread.sleep(1000);
//        // Switch to Google tab
//        driver.switchTo().window(googleTab);
//        Thread.sleep(1000);
//        // Switch to YouTube tab
//        driver.switchTo().window(youtubeTab);
//
//
    }


    @AfterClass
    public void after_all() {
        driver.quit();
    }
}
