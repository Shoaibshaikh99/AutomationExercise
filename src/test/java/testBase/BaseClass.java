package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.SocketException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

    //In this class we are mentioning commonly used methods

    public static WebDriver driver;
    public Logger logger;
    public Properties p;

    @BeforeClass(groups= {"Master","Sanity","Regression"})
    @Parameters({"os","browser"})
    public void setup(String os,String br) throws IOException {
        int retryCount = 0;
        int maxRetries = 3;
        boolean success = false;

        while (retryCount < maxRetries && !success) {
            try {
                // loading properties file
                FileReader file = new FileReader(".//src//test//resources//config.properties");
                p = new Properties();
                p.load(file);

                // Loading log4j file
                logger = LogManager.getLogger(this.getClass());

                if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {
                    DesiredCapabilities capabilities = new DesiredCapabilities();

                    // os
                    if (os.equalsIgnoreCase("windows")) {
                        capabilities.setPlatform(Platform.WIN10);
                    } else if (os.equalsIgnoreCase("mac")) {
                        capabilities.setPlatform(Platform.MAC);
                    } else {
                        System.out.println("No matching os");
                        return;
                    }

                    // browser
                    switch (br.toLowerCase()) {
                        case "chrome":
                            capabilities.setBrowserName("chrome");
                            break;
                        case "edge":
                            capabilities.setBrowserName("MicrosoftEdge");
                            break;
                        default:
                            System.out.println("No matching browser");
                            return;
                    }

                    driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
                }

                if (p.getProperty("execution_env").equalsIgnoreCase("local")) {
                    switch (br.toLowerCase()) {
                        case "chrome":
                            driver = new ChromeDriver();
                            break;
                        case "edge":
                            driver = new EdgeDriver();
                            break;
                        case "firefox":
                            driver = new FirefoxDriver();
                            break;
                        default:
                            System.out.println("Invalid browser name..");
                            return;
                    }
                }

                // If connection setup is successful
                driver.manage().deleteAllCookies();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                driver.get(p.getProperty("appURL"));
                driver.manage().window().maximize();
                success = true; // mark the connection as successful
            } catch (SocketException e) {
                System.out.println("Connection reset, retrying... (" + (retryCount + 1) + "/" + maxRetries + ")");
                retryCount++;
                try {
                    Thread.sleep(2000); // 2 seconds delay before retrying
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }

        if (!success) {
            System.out.println("Failed to connect after " + maxRetries + " attempts.");
        }
    }

    @AfterClass(groups= {"Master","Sanity","Regression"})
    public void tearDown() {
        driver.close();
    }

    public String randomString() {
        return RandomStringUtils.randomAlphabetic(5);
    }

    public String randomNumber() {
        return RandomStringUtils.randomNumeric(10);
    }

    public String randomAlphaNumeric() {
        String str = RandomStringUtils.randomAlphabetic(3);
        String num = RandomStringUtils.randomNumeric(3);
        return str + "@" + num;
    }

    // Below methods return random data 
    private String[] cities = {"Mumbai", "Delhi", "Bangalore", "Hyderabad", "Chennai", "Kolkata", "Pune", "Ahmedabad", "Jaipur", "Lucknow"};
    private String[] states = {"Maharashtra", "Delhi", "Karnataka", "Telangana", "Tamil Nadu", "West Bengal", "Maharashtra", "Gujarat", "Rajasthan", "Uttar Pradesh"};
    private String[] pincodes = {"400001", "110001", "560001", "500001", "600001", "700001", "411001", "380001", "302001", "226001"};
    private String[] countries = {"India", "United States", "Canada", "Australia", "New Zealand", "Singapore"};
    private String[] year = {"1990","1991","1992","1993","1994","1995","1996","1997", "1998", "1999", "2000"};

    private String getRandomElement(String[] elements) {
        Random rand = new Random();
        return elements[rand.nextInt(elements.length)];
    }

    public String getRandomCity() {
        return getRandomElement(cities);
    }

    public String getRandomState() {
        return getRandomElement(states);
    }

    public String getRandomPincode() {
        return getRandomElement(pincodes);
    }

    public String getRandomCountry() {
        return getRandomElement(countries);
    }

    public String getRandomYear() {
        return getRandomElement(year);
    }

    public String captureScreen(String tname) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
        File targetFile = new File(targetFilePath);
        sourceFile.renameTo(targetFile);
        return targetFilePath;
    }
}
