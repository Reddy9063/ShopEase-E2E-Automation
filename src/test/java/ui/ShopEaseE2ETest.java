package ui;

import config.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.TestUtils;
import java.util.Properties;
import java.io.FileInputStream;

public class ShopEaseE2ETest {
    private WebDriver driver;
    private Properties props = new Properties();

    @BeforeClass
    public void setup() throws Exception {
        FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
        props.load(fis);
        driver = DriverFactory.getDriver(props.getProperty("browser"));
        driver.get(props.getProperty("baseUrl"));
    }

    @Test(priority = 1)
    public void testLogin() {
        driver.findElement(By.id("username")).sendKeys(props.getProperty("username"));
        driver.findElement(By.id("password")).sendKeys(props.getProperty("password"));
        driver.findElement(By.id("loginBtn")).click();
        Assert.assertTrue(driver.getPageSource().contains("Welcome"), "Login failed!");
    }

    @Test(priority = 2, dependsOnMethods = "testLogin")
    public void testAddToCartAndOrder() {
        // Add first product to cart
        driver.findElements(By.linkText("Add to Cart")).get(0).click();
        Assert.assertTrue(driver.getPageSource().contains("Cart"), "Cart not updated!");
        // Place order
        driver.findElement(By.xpath("//button[text()='Place Order']")).click();
        Assert.assertTrue(driver.getPageSource().contains("Order placed!"), "Order not placed!");
    }

    @Test(priority = 3, dependsOnMethods = "testAddToCartAndOrder")
    public void testOrderHistory() {
        driver.findElement(By.linkText("Order History")).click();
        Assert.assertTrue(driver.getPageSource().contains("Order 1"), "Order history not found!");
    }

    @AfterMethod
    public void captureOnFailure(ITestResult result) {
        TestUtils.logFailure(result, driver);
    }

    @AfterClass
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
