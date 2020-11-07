import java.io.File; // For File use 
import java.io.IOException; // For File errors
import java.util.Scanner; // For Scanner use   
import java.util.ArrayList; // For ArrayList use 

public class WordFind
{
    public static void main(String[] args) 
    {
        int rowSize = 0;
        int colSize = 0; 
    
/***********************************************************
 * First argument 
 * Create input stream from text file
 * Determine row and column size 
 ***********************************************************/
        if (args.length > 0)
        {
            File gridFile = new File(args[0]);
            String strCurr = null; 
            String tempChange = null; 
            String strAlpha = "";
            String comparisonSumString = "0";
            // String strRev = null;

            try 
            {
                Scanner fin = new Scanner(gridFile);
                while (fin.hasNextLine())
                {
                    strCurr = fin.nextLine();
                    tempChange = strCurr.replaceAll("[^A-Za-z]+", "");
                    
                    // Increment row size if line has alphabet chars 
                    if ((tempChange.matches("[A-Za-z]+")))
                    {
                        rowSize++;
                    }
                    strAlpha = AlphaOnlyString(strAlpha, strCurr);
                    // System.out.println(strCurr);
                    // System.out.println(strAlpha);
                }

            // Close file
            fin.close();
            } catch (IOException e) 
            {
                e.printStackTrace();
                System.out.println("An error occurred with opening grid file.");
            }

            // Determine column size
            int totalElements = strAlpha.length(); 
            colSize = totalElements/rowSize; 
            // System.out.println("Row size: " + rowSize + " Col size: " + colSize + "\n");
            
/***********************************************************
 * Build 2D char array from all characters from first file
 ***********************************************************/
            strAlpha = strAlpha.toUpperCase();
            char[] charArr1D = strAlpha.toCharArray();

            char[][] wordGrid;
            wordGrid = new char[rowSize][colSize];

            int increment = 0;
            for(int i = 0; i < rowSize; i++)
            {
                for(int j = 0; j < colSize; j++)
                {
                    if(increment == charArr1D.length)
                    {
                        break;
                    } 

                    wordGrid[i][j] = charArr1D[increment];
                    // System.out.print(wordGrid[i][j] + " ");

                    increment++;
                }
            }

/***********************************************************
 * Second argument 
 * Read in the words to search from second file 
 ***********************************************************/
            if (args.length > 1)
            {
                File searchForFile = new File(args[1]);
                String strCurr2 = null;
                String tempChange2 = null; 

                try 
                {
                    Scanner fin2 = new Scanner(searchForFile);
                    while (fin2.hasNextLine())
                    {
                        strCurr2 = fin2.nextLine();
                        // System.out.println(strCurr2);
                        tempChange2 = strCurr2.replaceAll("[^A-Za-z]+", "");

                        System.out.println("Word to search: " + tempChange2);
/***********************************************************************************
 * Obtain text horizontally, toward right 
 ***********************************************************************************/
                        // isEast
                        for (int r = 0; r < rowSize; r++)
                        {
                            char[] eastChar= wordGrid[r];
                            String eastText = null;
                            eastText = (String.valueOf(eastChar));

                            // System.out.print("East text: " + eastText + "\n");
                            // Search for pattern
                            do {
                                int[] resultEast = getIndexAndCount(eastText, tempChange2);
                                if(resultEast[0] > 0)
                                { 
                                    resultEast[2] = r; 
                                    System.out.println(strCurr2 + " was found starting at " + resultEast[2] + "," + resultEast[0] + " and oriented East (" + resultEast[1] + ")");
                                    break;
                                }
                                r++;
                            } while (r < rowSize);
                        }
/***********************************************************************************
 * Obtain text horizontally, toward left 
 ***********************************************************************************/
                        // isWest
                        for (int left = 0; left < rowSize; left++)
                        {
                            char[] eastChar = wordGrid[left]; 
                            String eastText = null;
                            eastText = (String.valueOf(eastChar));
                            String westText = reverseString(eastText);

                            // System.out.print("West text: " + westText + "\n");

                            // Search for pattern 
                            do {
                                int[] resultWest = getIndexAndCount(westText, tempChange2);
                                if(resultWest[0] > 0)
                                { 
                                    resultWest[2] = left - resultWest[0]; 
                                    System.out.println(strCurr2 + " was found starting at " + resultWest[2] + "," + resultWest[0] + " and oriented West (" + resultWest[1] + ")");
                                    break;
                                }
                                left++;
                            } while (left < rowSize);

                        }
/***********************************************************************************
 * Transpose 2D char array for access to column elements as rows   
 ***********************************************************************************/
                        int newRS = colSize;
                        int newCS = rowSize;
                        char[][] transposedWG = new char[newRS][newCS];

                        for(int i= 0; i < colSize; i++)
                        {
                            for(int j = 0; j < rowSize; j++)
                            {
                                transposedWG[i][j] = wordGrid[j][i];
                            }
                        }
/***********************************************************************************
 * Obtain vertical text horizontally, toward south  
 ***********************************************************************************/
                        // isSouth
                        for (int down = 0; down < newRS; down++)
                        {
                            char[] southChar = transposedWG[down]; 
                            String southText = null;
                            southText = (String.valueOf(southChar));

                            // System.out.print("South text " + southText + "\n");

                            // Search for pattern 
                            int[] resultSouth = getIndexAndCount(southText, tempChange2);
                            if (resultSouth[0] > 0)
                            {
                                resultSouth[2] = down + 1;
                                System.out.println(strCurr2 + " was found starting at " + resultSouth[0] + "," + resultSouth[2] + " and oriented South (" + resultSouth[1] + ")");
                                break;
                            }
                            else 
                            {
                                int sumOfComparisons = resultSouth[1] * newRS -1; 
                                updateComparisonSumString(comparisonSumString, sumOfComparisons);
                            }
                        }
/***********************************************************************************
 * Obtain vertical text horizontally, toward north 
 ***********************************************************************************/
                        // isNorth
                        for (int up = 0; up < newRS; up++)
                        {
                            char[] southChar = transposedWG[up]; 
                            String southText = null;
                            southText = (String.valueOf(southChar));
                            String northText = reverseString(southText);

                            System.out.println("North text: " + northText); 

                            // Search for pattern 
                            int[] resultNorth = getIndexAndCount(northText, tempChange2);
                            if (resultNorth[0] > 0)
                            {
                                resultNorth[2] = up + resultNorth[0];
                                System.out.println(strCurr2 + " was found starting at " + up + "," + resultNorth[2] + " and oriented North (" + resultNorth[1] + ")");
                                break;
                            }
                            else 
                            {
                                int sumOfComparisons = resultNorth[1] * newRS -1;
                                updateComparisonSumString(comparisonSumString, sumOfComparisons); 
                            }
                        }
/***********************************************************************************
 * Obtain text diagonally, toward southwest
 ***********************************************************************************/
                        // isSW

/***********************************************************************************
 * Obtain text diagonally, toward northeast
 ***********************************************************************************/
                        // isNE

/***********************************************************************************
 * Obtain text diagonally, toward northwest
 ***********************************************************************************/
                        // is NW
/***********************************************************************************
 * Obtain text diagonally, toward southeast
 ***********************************************************************************/
                        // is SE
                            
/***********************************************************************************
 * Final determinants
 ***********************************************************************************/
                        //End of searches. Unfound.

                    } // This ends [while (fin2.hasNextLine())]

                    fin2.close();
                } catch (IOException e) 
                {
                    e.printStackTrace();
                    System.out.println("An error occurred with opening word(s) to search file.");
                }
            }
/***********************************************************
 * Prompt user for word to search if no second file given 
 ***********************************************************/
            else 
            {
                System.out.println("Enter word to search: ");
                System.out.println("Enter 'quit' to exit program.");
                try 
                {
                    Scanner input = new Scanner(System.in);
                    while (input.hasNextLine())
                    {
                        String wordIn = input.next();
                        String wordCaps = wordIn.toUpperCase(); 
                        if(!(wordCaps.equals("QUIT")))
                        {
                            System.out.println("Word To Search is: " + wordCaps + "\n");
                            // String tempChange3 = wordCaps.replaceAll("[^A-Za-z]+", "");

                            // isEast

                        } // this ends if statement.
                        else
                        {
                            System.exit(0);
                        }
                    
                    } // This ends [while (fin2.hasNextLine())]
                input.close();
                } catch(Exception e)
                    {
                        e.printStackTrace();
                    }
            } // ends overall else 
        }

/***********************************************************
 * Prompt user to enter at least one file into command line
 ***********************************************************/
        else
        {
            System.out.println("Please run program with at least one file - the grid.");
        }

    }// ends main

