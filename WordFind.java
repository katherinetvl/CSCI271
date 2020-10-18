import java.io.File; // For File use 
import java.io.IOException; // For File errors
import java.util.Scanner; // For File reading 

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

            try 
            {
                Scanner fin = new Scanner(gridFile);
                while (fin.hasNextLine())
                {
                    // rowSize++;
                    strCurr = fin.nextLine();
                    rowSize++;
                    colSize++;
                    System.out.println(strCurr);
                }

            // Close file
            fin.close();
            } catch (IOException e) 
            {
                e.printStackTrace();
                System.out.println("An error occurred with opening grid file.");
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
                        System.out.println(strCurr2);
                    }

                fin2.close();
                } catch (IOException e) // end of try 
                {
                    e.printStackTrace();
                    System.out.println("An error occurred with opening word(s) to search file.");
                } // end for catch
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
                        String wordToSearch = input.next();
                        String wordToSearchLower = wordToSearch.toLowerCase(); 
                        if(!(wordToSearchLower.equals("quit")))
                        {
                            System.out.println("Word To Search is: " + wordToSearchLower + "\n");
                        }
                        else 
                        {
                            System.exit(0);
                        }
                    }
                // input.close();
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
}

// Functions 
/**********************************************
* Function Name: AlphaOnlyString
* Description: Building a string of just alpha characters 
* Input: StringExisting, FileString
* Output: StringExisting with only alpha chars of FileString concatenated on
**********************************************
public static void ScanWordGrid(Char[] allChars, String fileLine) 
{

} */

// Obtain row and col (i and j values)

// Convert string to char array 

// Build word grid 

// Search grid for pattern 
