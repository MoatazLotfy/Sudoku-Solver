package sudoku;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JOptionPane;

public class Solve {

    public static int solve_limit = 0;
    public static int solve_cur = 0;
    public static String[][] recieved_sudoku = new String[9][9];// the recieved sudoku from constractor to solve it
    public static Set<Integer> set;
    public static int counter = 0;
    public static int Solve_flag = 0;

    public Solve(String[][] arr) {// initialize the reciecved sudoku
        recieved_sudoku = arr;
    }

    public void init_counter() {// this function to know number of the empty cells
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                if (recieved_sudoku[row][column].equals("") || recieved_sudoku[row][column].equals("0")) {
                    counter++;
                }
            }
        }
    }

    /*this to check element in one box*/
    public boolean checkboxdata(String matrix[][], int startrow, int startcolumn, int endrow, int endcolumn, int number) {
        String[] array = new String[9];
        int arraycount = 0;

        for (int row = startrow; row <= endrow; row++) {
            for (int column = startcolumn; column <= endcolumn; column++) {
                array[arraycount] = matrix[row][column];
                arraycount++;
            }

        }
        for (int counter1 = 0; counter1 < 9; counter1++) {
            if (array[counter1].equals(Integer.toString(number))) {
                return false;
            }
        }
        return true;
    }

    /*this to check element in one row and column*/
    public void checkrowandcolumn(String matrix[][], int emptyrow, int emptycolumn) {
        try {
            int[] data = {2, 2, 2, 5, 2, 8, 5, 2, 5, 5, 5, 8, 8, 2, 8, 5, 8, 8};
            int k = 0;
            int l = 0;
            int u = 0;
            int m = 0;
            for (int h = 0; h < 18; h = h + 2) {
                if (emptyrow <= data[h] && emptycolumn <= data[h + 1]) {
                    k = data[h];
                    l = data[h + 1];
                    u = data[h] - 2;
                    m = data[h + 1] - 2;
                    break;
                } else {
                    continue;
                }
            }
            set = new HashSet<>();
            for (int number = 1; number <= 9; number++) {

                int found_element = 0;

                for (int column = 0; column < 9; column++) {
                    if (matrix[emptyrow][column].equals(Integer.toString(number)) || matrix[column][emptycolumn].equals(Integer.toString(number))
                            || !checkboxdata(matrix, u, m, k, l, number) == true) {
                        found_element++;
                    }
                }
                if (found_element == 0) {
                    set.add(number);
                }
            }
            // System.out.println(set);
        } catch (Exception e) {

        }
    }

    // reduction method to optimize the back track solving
    public void Solve_aux(int row, int column) {
        checkrowandcolumn(recieved_sudoku, row, column);
        if (set.size() == 1) {
            recieved_sudoku[row][column] = new ArrayList<>(set).get(0) + "";
            counter--;
        }
    }

    /*the back track algorithm*/
    public boolean backtack(int row, int column) {
        solve_cur++;
        int flag = 0;
        int next_row = 0;
        int next_column = 0;
        try {
            /*to get the next row and column*/
            next_row = row * 9 + column + 1;
            next_column = next_row % 9;
            next_row = next_row / 9;

            /*equation for know the start and end of the box*/
            int start_row = 3 * (row / 3);
            int start_col = 3 * (column / 3);
            int end_row = start_row + 2;
            int end_col = start_col + 2;

            // if cell is filled go to the new cell
            if (!(recieved_sudoku[row][column].equals("") || recieved_sudoku[row][column].equals("0"))) {
                if (row == 8 && column == 8) {
                    return true;
                }
                return backtack(next_row, next_column);
            } else {// if it is empty cell
                for (int element = 1; element <= 9; element++) {
                    flag = 0;
                    for (int column1 = 0; column1 < 9; column1++) { // check the element in this position 
                        if (recieved_sudoku[row][column1].equals(Integer.toString(element)) || recieved_sudoku[column1][column].equals(Integer.toString(element))
                                || checkboxdata(recieved_sudoku, start_row, start_col, end_row, end_col, element) == false) {
                            flag++;
                            break;
                        }
                    }
                    if (flag == 0) {
                        recieved_sudoku[row][column] = element + "";
                        solve_limit++;
                        if (row == 8 && column == 8) {// check if finish
                            return true;
                        }
                        if (backtack(row, column)) {
                            return true;
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
        recieved_sudoku[row][column] = "";
        return false;// if no element from 1 - 9 is right return false to the previous  call and continue
    }

    /*the main solve function that all the two algorithm*/
    public String[][] Solve() {
        int counter1 = 0;
        int last_counter = 0;
        this.init_counter();
        while (counter != 0 && last_counter != counter) {
            last_counter = counter;
            for (int row = 0; row < 9; row++) {
                for (int column = 0; column < 9; column++) {
                    if (recieved_sudoku[row][column].equals("") || recieved_sudoku[row][column].equals("0")) {
                        this.Solve_aux(row, column);
                    }
                }
            }
        }
        if (!backtack(0, 0)) {// if invalid sudoku
            JOptionPane.showMessageDialog(null, "INVALID SUDOKU", "INVALID", JOptionPane.ERROR_MESSAGE);
        } else {
            Check c = new Check();
            if (c.checkrows(recieved_sudoku) && c.checkcolumn(recieved_sudoku)) {
                int[] data1 = {2, 2, 2, 5, 2, 8, 5, 2, 5, 5, 5, 8, 8, 2, 8, 5, 8, 8};
                for (int h = 0; h < 18; h = h + 2) {
                    if (c.checkboxdata(recieved_sudoku, data1[h] - 2, data1[h + 1] - 2, data1[h], data1[h + 1])) {
                        counter1++;
                    } else {
                        JOptionPane.showMessageDialog(null, "CHECK YOUR Sudoku", "ERROR", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                }
                if (counter1 == 9) {
                    Solve_flag = 1;
                    System.out.println("Solved Sudoku");// if sudoku solved 
                }
            }
        }
        return recieved_sudoku; // return the sudoku after finish the two solving algorithms
    }
}
