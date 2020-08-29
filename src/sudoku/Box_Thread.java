package sudoku;

public class Box_Thread implements Runnable {

    public int Start_Row = 0, Start_Column = 0 , End_Row ,End_Column;
    Check check = new Check();
    public int id;
    public String array_recieved[][];

    public Box_Thread(String arr[][],int id,int Start_r,int start_c){
        Start_Column = start_c;
        Start_Row = Start_r;
        End_Row = Start_Row+2;
        End_Column = Start_Column+2;
        array_recieved = arr;
        this.id = id;
    }
    @Override
    public  void run() {/*thread of box is lunched*/
       
      //System.out.println("thread Box" + " " + id + " " + "Started" +"row "+Start_Row + "column "+Start_Column);
       check.checkboxdata(array_recieved, Start_Row, Start_Column, End_Row, End_Column);
   //check.print_error();
    }
}