    // Functions 
    /***********************************************************
    * Function Name: AlphaOnlyString
    * Description: Building a string of just alpha characters 
    * Input: String1, string2
    * Output: String1 with alpha chars from string2 appended
    ************************************************************/
    public static String AlphaOnlyString(String finalString, String fileString) 
    {
        fileString = fileString.replaceAll("[^A-Za-z]+", "");
        finalString = finalString.concat(fileString);
        return finalString;
    }

    /***********************************************************
    * Function Name: reverseString
    * Description: A recursive function that reverses characters in string
    * Input: String
    * Output: String with reversed characters 
    ***********************************************************/
    public static String reverseString(String x)
    {
        if (x.isEmpty())
        {
            return x;
        }
        return reverseString(x.substring(1)) + x.charAt(0);
    }

    /***********************************************************
    * Function Name: getIndexAndCount 
    * Description: Returns index from Brute Force String Matching Algorithm,
    * and the count of comparisons made
    * Input: String containing text, String of pattern to search for 
    * Output: Index of pattern found, count, int holder for row calculation
    ***********************************************************/
    public static int[] getIndexAndCount(String T, String P)
	{
	    int[] result = new int[3];
	    int n = T.length();
	    int m = P.length();
	    int k = n - m; 
	    int count = 0; 
	    
	    result[0] = -1; 
	    for (int i = 0; i < k; i++)
	    {
	        int j = 0;
	        while((j < m && (T.charAt(i+j) == P.charAt(j))))
	        {
	            j++;
	            count++; 
	        }
	        if(j == m)
	        {
	            result[0] = i;
	            break;
	        }
	        count++; 
	    }
        
        result[0] = result[0] + 1; 
	    result[1] = count;
        result[2] = 0; 
        
	    return result;
    }

