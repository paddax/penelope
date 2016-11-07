package com.winbro.penelope;

import java.lang.*;
import java.util.*;

public abstract class AbstractNode implements INode {

    private String name;
    private PartType parttype;
    protected int partCount = 1;
    private FixtureType in;
    private FixtureType out;
    private ENodeState state = ENodeState.READY;

    private Map<String, Process> accept = new LinkedHashMap<>();
    private Map<String, Process> reject = new LinkedHashMap<>();
    private Map<String, Process> output = new LinkedHashMap<>();

    private Transaction transaction;
    private long lastContact;
    private long authorisation;

    public long getID() {
        return 0;
    }

    public AbstractNode(String name) {
        this.name = name;
    }

    @Override
    public PartType getParttype() {
        return parttype;
    }

    public void setParttype(PartType parttype) {
        this.parttype = parttype;
    }

    @Override
    public int getMaxPartCount() {
        return partCount;
    }

    @Override
    public FixtureType getIn() {
        return in;
    }

    public void setIn(FixtureType in) {
        this.in = in;
    }

    @Override
    public FixtureType getOut() {
        return out;
    }

    public void setOut(FixtureType out) {
        this.out = out;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Map<String, Process> accept() {
        return Collections.unmodifiableMap(accept);
    }

    @Override
    public Map<String, Process> reject() {
        return Collections.unmodifiableMap(reject);
    }

    @Override
    public Map<String, Process> output() {
        return Collections.unmodifiableMap(output);
    }

    public void setAccept(Collection<Process> processes) {
        this.accept = new LinkedHashMap<>();
        for (Process p : processes) {
            accept.put(p.getName(), p);
        }
    }

    public void setReject(Collection<Process> processes) {
        this.reject = new LinkedHashMap<>();
        for (Process p : processes) {
            reject.put(p.getName(), p);
        }
    }

    public void setOutput(Collection<Process> processes) {
        this.output = new LinkedHashMap<>();
        for (Process p : processes) {
            output.put(p.getName(), p);
        }
    }

    @Override
    public Transaction getTransaction() {
        return transaction;
    }

    @Override
    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public ENodeState getState() {
        return state;
    }

    public void setState(ENodeState state) {
        this.state = state;
    }

    private boolean includes(Map<String, Process> map, Collection<Process> list) {
        for (Process listProcess : list) {
            Process mapProcess = map.get(listProcess.getName());
            if (mapProcess != null) {
                if (!mapProcess.getState().intersection(listProcess.getState()).isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean wants(Part x) {
        return includes(accept, x.getProcessList()) && !includes(reject, x.getProcessList());
    }

    @Override
    public void setLastContact(long ts) {
        this.lastContact = ts;
    }

    @Override
    public long getLastContact() {
        return lastContact;
    }

    @Override
    public long getAuthorisation() {
        return authorisation;
    }

    public void setAuthorisation(long auth) {
        this.authorisation = auth;
    }
}
