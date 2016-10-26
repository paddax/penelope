package org.fsd.penelope;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PartType {

    private String name;
    private Map<String, Process> map;

    public PartType(String name, List<Process> processes) {
        this.map = new LinkedHashMap<String, Process>();
        for (Process p : processes) {
            map.put(p.getName(), p);
        }
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

}
