package org.fsd.penelope;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class FifoShelf extends AbstractNode {

	private LinkedList<Part> parts;

	public FifoShelf(String name) {
		super(name);
		this.parts = new LinkedList<>();

	}

	public void setMaxPartCount(int partCount) {
		this.partCount = partCount;
	}

	public void addPart(Part part) {
		parts.add(part);
	}

	@Override
	public Part getHead() {
		return parts.getFirst();
	}

	@Override
	public Part removeHead() {
		return parts.removeFirst();
	}

	@Override
	public boolean hasPartAvailable() {
		return parts.size() > 0 && this.getTransactionState() == ETransactionState.IDLE;
	}

	@Override
	public List<Part> listParts() {
		return Collections.unmodifiableList(this.parts);
	}

	@Override
	public int getPartCount() {
		return parts.size();
	}
}

