// 213394364 Niv Ben David Ohayone

import java.util.regex.Pattern;

/**
 * The Relation interface defines a contract for classes representing
 * relations, specifying methods to retrieve the string representation and
 * regex pattern of a relation.
 */
public interface Relation {

        /**
         * Return the string of the relation.
         *
         * @return the string of the relation.
         */
        String getString();

        /**
         * Return the regex of the relation.
         *
         * @return the regex of the relation.
         */
        Pattern getRegex();
}