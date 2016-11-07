package com.winbro.penelope;

import java.lang.*;
import java.util.*;
import java.util.stream.IntStream;

public class PartType {

    private long id;
    private String name;
    private Map<String, Process> map;

    public PartType(long id, String name, List<Process> processes) {
        this.map = new LinkedHashMap<String, Process>();
        for (Process p : processes) {
            map.put(p.getName(), p);
        }
    }

    public long getId() {
        return id;
    }

    public Map<String, Process> getProcesses() {
        return Collections.unmodifiableMap(map);
    }

    public Collection<Process> getProcessList() {
        return map.values();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Process> generateProcess(EProcessState ... values) {
        ArrayList<Process> p = new ArrayList<>(map.size());
        int c = 0;
        for(Map.Entry<String,Process> x : map.entrySet()) {
            if(c < values.length) {
                p.add(new Process(x.getValue().getName(), values[c]));
                c++;
            }
        }
        return p;
    }

}
