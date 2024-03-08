package utils;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SettingsManager {
    private static final String SETTINGS_FILE = "settings.properties";

    private Properties properties;

    public SettingsManager() {
        properties = new Properties();
        loadSettings();
    }

    private void loadSettings() {
        try (FileInputStream input = new FileInputStream(SETTINGS_FILE)) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getRepositoryType() { return properties.getProperty("Repository");}

    public String getCarRepositoryFile() {
        return properties.getProperty("Cars");
    }

    public String getCustomerRepositoryFile() {
        return properties.getProperty("Customers");
    }

    public String getRentalRepositoryFile() {
        return properties.getProperty("Rentals");
    }
}
