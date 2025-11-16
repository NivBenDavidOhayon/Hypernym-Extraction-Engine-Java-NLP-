// 213394364 Niv Ben David Ohayone

import java.io.IOException;
import java.rmi.UnexpectedException;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * The DiscoverHypernym class is a program that reads command line arguments,
 * performs operations on a corpus based on the provided arguments, and
 * prints the results. Specifically, it reads a folder of a corpus, identifies
 * occurrences of a given lemma, and outputs the occurrences along with their
 * frequencies in descending order.
 */
public class DiscoverHypernym {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IOException the io exception
     */
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("Must get at least two args!");
            return;
        }
        String lemma = args[1];
        TreeMap<String, Integer> map = CorpusReader.readFolder(args[0], lemma);
        map.entrySet().stream().sorted(Map.Entry.comparingByValue
                (Comparator.reverseOrder())).forEach(entry -> System.out.println
                (entry.getKey() + ": ("+entry.getValue()+")"));
        if (map.isEmpty()) {
            System.out.println("The lemma doesn't appear in the corpus.");
        }
    }
}

