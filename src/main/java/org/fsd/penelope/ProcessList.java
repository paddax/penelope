package org.fsd.penelope;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProcessList {

	private Map<String,Process> map;
	
	public ProcessList() {
		map = new LinkedHashMap<>();
	}
	
	public void addProcess(Process process) {
		map.put(process.getName(), process);
	}
	
	public Map<String,Process> getMap() {
		return Collections.unmodifiableMap(map);
	}
}
