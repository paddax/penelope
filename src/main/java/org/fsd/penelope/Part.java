package org.fsd.penelope;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Part {

	private String name;
	private Map<String,Process> processes;
	
	public Part(PartType type, String name) {
		this.name = name;
		this.processes = new LinkedHashMap<String, Process>();
		this.processes.putAll(type.getProcesses());
	}

	public String getName() {
		return name;
	}

	public Map<String, Process> getProcesses() {
		return processes;
	}
}
