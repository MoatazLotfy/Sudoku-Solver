package sudoku;

public class Column_Thread implements Runnable{
    
    Check check = new Check();
    public int id;
    public String array_recieved[][];
    public Column_Thread(String arr[][])
    {   
        array_recieved = arr;
    }
    
    @Override
    public void run() {/*thread of column is lunched*/
       //System.out.println("thread Column"+" "+2+" "+"Started");
        check.checkcolumn(array_recieved);
    }
}