import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * KnowledgeBase is the memory where the data is loaded and contained. It is uses the array data structure and
 *  binary search tree data structure for the respective apps.
 *
 *
 */
public class KnowledgeBase {



    public static AVLTree<Record> base;

    public static int n = 0;

    public static boolean notFirstTime = false;

    public static int sCount, iCount; 
    

    /**
     * CreateBase calls on BST constructor and initialises base
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
     * Adds a Record to the KnowledgeBase. It first checks if the Record exists in the KB, if so then that
     *  Record is replaced if the new confidence score is higher, if not, for the array app, it is not added unless
     *  it is the first file loaded, for the BST app is added as a leaf.
     *
     * @param record    Record to be added
     */
    public static void AddToInitialKB(Record record) {

        base.insert(record);        

        // BinaryTreeNode<Record> node = base.find(record);

        // if (node != null && record.getConfScore() >= node.data.getConfScore())
        //     node.data.update(record);
        // else
        //     base.insert(record);

    }

    public static void QueryKB(Record record) {

        BinaryTreeNode<Record> node = base.find(record);

        if ( node != null )
            System.out.println(node.data);
        else
            System.out.println("Term not found: " + record.getTerm());


    }

    /**
     * Searches KB for a Record using only the unique term. Utilises the find method in the AVLAVLTree
     *  class
     * @param term  Unique string key as search term
     * @return  Desired Record is returned
     */
    public static Record FindByTerm(String term) {

        Record record = new Record(term, "", 0);
        return base.find(record).data;

    }

    /**
     * Searches KB for a Record using the unique term and it's statement. Utilises the find method in the AVLAVLTree
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

    public static Record QueryToRecord(String query) {
        return CreateRecord(query, query, 0);
    }

    /**
     * A textfile is read using a Scanner and a loop. The lines are converted to Records and then stored in the
     *  KnowledgeBase
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