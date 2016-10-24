package org.fsd.penelope;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class Process {

	private EnumSet<EProcessState> state = EnumSet.of(EProcessState.VIRGIN);
	private String name;
	
	public Process(String n) {
		this(n, EProcessState.VIRGIN);
	}
	
	public Process(String name, EProcessState s) {
		this.name = name;
		this.state = EnumSet.of(s);
	}
	
	public EnumSet<EProcessState> getState() {
		return state;
	}
	
	private void setState(EnumSet<EProcessState> state) {
		this.state = state;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Process create(EProcessState s) {
		Process p = new Process(name);
		p.state = EnumSet.of(s);
		return p;
	}
	
	public Process createAll() {
		Process p = new Process(name);
		p.setState(EnumSet.allOf(EProcessState.class));
		return p;
	}
	
	public Process createNone() {
		Process p = new Process(name);
		p.setState(EnumSet.noneOf(EProcessState.class));
		return p;
	}
	
	public Process createNot(EProcessState s) {
		Process p = new Process(name);
		p.setState(EnumSet.complementOf(EnumSet.of(s)));
		return p;
	}
	
	
}
