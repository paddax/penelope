package com.winbro.penelope;

import java.lang.*;
import java.util.*;

public class Part {

    private String name;
    private Map<String, Process> processes;
    private INode location;

    public Part(PartType type, String name) {
        this.name = name;
        this.processes = new LinkedHashMap<>();
        this.processes.putAll(type.getProcesses());
    }

    public String getName() {
        return name;
    }

    public Map<String, Process> getProcesses() {
        return processes;
    }

    public Collection<Process> getProcessList() {
        return processes.values();
    }

    public INode getLocation() {
        return location;
    }

    public void setLocation(INode location) {
        this.location = location;
    }
}
