package com.jgk.springrecipes.springsecurity.controller.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyRestController {
    public MyRestController() {
        System.out.println("MyRestController");
    }
    
    @RequestMapping(value="/somehelp")
    public @ResponseBody List<String> somehelp() {
        List<String> deployHelp = new ArrayList<String>();
        deployHelp.add("Help for MyRestController");
        deployHelp.add(String.format(" - ../%s/","somehelp"));
        return deployHelp;
    }

    @RequestMapping(value="/jedi")
    public @ResponseBody List<String> jedi() {
        List<String> deployHelp = new ArrayList<String>();
        deployHelp.add("Help for MyRestController");
        deployHelp.add(String.format(" - ../%s/","jedi"));
        return deployHelp;
    }

}
