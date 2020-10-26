import java.io.File; // For File use 
import java.io.IOException; // For File errors
import java.util.Scanner; // For Scanner use   
import java.util.ArrayList; // For ArrayList use 
import java.util.Arrays; 

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
                // System.out.print("\n");
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
                        tempChange2 = strCurr.replaceAll("[^A-Za-z]+", "");

                        /* The 8 directions 
                        boolean isEast = false; 
                        boolean isWest = false;
                        boolean isSouth = false;
                        boolean isNorth = false;
                        boolean isSW = false;
                        boolean isNE = false;
                        boolean isSE = false;
                        boolean isNW = false;
                        */

/***********************************************************************************
 * Obtain text horizontally, toward right 
 ***********************************************************************************/
                        // isEast
                        for (int r = 0; r < rowSize; r++)
                        {
                            char[] eastChar= wordGrid[r];
                            // String eastT = new String();
                            String eastText = null;
                            eastText = (String.valueOf(eastChar));
                            // Search for pattern 
                            int[] resultEast = getIndexAndCount(eastText, tempChange2);
                            if (resultEast[0] > -1)
                            {
                                // isEast = true;
                                resultEast[2] = r;
                                System.out.println(strCurr2 + " was found starting at " + resultEast[2] + "," + resultEast[0] + " and oriented East (" + resultEast[1] + ")");
                                break;
                            }
                            else 
                            {
                                int sumOfComparisons = resultEast[1] * rowSize -1;
                                updateComparisonSumString(comparisonSumString, sumOfComparisons);
                            }
                        }
