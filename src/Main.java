import java.util.ArrayList;

/**
 * This class initiates and runs the program with regards to the knowledge base. 
 * It also controls and manages the experiments, including automation of the varying of n and the storing of the values of counter.
 * 
 */
public class Main {
    
    private final static String file1 = "GenericsKB.txt";
    private final static String file2 = "test.txt";//"GenericsKB-queries.txt";

    public static int sCount, iCount, sTotal, iTotal, sBest, iBest, sWorst, iWorst, sN, iN, sAvg, iAvg; 

    private static int[] iAvgArr= new int[10], sAvgArr= new int[10], iWorstArr= new int[10], sWorstArr= new int[10], iBestArr= new int[10], sBestArr = new int[10];

    private static boolean notFirstRecord = false;

    /**
     * Initiates program and holds the the values n to be used; spaced evenly on the logarithmic scale.
     * It stores each counter after it has been processed and thereafter resets it.
     * Counters are added to the a list to be print to STDOUT.
     * @param args
     */
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

    /**
     * A method call will use the counter that is tracking insert operations and process it to update the best, worst and sums up total for calculating the average.
     */
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

    /**
     * A method call will use the counter that is tracking search operations and process it to update the best, worst and sums up total for calculating the average.
     */
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

    /**
     * Calculates the average of the insert operations from each insert
     * @return average of the insert operations from each insert
     */
    public static int GetInsertAverage() {

        return (int)(iTotal / iN);

    }

     /**
     * Calculates the average of the search operations from each insert
     * @return average of the search operations from each search
     */
    public static int GetSearchAverage() {

        return (int)(sTotal / sN);

    }

}