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
		TernarySearchTree TST;
		String file;
		public stop(String filename) throws IOException{
			TST = new TernarySearchTree();
			file = filename;
			int ID = 2;
			TST.listOfAllNames.clear();
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
				TST.put(formattedName, ID);
				ID++;
	        }
		}
		public ArrayList<String> getName(String name) throws IOException{
			int value = TST.get(name);
			String out;
			ArrayList<String> values = new ArrayList<>();
			if(value >= 0) {
				for(int i = 0; i < TST.listOfAllNames.size();i++){
					int line = TST.listOfAllNames.get(i);
					Stream<String> lines = Files.lines(Paths.get(file));
					out = lines.skip(line-1).findFirst().get();
					String[] outProperties = out.split(",");
					values.add(outProperties[2]);
				}
				TST.listOfAllNames.clear();
				return values;
			}
			else {return null;}
		}
		
	}