import java.util.Scanner;

public class app {
	public static void main(String[]args) {
		System.out.println("Vancouver Bus Management System");
		System.out.println("-------------------------------");
		
		boolean running = true;
		while(running = true) {
		System.out.println("Enter:");
		System.out.println("0 to exit the program");
		System.out.println("1 to find the shortest path between 2 stops");
		System.out.println("2 to search for a bus stop");
		System.out.println("3 to search for trips by time");
		Scanner sc = new Scanner(System.in);
		String choice = sc.next();
		switch(choice) {
		case "0":
			System.out.println("Exiting...");
			System.exit(0);
			running = false;
			break;
		case "1":
			
		default:
			System.out.println("Invalid input, please try again.");
			break;
		}
	}
	}
}
