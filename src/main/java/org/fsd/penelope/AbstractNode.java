package org.fsd.penelope;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class AbstractNode {
	
	private String name;
	private PartType parttype;
	protected int partCount = 1;
	private FixtureType in;
	private FixtureType out;
	
	private Map<String, Process> accept = new LinkedHashMap<>();
	private Map<String, Process> reject = new LinkedHashMap<>();
	private Map<String, Process> output = new LinkedHashMap<>();
	
	private LinkedList<Part> parts;
	
	public AbstractNode(String name) {
		this.name = name;
		parts = new LinkedList<>();
	}
	
	public PartType getParttype() {
		return parttype;
	}

	public void setParttype(PartType parttype) {
		this.parttype = parttype;
	}

	public int getPartCount() {
		return partCount;
	}

	public void setPartCount(int partCount) {
		this.partCount = partCount;
	}

	public FixtureType getIn() {
		return in;
	}

	public void setIn(FixtureType in) {
		this.in = in;
	}

	public FixtureType getOut() {
		return out;
	}

	public void setOut(FixtureType out) {
		this.out = out;
	}

	public String getName() {
		return name;
	}

	public Map<String, Process> accept() {
		return Collections.unmodifiableMap(accept);
	}

	public Map<String, Process> reject() {
		return Collections.unmodifiableMap(reject);
	}
	
	public Map<String, Process> output() {
		return Collections.unmodifiableMap(output);
	}
	
	public void setAccept(List<Process> processes) {
		this.accept = new LinkedHashMap<String,Process>();
		for(Process p: processes) {
			accept.put(p.getName(), p);
		}
	}
	
	public void setReject(List<Process> processes) {
		this.reject = new LinkedHashMap<String,Process>();
		for(Process p: processes) {
			reject.put(p.getName(), p);
		}
	}
	
	public void setOutput(List<Process> processes) {
		this.output = new LinkedHashMap<String,Process>();
		for(Process p: processes) {
			output.put(p.getName(), p);
		}
	}
	
	public void addPart(Part part) {
		parts.add(part);
	}
	
	public Part getHead() {
		return parts.getFirst();
	}
	
	public Part removeHead() {
		return parts.removeFirst();
	}
	
}