    /***********************************************************
    * Function Name: reverseArrayList 
    * Description: Reverse elements in an Arraylist 
    * Input: Character Arraylist
    * Output: Character Arraylist
    ***********************************************************/
    public static ArrayList<Character> reverseArrayList(ArrayList<Character> origList) 
    { 
        //Create arraylist to hold reversed arraylist
        ArrayList<Character> revArrayList = new ArrayList<Character>(); 
        for (int i = origList.size() - 1; i >= 0; i--) 
        { 
            // Append elements in reverse order 
            revArrayList.add(origList.get(i)); 
        } 
        return revArrayList; 
    } 

    /***********************************************************
    * Function Name: getString 
    * Description: Iterates over a character Arraylist and builds string
    * Input: Character Arraylist
    * Output: String
    ***********************************************************/
    public static String getString(ArrayList<Character> list)
    {    
        StringBuilder builder = new StringBuilder(list.size());
        
        for(Character ch: list)
        {
            builder.append(ch);
        }
        return builder.toString();
    }

    /***********************************************************
    * Function Name: updateComparisonSumString
    * Description: Adds an integer into a string for storing
    * Input: Int
    * Output: String
    ***********************************************************/
    public static String updateComparisonSumString(String s, int y)
    {
        // change String to int
        int i = Integer.parseInt(s);
        // add y 
        i = i + y; 
        // convert back to String
        s = Integer.toString(i); 
        return s;
    }
}