/***********************************************************************************
 * Obtain text horizontally, toward left 
 ***********************************************************************************/
                        // isWest
                        for (int r = 0; r < rowSize; r++)
                        {
                            char[] eastChar = wordGrid[r]; 
                            //String eastT = new String();
                            String eastText = null;
                            eastText = (String.valueOf(eastChar));
                            String westText = reverseString(eastText);

                            // Search for pattern 
                            int[] resultWest = getIndexAndCount(westText, tempChange2);
                            if (resultWest[0] > -1)
                            {
                                //isWest = true;
                                resultWest[2] = r;
                                System.out.println(strCurr2 + " was found starting at " + resultWest[2] + "," + resultWest[0] + " and oriented West (" + resultWest[1] + ")");
                                break;
                            }
                            else 
                            {
                                int sumOfComparisons = resultWest[1] * rowSize -1; 
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
                        for (int r = 0; r < newRS; r++)
                        {
                            char[] southChar = transposedWG[r]; 
                            //String southT = new String();
                            String southText = null;
                            southText = (String.valueOf(southChar));

                            // Search for pattern 
                            int[] resultSouth = getIndexAndCount(southText, tempChange2);
                            if (resultSouth[0] > -1)
                            {
                                isSouth = true;
                                resultSouth[2] = r;
                                System.out.println(strCurr2 + " was found starting at " + resultSouth[2] + "," + resultSouth[0] + " and oriented South (" + resultSouth[1] + ")");
                                break;
                            }
                            else 
                            {
                                int sumOfComparisons = resultSouth[1] * newRS -1; 
                            }
                        }
/***********************************************************************************
 * Obtain vertical text horizontally, toward north 
 ***********************************************************************************/
                        // isNorth
                        for (int r = 0; r < newRS; r++)
                        {
                            char[] southChar = transposedWG[r]; 
                            String southT = new String();
                            String southText = null;
                            southText = (String.valueOf(southT));
                            String northText = reverseString(southText);

                            // Search for pattern 
                            int[] resultNorth = getIndexAndCount(northText, tempChange2);
                            if (resultNorth[0] > -1)
                            {
                                isNorth = true;
                                resultNorth[2] = r;
                                System.out.println(strCurr2 + " was found starting at " + resultNorth[2] + "," + resultNorth[0] + " and oriented North (" + resultNorth[1] + ")");
                                break;
                            }
                            else 
                            {
                                int sumOfComparisons = resultNorth[1] * newRS -1; 
                            }
                        }
/***********************************************************************************
 * Obtain text diagonally, toward southwest
 ***********************************************************************************/
                        // isSW
                        // Track 2D char grid size 
                        int N = wordGrid.length;
                        int M = wordGrid[0].length;

                        // Create an arraylist to hold anti diagonals 
                        ArrayList<Character> intermediate = new ArrayList<Character>();

                        for (int d = 0; d < N + M - 1; d++) 
                        {
                            intermediate.clear();
                            
                            int r = d < M ? 0 : d - M + 1;
                            int c = d < M ? d : M - 1;
                            
                            while (r < N && c > -1) 
                            {
                                intermediate.add(wordGrid[r][c]);
                                r++;
                                c--;
                            }
                            // Intermediate is an arraylist object 
                            // System.out.println("Intermediate" + intermediate);
                            // System.out.println(reverseArrayList(intermediate));
                            
                            // Convert arraylist into string
                            String finalString = getString(intermediate);

                            // Search for pattern 
                            int[] resultSW = getIndexAndCount(finalString, tempChange2);
                            if (resultSW[0] > -1)
                            {
                                isSW = true;
                                resultSW[2] = r;
                                System.out.println(strCurr2 + " was found starting at " + resultSW[2] + "," + resultSW[0] + " and oriented Southwest (" + resultSW[1] + ")");
                                break;
                            }
                            else 
                            {
                                int sumOfComparisons = resultSW[1] * finalString.length(); 
                            }
                        }
/***********************************************************************************
 * Obtain text diagonally, toward northeast
 ***********************************************************************************/
                        // isNE
                        // Create an arraylist to hold anti diagonals 
                        ArrayList<Character> intermediate2 = new ArrayList<Character>();

                        for (int d = 0; d < N + M - 1; d++) 
                        {
                            intermediate.clear();
                            
                            int r = d < M ? 0 : d - M + 1;
                            int c = d < M ? d : M - 1;
                            
                            while (r < N && c > -1) 
                            {
                                intermediate.add(wordGrid[r][c]);
                                r++;
                                c--;
                            }
                            intermediate2 = reverseArrayList(intermediate);
                            String finalString2 = getString(intermediate2);

                            // Search for pattern 
                            int[] resultNE = getIndexAndCount(finalString2, tempChange2);
                            if (resultNE[0] > -1)
                            {
                                isNE = true;
                                resultNE[2] = r;
                                System.out.println(strCurr2 + " was found starting at " + resultNE[2] + "," + resultNE[0] + " and oriented Northeast (" + resultNE[1] + ")");
                                break;
                            }
                            else 
                            {
                                int stringLength = finalString2.length();
                                int sumOfComparisons = resultNE[1] * stringLength; 
                            }
                        }
/***********************************************************************************
 * Obtain text diagonally, toward northwest
 ***********************************************************************************/
                        String majorDiagonal = "";
                        String minorDiagonal = "";
                        int m = rowSize;
                        int n = colSize; 
                        char currChar = 'x';
                        String charStr = "";

                        for(int k = 0; k < m; k++)
                        {
                            int i = k; 
                            int j = 0;
                            
                            while (i > 0 && j <= n - 1)
                            {
                                currChar = wordGrid[i][j];
                                charStr = Character.toString(currChar);
                                majorDiagonal = majorDiagonal.concat(charStr);
                                i--;
                                j++;
                            }

                            // Search for pattern 
                            int[] resultNW = getIndexAndCount(majorDiagonal, tempChange2);
                            if (resultNW[0] > -1)
                            {
                                isNW = true;
                                resultNW[2] = m;
                                System.out.println(strCurr2 + " was found starting at " + resultNW[2] + "," + resultNW[0] + " and oriented Northwest (" + resultNW[1] + ")");
                                break;
                            }
                            else 
                            {
                                int stringLength = majorDiagonal.length();
                                int sumOfComparisons = resultNW[1] * stringLength; 
                            }
                        }

                        for (int k = 0; k < n -1; k++)
                        {
                            int i = m - 1;
                            int j = k; 
                            
                            while(j <= n - 1)
                            {
                                currChar = wordGrid[i][j];
                                charStr = Character.toString(currChar);
                                minorDiagonal = minorDiagonal.concat(charStr);
                                i--;
                                j++;
                            }
                            // Search for pattern 
                            int[] resultNW = getIndexAndCount(minorDiagonal, tempChange2);
                            if (resultNW[0] > -1)
                            {
                                isNW = true;
                                resultNW[2] = m;
                                System.out.println(strCurr2 + " was found starting at " + resultNW[2] + "," + resultNW[0] + " and oriented Northwest (" + resultNW[1] + ")");
                                break;
                            }
                            else 
                            {
                                int stringLength = minorDiagonal.length();
                                int sumOfComparisons = resultNW[1] * stringLength; 
                            }
                        }
/***********************************************************************************
 * Obtain text diagonally, toward southeast
 ***********************************************************************************/
                        // is SE
                            for(int k = 0; k < m; k++)
                            {
                                int i = k; 
                                int j = 0;
                                
                                while (i > 0 && j <= n - 1)
                                {
                                    currChar = wordGrid[i][j];
                                    charStr = Character.toString(currChar);
                                    majorDiagonal = majorDiagonal.concat(charStr);
                                    i--;
                                    j++;
                                }

                                // Search for pattern 
                                String revMajorDiagonal = reverseString(majorDiagonal);
                                int[] resultSE = getIndexAndCount(revMajorDiagonal, tempChange2);
                                if (resultSE[0] > -1)
                                {
                                    isSE = true;
                                    resultSE[2] = m;
                                    System.out.println(strCurr2 + " was found starting at " + resultSE[2] + "," + resultSE[0] + " and oriented Southeast (" + resultSE[1] + ")");
                                    break;
                                }
                                else 
                                {
                                    int stringLength = majorDiagonal.length();
                                    int sumOfComparisons = resultSE[1] * stringLength; 
                                }
                            }
                            for (int k = 0; k < n -1; k++)
                            {
                                int i = m - 1;
                                int j = k; 
                                
                                while(j <= n - 1)
                                {
                                    currChar = wordGrid[i][j];
                                    charStr = Character.toString(currChar);
                                    minorDiagonal = minorDiagonal.concat(charStr);
                                    i--;
                                    j++;
                                }
                                // Search for pattern 
                                String revMinorDiagonal = reverseString(minorDiagonal);
                                int[] resultSE = getIndexAndCount(minorDiagonal, tempChange2);
                                if (resultSE[0] > -1)
                                {
                                    isSE = true;
                                    resultSE[2] = m;
                                    System.out.println(strCurr2 + " was found starting at " + resultSE[2] + "," + resultSE[0] + " and oriented Southeast (" + resultSE[1] + ")");
                                    break;
                                }
                                else 
                                {
                                    int stringLength = revMinorDiagonal.length();
                                    int sumOfComparisons = resultSE[1] * stringLength; 
                                }
                            }


/***********************************************************************************
 * Final determinants
 ***********************************************************************************/
                        //End of searches. Unfound.
                        String justZero = "0";
                        if(!comparisonSumString.equals(justZero))
                        {
                            int i = Integer.parseInt(comparisonSumString);
                            System.out.println(strCurr2 + " was not found. (" + i + ")");
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
                            String tempChange3 = wordCaps.replaceAll("[^A-Za-z]+", "");

                            // The 8 directions 
                            boolean isEast = false; 
                            boolean isWest = false;
                            boolean isSouth = false;
                            boolean isNorth = false;
                            boolean isSW = false;
                            boolean isNE = false;
                            boolean isSE = false;
                            boolean isNW = false;
    
                            // isEast
                            for (int r = 0; r < rowSize; r++)
                            {
                                char[] eastChar= wordGrid[r];
                                String eastT = new String();
                                String eastText = null;
                                eastText = (String.valueOf(eastT));
                                // Search for pattern 
                                int[] resultEast = getIndexAndCount(eastText, tempChange3);
                                if (resultEast[0] > -1)
                                {
                                    isEast = true;
                                    resultEast[2] = r;
                                    System.out.println(wordIn + " was found starting at " + resultEast[2] + "," + resultEast[0] + " and oriented East (" + resultEast[1] + ")");
                                    break;
                                }
                                else 
                                {
                                    int sumOfComparisons = resultEast[1] * rowSize -1;
                                    updateComparisonSumString(comparisonSumString, sumOfComparisons);
                                }
                            }

                            // isWest
                            for (int r = 0; r < rowSize; r++)
                            {
                                char[] eastChar = wordGrid[r]; 
                                String eastT = new String();
                                String eastText = null;
                                eastText = (String.valueOf(eastT));
                                String westText = reverseString(eastText);
    
                                // Search for pattern 
                                int[] resultWest = getIndexAndCount(westText, tempChange3);
                                if (resultWest[0] > -1)
                                {
                                    isWest = true;
                                    resultWest[2] = r;
                                    System.out.println(wordIn + " was found starting at " + resultWest[2] + "," + resultWest[0] + " and oriented West (" + resultWest[1] + ")");
                                    break;
                                }
                                else 
                                {
                                    int sumOfComparisons = resultWest[1] * rowSize -1; 
                                }
                            }
                        
                            // transpose 
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

                            // isSouth
                            for (int r = 0; r < newRS; r++)
                            {
                                char[] southChar = transposedWG[r]; 
                                String southT = new String();
                                String southText = null;
                                southText = (String.valueOf(southT));
    
                                // Search for pattern 
                                int[] resultSouth = getIndexAndCount(southText, tempChange3);
                                if (resultSouth[0] > -1)
                                {
                                    isSouth = true;
                                    resultSouth[2] = r;
                                    System.out.println(wordIn + " was found starting at " + resultSouth[2] + "," + resultSouth[0] + " and oriented South (" + resultSouth[1] + ")");
                                    break;
                                }
                                else 
                                {
                                    int sumOfComparisons = resultSouth[1] * newRS -1; 
                                }
                            }

                            // isNorth
                            for (int r = 0; r < newRS; r++)
                            {
                                char[] southChar = transposedWG[r]; 
                                String southT = new String();
                                String southText = null;
                                southText = (String.valueOf(southT));
                                String northText = reverseString(southText);
    
                                // Search for pattern 
                                int[] resultNorth = getIndexAndCount(northText, tempChange3);
                                if (resultNorth[0] > -1)
                                {
                                    isNorth = true;
                                    resultNorth[2] = r;
                                    System.out.println(wordIn + " was found starting at " + resultNorth[2] + "," + resultNorth[0] + " and oriented North (" + resultNorth[1] + ")");
                                    break;
                                }
                                else 
                                {
                                    int sumOfComparisons = resultNorth[1] * newRS -1; 
                                }
                            }

                            // isSW
                            // Track 2D char grid size 
                            int N = wordGrid.length;
                            int M = wordGrid[0].length;
    
                            // Create an arraylist to hold anti diagonals 
                            ArrayList<Character> intermediate = new ArrayList<Character>();
    
                            for (int d = 0; d < N + M - 1; d++) 
                            {
                                intermediate.clear();
                                
                                int r = d < M ? 0 : d - M + 1;
                                int c = d < M ? d : M - 1;
                                
                                while (r < N && c > -1) 
                                {
                                    intermediate.add(wordGrid[r][c]);
                                    r++;
                                    c--;
                                }
                                // Intermediate is an arraylist object 
                                // System.out.println("Intermediate" + intermediate);
                                // System.out.println(reverseArrayList(intermediate));
                                
                                // Convert arraylist into string
                                String finalString = getString(intermediate);
    
                                // Search for pattern 
                                int[] resultSW = getIndexAndCount(finalString, tempChange3);
                                if (resultSW[0] > -1)
                                {
                                    isSW = true;
                                    resultSW[2] = r;
                                    System.out.println(wordIn + " was found starting at " + resultSW[2] + "," + resultSW[0] + " and oriented Southwest (" + resultSW[1] + ")");
                                    break;
                                }
                                else 
                                {
                                    int sumOfComparisons = resultSW[1] * finalString.length(); 
                                }
                            }

                            // isNE
                            // Create an arraylist to hold anti diagonals 
                            ArrayList<Character> intermediate2 = new ArrayList<Character>();
    
                            for (int d = 0; d < N + M - 1; d++) 
                            {
                                intermediate.clear();
                                
                                int r = d < M ? 0 : d - M + 1;
                                int c = d < M ? d : M - 1;
                                
                                while (r < N && c > -1) 
                                {
                                    intermediate.add(wordGrid[r][c]);
                                    r++;
                                    c--;
                                }
                                intermediate2 = reverseArrayList(intermediate);
                                String finalString2 = getString(intermediate2);
    
                                // Search for pattern 
                                int[] resultNE = getIndexAndCount(finalString2, tempChange3);
                                if (resultNE[0] > -1)
                                {
                                    isNE = true;
                                    resultNE[2] = r;
                                    System.out.println(wordIn + " was found starting at " + resultNE[2] + "," + resultNE[0] + " and oriented Northeast (" + resultNE[1] + ")");
                                    break;
                                }
                                else 
                                {
                                    int stringLength = finalString2.length();
                                    int sumOfComparisons = resultNE[1] * stringLength; 
                                }
                            }

                            String majorDiagonal = "";
                            String minorDiagonal = "";
                            int m = rowSize;
                            int n = colSize; 
                            char currChar = 'x';
                            String charStr = "";
    
                            for(int k = 0; k < m; k++)
                            {
                                int i = k; 
                                int j = 0;
                                
                                while (i > 0 && j <= n - 1)
                                {
                                    currChar = wordGrid[i][j];
                                    charStr = Character.toString(currChar);
                                    majorDiagonal = majorDiagonal.concat(charStr);
                                    i--;
                                    j++;
                                }
    
                                // Search for pattern 
                                int[] resultNW = getIndexAndCount(majorDiagonal, tempChange3);
                                if (resultNW[0] > -1)
                                {
                                    isNW = true;
                                    resultNW[2] = m;
                                    System.out.println(wordIn + " was found starting at " + resultNW[2] + "," + resultNW[0] + " and oriented Northwest (" + resultNW[1] + ")");
                                    break;
                                }
                                else 
                                {
                                    int stringLength = majorDiagonal.length();
                                    int sumOfComparisons = resultNW[1] * stringLength; 
                                }
                            }
    
                            for (int k = 0; k < n -1; k++)
                            {
                                int i = m - 1;
                                int j = k; 
                                
                                while(j <= n - 1)
                                {
                                    currChar = wordGrid[i][j];
                                    charStr = Character.toString(currChar);
                                    minorDiagonal = minorDiagonal.concat(charStr);
                                    i--;
                                    j++;
                                }
                                // Search for pattern 
                                int[] resultNW = getIndexAndCount(minorDiagonal, tempChange3);
                                if (resultNW[0] > -1)
                                {
                                    isNW = true;
                                    resultNW[2] = m;
                                    System.out.println(wordIn + " was found starting at " + resultNW[2] + "," + resultNW[0] + " and oriented Northwest (" + resultNW[1] + ")");
                                    break;
                                }
                                else 
                                {
                                    int stringLength = minorDiagonal.length();
                                    int sumOfComparisons = resultNW[1] * stringLength; 
                                }
                            }

                            // is SE
                                for(int k = 0; k < m; k++)
                                {
                                    int i = k; 
                                    int j = 0;
                                    
                                    while (i > 0 && j <= n - 1)
                                    {
                                        currChar = wordGrid[i][j];
                                        charStr = Character.toString(currChar);
                                        majorDiagonal = majorDiagonal.concat(charStr);
                                        i--;
                                        j++;
                                    }
    
                                    // Search for pattern 
                                    String revMajorDiagonal = reverseString(majorDiagonal);
                                    int[] resultSE = getIndexAndCount(revMajorDiagonal, tempChange3);
                                    if (resultSE[0] > -1)
                                    {
                                        isSE = true;
                                        resultSE[2] = m;
                                        System.out.println(wordIn + " was found starting at " + resultSE[2] + "," + resultSE[0] + " and oriented Southeast (" + resultSE[1] + ")");
                                        break;
                                    }
                                    else 
                                    {
                                        int stringLength = majorDiagonal.length();
                                        int sumOfComparisons = resultSE[1] * stringLength; 
                                    }
                                }
                                for (int k = 0; k < n -1; k++)
                                {
                                    int i = m - 1;
                                    int j = k; 
                                    
                                    while(j <= n - 1)
                                    {
                                        currChar = wordGrid[i][j];
                                        charStr = Character.toString(currChar);
                                        minorDiagonal = minorDiagonal.concat(charStr);
                                        i--;
                                        j++;
                                    }
                                    // Search for pattern 
                                    String revMinorDiagonal = reverseString(minorDiagonal);
                                    int[] resultSE = getIndexAndCount(minorDiagonal, tempChange3);
                                    if (resultSE[0] > -1)
                                    {
                                        isSE = true;
                                        resultSE[2] = m;
                                        System.out.println(wordIn + " was found starting at " + resultSE[2] + "," + resultSE[0] + " and oriented Southeast (" + resultSE[1] + ")");
                                        break;
                                    }
                                    else 
                                    {
                                        int stringLength = revMinorDiagonal.length();
                                        int sumOfComparisons = resultSE[1] * stringLength; 
                                    }
                                }
    
                            //End of searches. Unfound.
                            String justZero = "0";
                            if(!comparisonSumString.equals(justZero))
                            {
                                int i = Integer.parseInt(comparisonSumString);
                                System.out.println(wordIn + " was not found. (" + i + ")");
                            }
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