package data;

import org.testng.annotations.DataProvider;

public class LoginDataProvider {
    @DataProvider(name = "loginData")
    public static Object[][] loginData() {
        return new Object[][] {
            {"testuser", "testpass"},
            {"invaliduser", "invalidpass"}
        };
    }
}
