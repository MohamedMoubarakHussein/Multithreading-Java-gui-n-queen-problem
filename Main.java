import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main extends JPanel {

	public static int n = 8;
	public static int  search_sleep = 400;
     static JFrame frame =new JFrame();
	 static Main panel=new Main();
      static int[][] board = new int[n][n];
	static List<int[][]> temp = new ArrayList<int[][]>();
    static boolean tempz = false;
    static Image zz;


    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedImage b  = ImageIO.read(new File("C:\\Users\\RE-PC\\Desktop\\parraller\\1.png"));
        zz  = b.getScaledInstance(64 , 64 , BufferedImage.SCALE_SMOOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setSize(750,512);
        frame.setVisible(true);

        Thread[] arr = new Thread[8];
        for (int i = 0; i < 8; i++) {

            Thread   s  =new Thread(new Search());
               arr[i] = s;
               arr[i].start();
        }

        for (Thread s: arr) {
            s.join();
        }




            for (int i = 1; i < Main.temp.size() ; i++) {
                Main.board = Arrays.stream(Main.temp.get(i)).map(int[]::clone).toArray(int[][]::new);

                System.out.println(" ");
                Main.frame.repaint();
                Thread.sleep(search_sleep);

            }




    }





    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);


        boolean flag = true;
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < n ; j++) {
                if(flag) {
                    g.setColor(Color.white);
                    flag = false;

                }else {
                    g.setColor(Color.white.darker());
                    flag = true;
                }
                g.fillRect(i*64,j*64,64,64 );

            }
            if(flag) {
                flag = false;
            }else {
                flag = true;
            }
        }
        //
        int[][] arr = new int[n][n];
        for(int row = 0 ; row < n ; row++) {
            for(int column = 0 ; column < n ; column++ ) {
                arr[row][column] = board[column][row];
            }
        }
        for(int row = 0 ; row < n ; row++) {
            for(int column = 0 ; column < n ; column++ ) {
                if(arr[row][column] == 1) {
                    g.drawImage( zz, row*64 ,column*64 , this );
                }}
        }

    }



}