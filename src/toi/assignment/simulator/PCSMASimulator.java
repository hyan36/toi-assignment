package toi.assignment.simulator;

import java.util.ArrayList;
import java.util.List;

import toi.assignment.simulator.aloha.PCSMA;
import toi.assignment.simulator.util.Monitor;
import toi.assignment.simulator.util.Network;

public class PCSMASimulator {

	public static void main(String[] args) {
		long P = Parameters.P;
		long Q = Parameters.Q;
		int packagePerChannel = Parameters.PackagePerNode;
		int networkSize = Parameters.NoNodes; 
		double possibility = 1;
		List<PCSMA> nodes = new ArrayList<PCSMA>();
		for (int i = 0 ; i < networkSize; i++) {
			String name = "Node["+i+"]";
			PCSMA temp = new PCSMA(P, Q, packagePerChannel, name);
			temp.setPConfig(possibility);
			nodes.add(temp);
		}
		Monitor.NoNodes = networkSize;
		Network<PCSMA> aloha = new Network<PCSMA>(P, Q, packagePerChannel, networkSize, nodes);
		aloha.activate();

	}

}
