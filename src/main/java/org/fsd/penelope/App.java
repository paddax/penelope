package org.fsd.penelope;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

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

	/**
	 * Determines if this node is a valid target for a source node
	 * @param x
	 * @return
	 */
    public static Predicate<INode> isValidTarget(INode x) {
        return p-> x != p && x.getTransactionState() == ETransactionState.IDLE && p.wants(x.getHead());
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

        Gson g = new Gson();
        g.fromJson("", PartType.class);

        FifoShelf shelf2 = new FifoShelf("Before EDM");
        shelf2.setMaxPartCount(2);
        processes.clear();
        processes.add(edm.create(EProcessState.COMPLETE));
        shelf1.setReject(processes);
        shelf2.setIn(noFixture);
        shelf2.setOut(cfFixture);
        nodes.add(shelf2);

        for (int i = 1; i <= 7; i++) {
            nodes.add(createEDM("EDM" + i));
        }

        for (int i = 1; i <= shelf1.getMaxPartCount(); i++) {
            shelf1.addPart(new Part(pt, "AAA" + i));
        }

        ArrayList<String> list1 = new ArrayList<>();
		List<String> unavailable = list1.stream()
				.filter(e -> (list1.stream()
						.filter(d -> d.equals(e))
						.count())<1)
				.collect(Collectors.toList());

        while (true) {

            for (INode node : nodes) {
				if(node.hasPartAvailable() && node.getTransactionState() == ETransactionState.IDLE) {
					List<INode> targets = nodes.stream()
							.filter(isValidTarget(node))
							.collect(Collectors.toList());
					System.out.println("Length: " + targets.size());
				}
            }

			long total = LongStream.range(1,10000000L).parallel().filter(x -> (x >> 1) * 2 == x).count();
			System.out.println(total);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }

    private static void createTransaction(Part x, INode tx, INode rx) {
        tx.setTransactionState(ETransactionState.OFFER_SEND);
        rx.setTransactionState(ETransactionState.OFFER_RECEIVE);
        transactions.add(new Transaction(tx, rx, x));

    }

    private static INode createEDM(String name) {
        Machine mc = new Machine(name);
        ArrayList<Process> processes = new ArrayList<Process>();
        processes.add(edm.create(EProcessState.COMPLETE));
        mc.setReject(processes);
		processes.clear();
		processes.add(edm.createNot(EProcessState.COMPLETE));
		mc.setAccept(processes);
        processes.clear();
        processes.add(edm.createAll());
        mc.setOutput(processes);
        mc.setIn(cfFixture);
        mc.setOut(cfFixture);

        return mc;
    }
}
