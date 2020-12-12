public class PuzzleBoard 
{
    private int endPegLoc = -1;
    private int totalPegCount;
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
        setEndPegLoc(n);
    }

    // User specified end peg location
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
        for (int i = 0; i < 15; i++) 
        {
            if(boardAsArray[i] == 'O')
            {
                totalPegCount++;
            }
        }
        return totalPegCount;
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

    // Checks if jump is valid 
    public boolean isValidMove(int first, int second, int third)
    {
        boolean isValidMove = false;

        if (boardAsArray[first] == 'O' && boardAsArray[second] == 'O' && boardAsArray[third] == '-')
        {
            isValidMove = true;
        }
        return isValidMove; 
    }

    // Checks if any jump is valid
    public boolean noValidMoves(int[][] allPossibleMoves)
    {
        int rowSize = 36;
        int nope = 0;

        for(int i = 0; i < rowSize; i++)
        {
            int[] moveRow = allPossibleMoves[i];

            for(int j = 0; j < moveRow.length; j++)
            {
                int first = moveRow[0];
                int second = moveRow[1];
                int third = moveRow[2];
                if (boardAsArray[first] == 'O' && boardAsArray[second] == 'O' && boardAsArray[third] == '-')
                {
                    nope++;
                }
            }
        }
        if(nope > 1)
        {
            return false;
        }

        return true;
    }

    // Performs a jump
    public void pegJump(int jumpingP, int removedP, int newPegPosition)
    {
        for(int i = 0; i < 15; i++)
        {
            if(i == jumpingP)
            {
                boardAsArray[i] = '-';
            }
            if(i == removedP)
            {
                boardAsArray[i] = '-';
            }
            if(i == newPegPosition)
            {
                boardAsArray[i] = 'O';
            }
        }
    }

        // Undo a jump 
        public void undoJump(int jumpingP, int removedP, int newPegPosition)
        {
            for(int i = 0; i < 15; i++)
            {
                if(i == jumpingP)
                {
                    boardAsArray[i] = 'O';
                }
                if(i == removedP)
                {
                    boardAsArray[i] = 'O';
                }
                if(i == newPegPosition)
                {
                    boardAsArray[i] = '-';
                }
            }
        }

}