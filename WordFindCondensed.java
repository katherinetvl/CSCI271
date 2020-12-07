/***********************************************
* Katherine Le
* lek4@winthrop.edu
* CSCI 271 PA2
* Description: Search for words in a char grid
************************************************/
import java.io.File; // For File use 
import java.io.IOException; // For File errors
import java.util.Scanner; // For Scanner use   
import java.util.ArrayList; // For ArrayList use 

public class WordFindCondensed
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

                boolean foundNE = false;
                boolean foundSW = false;
                boolean foundSE = false;
                boolean foundNW = false;

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

/***********************************************************************************
 * Obtain text horizontally, toward left 
 ***********************************************************************************/

/***********************************************************************************
 * Transpose 2D char array for access to column elements as rows   
 ***********************************************************************************/

/***********************************************************************************
 * Obtain vertical text horizontally, toward south  
 ***********************************************************************************/

/***********************************************************************************
 * Obtain vertical text horizontally, toward north 
 ***********************************************************************************/

/***********************************************************************************
 * Obtain text diagonally, toward northeast
 * Reverse to obtain text diagonally, toward southwest 
 ***********************************************************************************/
                        // isNE
                        int diagRS = wordGrid.length; 
                        int diagCS = wordGrid[0].length; 
                        
                        ArrayList<Character> intermediate = new ArrayList<Character>();

                        // first half
                        for (int r = 0; r < diagRS; r++) 
                        {
                            for (int row = r, col = 0; row >= 0 && col < diagCS; row--, col++) 
                            {
                                intermediate.add(wordGrid[row][col]);
                            }

                            String finalNE = GetString(intermediate);
                            String finalSW = ReverseString(finalNE);

                            // search for: word pattern 
                            // direction: NE text, upper half 
                            int[] resultNE = getIndexAndCount(finalNE, tempChange2);
                            if (resultNE[0] > 0)
                            {
                                resultNE[2] = resultNE[0];
                                resultNE[0] = (r - resultNE[0]) + 2;
                                System.out.println(strCurr2 + " was found starting at " + resultNE[0] + "," + resultNE[2] + " and oriented Northeast (" + resultNE[1] + ")");
                                foundNE = true; 
                                break;
                            }
                            else 
                            {
                                int sumOfComparisons = resultNE[1] * diagRS - 1;
                                comparisonSumString = UpdateComparisonSumString(comparisonSumString, sumOfComparisons); 
                            }

                            // search for: word pattern
                            // direction: SW text, upper half 
                            int[] resultSW = getIndexAndCount(finalSW, tempChange2);
                            if(resultSW[0] > 0)
                            {
                                resultSW[2] = 2 + (r - resultSW[0]);
                                System.out.println(strCurr2 + " was found starting at " + resultSW[0] + "," + resultSW[2] + " and oriented Southwest (" + resultSW[1] + ")");
                                foundSW = true;
                                break;
                            }
                            else
                            {
                                int sumOfComparisons = resultSW[1] * diagRS - 1;
                                comparisonSumString = UpdateComparisonSumString(comparisonSumString, sumOfComparisons); 
                            }

                            intermediate.clear();
                        }

                        intermediate.clear();
                        // clear arraylist for second half of matrix 

                        for (int c = 1; c < diagCS; c++) 
                        {
                            for (int row = diagRS-1, col = c; row >= 0 && col < diagCS; row--, col++) 
                            {
                                intermediate.add(wordGrid[row][col]);
                            }
                            
                            String finalNE2 = GetString(intermediate);
                            String finalSW2 = ReverseString(finalNE2);

                            // search for : word pattern 
                            // direction: NE text, lower half of matrix
                            int[] resultNE2 = getIndexAndCount(finalNE2, tempChange2);

                            if (resultNE2[0] > 0)
                            {
                                resultNE2[2] = c + resultNE2[0];
                                resultNE2[0] = (diagCS - resultNE2[0]) + 1;
                                System.out.println(strCurr2 + " was found starting at " + resultNE2[0] + "," + resultNE2[2] + " and oriented Northeast (" + resultNE2[1] + ")");
                                foundNE = true; 
                                break;
                            }
                            else 
                            {
                                int sumOfComparisons = resultNE2[1] * diagRS - 1;
                                comparisonSumString = UpdateComparisonSumString(comparisonSumString, sumOfComparisons); 
                            }

                            // search for : word pattern 
                            // direction: SW text, lower half of matrix
                            int[] resultSW2 = getIndexAndCount(finalSW2, tempChange2);
                            if(resultSW2[0] > 0)
                            {
                                resultSW2[2] = (diagCS + 1) - resultSW2[0]; // keep 
                                resultSW2[0] = diagCS - (diagCS - (resultSW2[0] + c));
                                System.out.println(strCurr2 + " was found starting at " + resultSW2[0] + "," + resultSW2[2] + " and oriented Southwest (" + resultSW2[1] + ")");
                                foundSW = true;
                                break;
                            }
                            else
                            {
                                int sumOfComparisons = resultSW2[1] * diagRS - 1;
                                comparisonSumString = UpdateComparisonSumString(comparisonSumString, sumOfComparisons); 
                            }

                            intermediate.clear();
                        }
