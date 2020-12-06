/************************************************************
 * Obtaining anti-diagonals for M x N matrix
 * Nov. 6, 2020 
 * Source: https://javabypatel.blogspot.com/2016/12/print-matrix-diagonally-or-diagonal.html?m=1
 * Source: https://stackoverflow.com/questions/23951001/want-all-diagonals-in-a-mxn-matrix-in-java
 *************************************************************/
public class MatrixInDiagonal 
{
    public static void print(int [][] mat)
    {
        int RS = mat.length; 
        int CS = mat[0].length; 

        System.out.println("Diagonals \n");

        // First half 
        System.out.print("First half \n");
        for (int r = 0; r < RS; r++) 
        {
            for (int row = r, col = 0; row >= 0 && col < CS; row--, col++) 
            {
             System.out.print(mat[row][col] + " "); 
            }
            System.out.println();
        }

        // Second half
        System.out.print("Second half \n");
        for (int c = 1; c < CS; c++) 
        {
            for (int row = RS-1, col = c; row >= 0 && col < CS; row--, col++) 
            {
             System.out.print(mat[row][col] + " "); 
            }
            System.out.println();
        }

        int m = 6;
        int n = 4;

        System.out.println("Anti-Diagonals \n");
        // First half 
        System.out.print("First half \n");
        for(int j=n-1; j>=0; j--)
        {
            for(int k=0; k<m; k++)
            {
                if((j + k) < n)
                {
                    System.out.print(mat[k][j + k] + " ");
                } 
                else 
                {
                    break;
                }
            }
            System.out.println();
        }

        // Second half 
        System.out.print("Second half \n");
        for(int i=1; i<m; i++)
        {
            for(int j=i, k=0; j<m && k<n; j++, k++){
                System.out.print(mat[j][k] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int [][] a = {  {1,2,3,4},
                        {5,6,7,8},
                        {9,10,11,12},
                        {13,14,15,16},
                        {17,18,19,20},
                        {21,22,23,24}
                    };
        print(a);
    }
}