public class PuzzleBoard 
{
    private int endPegLoc = -1;
    private int totalPegCount;
    private int length;
    private char[] boardAsArray;

    // Constructors
    public PuzzleBoard() 
    {
        // no pegs specified by user
        boardAsArray = new char[15];
        for (int i = 0; i < 15; i++) 
        {
            boardAsArray[i] = 'O';
            {
                if (i == 12) {
                    boardAsArray[i] = '-';
                }
            }
        }
        totalPegCount = 14;
    }

    public PuzzleBoard(int x) 
    {
        // element at x needs to be the missing peg
        boardAsArray = new char[15];
        for (int i = 0; i < 15; i++) 
        {
            boardAsArray[i] = 'O';
            {
                if (i == x) {
                    boardAsArray[i] = '-';
                }
            }
        }
        totalPegCount = 14;
    }

    public PuzzleBoard(int m, int n) 
    {
        // element at m needs to be the missing peg
        // last peg remaining needs to be at n
        boardAsArray = new char[15];
        for (int i = 0; i < 15; i++) 
        {
            boardAsArray[i] = 'O';
            {
                if (i == m) 
                {
                    boardAsArray[i] = '-';
                }
            }
        }
        totalPegCount = 14;
        setEndPegLoc(n);
    }

    public void setEndPegLoc(int endPegLoc) 
    {
        this.endPegLoc = endPegLoc;
    }

    public int getEndPegLoc() 
    {
        return endPegLoc;
    }

    // Return peg count
    public int pegCount() 
    {
        totalPegCount = 0; 
        return totalPegCount;
    }

    // Return board size 
    public int boardLength()
    {
        length = 15;
        return length; 
    }

    // Print board status
    public void printBoard() 
    {
        int index = 0; 
        for (int r = 0; r < 5; r++) 
        {
            for(int j = 5; j >= r; j--)
            {
                System.out.print(" ");
            }
            for(int j = 0; j<= r; j++)
            {
                System.out.print(boardAsArray[index] + " ");
                index++;
            }
            System.out.println("\n");
        }
    }
}