/***********************************************************************************
 * Obtain text diagonally, toward southeast
 * Reverse to obtain text diagonally, toward northwest 
 ***********************************************************************************/
                        // is SE
                        int xDiagRS = rowSize;
                        int xDiagCS = colSize;

                        ArrayList<Character> intermediate2 = new ArrayList<Character>();

                        // first half of matrix 
                        for(int j = xDiagCS-1; j >= 0; j--)
                        {
                            for(int k = 0; k < xDiagRS; k++)
                            {
                                if((j + k) < xDiagCS)
                                {
                                    intermediate2.add(wordGrid[k][j + k]);
                                } 
                                else 
                                {
                                    break;
                                }
                            }

                            String finalSE = GetString(intermediate2);
                            String finalNW = ReverseString(finalSE);

                            // search for: word pattern 
                            // direction: SE text, upper half 
                            int[] resultSE = getIndexAndCount(finalSE, tempChange2);
                            if (resultSE[0] > 0)
                            {
                                resultSE[2] = (j + resultSE[0]);
                                System.out.println(strCurr2 + " was found starting at " + resultSE[0] + "," + resultSE[2] + " and oriented Sortheast (" + resultSE[1] + ")");
                                foundNE = true; 
                                break;
                            }
                            else 
                            {
                                int sumOfComparisons = resultSE[1] * xDiagRS - 1;
                                comparisonSumString = UpdateComparisonSumString(comparisonSumString, sumOfComparisons); 
                            }

                            // search for: word pattern
                            // direction: NW text, upper half 
                            int[] resultNW = getIndexAndCount(finalNW, tempChange2);
                            if(resultNW[0] > 0)
                            {
                                resultNW[2] = (xDiagRS + 1) - resultNW[0];
                                resultNW[0] = resultNW[2] - j;
                                System.out.println(strCurr2 + " was found starting at " + resultNW[0] + "," + resultNW[2] + " and oriented Northwest (" + resultNW[1] + ")");
                                foundSW = true;
                                break;
                            }
                            else
                            {
                                int sumOfComparisons = resultNW[1] * xDiagRS - 1;
                                comparisonSumString = UpdateComparisonSumString(comparisonSumString, sumOfComparisons); 
                            }                            

                            intermediate2.clear();
                        }
                
                        intermediate2.clear();
                        // second half of matrix

                        for(int i = 1; i < xDiagRS; i++)
                        {
                            for(int j = i, k = 0; j< xDiagRS && k < xDiagCS; j++, k++)
                            {
                                intermediate2.add(wordGrid[j][k]);
                            }
                            
                            String finalSE2 = GetString(intermediate2);
                            String finalNW2 = ReverseString(finalSE2);

                            // search for: word pattern 
                            // direction: SE text, lower half 
                            int[] resultSE2 = getIndexAndCount(finalSE2, tempChange2);
                            if (resultSE2[0] > 0)
                            {
                                resultSE2[2] = resultSE2[0];
                                resultSE2[0] = i + resultSE2[0];
                                System.out.println(strCurr2 + " was found starting at " + resultSE2[0] + "," + resultSE2[2] + " and oriented Sortheast (" + resultSE2[1] + ")");
                                foundNE = true; 
                                break;
                            }
                            else 
                            {
                                int sumOfComparisons = resultSE2[1] * xDiagRS - 1;
                                comparisonSumString = UpdateComparisonSumString(comparisonSumString, sumOfComparisons); 
                            }

                            // search for: word pattern
                            // direction: NW text, lower half 
                            int[] resultNW2 = getIndexAndCount(finalNW2, tempChange2);
                            if(resultNW2[0] > 0)
                            {
                                resultNW2[0] = (xDiagRS + 1) - resultNW2[0];
                                resultNW2[2] = resultNW2[0] - i;
                                System.out.println(strCurr2 + " was found starting at " + resultNW2[0] + "," + resultNW2[2] + " and oriented Northwest (" + resultNW2[1] + ")");
                                foundNW = true;
                                break;
                            }
                            else
                            {
                                int sumOfComparisons = resultNW2[1] * xDiagRS - 1;
                                comparisonSumString = UpdateComparisonSumString(comparisonSumString, sumOfComparisons); 
                            } 

                            intermediate2.clear();
                        }
