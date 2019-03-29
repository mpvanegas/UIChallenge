package drivers;

public class DriverManagerFactory {

    public static DriverManager getManager(String browser) {

        DriverManager driverManager;

        switch (browser) {
            case "chrome":
                driverManager = new ChromeDriverManager();
                break;
            default:
                driverManager = new ChromeDriverManager();
        }
        return driverManager;
    }
}
