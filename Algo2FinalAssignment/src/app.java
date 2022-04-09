import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;

public class app {
	//driver code, takes user input and calls other methods.
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

//graph class to represent bus network
class Graph{
    private int stopCount;
	private ArrayList<Edge>[] aList; //The graph is created using an adjacency list
	private Map<Integer,Integer> stopIDMap; // tracks stop ID + array index
	private Map<String,Integer> stopMap; //tracks stop name + stop ID
	private Map<Integer,String> arrayMap;//tracks array index + name of stop
	//dijkstra dij;
	
	public Graph(String stopsTxt, String transfersTxt, String stopTimesTxt) throws IOException {
		stopIDMap = new HashMap<Integer,Integer>();
		stopMap = new HashMap<String,Integer>();
		arrayMap = new HashMap<Integer,String>();
		
		BufferedReader in = new BufferedReader(new FileReader(stopsTxt));//br for "stops.txt"
		String stopName = null;
		String currLine = null;
		String[] split = null;
		
		int stopID = 0;
		int stopCountTemp = 0;
		double distance = 0;
		int startID = 0;
		int endID = 0;
		int startArrayIndex = 0;
		int endArrayIndex = 0;
		
		in.readLine();//skip first line
		while((currLine = in.readLine()) != null) {
			split = currLine.split(","); //file is separated by commas
			stopName = split[2];//stop_name is before 3rd comma
			stopID = Integer.parseInt(split[0]);
			//add to respective maps
			stopMap.put(stopName, stopID);
			stopIDMap.put(stopID, stopCountTemp);
			arrayMap.put(stopCountTemp, stopName);
			stopCountTemp++;
		}
		
		stopCount = stopCountTemp;
		//update adjacency list
		aList = new ArrayList[stopCount];
		for(int i = 0; i < stopCount; i++) {
			aList[i] = new ArrayList<Edge>();
		}
		in.close();//close br before using for next file
		in = new BufferedReader(new FileReader(transfersTxt));//br for "transfers.txt"
		in.readLine(); //skip first line
		
		while((currLine = in.readLine()) != null){
			split = currLine.split(",");
			if(Integer.parseInt(split[2]) == 0) {distance = 2.0;}
			else {distance = Double.parseDouble(split[3])/100;}
			startID = Integer.parseInt(split[0]);
			endID = Integer.parseInt(split[1]);
			startArrayIndex = stopIDMap.get(startID);
			endArrayIndex = stopIDMap.get(endID);
			aList[startArrayIndex].add(new Edge(startArrayIndex,endArrayIndex,distance));
			
		}
		in.close();
		in = new BufferedReader(new FileReader(stopsTxt));//br for "stops.txt"
		int CurrentID = 0;
		int previousID = -1;
		int invalidTimes = 0; 
		while((currLine = in.readLine()) != null){
			split = currLine.split(",");
			try {
				/*times must be in the format hh:mm:ss
				this will reject invalid times such as 25:60:60 etc.
				*/
				LocalTime.parse(split[1]);
				LocalTime.parse(split[2]);
			}
			catch (DateTimeParseException | NullPointerException e){
				invalidTimes++;
				continue;
			}
		}
	}
}

