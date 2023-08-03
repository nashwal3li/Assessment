package Tasks;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentManager {
    private static ExtentReports extent;
    private static ExtentTest test;
    public static ExtentReports getInstance() {
        if (extent == null) {
            String reportName = "Test-Report.html";
            ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportName);
            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
        }
        return extent;
    }

    public static ExtentTest createTest(String testName) {
        test = extent.createTest(testName);
        return test;
    }
}
