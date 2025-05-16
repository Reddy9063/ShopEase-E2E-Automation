package api;

import io.restassured.response.Response;
import org.testng.annotations.*;
import utils.ApiUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.io.FileInputStream;

public class ProductApiTest {
    private Properties props = new Properties();

    @BeforeClass
    public void setup() throws Exception {
        FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
        props.load(fis);
    }

    @Test
    public void testGetProducts() {
        String url = props.getProperty("apiBaseUrl") + "/products";
        Response response = ApiUtils.get(url, new HashMap<>());
        ApiUtils.validateStatusCode(response, 200);
    }
}
