
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author surajit.samui
 */
public class TokenAuthentication {

    private final static Logger logger = Logger.getLogger(RestTeemplateTestClient.class.getName());

    public static void main(String[] args) {
        System.out.println("Hi surajit this token ");

    }

    public static String tokenAuthentication(String inputRequest, Properties props1, Properties dbFlagprops, Properties propsFolderKeys, Properties URLprops) throws IOException {
        String dbFlag = dbFlagprops.getProperty("db_Flag");
        Long THIS_APP_ID = 148395L;
        final String appPwd = "surajit123";
        final Long OTHERAPP_ID = 213l;
        final String CONTEXT = "hjsdgfhdjfgfhjgew";
        final Boolean oneTimeToken = false;
        String output = "", urlString = "";
        logger.info("THIS_APP_ID:::::::" + THIS_APP_ID);
        logger.info("OTHERAPP_ID:::::::" + OTHERAPP_ID);
        String auth_token = "";
        try {
            auth_token = "sdjhfgwyufgweyur";
//            JSONObject obj = (JSONObject) JSONSerializer.toJSON(inputRequest);
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "Exception Message", e);
        }
        try {
            System.setProperty("ENV_name", "prod-https");
            logger.info("Initiating the token...");
            try {
                if (dbFlag.equals("PROD")) {
                    urlString = URLprops.getProperty("prod_url");
                } else {
                    urlString = URLprops.getProperty("other_url");
                }
                urlString = "https://url/token/generate";
                System.out.println("urlString is " + urlString);
                HttpURLConnection conn = (HttpURLConnection) new URL(urlString).openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                Map<String, Object> jsonParams = new LinkedHashMap();
                jsonParams.put("appId", THIS_APP_ID);
                jsonParams.put("appPassword", appPwd);
                jsonParams.put("otherApp", OTHERAPP_ID);
                jsonParams.put("context", CONTEXT);
                jsonParams.put("oneTimeToken", oneTimeToken);
                jsonParams.put("token", auth_token);
                JSONObject json = new JSONObject();
                json.putAll(jsonParams);
                OutputStream os = conn.getOutputStream();
                System.out.println("os value is " + os);
                os.write(json.toString().getBytes());
                System.out.println("os value is " + os);
                os.flush();
                os.close();
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), Charset.forName("UTF-8")));
                System.out.println("reader value==" + reader);
                String s = null;
                while ((s = reader.readLine()) != null) {
                    System.out.println(s);
                }
                output = reader.readLine();
                System.out.println("output value==" + output);
                conn.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }
}
