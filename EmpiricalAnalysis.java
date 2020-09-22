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
        int[] arr1 = new int[10];
        System.out.println(arr1.length + "\n");

        // Generate random array 
        for (int y = 0; y < arr1.length; y++)
        {
            arr1[y] = RandomInt(10);
        }

        // Invoke SortAnalysis 
        int result = SortAnalysis(arr1);
        System.out.println(result + "\n");

        /* Test array contents by printing 
        for (int z = 0; z < arr1.length; z++)
        {
            System.out.println(arr1[z] + "\n");
        }
        System.out.println(); 
        */

        int[] arr2 = new int[4];
        System.out.println(arr2.length + "\n");

        // Generate ascending array 
        for (int y = 0; y < arr2.length; y++)
        {
            arr2[y] = y;
        }

        /* Test array contents by printing 
        for (int z = 0; z < arr2.length; z++)
        {
            System.out.println(arr2[z] + "\n");
        }
        System.out.println(); 
        */

        int[] arr3 = new int[8];
        System.out.println(arr3.length + "\n");

        // Generate descending array 
        for (int y = arr3.length - 1; y > 0; y--)
        {
            arr3[y] = y;
        }

        /* Test array contents by printing 
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

        for (int i = 1; i < (arr.length - 1); i++)
        {
            int v = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > v)
            {
                arr[j + 1] = arr[j];
                j = j - 1;
                count = count + 1;
            }
            arr[j + 1] = v;
        }
        return count;
    }

    // Function to generate random int
	public static int RandomInt(int num)
	{	
        int a = (int)Math.round(Math.random() * num);

		return a;
	}
}