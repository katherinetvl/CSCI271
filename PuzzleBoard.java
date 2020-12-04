public class PuzzleBoard 
{
    private int endPegLoc = -1; 
    private int count;
    private char[][] boardAsArray;
    
    // Constructors
    public PuzzleBoard() 
    {
        boardAsArray = new char[5][5];
        for(int i = 0; i <5; i++)
        {
            for(int j = 0; j <5; j++)
            {
                boardAsArray[i][j] = 'O';
                if(i == 4 && j == 2)
                {
                    boardAsArray[i][j] = '-';
                }
                if(j > i)
                {
                    boardAsArray[i][j] = 'X';
                }
            }
        }
    }

    public PuzzleBoard(int x)
    {
        // element at x needs to be the missing peg
        boardAsArray = new char[5][5];
        for(int i = 0; i <5; i++)
        {
            for(int j = 0; j <5; j++)
            {
                boardAsArray[i][j] = 'O';
                if(i == 4 && j == 2)
                {
                    boardAsArray[i][j] = '-';
                }
                if(j > i)
                {
                    boardAsArray[i][j] = 'X';
                }
            }
        }
    }

    public PuzzleBoard(int m, int n)
    {

    }
  
    // Initialize peg puzzle 
    private void init() 
    {
      count = 14;
    }

    // Print board status
    public void printBoard() 
    {
        // array[][] = {{1, 1, 1, 1, 1}, 
        //          {1, 1, 1, 1, 1},
        //          {1, 1, 0, 1, 1}}
        for (int i = 0; i < 5; i++) 
        {
            for (int j = 0; j < 5 - i; j++) 
            {
                System.out.print(" ");
            }
            for (int k = 0; k <= i; k++) 
            {
                if(// array[][] == '1')
                {
                    System.out.print("* ");
                    // array[][]++;
                }
                else
                {
                    System.out.print("  ");
                    // array[][]++;
                }
            }
            System.out.println();
        }
    }
}
  
    // Return peg count
    public int pegCount() 
    {
        return count; 
    }
}
