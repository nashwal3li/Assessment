package Tasks;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Test
public class Task1 extends DriverSetup {

    private static ExtentReports extent;
    private static ExtentTest test;
    //@BeforeTest
//   public void setup(){
////        System.setProperty("webdriver.chrome.driver","D:\\GIT Framework\\assessment\\resources\\drivers\\chromedriver.exe");
//        driver = new ChromeDriver();
//   }
    @Test
    public static void task1() throws InterruptedException, AWTException {
        // Scroll down to go to careers
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement career = driver.findElement(By.linkText("Careers"));
        js.executeScript("arguments[0].scrollIntoView();", career);
        career.click();

        //driver.wait(10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();
        //Apply
        WebElement apply = driver.findElement(By.linkText("Apply"));
        apply.click();

        // Go to Apply
        WebElement goToApply = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[1]/nav/div[1]/ul/li[5]/div/ul/li[1]/a"));
        goToApply.click();

        String mainTabHandle = driver.getWindowHandle();
        Set<String> allHandles = driver.getWindowHandles();

        for (String handle : allHandles) {
            if (!handle.equals(mainTabHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();
        //Select File
        WebElement file = driver.findElement(By.cssSelector("#career-workflow > div > div > div.modal-body > div > div > div > div.upload-resume-component > div:nth-child(2) > div > div > div > div > div > a"));
        file.click();
        // WebElement cv = driver.findElement(By.xpath("//*[@id=\"career-workflow\"]/div/div/div[2]/div/div/div/div[2]/div[2]/div/div/div"));
        //cv.sendKeys("D:\\Personal Data\\CVs\\Nashwa-Ali-Kandil");
        String filePath = "D:\\Personal Data\\CVs\\Nashwa-Ali-Kandil.pdf";
        //file.sendKeys(filePath);
        Robot robot = new Robot();
        StringSelection stringSelection = new StringSelection("filePath");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_C);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        // Press Enter to confirm the file selection
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        // Verify The CV is uploaded successfully

        extent = ExtentManager.getInstance();
        test = ExtentManager.createTest("Test Case1");
        extent.flush();

    }

}
