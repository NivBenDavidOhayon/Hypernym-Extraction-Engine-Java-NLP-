// 213394364 Niv Ben David Ohayone
import java.util.regex.Pattern;

/**
 * The WhichIs class represents a regular expression pattern used to match a
 * specific string pattern. It extends a class named RelationsCollection and
 * provides methods to retrieve the compiled regular expression pattern and
 * the string representation of the pattern.
 */
public class WhichIs extends RelationsCollection {
    private String regex;

    /**
     * Instantiates a new Which is.
     */
    public WhichIs() {
        this.regex = "<np>([^<]+)</np>\\s?(,?\\s?)which\\sis\\s"
                + "((an\\sexample|a\\skind|a\\sclass)\\sof\\s)?<np>([^<]+)</np>";
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
