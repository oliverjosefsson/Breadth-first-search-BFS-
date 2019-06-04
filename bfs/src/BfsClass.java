import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *  created by Oliver and Björn.
 */
public class BfsClass {

	private int V;
	private LinkedList<Integer> adjacency[];
	private ArrayList list;

	public BfsClass(int V, LinkedList<Integer> adj[], ArrayList wordList) {
		this.V = V;
		this.adjacency = adj;
		this.list = wordList;
	}

	/**
	 * Breadth-first-search on two nodes
	 * @param startNode
	 * @param destNode
	 * @return
	 */
	public int bfs(String startNode, String destNode) {
		LinkedList<Integer> queue = new LinkedList<Integer>();
		//        LinkedList<Integer> queueSize = new LinkedList<Integer>();
		HashMap<Integer, Integer> parent = new HashMap<Integer, Integer>();
		int s = 0;
		int goalLocation = 0;
		int distance = 0;                                                                              // Distance between nodes

		boolean visited[] = new boolean[V];                                                             // Array for visited
		boolean foundWay = false;

		if (startNode.equals(destNode)) {                                                              // Controls if the start and the goal node are one and the same.

			return distance;

		} else {

			distance = -1;

			boolean startNodeFound = false;
			boolean destNodeFound = false;

			for (int i = 0; i < list.size(); i++) {

				if (startNode.equals(list.get(i))) { 			// if startnode equals the the word in place i in the list
					s = i;
					startNodeFound = true;
				} else if (destNode.equals(list.get(i))) {      // if destnode equals the the word in place i in the list
					goalLocation = i;
					destNodeFound = true;
				} else if (startNodeFound && destNodeFound) {   // When both node is found

					i = list.size();

					visited[s] = true;
					queue.add(s);
					//                    queueSize.add(queue.size());
					while (queue.size() != 0 && !foundWay) {
						s = queue.poll();
						Iterator<Integer> a = adjacency[s].listIterator();
						                        
						while (a.hasNext() && !foundWay) {
							int n = a.next();
							if (!visited[n]) {
								visited[n] = true;
								parent.put(n, s);
								if (goalLocation == n) {
									queue.clear();
									foundWay = true;
								} else {
									queue.add(n);
								}
							}
						}
					}
					if (foundWay) { // Returns the distance between the goal node and start node
						return distance(goalLocation, distance, parent);
					}
				}
			}
			return -1;
		}
	}

	/**
	 * Calculate distance between nodes
	 * @param goalLocation
	 * @param distance
	 * @param parent
	 * @return
	 */
	public static int distance(int goalLocation, int distance, HashMap<Integer, Integer> parent) {

		ArrayList<Integer> shortestPath = new ArrayList<>();
		Integer node = goalLocation;
		while (node != null) {
			shortestPath.add(node);
			node = parent.get(node);
			distance++;
		}
		return distance;

	}
}
