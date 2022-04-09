import java.util.ArrayList;

public class Dijkstra {
	private Graph g;
	private Integer[] path;
    private Double[] distance;
    
    public Dijkstra(Graph h, int x) {
    	g = h;
    	int current;
    	path = new Integer[g.stopCount()];
    	distance = new Double[g.stopCount()];
    	boolean[] hasVisited = new boolean[g.stopCount()];
        
    	//set all distances to infinity
    	for(int i = 0; i < g.stopCount(); i++) {
        	distance[i] = null;
        }
        //set all paths to null
    	for(int i = 0; i < g.stopCount(); i++) {
        	path[i] = null;
        }  
    	//set all elements to false
    	for(int i = 0; i < g.stopCount(); i++) 
        {
        	hasVisited[i] = false;
        } 
    	//create new Priority queue
    	PriorityQueue PQ = new PriorityQueue(3);
    	distance[x] = 0.0;
    	PQ.insert(distance[x], x);
    	
    	while(!PQ.isEmpty()){
    		current = PQ.next();
    		if(!hasVisited[current]){
    			hasVisited[current] = true; //it has now visited this stop/vertex
    			
    			for(Object object : g.edgeBeside(current)) {
    				Edge edge = (Edge) object; //cast to Edge
    				//if the current edge has a destination
    				if(distance[edge.getEnd()] != null) {
    					
    					if(distance[edge.getEnd()] > (edge.getDistance() + distance[current])) {
    						distance[edge.getEnd()] = edge.getDistance() + distance[current];
    						path[edge.getEnd()] = current;
    					}
    				
    				}
    				
    				else {
    					distance[edge.getEnd()] = edge.getDistance() + distance[current];
    					path[edge.getEnd()] = current;
    				}
    				
    				PQ.insert(distance[edge.getEnd()], edge.getEnd()); //unvisited stop gets added to PQ
    			}
    		}
    	}
    }
    
    //finds path to a stop
    public ArrayList<Integer> path(int stop){
    	ArrayList<Integer> pathFound = new ArrayList<Integer>();
		int currStop = stop;
		pathFound.add(currStop);
		while(path[currStop] != null) {
			pathFound.add(path[currStop]);
			currStop = path[currStop];			
		}
    	return pathFound;
    }
    //finds distance to a stop
    public Double dist(int stop) {return distance[stop];}
}
