import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;

//main application class
public class app {
	//driver code, takes user input and calls other methods.
	public static void main(String[]args) throws IOException {
		//this console interface enables selection between features
		//and exiting the system, as requested by specification doc.
		System.out.println("-------------------------------");
		System.out.println("Vancouver Bus Management System");

		boolean running = true;
		while(running = true) {
		System.out.println("-------------------------------");
		System.out.println("Enter:");
		System.out.println("0 to exit the program");
		System.out.println("1 to find the shortest path between 2 stops (n/a)");
		System.out.println("2 to search for a bus stop");
		System.out.println("3 to search for trips by time (n/a)");
		System.out.println("-------------------------------");
		
		Graph VCBusGraph = null; //Graph of the Vancouver Bus System
		Scanner sc = new Scanner(System.in);
		String choice = sc.next();
		switch(choice) {
		//4. "option to exit the programme"
		case "0":
			System.out.println("Exiting...");
			System.exit(0);
			running = false;
			break;
		
		//todo
		case "1":
			break;
		
		//2. "Searching for a bus stop by full name or by the first few characters in the name, using a
		//ternary search tree (TST), returning the full stop information for each stop matching the
		//search criteria (which can be zero, one or more stops)"
		case "2":
			boolean running2 = true;
			while(running2) {
				System.out.println("-------------------------------");
				System.out.print("Enter search for bus stop (0 to return to menu)");
				String search = sc.next(); 
				search += sc.nextLine();
				if(search.equals("0")) {main(args);}
				stop newTST = new stop("stops.txt");//create stop object
				int result = newTST.T.get(search); //TST to check user input
				if(result >= 0) {stop.printStops(newTST);}//if something matches, print
				//as requested in functionality no. 2, keywords (wb,nb etc.) are moved to the back of the
				//string when searching to allow meaningful searches. However they are printed in their original format
				else {System.out.println("No matching stops found, sorry");}//error checking
			}
			break;
		
		//todo	
		case "3":
			break;
		
		default:
			System.out.println("-------------------------------");
			System.out.println("Invalid input, please try again.");//error checking
			break;
		}
	}
	}
	
	}

//graph class to represent bus network
class Graph{
    private int stopCount;
	private ArrayList<Edge>[] aList; //the graph is represented using an adjacency list
	private Map<Integer,Integer> stopIDMap; // tracks stop ID + array index
	private Map<String,Integer> stopMap; //tracks stop name + stop ID
	private Map<Integer,String> arrayMap;//tracks array index + name of stop
	
	//receives file names as parameters
	public Graph(String stopsTxt, String transfersTxt, String stopTimesTxt) throws IOException {
		//corresponding hash maps.
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
		
		//read each of the 3 files
		
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
		
		//same process as with stops.txt 
		while((currLine = in.readLine()) != null){
			split = currLine.split(",");
			//as per assignment specification, distance/weight of the edge changes.
			if(Integer.parseInt(split[2]) == 0) {distance = 2.0;}
			else {distance = Double.parseDouble(split[3])/100;}
			startID = Integer.parseInt(split[0]);
			endID = Integer.parseInt(split[1]);
			startArrayIndex = stopIDMap.get(startID);
			endArrayIndex = stopIDMap.get(endID);
			//add new path/trip to adjacency list.
			aList[startArrayIndex].add(new Edge(startArrayIndex,endArrayIndex,distance));
			
		}
		in.close();
		in = new BufferedReader(new FileReader(stopsTxt));//br for "stops.txt"
		int currentID = 0;
		int previousID = -1;
		int invalidTimes = 0;//tracker for developer menu
		while((currLine = in.readLine()) != null){
			split = currLine.split(",");
			try {
				/*times must be in the format hh:mm:ss
				this will reject invalid times such as 25:60:60 etc.
				*/
				LocalTime.parse(split[1].replaceAll("\\s", "0"));
				LocalTime.parse(split[2].replaceAll("\\s", "0"));
			}
			catch (DateTimeParseException | NullPointerException e){
				//an invalid time has been found and is therefore not added
				invalidTimes++;//tracker for developer
				continue;//skip this time
			}
			currentID = Integer.parseInt(split[0]);
			endID = Integer.parseInt(split[3]);
			if(previousID == currentID) {
				startArrayIndex = stopIDMap.get(startID);
				endArrayIndex = stopIDMap.get(endID);
				//add edge to adjacency list
				aList[startArrayIndex].add(new Edge(startArrayIndex,endArrayIndex,1.0));
			}
			startID = endID;
			previousID = currentID;			
		}
		in.close();
	}
}

