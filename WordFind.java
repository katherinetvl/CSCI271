import java.io.File; // For File use 
import java.io.IOException; // For File errors
import java.util.Scanner; // For File reading 
// import java.util.regex.*; // For String replacement 

public class WordFind
{
    public static void main(String[] args) 
    {
        int rowSize = 0;
        int colSize = 0; 
    
    // Create input stream from first argument (txt file)
        if (args.length > 0)
        {
            File gridFile = new File(args[0]);
            String strCurr = null; 
            String tempChange = null; 
            String strAlpha = "";
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
            
            // Build 2D char array from string of all alpha chars from grid 
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
                    System.out.print(wordGrid[i][j] + " ");

                    increment++;
                }
                System.out.print("\n");
            }

            if (args.length > 1)
            {
                File searchForFile = new File(args[1]);
                String strCurr2 = null;

                try 
                {
                    Scanner fin2 = new Scanner(searchForFile);
                    while (fin2.hasNextLine())
                    {
                        strCurr2 = fin2.nextLine();
                        // System.out.println(strCurr2);
                    }

                fin2.close();
                } catch (IOException e) 
                {
                    e.printStackTrace();
                    System.out.println("An error occurred with opening word(s) to search file.");
                }
            }
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
                        }
                        else 
                        {
                            System.exit(0);
                        }
                    }
                input.close();
                } catch(Exception e)
                    {
                        e.printStackTrace();
                    }
            }

        } // end for "if there are arguments in command line" 

    // Prompt user for word to search if no file given
        else
        {
            System.out.println("Please run program with at least one file - the grid.");
        }

    }

// Functions 
/**********************************************
* Function Name: AlphaOnlyString
* Description: Building a string of just alpha characters 
* Input: string1, string2
* Output: string1 with alphabetical chars from string appended
**********************************************/
    public static String AlphaOnlyString(String finalString, String fileString) 
    {
        fileString = fileString.replaceAll("[^A-Za-z]+", "");
        finalString = finalString.concat(fileString);
        return finalString;
    }

/*******************************************************
* Function Name: ReverseString
* Description: A recursive function that reverses characters in string
* Input: string
* Output: string with reversed characters 
**********************************************************/
    public static String reverseString(String x)
    {
        if (x.isEmpty())
        {
            return x;
        }
        return reverseString(x.substring(1)) + x.charAt(0);
    }
}