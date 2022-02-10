package resources;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Parameters;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {

    public WebDriver driver;
    public Properties prop;

    @Parameters("browser")
    public WebDriver initializeDriver(String browser) throws IOException
    {



        if(browser.equals("chrome"))
        {
            System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
            driver= new ChromeDriver();

        }
        else if (browser.equals("firefox"))
        {
            driver= new FirefoxDriver();
        }
        else if(browser.equalsIgnoreCase("IE"))
        {
            System.setProperty("webdriver.ie.driver", "drivers\\IEDriverServer.exe");
            driver = new InternetExplorerDriver();

        }
        driver.manage().window().maximize();


        return driver;


    }


}
