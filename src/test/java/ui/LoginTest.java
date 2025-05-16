package ui;

import config.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
import java.util.Properties;
import java.io.FileInputStream;

public class LoginTest {
    private WebDriver driver;
    private Properties props = new Properties();

    @BeforeClass
    public void setup() throws Exception {
        FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
        props.load(fis);
        driver = DriverFactory.getDriver(props.getProperty("browser"));
        driver.get(props.getProperty("baseUrl"));
    }

    @Test
    public void testLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(props.getProperty("username"), props.getProperty("password"));
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login should be successful");
    }

    @AfterClass
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
