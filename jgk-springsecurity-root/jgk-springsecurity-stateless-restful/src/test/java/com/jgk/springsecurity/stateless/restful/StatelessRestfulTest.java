package com.jgk.springsecurity.stateless.restful;
import static com.jayway.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Header;
import com.jayway.restassured.response.Headers;
import com.jayway.restassured.response.Response;

public class StatelessRestfulTest {
    
    @Test public void tester() {
        String[] restfulUrls = new String[] {
                "http://localhost:8080/jgk-springsecurity-stateless-restful/rest/api/",
                "http://localhost:8080/jgk-springsecurity-stateless-restful/rest/api/help/",
                "http://localhost:8080/jgk-springsecurity-stateless-restful/rest/api/BOGEY/"
        };
        for (String restfulUrl : restfulUrls) {
            checkUrl(restfulUrl);
        }
    }
    private void checkUrl( String restfulUrl ) {
        Response r = RestAssured.given().auth().preemptive().basic("rest", "rest").get(restfulUrl, new HashMap<String,String>());
        System.out.println(r.asString());
        
    }
    private String interrogateResponse(Response r) {
        StringBuilder sb = new StringBuilder();
        sb.append(r.asString());
        //expect().body("lotto.lottoId", equalTo(5)).when().get("/gs-deploy");
     // Get all headers
        Headers allHeaders = r.getHeaders();
        for (Header header : allHeaders) {
            sb.append("|");
            sb.append("header:"+header);
        }
     // Get all cookies as simple name-value pairs
        Map<String, String> allCookies = r.getCookies();
        sb.append("Cookies: " + allCookies);
        
        int statusCode = r.getStatusCode();
        sb.append("|");
        sb.append("Status code: "+statusCode);
        return sb.toString();
    }

}
