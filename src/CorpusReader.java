// 213394364 Niv Ben David Ohayone

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * The CorpusReader class reads files, extracts hyponyms, and updates a word
 * frequency map based on a provided lemma. It also provides a method to read
 * a folder of files and return the resulting word frequency map.
 */
public class CorpusReader {


    /**
     * The findHyponyms method extracts hyponyms from a given line by applying
     * a regular expression pattern. It returns a list of the extracted hyponyms.
     *
     * @param line the line
     * @return the list
     */
    public static List<String> findHyponyms(String line){
        // Create a new ArrayList to store the extracted hyponyms
        List<String> hyponyms = new ArrayList<>();
        // Define the regular expression pattern to match the hyponyms
        String regex = "<np>(.*?)</np>";
        // Compile the regex pattern into a Pattern object
        Pattern pattern = Pattern.compile(regex);
        // Create a Matcher object by applying the pattern to the input line
        Matcher matcher = pattern.matcher(line);
        // Iterate over the matches found by the Matcher
        while (matcher.find()){
            // Extract the hyponym from the match and trim any leading/trailing spaces
            String hyponym = matcher.group(1).trim();
            // Add the hyponym to the list of extracted hyponyms
            hyponyms.add(hyponym);
        }
        // Return the list of extracted hyponyms
        return hyponyms;
    }


    /**
     * The readFile method reads the contents of a file, searches for
     * occurrences of a given lemma in each line, and applies different patterns
     * to extract relationships. It updates a TreeMap with the frequencies of
     * hypernyms and matching hyponyms related to the lemma found in the file.
     *
     * @param fromFile the from file
     * @param map      the map
     * @param lemma    the lemma
     * @throws IOException the io exception
     */
    public static void readFile(File fromFile, TreeMap<String, Integer> map, String lemma)
            throws IOException {
        // Define the patterns to match relations
        Relation[] Patterns = {new SuchAs(), new SuchNpAs(),
                new Including(), new Especially()};
        Relation pattern5 = new WhichIs();

        BufferedReader buffer = null;
        try {
            buffer = new BufferedReader(new InputStreamReader(new FileInputStream(fromFile)));
            String line;
            // check any line in the code
            while ((line = buffer.readLine()) != null) {
                //check if the line contain the lemma at all
                if (line.contains(lemma)) {
                    for (Relation pattern : Patterns) {
                        Pattern relationPattern = Pattern.compile(pattern.getString());
                        List<String> matchPortions1 = new ArrayList<>();
                        Matcher matchOne = relationPattern.matcher(line);
                        // Find all match portions in the line
                        while (matchOne.find()) {
                            String matchPortion = matchOne.group();
                            matchPortions1.add(matchPortion);
                        }
                        // Process each match portion
                        for (String matchPortion : matchPortions1) {
                            List<String> hyponyms = findHyponyms(matchPortion);
                            String hypernym = hyponyms.get(0);
                            boolean isHypernym = true;
                            // Check if hyponyms match the lemma and update the map
                            for (String hyponym : hyponyms) {
                                if (isHypernym) {
                                    isHypernym = false;
                                    continue;
                                }
                                if (hyponym.equalsIgnoreCase(lemma)) {
                                    updateMapWithHypernymFrequency(map, hypernym);
                                }
                            }
                        }
                    }
                    // Apply the "WhichIs" pattern to the line
                    Pattern relationPattern2 = Pattern.compile(pattern5.getString());
                    Matcher matchTwo = relationPattern2.matcher(line);
                    List<String> matchPortions2 = new ArrayList<>();

                    // Find all match portions in the line
                    while (matchTwo.find()) {
                        String matchPortion = matchTwo.group();
                        matchPortions2.add(matchPortion);
                    }
                    // Process each match portion
                    for (String matchPortion : matchPortions2) {
                        updateMapWithMatchingHyponym(map, lemma, matchPortion);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //Close the stream//
            if (buffer != null) {
                buffer.close();
            }
        }
    }

    /**

     * The updateMapWithMatchingHyponym method updates a TreeMap by
     * incrementing the frequency count of a hypernym if a provided lemma
     * matches a given hyponym. It first retrieves a list of hyponyms from
     * the matchPortion by calling the findHyponyms method. Then, it checks
     * if the hyponym matches the provided lemma. If it does, it follows the
     * same logic as the updateMapWithHypernymFrequency method to update the
     * map accordingly.
     *
     * @param map the map
     * @param lemma   the lemma
     * @param matchPortion   the matchPortion
     */

    private static void updateMapWithMatchingHyponym(TreeMap<String, Integer> map,
                                                     String lemma, String matchPortion) {
        // Call the findHyponyms method to retrieve a list of hyponyms from the matchPortion
        List<String> hyponyms = findHyponyms(matchPortion);
        // Retrieve the hypernym and hyponym from the hyponyms list
        String hypernym = hyponyms.get(1);
        String hyponym = hyponyms.get(0);
        // Check if the hyponym matches the provided lemma
        if (hyponym.equalsIgnoreCase(lemma)) {
            // If it does, check if the map already contains the hypernym as a key
            if (map.containsKey(hypernym)) {
                // If it does, retrieve the current value associated with the hypernym
                int value = map.get(hypernym);
                // Increment the value by 1 and update the map with the new value
                map.put(hypernym, value + 1);
            } else {
                /*If the hypernym is not already a key in the map, add it with
                an initial value of 1*/
                map.put(hypernym, 1);
            }
        }
    }

    /**
     * The updateMapWithHypernymFrequency method updates a TreeMap by
     * incrementing the frequency count of a given hypernym. If the hypernym
     * already exists as a key in the map, its value is retrieved and
     * incremented by 1. If the hypernym is not already present, it is
     * added as a new key with an initial value of 1.
     *
     * @param map the map
     * @param hypernym   the hypernym
     */
    private static void updateMapWithHypernymFrequency(TreeMap<String,
            Integer> map, String hypernym) {
        // Check if the map already contains the hypernym as a key
        if (map.containsKey(hypernym)) {
            // If it does, retrieve the current value associated with the hypernym
            int value = map.get(hypernym);
            // Increment the value by 1 and update the map with the new value
            map.put(hypernym, value + 1);
        } else {
            /* If the hypernym is not already a key in the map, add it with an
             initial value of 1*/
            map.put(hypernym, 1);
        }
    }


    /**
     * The readFolder method reads a folder containing files specified by the
     * address and processes each file to update a TreeMap with word
     * frequencies. It returns the resulting map of word frequencies.
     *
     * @param address the address
     * @param lemma   the lemma
     * @return the tree map
     * @throws IOException the io exception
     */
    public static TreeMap<String, Integer> readFolder(String address, String lemma)
            throws IOException {
        // Create a File object using the specified folder address
        File folder = new File(address);
        // Get an array of File objects representing the files in the folder
        File[] folderFiles = folder.listFiles();
        // Create a TreeMap to store word frequencies
        TreeMap<String, Integer> map = new TreeMap<>();
        //Read every file in the folder
        for (File file : folderFiles) {
            /* Call the readFile method to process the file and update the map
             with word frequencies*/
            readFile(file, map, lemma);
        }
        // Return the map containing word frequencies
        return map;
    }
}
