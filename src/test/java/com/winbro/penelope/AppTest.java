package com.winbro.penelope;

import com.winbro.penelope.engine.F4Engine;
import groovy.lang.IntRange;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private static long CF88 = 1234;
    private static long NO_FIXTURE = 1;
    private static long CFM_FIXTURE = 2;
    private static long ALL_AUTHORISATION = 123;

    @org.junit.Test
    public void simpleTest() {
        F4Engine f4 = new F4Engine();

        ArrayList<Process> processes = new ArrayList<>();
        processes.add(new Process("EDM"));
        processes.add(new Process("Ablation"));
        processes.add(new Process("Inspection"));
        PartType pt = new PartType(CF88, "CF88", processes);
        f4.setPartTypes(new ArrayList<PartType>() {{
            add(pt);
        }});
        f4.setFixtureTypes(new ArrayList<FixtureType>() {{
            new FixtureType(NO_FIXTURE, "NONE", 10000);
            new FixtureType(CFM_FIXTURE, "CFM", 4);
        }});

        ArrayList<INode> nodes = new ArrayList<INode>();

        FifoShelf f = new FifoShelf("Goods Inward");
        f.setMaxPartCount(20);
        f.setIn(f4.getFixtureType(NO_FIXTURE));
        f.setOut(f4.getFixtureType(NO_FIXTURE));
        f.setAccept(pt.getProcesses().values());
        f.setAuthorisation(ALL_AUTHORISATION);
        f.setLastContact(System.currentTimeMillis());
        f.setParttype(f4.getPartType(CF88));
        nodes.add(f);

        FifoShelf f1 = new FifoShelf("EDM Fixture");
        f.setMaxPartCount(2);
        f.setIn(f4.getFixtureType(NO_FIXTURE));
        f.setOut(f4.getFixtureType(CFM_FIXTURE));
        f.setAccept(pt.generateProcess(EProcessState.VIRGIN, EProcessState.ALL, EProcessState.ALL));
        f.setOutput(pt.generateProcess(EProcessState.ALL, EProcessState.NONE, EProcessState.NONE));
        f.setAuthorisation(ALL_AUTHORISATION);
        f.setLastContact(System.currentTimeMillis());
        f.setParttype(f4.getPartType(CF88));
        nodes.add(f);
        IntStream.range(1, 6).forEach(i -> {
            Machine m = new Machine("MC" + i);
            m.setOutput(pt.generateProcess(EProcessState.ALL, EProcessState.NONE, EProcessState.NONE));
            m.setAccept(pt.generateProcess(EProcessState.NOT_COMPLETE, EProcessState.ALL, EProcessState.ALL));
            m.setReject(pt.generateProcess(EProcessState.COMPLETE, EProcessState.NONE, EProcessState.NONE));
            m.setParttype(pt);
            m.setIn(f4.getFixtureType(CFM_FIXTURE));
            m.setOut(f4.getFixtureType(CFM_FIXTURE));
            m.setAuthorisation(ALL_AUTHORISATION);
            m.setLastContact(System.currentTimeMillis());
            nodes.add(m);
        });

        f4.setNodes(nodes);
        f4.start();


    }
}
