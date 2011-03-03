package com.jgk.springrecipes.mvc.release.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/rest")
public class RestController {
	
	@SuppressWarnings("serial")
	List<String> releases = new ArrayList<String>() {{
		add("gs-1.0.2"); 
		add("gs-1.0.3"); 
		add("gs-1.0.5"); 
	}};
	
	@RequestMapping(value="/releases",method=RequestMethod.GET) 
	public ModelAndView getReleases() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("releases");
		mav.addObject("releases", releases);
		return mav;
	}
}