/***********************************************************************************
 * Final determinants
 ***********************************************************************************/
                        //End of searches. Unfound.
                        if(!foundSW && !foundNE && !foundNW && !foundSE)
                        {
                            System.out.println(strCurr2 + " was not found.");
                        }
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
                boolean isEast = false;
                boolean isWest = false;
                boolean isSouth = false;
                boolean isNorth = false; 
                try 
                {
                    Scanner input = new Scanner(System.in);
                        String wordIn = input.next();
                        String wordCaps = wordIn.toUpperCase(); 
                    input.close();

/***********************************************************************************
 * Obtain text horizontally, toward right 
 ***********************************************************************************/
                        // isEast
                        for (int right = 0; right < rowSize; right++)
                        {
                            char[] eastChar= wordGrid[right];
                            String eastText = null;
                            eastText = (String.valueOf(eastChar));

                            // Search for pattern
                            int[] resultEast = getIndexAndCount(eastText, wordCaps);
                            if (resultEast[0] > 0)
                            {
                                resultEast[2] = right + 1;
                                System.out.println(wordIn + " was found starting at " + resultEast[2] + "," + resultEast[0] + " and oriented East (" + resultEast[1] + ")");
                                isEast = true; 
                                break;
                            }
                            else 
                            {
                                int sumOfComparisons = resultEast[1] * right-1; 
                                comparisonSumString = UpdateComparisonSumString(comparisonSumString, sumOfComparisons);
                            }
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
                            String westText = ReverseString(eastText);

                            // System.out.print("West text: " + westText + "\n");

                            // Search for pattern 
                            int[] resultWest = getIndexAndCount(westText, wordCaps);
                            if(resultWest[0] > 0)
                            { 
                                resultWest[0] = (colSize - resultWest[0]) + 1;
                                resultWest[2] = left + 1;
                                System.out.println(wordIn + " was found starting at " + resultWest[2] + "," + resultWest[0] + " and oriented West (" + resultWest[1] + ")");
                                isWest = true; 
                                break;
                            }
                            else 
                            {
                                int sumOfComparisons = resultWest[1] * left-1; 
                                comparisonSumString = UpdateComparisonSumString(comparisonSumString, sumOfComparisons);
                            }
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

                            // Search for pattern 
                            int[] resultSouth = getIndexAndCount(southText, wordCaps);
                            if (resultSouth[0] > 0)
                            {
                                resultSouth[2] = down + 1;
                                System.out.println(wordIn + " was found starting at " + resultSouth[0] + "," + resultSouth[2] + " and oriented South (" + resultSouth[1] + ")");
                                isSouth = true; 
                                break;
                            }
                            else 
                            {
                                int sumOfComparisons = resultSouth[1] * newRS -1; 
                                comparisonSumString = UpdateComparisonSumString(comparisonSumString, sumOfComparisons);
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
                            String northText = ReverseString(southText);

                            // Search for pattern 
                            int[] resultNorth = getIndexAndCount(northText, wordCaps);
                            if (resultNorth[0] > 0)
                            {
                                resultNorth[0] = (newRS - resultNorth[0]) + 1;
                                resultNorth[2] = up + 1;
                                System.out.println(wordIn + " was found starting at " + resultNorth[0] + "," + resultNorth[2] + " and oriented North (" + resultNorth[1] + ")");
                                isNorth = true; 
                                break;
                            }
                            else 
                            {
                                int sumOfComparisons = resultNorth[1] * newRS -1;
                                comparisonSumString = UpdateComparisonSumString(comparisonSumString, sumOfComparisons); 
                            }
                        }
                        if(!isEast && !isWest &&! isSouth &&! isNorth)
                        {
                            System.out.println(wordIn + " was not found.");
                        }

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
    * Function Name: ReverseString
    * Description: A recursive function that reverses characters in string
    * Input: String
    * Output: String with reversed characters 
    ***********************************************************/
    public static String ReverseString(String x)
    {
        if (x.isEmpty())
        {
            return x;
        }
        return ReverseString(x.substring(1)) + x.charAt(0);
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
	    for (int i = 0; i <= k; i++)
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
    * Function Name: GetString 
    * Description: Iterates over a character Arraylist and builds string
    * Input: Character Arraylist
    * Output: String
    ***********************************************************/
    public static String GetString(ArrayList<Character> arrayList)
    {    
        StringBuilder builder = new StringBuilder(arrayList.size());
        
        for(Character ch: arrayList)
        {
            builder.append(ch);
        }
        return builder.toString();
    }

    /***********************************************************
    * Function Name: UpdateComparisonSumString
    * Description: Parses string to int, adds an int, and converts back to string
    * Input: String, Int
    * Output: String with new int sum stored 
    ***********************************************************/
    public static String UpdateComparisonSumString(String s, int y)
    {
        // parse String to int
        int i = Integer.parseInt(s);
        // add y 
        i = i + y; 
        // convert back to String
        s = Integer.toString(i); 
        return s;
    }
}