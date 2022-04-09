public class Node implements Comparable<Node> {
	int x;
	double distance;
    public Node(int x, double dist) {
        this.x = x;
        this.distance = dist;
    }
    public int compareTo(Node node) {
        if (distance < node.distance) {
            return -1; 
        }
        if (distance > node.distance) {
            return 1; 
        }
        return 0; 
    }
}