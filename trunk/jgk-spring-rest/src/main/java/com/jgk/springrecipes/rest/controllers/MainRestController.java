package com.jgk.springrecipes.rest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainRestController {
	
	@RequestMapping(value="/catalog")
	public @ResponseBody String gravy() {
		StringBuilder sb = new StringBuilder();
		sb.append("<html><body>Gravy time</body></html>");
		return sb.toString();
	}

}
