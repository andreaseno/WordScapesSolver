import java.util.*;
import java.io.*;

public class WordScape{
    static char[] letters;
    static HashSet<String> dictionary;
    static int buckets = 100;
    static LinkedList<String> permutations;
    static LinkedList<String> solutions;


    static void AllStrings(boolean[] used, int k, String prefix)
    {
        if (prefix.length()>=3 && !permutations.contains(prefix)){
            permutations.add(prefix);
            // System.out.println("prefix : "+ prefix);
        }
        // Base case: k is 0,
        if (k == 0)
        {
            return;
        }
     
        // // One by one add all characters
        // // from set and recursively
        // // call for k equals to k-1
        for (int i = 0; i < letters.length; ++i){
            if (!used[i]){
                used[i] = true;
                AllStrings(used, k--, prefix+letters[i]);
                used[i] = false;
            }
        }
        // {
     
        //     // Next character of input added
        //     String newPrefix = prefix + set[i];
             
        //     // k is decreased, because
        //     // we have added a new character
        //     AllKLengthRec(set, newPrefix,
        //                             n, k - 1);
        // }
    }
    
    
    private static void printSol() throws FileNotFoundException{
        // for (int i = 0; i < letters.length; i++){ 
        //     System.out.println(letters[i]);
        // }

        // import whole dictionary into hashmap
        // initialize hm
        dictionary = new HashSet<String>();
        permutations = new LinkedList<String>();
        solutions = new LinkedList<String>();
        // FOR LATER MAKE THIS WORK REGARDLESS WHERE FILE IS
        File dict = new File("/Users/roa/Desktop/gwu_stuff/personal/WordScape/dictionary.txt");
        BufferedReader br = new BufferedReader(new FileReader(dict));
        String word;
        try {
            // read in the file
            while((word = br.readLine()) != null) {
                // System.out.println(word + " hash = "+hash(word));
                // hash each word
                dictionary.add(word.toUpperCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // try every combination of letters
        // hash current combination
        // look up in hashtablle
        // if found add to a list of solutions
        // System.out.println("\n\n"+dictionary.contains("rock"));
        // String searchquery = "";
        // for (int i = 0; i<letters.length; i++){
        //     searchquery+=letters.get(i);
        // }
        // System.out.println(searchquery);
        // for (int i = 0; i<letters.length; i++){
        boolean [] used = new boolean[letters.length];
        // for (int i = 0; i < letters.length; ++i){
            // used[i] = true;
        AllStrings(used, letters.length, "");
            // used[i] = false;
        // }
        


        for (int i = 0; i<permutations.size(); i++){
            // System.out.println(permutations.get(i));
            String curperm = permutations.get(i);
            if (dictionary.contains(curperm)){
                solutions.add(curperm);
            }
        }
        for (int i = 0; i<solutions.size(); i++){
            System.out.println(solutions.get(i));
        }



    }
    public static void main (String[] argv) throws FileNotFoundException
    {   
        if (argv.length==1){
            // parse input
                letters = new char[argv[0].length()];
                // letters = new ArrayList<Character>()
                for (int i = 0; i < argv[0].length(); i++){
                    letters[i] = argv[0].toUpperCase().charAt(i);
                }
            // solve for the given solution
            printSol();
        }
        else System.out.println("please call as: WordScape <insert letters here> ");
    }
}