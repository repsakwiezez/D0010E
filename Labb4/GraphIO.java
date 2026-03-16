import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class GraphIO{
	
	static public void readFile(Graph g, String filename) throws IOException{
		
		try (Scanner scan = new Scanner(new File(filename))) { //Tries to create a scanner to read file, if it doesn't work throws IOException and closes scanner
		
			int n = scan.nextInt();				//Checks the first line in the file that determines how far Nodeinfo goes
		
			for (int i = 0; i < n; i++) {		//for loop using the initial n to determine when to stop
			
				int id = scan.nextInt();		//Takes the first value on the line and puts it as id
				int x = scan.nextInt();			//Takes the second value on the line and puts it as x-cord
				int y = scan.nextInt();			//Takes the third value on the line and puts it as y-cord
			
				g.addNode(id, x, y);			//End of the loop adds the three values in to Graph as NODE
			}
		
			while (scan.hasNextInt()) {			//Scans the rest of the file, goes until there is no more integers
			
				int id1 = scan.nextInt();		//Takes the first value and puts it as id1
				int id2 = scan.nextInt();		//Takes the second value and puts it as id2
				int weight = scan.nextInt();	//Takes the third value and puts it as weight
			
				g.addEdge(id1, id2, weight);	//End of the loop adds the three values in to Graph as EDGE
			}
		}
	}
}