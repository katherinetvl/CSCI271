/************************************************************
* Katherine Le
* lek4@winthrop.edu
* CSCI 271 PA4
* Description: Resursively remove pegs until 1 remains
*************************************************************/
import java.util.ArrayList; // For ArrayList use 

public class PuzzlePegs
{
    public static void main(String[] args) 
    {

        int[][] validMoves = 
        {{ 0, 1, 3 }, { 0, 2, 5 },
        { 1, 3, 6 }, { 1, 4, 8 }, 
        { 2, 4, 7 }, { 2, 5, 9 },
        { 3, 1, 0 }, { 3, 4, 5 }, { 3, 6, 10 }, { 3, 7, 12 }, 
        { 4, 7, 11 }, { 4, 8, 13 }, 
        { 5, 2, 0 }, { 5, 4, 3 }, { 5, 8, 12 }, { 5, 9, 14 }, 
        { 6, 3, 1 }, { 6, 7, 8 }, 
        { 7, 4, 2 }, { 7, 8, 9 }, 
        { 8, 4, 1 }, { 8, 7, 6 },
        { 9, 5, 2 }, { 9, 8, 7 }, 
        { 10, 6, 3 }, { 10, 11, 12 }, 
        { 11, 7, 4 }, { 11, 12, 13 },
        { 12, 7, 3 }, { 12, 8, 5 }, { 12, 11, 10 }, { 12, 13, 14 },
        { 13, 8, 4 }, {13, 12, 11 },
        { 14, 9, 5 }, { 14, 13,12 } 
        };

        // int rowSize = 36;

        ArrayList<Integer[]> solutionArray = new ArrayList<>();
        // int countJumps = 0; 

        if(args.length > 0)
        {
            if(args.length == 2)
            {
                for(int h = 0; h < args.length; h++)
                {
                    if(!IsValidInt(args[h]))
                    {
                        System.exit(0);
                    }
                }
                int startPeg = Integer.parseInt(args[0]) - 1;
                int endPeg = Integer.parseInt(args[1]) - 1;
                PuzzleBoard boardType2 = new PuzzleBoard(startPeg, endPeg);
                boardType2.printBoard();
            }
            else if(args.length == 1)
            {
                for(int h = 0; h < args.length; h++)
                {
                    if(!IsValidInt(args[h]))
                    {
                        System.exit(0);
                    }
                }

                int startPeg = Integer.parseInt(args[0]) - 1;
                PuzzleBoard boardType1 = new PuzzleBoard(startPeg);
                boardType1.printBoard();
            }
            else 
            {
                System.out.println("Too much input.");
                System.exit(0);
            }
        }
        else 
        {
            // no user defined command line arguments
            PuzzleBoard boardType0 = new PuzzleBoard();
            boardType0.printBoard();
            int numPegs = boardType0.pegCount();
            int solution = RecursiveJumpSolution(boardType0, validMoves, solutionArray, numPegs);

            if(solution == 0)
            {
                System.out.println("Solution completed.");
                System.out.println("The steps: ");
                PrintArrayList(boardType0, solutionArray, validMoves);
            }
            else
            {
                System.out.println("No solution found.");
                System.out.println("The steps thus leading to decision: ");
                PrintArrayList(boardType0, solutionArray, validMoves);
            }
        }


    } // End public static void main

/***************************************************************
 Functions begin here 
 ***************************************************************/
/***************************************************************
    * Function name: IsValidInt 
    * Function type: boolean
    * Input: string 
    * Output: true if parsed string integer
 ***************************************************************/

    public static boolean IsValidInt(String argumentStr) 
    {
        boolean IsValidInt = false;
        try
        {
           int y = Integer.parseInt(argumentStr);

           if(y >= 0 && y <= 14)
           {
               IsValidInt = true;
           }
        }
        catch (NumberFormatException ex)
        {
            System.out.println("Enter numbers between 0 - 14 to represent pegs 1 - 15.");
        }
        return IsValidInt;
    }

/***************************************************************
    * Function name: RecursiveJumpSolution
    * Input: PuzzleBoard puzzle board, int[][] possible moves, ArrayList for solution, int number of pegs in board
    * Output: Solution if no moves available and appropriate one peg remains
              Else, no solution
 ***************************************************************/
  public static int RecursiveJumpSolution(PuzzleBoard arrBoard, int[][] moveMatrix, ArrayList<Integer[]> pegsMoved, int currPegs)
    {
        if(currPegs == 1)
        {
            return 0;
        }
        else if(arrBoard.noValidMoves(moveMatrix))
        {
            return 1;
        }
        else
        {
            for(int i = 0; i < 36; i++)
            {
                int[] moveRow = moveMatrix[i];
    
                for(int j = 0; j < moveRow.length; j++)
                {
                    int col1 = moveRow[0];
                    int col2 = moveRow[1];
                    int col3 = moveRow[2];
    
                    Integer[] currMove = {col1, col2, col3};
                    if(arrBoard.isValidMove(col1, col2, col3))
                    {
                        arrBoard.pegJump(col1, col2, col3);        
                        arrBoard.printBoard();
                        currPegs = currPegs - 1;
                        // System.out.println(currPegs);
                        pegsMoved.add(currMove);
                    }
                }
            }
            return RecursiveJumpSolution(arrBoard, moveMatrix, pegsMoved, currPegs);
        }
    }
/***************************************************************
* Function name: PrintArrayList
* Input: PuzzleBoard puzzle board, int[][] possible moves, ArrayList for solution, int number of pegs in board
* Output: Solution if no moves available and appropriate one peg remains
            Else, no solution
 ***************************************************************/
public static void PrintArrayList(PuzzleBoard arrBoard, ArrayList<Integer[]> pegsMoved, int[][] movesMade)
    {

        for (int i = 0; i < pegsMoved.size(); i++)
        {
            Integer[] rowOfMoves = pegsMoved.get(i);

            for(int j = 0; j < 36; j++)
            {
                int[] moveRow = movesMade[j];
    
                for(int k = 0; k < moveRow.length; k++)
                {
                    int col1 = moveRow[0];
                    int col2 = moveRow[1];
                    int col3 = moveRow[2];
    
                    Integer[] currMove = {col1, col2, col3};
                    if(currMove == rowOfMoves)
                    {
                        System.out.println("Moves go here");
                        System.out.println(col1 + " " + col2 + " " + col3);
                        System.out.print("\n");
                    }
                    System.out.println(moveRow);
                }
            }

            System.out.println(rowOfMoves);
        }


    }
} // End public class pegs