package toi.assignment.simulator.aloha;

import toi.assignment.simulator.util.Node;

public class Aloha extends Node {

	public Aloha(long packageSize, long waitingPeriod, int totalPackages, String name) {
		super(packageSize, waitingPeriod, totalPackages, name);
		// TODO Auto-generated constructor stub
	}

	public boolean send() {
		boolean result = false;
		if (this.reciever.recieve(this, this.packageSize)) {			
			result = true;
		}
		this.totalCount ++;
		this.Rest(result);
		return result;
	}

}
