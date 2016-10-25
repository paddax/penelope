package org.fsd.penelope;

import java.util.*;

public abstract class AbstractNode {
	
	private String name;
	private PartType parttype;
	protected int partCount = 1;
	private FixtureType in;
	private FixtureType out;
	
	private Map<String, Process> accept = new LinkedHashMap<>();
	private Map<String, Process> reject = new LinkedHashMap<>();
	private Map<String, Process> output = new LinkedHashMap<>();

	private ETransactionState transationState = ETransactionState.IDLE;
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
		this.accept = new LinkedHashMap<>();
		for(Process p: processes) {
			accept.put(p.getName(), p);
		}
	}
	
	public void setReject(List<Process> processes) {
		this.reject = new LinkedHashMap<>();
		for(Process p: processes) {
			reject.put(p.getName(), p);
		}
	}
	
	public void setOutput(List<Process> processes) {
		this.output = new LinkedHashMap<>();
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

	public ETransactionState getTransationState() {
		return transationState;
	}

	public void setTransationState(ETransactionState transationState) {
		this.transationState = transationState;
	}

	private EnumSet<EProcessState> intersection(EnumSet<EProcessState> s1, EnumSet<EProcessState> s2) {
		EnumSet<EProcessState> result = EnumSet.noneOf(EProcessState.class);
        s1.forEach(p->{
            if(s2.contains(p))
                result.add(p);
        });
        return result;
	}

	private boolean includes(Map<String,Process> map, Collection<Process> list) {
        for(Process p : list) {
            Process inc = map.get(p.getName());
            if(inc != null) {
                if(intersection(inc.getState(), p.getState()) != null) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean wants(Part x) {
        return includes(accept, x.getProcessList()) && !includes(reject, x.getProcessList());
    }
}
