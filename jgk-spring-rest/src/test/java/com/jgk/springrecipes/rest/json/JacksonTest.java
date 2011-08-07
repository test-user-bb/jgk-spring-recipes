package com.jgk.springrecipes.rest.json;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;


public class JacksonTest {
	private ObjectMapper mapper;
	@Before
	public void setup() {
		mapper = new ObjectMapper(); // can reuse, share globally
		
	}

	@Test
	public void testSimpleBinding() {
		Map<String,Object> userData = new HashMap<String,Object>();
		Map<String,String> nameStruct = new HashMap<String,String>();
		nameStruct.put("first", "Joe");
		nameStruct.put("last", "Sixpack");
		userData.put("name", nameStruct);
		userData.put("gender", "MALE");
		userData.put("verified", Boolean.FALSE);
		userData.put("userImage", "Rm9vYmFyIQ==");
		try {
			mapper.writeValue(new File("target/user-modified-map.json"), userData);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSimple() {
		System.out.println(getJson());
		URL url = JacksonTest.class.getClassLoader().getResource("testdata/user.json");
		System.out.println(url);
		User user = null;
		try {
			user = mapper.readValue(new File(url.getFile()), User.class);
			//user = mapper.readValue(getJson(), User.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(user);
		try {
			mapper.writeValue(new File("target/user-modified.json"), user);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	String getJson() {
		StringBuilder sb = new StringBuilder();
		String SEP = "\n";
		sb.append("{").append(SEP);
		sb.append("\"name\" : { \"first\" : \"Joe\", \"last\" : \"Sixpack\" },").append(SEP);
		sb.append("\"gender\" : \"MALE\",").append(SEP);
		sb.append("\"verified\" : false,").append(SEP);
		sb.append("\"userImage\" : \"Rm9vYmFyIQ==\"").append(SEP);
		sb.append("}").append(SEP);
		return sb.toString();
	}
}
