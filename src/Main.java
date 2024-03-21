public class Main {
    
    private final static String file1 = "GenericsKB.txt";
    private final static String file2 = "GenericsKB-queries.txt";

    public static int sCount, iCount, sTotal, iTotal, sBest, iBest, sWorst, iWorst, sN, iN, sAvg, iAvg; 

    private static int[] instruments = new int[60];

    private static boolean notFirstRecord = false;

    public static void main(String[] args) {

        int[] n = {1, 10, 55, 100, 550, 1000, 5500, 10000, 30000, 50000};
        int epoch = 0;
        for (int i : n) {
            

            KnowledgeBase.ReadFile(file1, i);
            KnowledgeBase.ReadFile(file2, i);

            instruments[epoch++] = iBest;
            instruments[epoch++] = iWorst;
            instruments[epoch++] = GetInsertAverage();
            instruments[epoch++] = sBest;
            instruments[epoch++] = sWorst;
            instruments[epoch++] = GetSearchAverage();

            iCount = 0;
            sCount = 0;
            iBest = 0;
            sBest = 0;
            iWorst = 0;
            sWorst = 0;
            iTotal = 0;
            sTotal = 0;
            iN = 0;
            sN = 0;
            notFirstRecord = false;

        }

        for (int i : instruments) {
            System.out.println(i);
        }

    }

    public static void UpdateInsertInstrument() {

        if (iCount < iBest)
            iBest = iCount;

        if (iCount > iWorst)
            iWorst = iCount;

        iTotal += iCount;
        iCount = 0;

        iN++;

    }

    public static void UpdateSearchInstrument() {

        if (!notFirstRecord || sCount < sBest)
            sBest = sCount; 

        if (sCount > sWorst)
            sWorst = sCount;

        sTotal += sCount;
        sCount = 0;

        notFirstRecord = true;

        sN++;

    }

    public static int GetInsertAverage() {

        return (int)(iTotal / iN);

    }

    public static int GetSearchAverage() {

        return (int)(sTotal / sN);

    }

}