package toi.assignment.simulator.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Monitor {

	public static int NoNodes;

	protected static List<Node> finishedNode = new ArrayList<Node>();

	public static synchronized void PrintSummary(Node node) {
		NoNodes--;
		if (node == null) {
			System.out.println("wrong");
		}
		finishedNode.add(node);
		if (NoNodes <= 0) {
			List<Node> nodes = finishedNode;
			Collections.sort(nodes, new Comparator<Node>() {
				@Override
				public int compare(Node o1, Node o2) {					
					return o1.GetName().compareTo(o2.GetName());
				}
				
			});
			PrintThroughput(nodes);
		}
	}
	
	
	public static void PrintThroughput(List<Node> nodes) {
		System.out.println("Utilization Summary");
		double totalThroughput = 0;
		double totalUtilization = 0;
		int size = nodes.size();
		for (int i = 0; i < size; i++) {
			Node n = nodes.get(i);
			
			//System.out.println(nodes.get(i));
			
			totalThroughput += n.getThroughput();
			totalUtilization += n.getUtilization();
			
		}
		double averageThroughput = (double) totalThroughput / (double) size;
		double averageUtilization = (double) totalUtilization / (double) size;
		System.out.println("Average throughput: " + averageThroughput);
		System.out.println("Average utilization: " + averageUtilization);
	}
}
