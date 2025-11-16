// 213394364 Niv Ben David Ohayone
import java.util.regex.Pattern;

/**
 * The RelationsCollection abstract class defines an interface for classes
 * representing relation collections, providing methods to retrieve the
 * regex pattern and string representation of the collection. Subclasses
 * implement these methods with specific implementations for their respective
 * relation collections.
 */
public abstract class RelationsCollection implements Relation {

    /**
     * Return the regex pattern for the relations collection.
     *
     * @return the regex pattern for the relations collection.
     */
    public abstract Pattern getRegex();

    /**
     * Return the string representation of the relations collection.
     *
     * @return the string representation of the relations collection.
     */
    public abstract String getString();
}
