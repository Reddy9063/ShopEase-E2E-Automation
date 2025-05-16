package api;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.ApiUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.io.FileInputStream;

public class ShopEaseApiTest {
    private Properties props = new Properties();

    @BeforeClass
    public void setup() throws Exception {
        FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
        props.load(fis);
    }

    @Test(priority = 1)
    public void testGetProducts() {
        String url = props.getProperty("apiBaseUrl") + "/products";
        Response response = ApiUtils.get(url, new HashMap<>());
        ApiUtils.validateStatusCode(response, 200);
        Assert.assertTrue(response.jsonPath().getList("name").size() > 0, "No products found!");
    }

    @Test(priority = 2)
    public void testGetOrders() {
        String url = props.getProperty("apiBaseUrl") + "/orders?username=" + props.getProperty("username");
        Response response = ApiUtils.get(url, new HashMap<>());
        ApiUtils.validateStatusCode(response, 200);
        // Orders may be empty if no order placed yet
        Assert.assertNotNull(response.jsonPath().getList("items"));
    }
}
