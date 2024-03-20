public class Main {
    
    private final static String file1 = "GenericsKB.txt";
    private final static String file2 = "GenericsKB-queries.txt";

    private static int[] instruments = new int[20];


    public static void main(String[] args) {

        int[] n = {1, 10, 55, 100, 550, 1000, 5500, 10000, 30000, 50000};
        int epoch = 0;
        for (int i : n) {
            

            KnowledgeBase.ReadFile(file1, i);
    
            KnowledgeBase.ReadFile(file2, i);

            instruments[epoch++] = KnowledgeBase.iCount;
            instruments[epoch++] = KnowledgeBase.sCount;

            KnowledgeBase.iCount = 0;
            KnowledgeBase.sCount = 0;

        }

        for (int i : instruments) {
            System.out.println(i);
        }

    }

}
