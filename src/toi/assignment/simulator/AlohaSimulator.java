package toi.assignment.simulator;

import java.util.ArrayList;
import java.util.List;

import toi.assignment.simulator.aloha.Aloha;
import toi.assignment.simulator.util.Monitor;
import toi.assignment.simulator.util.Network;

public class AlohaSimulator {

	public static void main(String[] args) {
		long P = Parameters.P;
		long Q = Parameters.Q;
		int packagePerChannel = Parameters.PackagePerNode;
		int networkSize = Parameters.NoNodes;

		List<Aloha> nodes = new ArrayList<Aloha>();
		for (int i = 0; i < networkSize; i++) {
			String name = "Node[" + i + "]";
			Aloha temp = new Aloha(P, Q, packagePerChannel, name);
			nodes.add(temp);
		}
		Monitor.NoNodes = networkSize;
		Network<Aloha> aloha = new Network<Aloha>(P, Q, packagePerChannel, networkSize, nodes);
		aloha.activate();

		// TODO Auto-generated method stub

	}

}
