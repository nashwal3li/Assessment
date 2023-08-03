package Tasks;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

@Test
public class Task2 extends DriverSetup {

    private static ExtentReports extent;
    private static ExtentTest test;
    public static void task2() throws InterruptedException {

        WebElement manageAccount = driver.findElement(By.linkText("Manage Account"));
        manageAccount.click();


        //Change to English Language
//        WebElement english = driver.findElement(By.linkText("English"));
//        english.click();

        //Press on forget Password
        WebElement forgetPassword = driver.findElement(By.id("goToRestPassword"));
        forgetPassword.click();

        WebElement invalidMSISDN = driver.findElement(By.id("username"));
        invalidMSISDN.sendKeys("12336786");

        WebElement captcha = driver.findElement(By.linkText("recaptcha-checkbox-border"));
        captcha.click();

        WebElement submit = driver.findElement(By.id("input-mobile-trigger"));
        submit.click();

        // Here there is an error while automating a (i am not a robot) captcha because it handels that the user is not a robot, but in this case the user is a robot.

        extent = ExtentManager.getInstance();
        test = ExtentManager.createTest("Test Case2");
        extent.flush();


    }


}
