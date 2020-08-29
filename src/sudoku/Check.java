package sudoku;

import java.util.HashMap;

/*this class for check all types*/
public class Check {

    public static String[] A = new String[81];
    public static HashMap<String, String> Repeated = new HashMap<>();
    /*check row*/

    public  boolean checkrows(String matrix[][]) {
        String arrayList = new String();
        int flag = 0; // for know if the number is exist or not >>to return false 
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                for (int next = column + 1; next < 9; next++) {// this for loop for dont take the pervious element i was checked
                    if (column + 1 != 9) {
                        if (matrix[row][column].equals(matrix[row][next]) && !matrix[row][column].equals("") && !matrix[row][column].equals("0")) {
                            flag++;
                            arrayList = (row+ "," +column );
                            Repeated.put(arrayList, matrix[row][column]);
                            arrayList = (row+ "," + next);
                            Repeated.put(arrayList, matrix[row][next]);
                             System.out.println("row of error " + "The Row " + row + "," + "The Column " + next + " number is" + matrix[row][column]);
                        } else {
                            continue;
                        }
                    }
                }
            }
        }
        if (flag != 0) {
            return false;
        }
        return true;
    }
    /* check column*/

    public  boolean checkcolumn(String matrix[][]) {
        String arrayList = new String();
        int flag = 0;
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                for (int next = row + 1; next < 9; next++) { // like before
                    if (column + 1 != 9) {
                        if (matrix[row][column].equals(matrix[next][column]) && !matrix[row][column].equals("") && !matrix[row][column].equals("0")) {
                            flag++;
                            arrayList = (row+ "," +column );
                            Repeated.put(arrayList, matrix[row][column]);
                            arrayList = (next+ "," + column);
                            Repeated.put(arrayList, matrix[next][column]);
                            //System.out.println(arrayList);
                            // System.out.println("column of error " + "The Column " + column + "," + "The Row " + next + " number is" + matrix[row][column]);

                        } else {
                            continue;
                        }
                    }
                }
            }
        }
        if (flag != 0) {
            return false;
        }
        return true;
    }

    /*check box*/
    public synchronized boolean checkboxdata(String matrix[][], int startrow, int startcolumn, int endrow, int endcolumn) {
        int flag = 0;
        String arrayList = new String();
        String[] array = new String[9];
        int[] rows = new int[9];
        int[] cols = new int[9];
        int arraycount = 0;
        try {
            for (int row = startrow; row <= endrow; row++) {
                for (int column = startcolumn; column <= endcolumn; column++) {
                    if (!matrix[row][column].equals("") && !matrix[row][column].equals("0")){
                        array[arraycount] = matrix[row][column];//values of an cell
                        rows[arraycount] = row;//array have all rows of filled cells
                        cols[arraycount] = column;//array have all columns of filled cells
                        arraycount++;
                    }
                }
            }
            //System.out.println(arraycount);

            for (int counter1 = 0; counter1 < arraycount; counter1++) {
                for (int counter2 = counter1 + 1; counter2 < arraycount; counter2++) {
                    if (array[counter1].equals(array[counter2])) {
                        flag++;
                        if (array[counter2].equals(matrix[rows[counter2]][cols[counter2]])) {
                            // list of errors in the same box
                            arrayList = (rows[counter1] + "," + cols[counter1]);
                            Repeated.put(arrayList, array[counter2]);
                            arrayList = (rows[counter2] + "," + cols[counter2]);
                            Repeated.put(arrayList, array[counter2]);

                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error"+e);
        }
        if (flag != 0) {
            return false;
        }
        return true;
    }
    public static int ie = 0;//counter static of size array A the have all indexes must be colored in the GUI

    public void print_error() {

        for (String x : Repeated.keySet()) {//fetch all indexes
            A[ie] = x;
            System.out.println(x);
            ie++;
        }
        
    }

}
