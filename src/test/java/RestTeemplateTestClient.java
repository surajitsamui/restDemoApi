/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.demo.*;
import java.util.logging.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


/**
 *
 * @author surajit.samui
 */
public class RestTeemplateTestClient {
    private final static Logger logger = Logger.getLogger(RestTeemplateTestClient.class.getName());

    public static void main(String[] args) {
        String url1 = "http://localhost:8081demoRestApi-1.0-SNAPSHOT/webresources/books/2011;author=mkyong;country=malaysia";
        String url2 = "http://localhost:8081/demoRestApi-1.0-SNAPSHOT/webresources/greeting";
        String url3 = "http://localhost:8081/demoRestApi-1.0-SNAPSHOT/webresources/books/2011;author=surajit;country=kolkata";
        String url4 = "http://localhost:8081/demoRestApi-1.0-SNAPSHOT/webresources/greeting/query?from=100&to=200&orderBy=20&orderBy=surajit123";
        String[] urls = {url1, url2, url3, url4};
        RestTemplate restTemplate = new RestTemplate();
        for (String url : urls) {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            String responseBody = response.getBody();
            HttpHeaders httpHeaders = restTemplate.headForHeaders(url);
            System.out.println("httpHeaders == " + httpHeaders);
            System.out.println("responseBody" + responseBody);
        }
    }
}
