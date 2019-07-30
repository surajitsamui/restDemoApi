
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author surajit.samui
 */
public class PropertiFileload {
    
    private final static Logger logger = Logger.getLogger(RestTeemplateTestClient.class.getName());
    public static void main(String[] args) throws IOException {
        String resourceName="databaseprop.properties";
        Properties prop=loadAllProperties(resourceName);
        String url=prop.getProperty("spring.datasource.url");
        System.out.println("url is "+url);
    }
    
    public static Properties loadAllProperties(String resourceName) throws IOException {

        Properties props = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try (InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
            props.load(resourceStream);
        } catch (IOException i) {
            i.printStackTrace();
            logger.log(Level.SEVERE, "Exception Message", i);
            logger.info(props.getProperty("User"));
        }
        return props;
    }
    
}
