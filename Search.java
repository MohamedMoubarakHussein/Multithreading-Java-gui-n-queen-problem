import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static java.util.Arrays.stream;
import static java.util.Collections.copy;

public class Search implements Runnable{
     int n= Main.n;
     List<int[][]> draw_seq = new ArrayList<int[][]>();
     int[][] board = new int[n][n];

//check s
     boolean guess( int row,int col) throws InterruptedException
    {
        // why cp

        draw_seq.add(Arrays.stream(board).map(int[]::clone).toArray(int[][]::new));
        if (col >= Main.n)
            return true;


        for (int i = row; i < Main.n; i++) {

            if (Safe.isSafe(board, i, col)) {

                board[i][col] = 1;

                // check the i in the next
                if (guess(0, col + 1))
                    return true;


                board[i][col] = 0;
            }

        }


        return false;
    }




    @Override
    public void run() {
        Random random =new Random();
        random.setSeed(random.nextLong(99999L *n));
        int i = random.nextInt(n);

    	 try {

		 boolean ans =	guess(i , 0);

    if(  ans&& !Main.tempz) {
        Main.tempz = true;
        System.out.println("found ans");
        Main.temp = new ArrayList<int[][]>(draw_seq);

    }

		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}

     
    }


}