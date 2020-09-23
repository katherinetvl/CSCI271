/********************************************
* Katherine Le
* CSCI 271 First Java PGM
* Description: Empirical Analysis Exploration
********************************************/
import java.lang.Math;      // for random() function 
import java.util.Arrays;    // for returning arr in function

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

        int[] arrAA = new int[1000];
        // Generate random array 
        for (int a = 0; a < arrAA.length; a++)
        {
            arrAA[a] = RandomInt(1000);
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

        // Output size and SortAnalysis count
        System.out.println("Randomly generated int arrays:");
        System.out.println("n " + "\t" + "M ");
        // System.out.println(arr1.length + "\t" + result);
        System.out.println(arrAA.length + "\t" + resultAA);

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
        int n = arr.length;

        for (int i = 1; i < n - 1; i++)
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