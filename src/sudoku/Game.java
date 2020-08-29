package sudoku;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Game {

    public static String mat[][] = new String[9][9];
    public static String levelname;
    public String highscorefile = "";
    public static String sodo_Game[][] = new String[9][9];

    String getHighScore() throws IOException {// to get the high score from the file  

        BufferedReader br = new BufferedReader(new FileReader("HS.txt"));
        String fileline = br.readLine();
        char tempArray2[] = fileline.toCharArray();

        for (int i = 0; i < tempArray2.length; i++) {
            highscorefile += tempArray2[i];
        }
        br.close();

        return highscorefile;
    }

    void setHighScore(int highScore) throws IOException // for setting the high score at file
    {
        System.out.println(highScore);
        BufferedWriter bw = new BufferedWriter(new FileWriter("HS.txt"));
        bw.write(Integer.toString(highScore));
        bw.close();

    }

    String[][] create_game(int level) throws IOException // generate the game
    {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                mat[i][j] = "";
            }
        }
        for (int i = 1; i <= 9; i++) {
            Random ran = new Random();
            int x_axis = ran.nextInt(9);
            int y_axis = ran.nextInt(9);
            mat[x_axis][y_axis] = Integer.toString(i);
        }
        String x[][] = new String[9][9];
        Solve sol = new Solve(mat);
        x = sol.Solve();
        for (int zeros = 0; zeros < level; zeros++) {
            Random ran = new Random();
            int x_axis = ran.nextInt(9);
            int y_axis = ran.nextInt(9);
            mat[x_axis][y_axis] = "0";

        }
        sodo_Game = mat;
        BufferedWriter bw = new BufferedWriter(new FileWriter("hint.txt"));
        bw.write("4");
        bw.close();
        return mat;

    }

    void Resume() throws FileNotFoundException, IOException {//to resume the game after saving

        BufferedReader br = new BufferedReader(new FileReader("save.txt"));
        String check = br.readLine();
        char[] m = new char[81];

        m = check.toCharArray();
        int q = 0;
        String n;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                mat[i][j] = Character.toString(m[q]);
                q++;

            }

        }
        q = 81;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sodo_Game[i][j] = Character.toString(m[q]);
                q++;

            }
        }

    }

}
