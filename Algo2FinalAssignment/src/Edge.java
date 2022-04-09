public class Edge {
	private double dist;
    private int x, y;
    public Edge(int x, int y, double dist) {
        this.x = x;
        this.y = y;
        this.dist = dist;
    }
    int getStart() {return x;}
    int getEnd() {return y;}
    double getDistance() {return dist;}
}
