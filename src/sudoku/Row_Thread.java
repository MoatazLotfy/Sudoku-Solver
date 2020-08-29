package sudoku;

public class Row_Thread implements Runnable {

    Check check = new Check();
    public int id;
    public String array_recieved[][];

    public Row_Thread(String arr[][]) {
        array_recieved = arr;
    }

    @Override
    public void run() {/*this run function will run if thread is started*/

        //System.out.println("thread Row"+" "+1+" "+"Started"); // print row thread are fired 

        check.checkrows(array_recieved);
    }
}
