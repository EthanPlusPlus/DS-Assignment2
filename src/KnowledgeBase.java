import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

/**
 * KnowledgeBase is the memory where the data is loaded and contained. It is uses the AVL tree data structure and
 */
public class KnowledgeBase {

    public static AVLTree<Record> base;

    public static int n = 0;

    public static boolean notFirstTime = false;


    /**
     * CreateBase calls on AVL constructor and initialises base
     */
    public static void CreateBase() {

        base = new AVLTree<Record>();

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
     * Adds a Record to the KnowledgeBase upon the first run of ReadFile(For the first textfile). It depends on the AVL Tree class insert. It will then update the instruments involved in the insertion.
     *
     * @param record    Record to be added
     */
    public static void AddToInitialKB(Record record) {

        base.insert(record);   
        
        Main.UpdateInsertInstrument();

    }

    /**
     * Checks if a term exists in the KnowledgeBase upon the second run of ReadFile(For the query textfile). It depends on the AVL Tree class find. It will then update the instruments involved in the search.
     *
     * @param record Record to be found
     */
    public static void QueryKB(Record record) {

        BinaryTreeNode<Record> node = base.find(record);

        if ( node != null )
            System.out.println(node.data);
        else
            System.out.println("Term not found: " + record.getTerm());

        Main.UpdateSearchInstrument();

    }

    /**
     * Searches KB for a Record using only the unique term. Utilises the find method in the AVLTree
     *  class
     * @param term  Unique string key as search term
     * @return  Desired Record is returned
     */
    public static Record FindByTerm(String term) {

        Record record = new Record(term, "", 0);
        return base.find(record).data;

    }

    /**
     * Searches KB for a Record using the unique term and it's statement. Utilises the find method in the AVLTree
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
     * A line containing only a term converted to a Record
     * @param query A term
     * @return Record with the query
     */
    public static Record QueryToRecord(String query) {
        return CreateRecord(query, query, 0);
    }

    /**
     * A textfile is read using a Scanner and a loop. The lines are converted to Records and then stored in the
     *  KnowledgeBase.
     * The first call will use AddToInitialKB in order to load the KB. 
     * The second call will use QUeryKB to compare queries to the KB.
     * @param pathname  The name of the textfile that must be in the same location
     *  as the java file (directory should work as well)
     */
    public static void ReadFile(String pathname, int n) {

        //Collections.shuffle(null);
            try {

                Scanner sc = new Scanner(new File(pathname));
                System.out.println("File has been found.");

                if (!notFirstTime){

                    ArrayList<Record> list = GetSubset(n, sc);

                    for (int i = 0; i < list.size(); i++) {
                        
                        AddToInitialKB( list.get(i) );

                    }

                    notFirstTime = true;
                    
                } else {
                
                    while (sc.hasNextLine()) {

                        String str = sc.nextLine();
        
                        QueryKB( QueryToRecord( str ) );
        
                    }

                    notFirstTime = false;

                }

                sc.close();
                System.out.println("Successfully updated the knowledge base!\n");
            }
            catch (FileNotFoundException e) {
                System.out.println("File not found! Please try again.\n");
            }


    }

    /**
     * This method creates a random subset from using the Scanner used in a file.
     * It first defines an offset to start from and then obtains the subset of size n. The list is then shuffled
     * @param n Size of subset
     * @param sc Scanner used to read file
     * @return List of subset
     */
    private static ArrayList<Record> GetSubset(int n, Scanner sc) {

        ArrayList<Record> list = new ArrayList<>();

        if (!notFirstTime){

            CreateBase();

            int counter = 0;
            
            Random random = new Random();
            int offset = random.nextInt(0, 50001 - n);

            while (sc.hasNextLine() && counter <= offset + n) {

                counter++;

                String str = sc.nextLine();

                if (counter < offset){
                    continue;
                }

                

                list.add( LineToRecord(str) );

            }

        }

        Collections.shuffle(list);

        return list;

    }

}