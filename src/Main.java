import java.util.ArrayList;

public class Main {
    
    private final static String file1 = "GenericsKB.txt";
    private final static String file2 = "GenericsKB-queries.txt";

    public static int sCount, iCount, sTotal, iTotal, sBest, iBest, sWorst, iWorst, sN, iN, sAvg, iAvg; 

    private static int[] iAvgArr= new int[10], sAvgArr= new int[10], iWorstArr= new int[10], sWorstArr= new int[10], iBestArr= new int[10], sBestArr = new int[10];

    private static boolean notFirstRecord = false;

    public static void main(String[] args) {

        int[] n = {1, 10, 55, 100, 550, 1000, 5500, 10000, 30000, 50000};
        int epoch = 0;
        for (int i : n) {
            

            KnowledgeBase.ReadFile(file1, i);
            KnowledgeBase.ReadFile(file2, i);

            iAvgArr[epoch] = GetInsertAverage();
            sAvgArr[epoch] = GetSearchAverage();
            iBestArr[epoch] = iBest;
            iWorstArr[epoch] = iWorst;
            System.out.println("ethan is a "+iWorst+" "+iWorstArr[epoch]);
            sBestArr[epoch] = sBest;
            sWorstArr[epoch] = sWorst;
            epoch++;
            

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

        ArrayList<int[]> instruments = new ArrayList<>();
        instruments.add(iAvgArr);
        instruments.add(sAvgArr);
        instruments.add(iBestArr);
        instruments.add(iWorstArr);
        instruments.add(sBestArr);
        instruments.add(sWorstArr);

        for (int[] i : instruments) {
            for (int j : i) {
                System.out.println(j);
            }
        }

    }

    public static void UpdateInsertInstrument() {

        if (iCount < iBest)
            iBest = iCount;

        if (iCount > iWorst){ //problem
            iWorst = iCount;
        }

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