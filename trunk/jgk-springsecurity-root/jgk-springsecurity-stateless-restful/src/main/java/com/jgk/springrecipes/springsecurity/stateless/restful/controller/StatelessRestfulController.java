package com.jgk.springrecipes.springsecurity.stateless.restful.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/rest")
public class StatelessRestfulController {
    private static final String PARENT_REQUEST_MAPPING = "/rest";
    public StatelessRestfulController() {
        System.out.println("StatelessRestfulController");
    }
    @RequestMapping(value="/help")
    public @ResponseBody List<String> adminHelp() {
        List<String> deployHelp = new ArrayList<String>();
        deployHelp.add("Help for admin");
        deployHelp.add(String.format(" - ../%s/",PARENT_REQUEST_MAPPING));
        return deployHelp;
    }
}
