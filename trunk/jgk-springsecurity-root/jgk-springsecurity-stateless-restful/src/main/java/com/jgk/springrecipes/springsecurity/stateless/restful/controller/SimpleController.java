package com.jgk.springrecipes.springsecurity.stateless.restful.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SimpleController {
    public SimpleController() {
        System.out.println("SimpleController");
    }
    @RequestMapping(value="/simple")
    public @ResponseBody List<String> restRoot() {
        List<String> deployHelp = new ArrayList<String>();
        deployHelp.add("Root for root");
        deployHelp.add(String.format(" - ../%s/","SOMI"));
        return deployHelp;
    }
    @RequestMapping(value="/help")
    public @ResponseBody List<String> restHelp() {
        List<String> deployHelp = new ArrayList<String>();
        deployHelp.add("Help for root");
        deployHelp.add(String.format(" - ../%s/","HELPY"));
        return deployHelp;
    }
    @RequestMapping(value="/OUCHER")
    public @ResponseBody List<String> restBOGEY() {
        List<String> deployHelp = new ArrayList<String>();
        deployHelp.add("OUCHER for ROOT");
        deployHelp.add(String.format(" - ../%s/","CLJVOIJSOJFS"));
        return deployHelp;
    }
}
