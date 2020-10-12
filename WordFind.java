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
            File theFile = new File(args[0]);
            String strCurr = null;

            try 
            {
                Scanner fileInput = new Scanner(theFile);
                // String s = fileInput.nextLine();
                while (fileInput.hasNextLine())
                {
                    // rowSize++;
                    strCurr = fileInput.nextLine();

                    

                    System.out.println(strCurr);
                }

    // Close file
            fileInput.close();
            } catch (IOException e)
            {
                e.printStackTrace();
                System.out.println("An error occurred with opening word grid file.");
            }
        }

    // Prompt user for word to search if no file given
        else
        {
            System.out.println("Enter word to search: ");
            Scanner input = new Scanner(System.in);

            String wordToSearch = input.next(); 
            System.out.println("Word To Search is: " + wordToSearch + "\n");
        }

    }
}

// Functions 
/**********************************************
* Function Name: ScanWordGrid
* Description: Function attempts to open text file and process through char grid
* Input: Command line argument file
* Output: Char grid has been read 
***********************************************/
public static void ScanWordGrid(String[] cmdLineArgument) 
{

}
// Deep Copy every string 

// Obtain row and col (i and j values )

// Convert string to char array 

// Build word grid 

// Search grid for pattern 
