// 213394364 Niv Ben David Ohayone
import java.util.regex.Pattern;

/**
 * The Including class represents a regular expression pattern used to match
 * a specific string pattern. It extends the RelationsCollection class and
 * provides methods to retrieve the compiled regular expression pattern and
 * the string representation of the pattern.
 */
public class Including extends RelationsCollection{
    private String regex;

    /**
     * Instantiates a new Including.
     */
    public Including() {
        this.regex = "<np>([^<]+)</np>\\s?(,?\\s?)including\\s<np>([^<]+)</np>" +
                "(\\s(,?\\s?)<np>([^<]+)</np>)*"
                + "(\\s?,?\\s?(and|or)\\s<np>([^<]+)</np>)?";
    }
    @Override
    public Pattern getRegex(){
        return Pattern.compile(this.regex);
    }
    @Override
    public String getString(){
        return this.regex;
    }

}
