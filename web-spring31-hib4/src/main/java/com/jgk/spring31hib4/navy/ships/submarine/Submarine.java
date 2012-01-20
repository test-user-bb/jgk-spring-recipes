package com.jgk.spring31hib4.navy.ships.submarine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Submarine {
    private List<String> subsystems;
    public void init() {
        System.out.println("init on submarine");
    }
    public Submarine(String..._subsystems) {
        this.subsystems = new ArrayList<String>();
        this.subsystems.addAll(Arrays.asList(_subsystems));
    }
    @PostConstruct public void before() {
        System.out.println("Just constructed: "+this.getClass().getName());
    }
    @PreDestroy public void after() {
        System.out.println("Before destroy: "+this.getClass().getName());
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Submarine [subsystems=");
        builder.append(subsystems);
        builder.append("]");
        return builder.toString();
    }
}
