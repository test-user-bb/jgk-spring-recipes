package com.jgk.springrecipes.mvc.jed.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller//(value="FunksterJoe")
public class HelloWorldController {
    @RequestMapping("/helloWorld")
    public ModelAndView helloWorld() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("helloWorld");
        mav.addObject("message", "Hello World!");
        return mav;
    }
    @RequestMapping(value="/owners/{ownerId}", method=RequestMethod.GET)
    public String findOwner(@PathVariable String ownerId, Model model) {
//      Owner owner = ownerService.findOwner(ownerId);
    	String owner = "Fred Flintstone";
      model.addAttribute("owner", owner);  
      return "displayOwner"; 
    }    
    @RequestMapping(value="/meds/bymedid/{medId}", method=RequestMethod.GET)
    public String findMedName(@PathVariable("medId") String medId, Model model) {
    	System.out.println("medId:"+medId);
    	String medName="Aspirin";
      model.addAttribute("medName", medName);  
      return "displayMedName"; 
    }    
}