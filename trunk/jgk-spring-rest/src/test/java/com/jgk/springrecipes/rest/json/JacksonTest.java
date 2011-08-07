package com.jgk.springrecipes.rest.json;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

public class JacksonTest {

	@Test
	public void testSimple() {
		System.out.println(getJson());
		URL url = JacksonTest.class.getClassLoader().getResource("testdata/user.json");
		System.out.println(url);
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
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
