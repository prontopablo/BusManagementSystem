import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

class Node implements Comparable<Node> {
    public double distance;
    public int x;
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

class Edge {
    public double dist;
    public int x, y;
    public Edge(int x, int y, double dist) {
        this.x = x;
        this.y = y;
        this.dist = dist;
    }
    int getStart() {return x;}
    int getEnd() {return y;}
    double getDistance() {return dist;}
}

class MinPriorityQueue {
	
}

class Graph{
	
}

public class app {
	public static void main(String[]args) {
		System.out.println("Vancouver Bus Management System");

		boolean running = true;
		while(running = true) {
		System.out.println("-------------------------------");
		System.out.println("Enter:");
		System.out.println("0 to exit the program");
		System.out.println("1 to find the shortest path between 2 stops");
		System.out.println("2 to search for a bus stop");
		System.out.println("3 to search for trips by time");
		System.out.println("-------------------------------");
		
		Scanner sc = new Scanner(System.in);
		String choice = sc.next();
		switch(choice) {
		case "0":
			System.out.println("Exiting...");
			System.exit(0);
			running = false;
			break;
		case "1":
			break;
		case "2":
			break;
		case "3":
			break;
		default:
			System.out.println("Invalid input, please try again.");
			break;
		}
	}
	}

	}

