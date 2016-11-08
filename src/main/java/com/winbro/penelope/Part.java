package com.winbro.penelope;

import com.winbro.penelope.data.PartData;

import java.lang.*;
import java.util.*;

public class Part {

    private final PartType partType;
    private long id;
    private String name;
    private Map<String, Process> processes;
    private INode location;

    public Part(long id, PartType type, String name) {
        this.id = id;
        this.name = name;
        this.partType = type;
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

    public PartData getPartData() {
        PartData result = new PartData();
        result.setId(id);
        result.setPart(name);
        result.setPartType(partType.getName());
        return result;
    }
}
