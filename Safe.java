public class Safe {
 
      
public static boolean isSafe(int board[][], int r, int c)
 {
    

     // check
     for (int  i = 0; i < c; i++) {
         if (board[r][i] == 1) {
             return false;
         }
         
     }

     
     int j = c;
     for ( int i = r; i >= 0 && j >= 0; i--) {
         if (board[i][j] == 1) {
             return false;
         }
         j--;
     }

      j = c;
     for (int i = r; j >= 0 && i < Main.n; i++) {
         if (board[i][j] == 1) {
             return false;
         }
         j--;
 }

     return true;
 }
}

