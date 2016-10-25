package org.fsd.penelope;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    private static Process edm;
	private static Process ablation;
	private static Process inspection;
	private static FixtureType noFixture;
	private static FixtureType cfFixture;
	private static List<AbstractNode> nodes = new ArrayList<>();

	public static void main( String[] args ) {
    	ArrayList<Process> processes = new ArrayList<Process>();
    	edm = new Process("EDM");
    	ablation = new Process("Ablation");
    	inspection = new Process("Inspection");
    	noFixture = new FixtureType("NONE");
    	cfFixture = new FixtureType("CFM");
    	processes.add(edm);
		processes.add(ablation );
		processes.add(inspection );
    	
    	PartType pt = new PartType("CF88", processes);
    	
    	FifoShelf shelf1 = new FifoShelf("Goods inward");
    	shelf1.setPartCount(20);
    	shelf1.setIn(noFixture);
    	shelf1.setOut(noFixture);
    	nodes.add(shelf1);
    	
    	FifoShelf shelf2 = new FifoShelf("Before EDM");
    	shelf2.setPartCount(2);
    	processes.clear();
    	processes.add(edm.create(EProcessState.COMPLETE));
    	shelf1.setReject(processes);
    	shelf2.setIn(noFixture);
    	shelf2.setOut(cfFixture);
    	nodes.add(shelf2);
    	
    	for(int i= 1; i<=5; i++) {
    		nodes.add(createEDM("EDM" + i));
    	}

        for(int i= 1; i<=shelf1.getPartCount(); i++) {
            shelf1.addPart(new Part(pt, "AAA" + i));
        }

    	while(true) {
    		try {
				Thread.sleep(1000);
				for(AbstractNode node: nodes) {
					if(node.getTransationState() == ETransactionState.IDLE) {
						Part x = node.getHead();
						for(AbstractNode node1: nodes) {
                            if(node1.getTransationState() == ETransactionState.IDLE && node1 != node) {
                                if (node1.wants(x)) {

                                }
                            }
						}
					}
				}
			} catch (InterruptedException e) {
			}
    	}
    }

	private static FifoShelf createEDM(String string) {
    	FifoShelf mc = new FifoShelf("EDM1");
    	mc.setPartCount(1);
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
