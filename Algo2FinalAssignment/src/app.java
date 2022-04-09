import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Map;

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

class Graph{
    int stop;
	ArrayList<Edge>[] aList; //The graph is created using an adjacency list
	Map<Integer,Integer> stopIDMap;
	Map<String,Integer> stopMap;
	Map<Integer,String> arrayMap;//tracks index array + name of stop
	//dijkstra dij;
	
	public Graph(String transfersTxt, String stopsTxt, String stopTimesTxt) {}
}

