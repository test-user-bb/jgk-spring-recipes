package com.jgk.springrecipes.rest.json;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

public class JacksonTest {

	@Test
	public void testSimple() {
		System.out.println(getJson());
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
		//User user = mapper.readValue(new File("user.json"), User.class);
		User user = null;
		try {
			user = mapper.readValue(getJson(), User.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(user);
	}

	String getJson() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"name\" : { \"first\" : \"Joe\", \"last\" : \"Sixpack\" },");
		sb.append("\"gender\" : \"MALE\",");
		sb.append("\"verified\" : false,");
		sb.append("\"userImage\" : \"Rm9vYmFyIQ==\"");
		sb.append("}");
		return sb.toString();
	}
}
