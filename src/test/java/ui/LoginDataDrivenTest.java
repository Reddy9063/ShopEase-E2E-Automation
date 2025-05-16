package ui;

import config.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
import data.LoginDataProvider;
import java.util.Properties;
import java.io.FileInputStream;

public class LoginDataDrivenTest {
    private WebDriver driver;
    private Properties props = new Properties();

    @BeforeClass
    public void setup() throws Exception {
        FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
        props.load(fis);
        driver = DriverFactory.getDriver(props.getProperty("browser"));
        driver.get(props.getProperty("baseUrl"));
    }

    @Test(dataProvider = "loginData", dataProviderClass = data.LoginDataProvider.class)
    public void testLogin(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        // Example: only pass for correct creds
        if (username.equals(props.getProperty("username")) && password.equals(props.getProperty("password"))) {
            Assert.assertTrue(loginPage.isLoginSuccessful(), "Login should be successful");
        } else {
            Assert.assertFalse(loginPage.isLoginSuccessful(), "Login should fail for invalid credentials");
        }
    }

    @AfterClass
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
