import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

//stop class to facilitate finding name and printing stop details (feature no. 2)
public class stop{
		TernarySearchTree T;//TST to find matching stop names
		String file;//file to be read in
	public stop(String filename) throws IOException{
		int ID = 2; //as line 1 of input file is not relevant
		T = new TernarySearchTree();
		TernarySearchTree.listOfAllNames.clear();
		file = filename;
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String currLine;
		br.readLine();//again skipping line 1 of input file
		
		while ((currLine = br.readLine()) != null)//while the current line isn't empty
	        {
				String[] properties = currLine.split(",");//properties[] keeps track of stop information
				String stopName = properties[2];//stop name is before 3rd comma in input file
				//we must format the stop name
				String[] temp = stopName.split(" ");//separate into temp by white space so we can move keywords
				List<String> t = Arrays.asList(temp);
				LinkedList<String> LL = new LinkedList<>(t);//temporary linked list (LL)
				//as requested in functionality no. 2, keywords (wb,nb etc.) are moved to the back of the
				//string when searching to allow meaningful searches.
				if(LL.get(0).equals("FLAGSTOP") || LL.get(0).equals("EB") || LL.get(0).equals("NB") || 
						LL.get(0).equals("SB") ||  LL.get(0).equals("WB"))
		            {
		                String str = LL.remove(0);
		                LL.add(str);
		            }
				String formattedName = LL.toString(); //formatted stop name
				formattedName = formattedName.replaceAll("\\p{P}", ""); //remove any punctuation characters
				T.put(formattedName, ID);//add the formatted stop name and stop ID to the TST.
				ID++;
	        }
		}
	
		//called by functionality no.2 from app.java. Prints matching stop information
		public static void printStops(stop tst) throws IOException {
			int numMatchingStops = 0;//developer stat
			System.out.println("Stops that match your search:");
			//Loops through stop names, printing matching stop info.
			for (int i = 0; i <= TernarySearchTree.listOfAllNames.size() -1; i++){
				String out;//output
				int currentLine = TernarySearchTree.listOfAllNames.get(i);
				Stream<String> lines = Files.lines(Paths.get(tst.file));
				out = lines.skip(currentLine - 1).findFirst().get();
				String [] outProperties = out.split(","); //properties split by commas
				for(int j = 0; j < outProperties.length;j++) {
					if(outProperties[j].equals(" ")) {
						outProperties[j] = "null";
					}
				}
				System.out.println("-------------------------------");
				System.out.println("ID:" + outProperties[0]);
				System.out.println("Code:" + outProperties[1]);
				System.out.println("Name:" + outProperties[2]);
				System.out.println("Description:" + outProperties[3]);
				System.out.println("Latitude:" + outProperties[4]);
				System.out.println("Longitude:" + outProperties[5]);
				System.out.println("Zone:" + outProperties[6]);
				System.out.println("URL:" + outProperties[7]);
				System.out.println("Location Type:" + outProperties[8]);
				//was getting out of bounds error - some stops don't have parent station listed
				if(outProperties.length > 9) { 
				System.out.println("Parent Station:" + outProperties[9]);}
				numMatchingStops++;//developer stat
			}
		}
}
		
		
