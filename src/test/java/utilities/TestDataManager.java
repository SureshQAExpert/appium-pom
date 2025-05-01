package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestDataManager {

	// Read the configProperties
	 private static Properties properties;

	    static {
	        try {
	            FileInputStream fis = new FileInputStream("src/test/resources/test-data/config.properties");
	            properties = new Properties();
	            properties.load(fis);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public static String getProperty(String key) {
	        return properties.getProperty(key);
	    }
	    
	 // Read the Json data
	    private static final String filePath = "src\\test\\resources\\test-data\\global_test_data.json";
	    private static JSONObject jsonObject = null;

	    static {
	        try {
	            String content = new String(Files.readAllBytes(Paths.get(filePath)));
	            jsonObject = new JSONObject(content);
	        } catch (Exception e) {
	            System.err.println("Error reading JSON file: " + e.getMessage());
	            e.printStackTrace();
	        }
	    }

	    public static JSONObject getJson() {
	        return jsonObject;
	    }

}
