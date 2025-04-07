import org.openqa.selenium.By;

public class CalculatorPage {
    public static void pressEight() {
        DriverSingleton.getDriverInstance().findElement(By.id("eight")).click();
    }

    public static void pressPlus() {
        DriverSingleton.getDriverInstance().findElement(By.id("add")).click();
    }

    public static void pressEquals() {
        DriverSingleton.getDriverInstance().findElement(By.id("equal")).click();
    }

    public static String getResult() {
        return DriverSingleton.getDriverInstance().findElement(By.id("screen")).getText();
    }
}
