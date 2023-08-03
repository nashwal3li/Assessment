package Tasks;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DriverSetup extends ExtentManager {

     static WebDriver driver;
    private String browser;

@BeforeTest
@Parameters({"browser"})
    public void setup(@Optional("chrome") String browser) throws IOException {
        this.browser = browser;
        if(browser.equalsIgnoreCase("Chrome")){
            //System.setProperty("webdriver.chrome.driver","D:\\GIT Framework\\assessment\\resources\\drivers\\chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")){
           // System.setProperty("webdriver.gecko.driver","D:\\GIT Framework\\assessment\\resources\\drivers\\geckodriver.exe");
            driver = new FirefoxDriver();
        }
         String propertyFilePath = "D:\\GIT Framework\\assessment\\resources\\config\\cconfig.properties";
         Properties properties = new Properties();
         FileInputStream inputStream = new FileInputStream(propertyFilePath);
         properties.load(inputStream);
         String url = properties.getProperty("url");
         driver.get(url);
    }

    @AfterTest
    public void tearDown(){
        if(driver !=null){
         //   driver.quit();
        }
    }









//     private static Properties properties;
//     private static ConfigReader configReader;
//     private final String filePath ="D:\\GIT Framework\\assessment\\resources\\config\\cconfig.properties";
//     public DriverSetup() {
//        properties = new Properties();
//        try {
//            FileInputStream file = new FileInputStream(filePath);
//            properties.load(file);
//            file.close();
//        } catch (IOException e) {
//                e.printStackTrace();
//                throw new RuntimeException("Configuration file is not found");
//        }
//    }
//    public static ConfigReader getInstance() {
//             if (configReader == null) {
//                 try {
//                     configReader = new ConfigReader();
//                 } catch (SAXException e) {
//                     throw new RuntimeException(e);
//                 } catch (IOException e) {
//                     throw new RuntimeException(e);
//                 }
//             }
//        return configReader;
//    }
//    public static String getBrowser(){
//    String browser = properties.getProperty("chromebrowser");
//        if (browser != null) {
//            return browser;
//        }
//        else {
//            throw new RuntimeException("browser not found in the Configuration file");
//        }
//    }
//
//
//    public String getURL() {
//        String url = properties.getProperty("VodafoneURL");
//        if(url != null)
//            return url;
//        else
//            throw new RuntimeException("URL not found in Configuration file");
//    }

}
