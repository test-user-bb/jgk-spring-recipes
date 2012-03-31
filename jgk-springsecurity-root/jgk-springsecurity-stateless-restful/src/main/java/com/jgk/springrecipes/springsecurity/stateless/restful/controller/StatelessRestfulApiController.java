package com.jgk.springrecipes.springsecurity.stateless.restful.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/api")
public class StatelessRestfulApiController {
    private static final String PARENT_REQUEST_MAPPING = "/api";
    public StatelessRestfulApiController() {
        System.out.println("StatelessRestfulController");
    }
    @RequestMapping(value="/")
    public @ResponseBody List<String> restRoot() {
        List<String> deployHelp = new ArrayList<String>();
        deployHelp.add("Root for rest");
        deployHelp.add(String.format(" - ../%s/",PARENT_REQUEST_MAPPING));
        return deployHelp;
    }
    @RequestMapping(value="/help")
    public @ResponseBody List<String> restHelp() {
        List<String> deployHelp = new ArrayList<String>();
        deployHelp.add("Help for rest");
        deployHelp.add(String.format(" - ../%s/",PARENT_REQUEST_MAPPING));
        return deployHelp;
    }
    @RequestMapping(value="/BOGEY")
    public @ResponseBody List<String> restBOGEY() {
        List<String> deployHelp = new ArrayList<String>();
        deployHelp.add("BOGEY for rest");
        deployHelp.add(String.format(" - ../%s/",PARENT_REQUEST_MAPPING));
        return deployHelp;
    }
}
