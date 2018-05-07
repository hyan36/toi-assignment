package toi.assignment.simulator.util;

import java.util.List;

public class Network<Type extends Node> {

	protected long packageSize;

	protected long waitingPeriod;

	protected int packagePerChannel;

	protected int totalPackages;

	protected Node reciever;

	protected List<Type> nodes;

	public Network(long packageSize, long waitingPeriod, int packagePerChannel, int noNodes, List<Type> nodes) {

		this.reciever = new Node("Reciever");
		this.nodes = nodes;
		for (int i = 0; i < noNodes; i++) {
			this.nodes.get(i).setReciever(this.reciever);
		}
		this.totalPackages = packagePerChannel * noNodes;
		this.packageSize = packageSize;
		this.waitingPeriod = waitingPeriod;
		this.packagePerChannel = packagePerChannel;
	}

	public void activate() {
		for (int i = 0; i < this.nodes.size(); i++) {
			Node node = this.nodes.get(i);
			node.start();
		}
	}

}
