package org.fsd.penelope;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class Process {

	private ProcessState state = new ProcessState(EProcessState.VIRGIN);
	private String name;
	
	public Process(String n) {
		this(n, new ProcessState(EProcessState.VIRGIN));
	}
	
	public Process(String name, ProcessState s) {
		this.name = name;
		this.state = new ProcessState(s);
	}

	public Process(String name, EnumSet<EProcessState> s) {
		this.name = name;
		state = new ProcessState(s);
	}
	
	public ProcessState getState() {
		return state;
	}

	private void setState(ProcessState state) {
		this.state = new ProcessState(state);
	}
	
	public String getName() {
		return name;
	}

	public Process create(EProcessState s) {
		return new Process(name, new ProcessState(s));
	}
	
	public Process createAll() {
		Process p = new Process(name, EnumSet.allOf(EProcessState.class));
		return p;
	}
	
	public Process createNone() {
		Process p = new Process(name);
		p.setState(new ProcessState());
		return p;
	}
	
	public Process createNot(EProcessState s) {
		Process p = new Process(name);
		ProcessState ps = new ProcessState(s);
		p.setState(ps.complement());
		return p;
	}
	
	
}
