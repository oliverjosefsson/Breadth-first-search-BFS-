import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Oliver and Björn.
 */
public class Graph {

    private int V;                              // number of vertices in this digraph
    private LinkedList<Integer> adjacency[];    // adjacency list for vertex v
    private ArrayList<String> wordList;         // list with words from file

    /**
     * Create graph
     * @param wordList
     */
    public Graph(ArrayList<String> wordList) {
    	this.wordList = wordList;
        V = wordList.size();                        	// Total number of vertices
        adjacency = new LinkedList[wordList.size()];  	
        for (int i = 0; i < wordList.size(); ++i) {
            adjacency[i] = new LinkedList<>();          // Space to add adjacency
        }
        searchEdges(wordList);
    }

    /**
     * Add edge in graph
     * @param v
     * @param w
     */
    public void addEdge(int v, int w) {
        adjacency[v].add(w);        
    }

    /**
     * Compare different nodes letters and addEdge() if similar.
     * @param wordList
     */
    public void searchEdges(ArrayList<String> wordList) {

    	int size = wordList.size();
        char[] charWord = new char[4]; //Array that holds the last 4 chars from the chosen word in wordList

        for (int i = 0; i < size; i++) {
            for (int x = 1; x < 5; x++) {
                charWord[x - 1] = wordList.get(i).charAt(x); //Insert chars from chosen word in wordList
                
            }

            for (int j = 0; j < size; j++) { // Loops to compare characters from one word with characters from other words.
                if (i != j) { // Dont check the same word  
                	
                    int matchingChar = equalChars(j, charWord, wordList); //Count matches
                   
                    if (matchingChar == 4) { // Add edge if four matching characters
                       
                    	addEdge(i, j);
                    }
                }
            }
        }
    }

    /**
     * Compare word and returns matched chars
     * @param j
     * @param charWord
     * @param wordList
     * @return
     */
    public int equalChars(int j, char[] charWord, ArrayList<String> wordList) {

        char charOther;
        int charMatching = 0;
        boolean[] usedChars = new boolean[4];
        
        for (int y = 0; y < 4; y++) { // Resets used characters.
            usedChars[y] = false;
        }

        for (int x = 0; x < 5; x++) { // Loops to see if characters from a word matches characters from another word.
            charOther = wordList.get(j).charAt(x);
           
            for (int b = 0; b < 4; b++) {
                if (usedChars[b] == false) {
                    if (charOther == charWord[b]) {
                        charMatching++;
                        usedChars[b] = true;
                        b = 4; // A charOther is not allowed to match with more then one char from charWord.
                    } 
                   
                }
            }
        }

        return charMatching;

    }

    /**
     * Run breadth-first-search on graph
     * @param startNode
     * @param destNode
     * @return
     */
    public int bfs(String startNode, String destNode) {

        BfsClass b = new BfsClass(V, adjacency, wordList);

        return b.bfs(startNode, destNode);
    }
}
