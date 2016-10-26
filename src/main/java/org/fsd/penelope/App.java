package org.fsd.penelope;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Hello world!
 */
public class App {
    private static Process edm;
    private static Process ablation;
    private static Process inspection;
    private static FixtureType noFixture;
    private static FixtureType cfFixture;
    private static List<INode> nodes = new ArrayList<>();
    private static List<Transaction> transactions = new LinkedList<>();

    public static Predicate<INode> isAllowed(INode x) {
        return p-> x != p && x.getTransactionState() == ETransactionState.IDLE;
    }
    public static void main(String[] args) {
        ArrayList<Process> processes = new ArrayList<Process>();
        edm = new Process("EDM");
        ablation = new Process("Ablation");
        inspection = new Process("Inspection");
        noFixture = new FixtureType("NONE");
        cfFixture = new FixtureType("CFM");
        processes.add(edm);
        processes.add(ablation);
        processes.add(inspection);

        PartType pt = new PartType("CF88", processes);

        FifoShelf shelf1 = new FifoShelf("Goods inward");
        shelf1.setMaxPartCount(20);
        shelf1.setIn(noFixture);
        shelf1.setOut(noFixture);
        nodes.add(shelf1);

        FifoShelf shelf2 = new FifoShelf("Before EDM");
        shelf2.setMaxPartCount(2);
        processes.clear();
        processes.add(edm.create(EProcessState.COMPLETE));
        shelf1.setReject(processes);
        shelf2.setIn(noFixture);
        shelf2.setOut(cfFixture);
        nodes.add(shelf2);

        for (int i = 1; i <= 5; i++) {
            nodes.add(createEDM("EDM" + i));
        }

        for (int i = 1; i <= shelf1.getPartCount(); i++) {
            shelf1.addPart(new Part(pt, "AAA" + i));
        }

        ArrayList<INode> result1 = new ArrayList<>();
        while (true) {
            for (INode node : nodes) {
                if (node.hasPartAvailable()) {
                    for (INode node1 : nodes) {
                        if (node1.getTransactionState() == ETransactionState.IDLE && node1 != node) {
                            if (node1.wants(node.getHead())) {
                                result1.add(node1);
                            }
                        }
                    }
                }
            }
            for (INode node : nodes) {
                List<INode> result = nodes.stream()
                        .filter(n -> n.hasPartAvailable())
                        .filter(isAllowed(node))
                        .filter(n -> n.wants(node.getHead()))
                        .collect(Collectors.toList());
            }
            //createTransaction(x, node, node1);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }

    private static void createTransaction(Part x, INode tx, INode rx) {
        tx.setTransactionState(ETransactionState.OFFERING);
        rx.setTransactionState(ETransactionState.OFFERED);
        transactions.add(new Transaction(tx, rx, x));

    }

    private static INode createEDM(String name) {
        Machine mc = new Machine(name);
        ArrayList<Process> processes = new ArrayList<Process>();
        processes.add(edm.create(EProcessState.COMPLETE));
        mc.setReject(processes);
        processes.clear();
        processes.add(edm.createAll());
        mc.setOutput(processes);
        mc.setIn(cfFixture);
        mc.setOut(cfFixture);

        return mc;
    }
}
