package com.jgk.spring31hib4.navy.ships.submarine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Submarine {
    private List<String> subsystems;
    public Submarine(String..._subsystems) {
        this.subsystems = new ArrayList<String>();
        this.subsystems.addAll(Arrays.asList(_subsystems));
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
