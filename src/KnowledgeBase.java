import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * KnowledgeBase is the memory where the data is loaded and contained. It is uses the array data structure and
 *  binary search tree data structure for the respective apps.
 *
 *
 */
public class KnowledgeBase {

    public static BinarySearchTree<Record> base;

    public static int n = 0;

    public static boolean notFirstTime = false;

    public KnowledgeBase() {}

    /**
     * CreateBase calls on BST constructor and initialises base
     */
    public static void CreateBase() {

        base = new BinarySearchTree<Record>();

    }

    /**
     * Creates new Record
     *
     * @param term  lowercase word acts as unique key in BST
     * @param stmnt first letter uppercase and belongs to term
     * @param confScore represents the validity of statement
     * @return  newly created Record
     */
    public static Record CreateRecord(String term, String stmnt, float confScore){

        return new Record(term, stmnt, confScore);

    }

    /**
     * Adds a Record to the KnowledgeBase. It first checks if the Record exists in the KB, if so then that
     *  Record is replaced if the new confidence score is higher, if not, for the array app, it is not added unless
     *  it is the first file loaded, for the BST app is added as a leaf.
     *
     * @param record    Record to be added
     */
    public static void AddToKB(Record record) {

        BinarySearchTree<Record>.BinaryTreeNode<Record> node = base.find(record);

        if (node != null && record.getConfScore() >= node.data.getConfScore())
            node.data.update(record);
        else
            base.insert(record);

    }

    /**
     * Searches KB for a Record using only the unique term. Utilises the find method in the BinarySearchTree
     *  class
     * @param term  Unique string key as search term
     * @return  Desired Record is returned
     */
    public static Record FindByTerm(String term) {

        Record record = new Record(term, "", 0);
        return base.find(record).data;

    }

    /**
     * Searches KB for a Record using the unique term and it's statement. Utilises the find method in the BinarySearchTree
     *  class
     * @param term  THe term we want to find
     * @param stmnt The statement we want to find
     * @return  Desired Record is returned
     */
    public static Record FindByTermAndStmnt(String term, String stmnt) {

        Record record = new Record(term, stmnt, 0);
        return base.find(record).data;

    }

    /**
     * A line containing the term, tab, statement, tab and confidence score converted to a Record
     * @param line  The String in aforementioned format
     * @return  The Record using details from the line
     */
    public static Record LineToRecord(String line) {

        String[] arr = line.split("\t");
        return CreateRecord(arr[0], arr[1], Float.parseFloat(arr[2]));

    }

    /**
     * A textfile is read using a Scanner and a loop. The lines are converted to Records and then stored in the
     *  KnowledgeBase
     * @param pathname  The name of the textfile that must be in the same location
     *  as the java file (directory should work as well)
     */
    public static void ReadFile(String pathname) {

        if (!notFirstTime)
            CreateBase();

        try {

            Scanner sc = new Scanner(new File(pathname));
            System.out.println("File has been found.");
            while (sc.hasNextLine()) {

                String str = sc.nextLine();

                AddToKB( LineToRecord( str ) );

            }
            sc.close();
            System.out.println("Successfully updated the knowledge base!\n");
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found! Please try again.\n");
        }

        notFirstTime = true;

    }

}