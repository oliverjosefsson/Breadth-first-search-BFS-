import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Oliver and Björn.
 */
public class Main {

	
    public static void main(String args[]) throws IOException {

        ArrayList<String> wordList;

//        wordList = readData("word-14-data.txt");
//        wordList = readData("word-250-data.txt");
        wordList = readData("word-5757-data.txt");
        
        Graph graph = new Graph(wordList);
        
//        readTest("word-14-test.txt", graph);
//        readTest("word-250-test.txt", graph);
        readTest("word-5757-test.txt", graph);
    }

    /**
     * reads the data-file and returns a list of words
     * @param filename
     * @return wordList
     * @throws IOException
     */
    public static ArrayList<String> readData(String filename) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
        ArrayList<String> wordList = new ArrayList<String>();
       
        while (true) {
            String word = r.readLine();
            if (word == null) { 
            	break; 
            }
            assert word.length() == 5; 
            wordList.add(word);
        }

        return wordList;
    }

    /**
     * reads the test-file 
     * @param filename
     * @param g
     * @throws IOException
     */
    public static void readTest(String filename, Graph g) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
        while (true) {
            String line = r.readLine();
            if (line == null) {
                break;
            }
            assert line.length() == 11; 
            String start = line.substring(0, 5);
            String goal = line.substring(6, 11);

            System.out.println(start + " -> " + goal + ": " + g.bfs(start, goal)); //Skriv ut vÃ¤gen mellan noderna
        }
    }
}
