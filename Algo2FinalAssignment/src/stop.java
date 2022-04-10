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

public class stop{
		TernarySearchTree T;
		String file;
		public stop(String filename) throws IOException{
			T = new TernarySearchTree();
			file = filename;
			int ID = 2;
			T.listOfAllNames.clear();
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String currLine;
			br.readLine();
			while ((currLine = br.readLine()) != null)
	        {
				String[] properties = currLine.split(",");
				String stopName = properties[2];
				String[] temp = stopName.split(" ");
				List<String> t = Arrays.asList(temp);
				LinkedList<String> tempLL = new LinkedList<>(t);
				if(tempLL.get(0).equals("FLAGSTOP") || tempLL.get(0).equals("EB") || tempLL.get(0).equals("NB") || 
						tempLL.get(0).equals("SB") ||  tempLL.get(0).equals("WB"))
		            {
		                String str = tempLL.remove(0);
		                tempLL.add(str);
		            }
				String formattedName = tempLL.toString();
				T.put(formattedName, ID);
				ID++;
	        }
		}
		public ArrayList<String> getName(String name) throws IOException{
			int value = T.get(name);
			String out;
			ArrayList<String> values = new ArrayList<>();
			if(value >= 0) {
				for(int i = 0; i < T.listOfAllNames.size();i++){
					int line = T.listOfAllNames.get(i);
					Stream<String> lines = Files.lines(Paths.get(file));
					out = lines.skip(line-1).findFirst().get();
					String[] outProperties = out.split(",");
					values.add(outProperties[2]);
				}
				T.listOfAllNames.clear();
				return values;
			}
			else {return null;}
		}
		public static void printStops(stop tst) throws IOException {
			System.out.println("Matching stops:");
			for (int i = 0; i <= TernarySearchTree.listOfAllNames.size() -1; i++){
				String out;
				int currentLine = TernarySearchTree.listOfAllNames.get(i);
				Stream<String> lines = Files.lines(Paths.get(tst.file));
				out = lines.skip(currentLine - 1).findFirst().get();
				String [] outProperties = out.split(",");
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
				System.out.println("Parent Station:" + outProperties[9]);
			}
		}
}
		
		
