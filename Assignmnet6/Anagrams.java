//Name - AKSHAT SAHU
//CWID - 20022122

import java.util.ArrayList;
import java.io.*;
import java.util.*;

/**
 * Anagrams class represents a program that finds anagrams in a given list of words.
 */
public class Anagrams {
    // Data
    final Integer[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
    Map<Character, Integer> letterTable;
    Map<Long, ArrayList<String>> anagramTable;

    /**
     * Constructor for the Anagrams class.
     * Initializes letterTable and anagramTable.
     */
    public Anagrams() {
        letterTable = new HashMap<Character, Integer>();
        buildLetterTable();
        anagramTable = new HashMap<Long, ArrayList<String>>();
    }

    /**
     * Builds the letterTable with characters and their corresponding prime numbers.
     */
    private void buildLetterTable() {
        Character[] a = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        int i = 0;
        while (i < 26) {
            letterTable.put(a[i], primes[i]);
            i++;
        }
    }

    /**
     * Adds a word to the anagramTable.
     *
     * @param s The word to be added.
     */
    private void addWord(String s) {
        if (!anagramTable.containsKey(myHashCode(s))) {
            ArrayList<String> x = new ArrayList<String>();
            x.add(s);
            anagramTable.put(myHashCode(s), x);
        } else {
            ArrayList<String> x = anagramTable.get(myHashCode(s));
            x.add(s);
            anagramTable.replace(myHashCode(s), x);
        }
    }

    /**
     * Computes the unique hash code for a given string.
     *
     * @param s The input string.
     * @return The computed hash code.
     */
    private Long myHashCode(String s) {
        int i = 0;
        long hashCode = 1;
        while (i < s.length()) {
            Character x = s.charAt(i);
            hashCode *= letterTable.get(x);
            i++;
        }
        return hashCode;
    }

    /**
     * Processes the input file and adds words to the anagramTable.
     *
     * @param s The file path.
     * @throws IOException If an I/O error occurs.
     */
    private void processFile(String s) throws IOException {
        FileInputStream fstream = new FileInputStream(s);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String strLine;
        while ((strLine = br.readLine()) != null) {
            this.addWord(strLine);
        }
        br.close();
    }

    /**
     * Gets the entries with the maximum number of anagrams.
     *
     * @return List of entries with the maximum number of anagrams.
     */
    private ArrayList<Map.Entry<Long, ArrayList<String>>> getMaxEntries() {
        ArrayList<Map.Entry<Long, ArrayList<String>>> x = new ArrayList<Map.Entry<Long, ArrayList<String>>>();
        int a = 0;
        for (Map.Entry<Long, ArrayList<String>> z : anagramTable.entrySet()) {
            if (z.getValue().size() == a) {
                x.add(z);
            } else {
                if (z.getValue().size() > a) {
                    x.clear();
                    x.add(z);
                    a = z.getValue().size();
                }
            }
        }
        return x;
    }

    /**
     * Main method to run the Anagrams program.
     *
     * @param args Command line arguments (not used in this program).
     */
    public static void main(String[] args) {
        Anagrams anagram = new Anagrams();
        final long startTime = System.nanoTime();
        try {
            anagram.processFile("words_alpha.txt");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        ArrayList<Map.Entry<Long, ArrayList<String>>> maxEntries = anagram.getMaxEntries();
        final long estimatedTime = System.nanoTime() - startTime;
        final double seconds = ((double) estimatedTime / 1000000000);
        System.out.println("Elapsed Time : " + seconds);
        System.out.println("Key  of  max  anagrams : " + maxEntries.get(0).getKey());
        System.out.println("List  of  max  anagrams :  " + maxEntries.get(0).getValue());
        System.out.println("Length  of  list  of  max  anagrams : " + maxEntries.get(0).getValue().size());
    }
}
