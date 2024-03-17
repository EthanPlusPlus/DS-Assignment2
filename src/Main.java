public class Main {
    
    final static String file1 = "GenericsKB.txt";
    final static String file2 = "GenericsKB-queries.txt";

    public static void main(String[] args) {

        KnowledgeBase.ReadFile(file1);
        KnowledgeBase.ReadFile(file2);

        System.out.println(KnowledgeBase.iCount);
        System.out.println(KnowledgeBase.sCount);

    }

}
