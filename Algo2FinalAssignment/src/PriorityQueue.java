public class PriorityQueue {
	private int currSize;
	private Node[] nodeArray;
	
	//MinPQ Constructor
	public PriorityQueue(int maxSize) {
        currSize = 0;
        nodeArray = new Node[maxSize+1];
        for(int i = 0; i<nodeArray.length;i++) {nodeArray[i] = null;}
    }
	
	//Check if pq has any elements
	public boolean isEmpty() {return currSize == 0;}
	
	//sink helper for PQ
	private void sink(int k) {
		Node x;
		//While k is smaller than either/both of children
		//swap with greater child
		while(currSize >= (k*2)) {
			int y = (k*2);
			if(currSize > y && nodeArray[y].distance > nodeArray[y+1].distance) {y++;}
			if(nodeArray[y].distance > nodeArray[k].distance) {break;}
			x = nodeArray[y];
			nodeArray[y] = nodeArray[k];
			nodeArray[k] = x;
			k = y;
		}
	}
	
	//swim helper for PQ
	private void swim(int i) {
		Node x;
		//while i isn't the root and larger than the parent, 
		//swap it with its parent 
		while(i > 1) {
			if(nodeArray[i].distance > nodeArray[i/2].distance) {break;}
			x = nodeArray[i/2];
			nodeArray[i/2] = nodeArray[i];
			nodeArray[i] = x;
			i = i/2;
		}
	}
	
	//Find the next stop/vertex in the PQ
	public int next() {
		int nextStop = nodeArray[1].x;
		nodeArray[1] = nodeArray[currSize--];
		nodeArray[(currSize+1)] = null;
		sink(1);
		if(nodeArray.length > 4 && (nodeArray.length/4) >= currSize) {
			//shrink();
		}
		return nextStop;
	}
	
	//Grow the node array to allow more nodes
	private void grow() {
		Node[] newNodeArray = new Node[nodeArray.length*2];
		for(int i = 0; i < newNodeArray.length; i++) {
			if(i<nodeArray.length) {newNodeArray[i] = nodeArray[i];}
			else {newNodeArray[i] = null;}
		}
		//once nodes have been copied and new nodes have been set to null, update
		nodeArray = newNodeArray;
	}
	
	//shrink the node array
	private void shrink() {
		Node[] newNodeArray = new Node[nodeArray.length/2];
		for(int i = 0; i < newNodeArray.length; i++) {
			newNodeArray[i] = nodeArray[i];
		}
		nodeArray = newNodeArray;
	}
	
	public void insert(double distance, int stop) {
		//Check to see if new node will fit
		if((currSize+1) >= nodeArray.length) {
			//grow();
			}
		nodeArray[currSize] = new Node(stop,distance);
		swim(currSize);
	}
}
