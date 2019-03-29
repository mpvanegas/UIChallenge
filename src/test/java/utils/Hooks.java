package utils;

import drivers.DriverFactory;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.HomePage;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Hooks {

    protected WebDriver webDriver;

    @Rule
    public TestRule watcher = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            DateFormat dateFormat = new SimpleDateFormat("YY_MM_dd_HH_mm_ss");
            Date date = new Date();
            String test = description.getMethodName();
            System.out.println(test);
            File srcFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
            File targetFile = new File("failedTests/" + test + "_" + dateFormat.format(date) + ".png");
            try {
                FileUtils.copyFile(srcFile, targetFile);
            } catch (IOException er) {
                e.printStackTrace();
            }
        }

        @Override
        protected void finished(Description description) {
            webDriver.quit();
        }
    };
    protected HomePage homePage;
    protected CommonFlows commonFlows;

    @Before
    public void setup() {

        webDriver = DriverFactory.createDriver(Configs.getValueByKey("browser"));

        webDriver.get("http://ecofoodmarket.herokuapp.com/");
        commonFlows = new CommonFlows(webDriver);
        homePage = PageFactory.initElements(webDriver, HomePage.class);
    }
}
