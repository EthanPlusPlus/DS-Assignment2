public class Main {
    
    final static String file1 = "GenericsKB.txt";
    final static String file2 = "GenericsKB-queries.txt";

    final static int n = 1000;

    public static void main(String[] args) {

        KnowledgeBase.ReadFile(file1, n);
        KnowledgeBase.ReadFile(file2, n);

        System.out.println(KnowledgeBase.iCount);
        System.out.println(KnowledgeBase.sCount);

    }

}
