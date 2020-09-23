/********************************************
* Katherine Le
* CSCI 271 First Java PGM
* Description: Empirical Analysis Exploration
********************************************/
import java.lang.Math;      // for random() function 

public class EmpiricalAnalysis 
{
    public static void main(String[] args) 
    {
        /* 
        int[] arr1 = new int[10];
        // Generate random array 
        for (int a = 0; a < arr1.length; a++)
        {
            arr1[a] = RandomInt(10);
        }
        */

        // Generate 20 arrays 
        int[] arrAA = new int[1000];
        for (int a = 0; a < arrAA.length; a++)
        {
            arrAA[a] = RandomInt(1000);
        }

        int[] arrAB = new int[2000];
        for (int b = 0; b < arrAB.length; b++)
        {
            arrAB[b] = RandomInt(2000);
        }

        int[] arrAC = new int[3000];
        for (int c = 0; c < arrAC.length; c++)
        {
            arrAC[c] = RandomInt(3000);
        }

        int[] arrAD = new int[4000];
        for (int d = 0; d < arrAD.length; d++)
        {
            arrAD[d] = RandomInt(4000);
        }

        int[] arrAE = new int[5000];
        for (int e = 0; e < arrAE.length; e++)
        {
            arrAE[e] = RandomInt(5000);
        }

        int[] arrAF = new int[6000];
        for (int f = 0; f < arrAF.length; f++)
        {
            arrAF[f] = RandomInt(6000);
        }

        int[] arrAG = new int[7000];
        for (int g = 0; g < arrAG.length; g++)
        {
            arrAG[g] = RandomInt(7000);
        }

        int[] arrAH = new int[8000];
        for (int h = 0; h < arrAH.length; h++)
        {
            arrAH[h] = RandomInt(8000);
        }

        int[] arrAI = new int[9000];
        for (int ii = 0; ii < arrAI.length; ii++)
        {
            arrAI[ii] = RandomInt(9000);
        }

        int[] arrAJ = new int[10000];
        for (int j = 0; j < arrAJ.length; j++)
        {
            arrAJ[j] = RandomInt(10000);
        }

        int[] arrAK = new int[11000];
        for (int k = 0; k < arrAK.length; k++)
        {
            arrAK[k] = RandomInt(11000);
        }

        int[] arrAL = new int[12000];
        for (int l = 0; l < arrAL.length; l++)
        {
            arrAL[l] = RandomInt(12000);
        }

        int[] arrAM = new int[13000];
        for (int m = 0; m < arrAM.length; m++)
        {
            arrAM[m] = RandomInt(13000);
        }

        int[] arrAN = new int[14000];
        for (int n = 0; n < arrAN.length; n++)
        {
            arrAN[n] = RandomInt(14000);
        }

        int[] arrAO = new int[15000];
        for (int o = 0; o < arrAO.length; o++)
        {
            arrAO[o] = RandomInt(15000);
        }

        int[] arrAP = new int[16000];
        for (int p = 0; p < arrAP.length; p++)
        {
            arrAP[p] = RandomInt(16000);
        }

        int[] arrAQ = new int[17000];
        for (int q = 0; q < arrAQ.length; q++)
        {
            arrAQ[q] = RandomInt(17000);
        }

        int[] arrAR = new int[18000];
        for (int r = 0; r < arrAR.length; r++)
        {
            arrAR[r] = RandomInt(18000);
        }

        int[] arrAS = new int[19000];
        for (int s = 0; s < arrAS.length; s++)
        {
            arrAS[s] = RandomInt(19000);
        }

        int[] arrAT = new int[20000];
        for (int t = 0; t < arrAT.length; t++)
        {
            arrAT[t] = RandomInt(20000);
        }

        /*
        //Test array contents by printing 
        for (int z = 0; z < arr1.length; z++)
        {
            System.out.println(arr1[z] + "\n");
        }
        System.out.println(); 
        */        

        // Invoke SortAnalysis for random array 
        // int result = SortAnalysis(arr1);
        int resultAA = SortAnalysis(arrAA);
        int resultAB = SortAnalysis(arrAB);
        int resultAC = SortAnalysis(arrAC);
        int resultAD = SortAnalysis(arrAD);
        int resultAE = SortAnalysis(arrAE);
        int resultAF = SortAnalysis(arrAF);
        int resultAG = SortAnalysis(arrAG);
        int resultAH = SortAnalysis(arrAH);
        int resultAI = SortAnalysis(arrAI);
        int resultAJ = SortAnalysis(arrAJ);
        int resultAK = SortAnalysis(arrAK);
        int resultAL = SortAnalysis(arrAL);
        int resultAM = SortAnalysis(arrAM);
        int resultAN = SortAnalysis(arrAN);
        int resultAO = SortAnalysis(arrAO);
        int resultAP = SortAnalysis(arrAP);
        int resultAQ = SortAnalysis(arrAQ);
        int resultAR = SortAnalysis(arrAR);
        int resultAS = SortAnalysis(arrAS);
        int resultAT = SortAnalysis(arrAT);

        // Output size and SortAnalysis count
        System.out.println("Randomly generated int arrays:");
        System.out.println("n " + "\t" + "M ");
        // System.out.println(arr1.length + "\t" + result);
        System.out.println(arrAA.length + "\t" + resultAA);
        System.out.println(arrAB.length + "\t" + resultAB);
        System.out.println(arrAC.length + "\t" + resultAC);
        System.out.println(arrAD.length + "\t" + resultAD);
        System.out.println(arrAE.length + "\t" + resultAE);
        System.out.println(arrAF.length + "\t" + resultAF);
        System.out.println(arrAG.length + "\t" + resultAG);
        System.out.println(arrAH.length + "\t" + resultAH);
        System.out.println(arrAI.length + "\t" + resultAI);
        System.out.println(arrAJ.length + "\t" + resultAJ);
        System.out.println(arrAK.length + "\t" + resultAK);
        System.out.println(arrAL.length + "\t" + resultAL);
        System.out.println(arrAM.length + "\t" + resultAM);
        System.out.println(arrAN.length + "\t" + resultAN);
        System.out.println(arrAO.length + "\t" + resultAO);
        System.out.println(arrAP.length + "\t" + resultAP);
        System.out.println(arrAQ.length + "\t" + resultAQ);
        System.out.println(arrAR.length + "\t" + resultAR);
        System.out.println(arrAS.length + "\t" + resultAS);
        System.out.println(arrAT.length + "\t" + resultAT);

        /*
        // Print random array after SortAnalysis 
        for (int z = 0; z < arr1.length; z++)
        {
            System.out.println(arr1[z] + "\n");
        }
        System.out.println(); 
        */

/********************************************
Ascending arrays
********************************************/
        int[] arr2 = new int[8];
         // Generate ascending array 
        for (int a = 0; a < arr2.length; a++)
        {
            arr2[a] = a;
        }

        // Invoke SortAnalysis for ascending array
        int result2 = SortAnalysis(arr2);

        // Output size and SortAnalysis count
        System.out.println("\n" + "Ascending int arrays:");
        System.out.println("n " + "\t" + "M ");
        System.out.println(arr2.length + "\t" + result2);

        /*
        // Print ascending array after SortAnalysis  
        for (int z = 0; z < arr2.length; z++)
        {
            System.out.println(arr2[z] + "\n");
        }
        System.out.println(); 
        */

/********************************************
Descending arrays
********************************************/
        int[] arr3 = new int[16];
        // Generate descending array 
        for (int a = arr3.length - 1; a > 0; a--)
        {
            arr3[a] = a;
        }

        // Invoke SortAnalysis for descending array
        int result3 = SortAnalysis(arr3);

        // Output size and SortAnalysis count
        System.out.println("\n" + "Ascending int arrays:");
        System.out.println("n " + "\t" + "M ");
        System.out.println(arr3.length + "\t" + result3);

        /*
        // Print descending array after SortAnalysis 
        for (int z = 0; z < arr3.length; z++)
        {
            System.out.println(arr3[z] + "\n");
        }
        System.out.println(); 
        */

    }
// Function for SortAnalysis
// Input: An array with orderable elements
// Output: The total number of key comparisons made 
    public static int SortAnalysis(int arr[])
    {
        int count = 0; 
        int end = arr.length;

        for (int i = 1; i < end - 1; i++)
        {
            int v = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > v)
            {
                count = count + 1;
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            /* 
            * in the event that comparison fails and while loop does not execute
            * the comparison was still made, so count should increment
            */ 
            if (j >= 0)
            {
                count = count + 1;
            }

            arr[j + 1] = v;
        }
        return count;
    }

// Function to generate random int
// Input: Integer
// Output: Integer such that 0 <= integer <= input integer
	public static int RandomInt(int num)
	{	
        int randInt = (int)Math.round(Math.random() * num);

		return randInt;
	}
}