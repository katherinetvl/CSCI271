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
        public int[][] validMoves = 
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
        { 14, 9, 5 }, { 14, 13, 12 } 
        };

        public ArrayList<Integer> puzzleSolution = new ArrayList<Integer>();

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
        }


    } // End public static void main

/***************************************************************
 Functions begin here 
 ***************************************************************/
/***************************************************************
    * Function name: IsValidInt 
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
    * Function name: PegJump 
    * Input: char[] board array before jump 
    * Output: char[] board array after jump 
 ***************************************************************/
    public static char[] PegJump(char[] boardArray, int jumpingP, int removedP, int newPegPosition)
    {
        for(int i = 0; i < 15; i++)
        {
            if(i == jumpingP)
            {
                boardArray[i] = '-';
            }
            if(i == removedP)
            {
                boardArray[i] = '-';
            }
            if(i == newPegPosition)
            {
                boardArray[i] = 'O';
            }
        }
        return boardArray;
    }

    public static boolean hasValidMove(char[] arrBoard, int[][] moveMatrix, int currBoardPeg)
    {
        boolean hasValidMove = false;
        int rowSize = 36; 

        for(int i = 0; i < rowSize; i++)
        {
            int[] moveRow = moveMatrix[i];

            for(int j = 0; j < moveRow.length; j++)
            {
                int first = moveRow[0];
                int second = moveRow[1];
                int third = moveRow[2];
                if (first == currBoardPeg)
                {
                    if (arrBoard[first] == 'O' && arrBoard[second] == '-' && arrBoard[third] == '-')
                    {
                        hasValidMove = true;
                        arrBoard = PegJump(arrBoard, first, second, third);
                        break;
                    }
                }
            }
        }
        return hasValidMove; 
    }
/***************************************************************
    * Function name: RecursiveJumpSolution
    * Input: PuzzleBoard board, int[][] moveMatrix, ArrayList pegsMoved
    * Output: No solution or ArrayList with solution
 ***************************************************************/
    public static ArrayList RecursiveJumpSolution(PuzzleBoard arrBoard, int[][] moveMatrix, ArrayList pegsMoved)
    {
        for(int i = 0; i < arrBoard.boardLength(); i++)
        {
            char checkPeg = arrBoard[i];
            if(hasValidMove(arrBoard, moveMatrix, i)
            {

            }
        }
        
        return pegsMoved;
    }

} // End public class pegs