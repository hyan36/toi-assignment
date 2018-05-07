package toi.assignment.simulator.aloha;

import java.util.Random;

import toi.assignment.simulator.util.Node;

public class PCSMA extends Node {
	
	protected double p;
	
	protected double pConfig;
	
	public void setPConfig(double p) {
		this.pConfig = p;
	}

	public PCSMA(long packageSize, long waitingPeriod, int totalPackages, String name) {
		super(packageSize, waitingPeriod, totalPackages, name);
		this.p = 1;
	}
	
	public boolean send() {		
		boolean result = false;
		Random rand = new Random();
		double randDouble = rand.nextDouble();
		if (!this.reciever.IsBusy()) {
			if (randDouble <= p) {
				if (this.reciever.recieve(this, this.packageSize)) {			
					result = true;
					p = 1;
				} else {
					p = this.pConfig;
				}
				this.totalCount++;
			}			
		}		
		this.Rest(result);
		return result;
	}	
}
