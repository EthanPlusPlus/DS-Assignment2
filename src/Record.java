import java.util.Objects;

/**
 * Record represent a collection containing a term, statement and confidence score
 *
 */
public class Record implements Comparable<Record>{

    String term, stmnt;
    float confScore;

    /**
     * Creates a Record and initialises the instance
     * @param term  A String representing a name of a general object or concept
     * @param stmnt A String representing the description of the term
     * @param confScore A float which represents the confidence in the validity of the statement
     */
    public Record(String term, String stmnt, float confScore) {

        this.term = term;
        this.stmnt = stmnt;
        this.confScore = confScore;

    }

    /**
     * Fetch term
     * @return term
     */
    public String getTerm() {
        return term;
    }

    /**
     * Fetch statement
     * @return statement
     */
    public String getStmnt() {
        return stmnt;
    }

    /**
     * Fetch confidence score
     * @return confidence score
     */
    public float getConfScore() {
        return confScore;
    }

    /**
     * Set term
     * @param term change to
     */
    public void setTerm(String term) {
        this.term = term;
    }

    /**
     * Set statement
     * @param stmnt change to
     */
    public void setStmnt(String stmnt) {
        this.stmnt = stmnt;
    }

    /**
     * Set confidence score
     * @param confScore change to
     */
    public void setConfScore(float confScore) {
        this.confScore = confScore;
    }

    /**
     * Change current Record details to match another Record excluding the term
     * @param record Record to take from
     */
    public void update(Record record) {

        this.stmnt = record.getStmnt();
        this.confScore = record.getConfScore();

    }

    /**
     * Compare Record to another lexicographically
     * @param o the object to be compared.
     * @return see javadoc for java.lang.String.compareTo()
     */
    public int compareTo(Record o) {

        return this.getTerm().compareTo(o.getTerm());

    }

    /**
     * Checks if current Record and another are the same
     * @param o Other Record
     * @return  true if same else false
     */
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Record record = (Record) o;
        return Float.compare(confScore, record.confScore) == 0 && Objects.equals(term, record.term) && Objects.equals(stmnt, record.stmnt);

    }

    /**
     * Display information about Record
     * @return  String containing the information separated by tabs
     */
    public String toString() {

        return term + "\t" + stmnt + "\t" + confScore;

    }

}
