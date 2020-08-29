package sudoku;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import static sudoku.scr2.array_of_data;

/*the solving sudoku screen*/
public class scr1 extends JFrame {

    private JButton btn1;
    private JButton btn2;
    private JButton btn3;
    private JButton back_btn;
    private final JLabel background;
    private JPanel panel;
    private JLabel row1;
    private JLabel row2;
    private JLabel col1;
    private JLabel col2;
    private JTextField[][] cell;
    public String[][] sudoku_data = new String[9][9];
    private JLabel timer_label;
    String selected_cell = null;
    int focused_x, focused_y;

    public scr1() {
        super("enter su");
        GUI gui = new GUI();
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) ((dimension.getWidth() - getWidth()) / 2);
        int height = (int) ((dimension.getHeight() - getHeight()) / 3);
        setLocation(width, height);
        panel = new JPanel();
        panel.setLayout(null);
        int[][] sudoku = new int[][]{
            {1, 0, 0, 0, 6, 0, 0, 0, 0},
            {6, 0, 0, 5, 0, 0, 0, 0, 4},
            {0, 2, 0, 4, 0, 0, 5, 0, 0},
            {0, 8, 0, 0, 0, 5, 0, 0, 7},
            {0, 0, 0, 0, 0, 0, 9, 3, 0},
            {0, 0, 4, 3, 0, 0, 0, 0, 8},
            {0, 9, 0, 0, 0, 7, 0, 5, 0},
            {0, 7, 0, 0, 0, 0, 6, 0, 9},
            {0, 0, 0, 1, 0, 0, 4, 0, 0}
        };
        int[][] sudoku2 = new int[][]{
            {3, 7, 0, 0, 8, 4, 0, 2, 6},
            {8, 0, 0, 0, 9, 0, 0, 0, 4},
            {0, 4, 0, 1, 0, 0, 0, 3, 0},
            {6, 0, 0, 2, 1, 9, 4, 0, 0},
            {2, 9, 0, 8, 0, 5, 0, 6, 1},
            {0, 0, 5, 6, 3, 7, 0, 0, 8},
            {0, 2, 0, 0, 0, 1, 0, 8, 0},
            {7, 0, 0, 0, 2, 0, 0, 0, 9},
            {1, 8, 0, 9, 5, 0, 0, 4, 2}
        };
        add(panel);

        panel.setBounds(
                0, 0, 700, 600);
        btn3 = new JButton("X\n");

        String[] columns = new String[]{
            "1", "2", "3", "4", "5", "6", "7", "8", "9"
        };
        cell = new JTextField[9][9];

        int x = 1;
        int innerbox_row_position = 50;
        int innerbox_col_position = 147;
        for (int i = 0;
                i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (innerbox_col_position == 551) {
                    innerbox_col_position = 147;

                }

                cell[i][j] = new JTextField();

                cell[i][j].setHorizontalAlignment(JTextField.CENTER);
                cell[i][j].setFont(new Font("Arial", Font.BOLD, 30));

                if (innerbox_col_position < 279) {
                    if (innerbox_row_position >= 182 && innerbox_row_position < 318) {
                        x = 2;
                    } else {
                        x = 1;
                    }
                }
                if (innerbox_col_position == 279) {
                    if (innerbox_row_position >= 186 && innerbox_row_position <= 318) {
                        x = 1;
                    } else {
                        x = 2;
                    }

                    innerbox_col_position = innerbox_col_position + 4;

                }
                if (innerbox_col_position == 415) {

                    if (innerbox_row_position >= 186 && innerbox_row_position <= 318) {
                        x = 2;
                    } else {
                        x = 1;
                    }
                    innerbox_col_position = innerbox_col_position + 4;
                }
                if (innerbox_row_position == 182) {

                    innerbox_row_position = innerbox_row_position + 4;

                } else if (innerbox_row_position == 318) {

                    innerbox_row_position = innerbox_row_position + 4;
                }
                if (x == 1) {
                    cell[i][j].setBackground(new Color(gui.cell_color1, gui.cell_color2, gui.cell_color3));

                } else {
                    cell[i][j].setBackground(Color.WHITE);
                }
                panel.add(cell[i][j]);

                cell[i][j].setBounds(innerbox_col_position, innerbox_row_position, 42, 42);

                //cell[i][j].setText(Integer.toString(sudoku4[i][j]));
                innerbox_col_position += 44;
            }
            innerbox_row_position += 44;

        }

        for (int ere = 0;
                ere < 9; ere++) {
            for (int rer = 0; rer < 9; rer++) {
                int y = ere;
                int o = rer;

                cell[ere][rer].addFocusListener(new FocusListener() {

                    @Override
                    public void focusGained(FocusEvent e) {

                        cell[y][o].setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(117, 176, 174)));
                        for (int j = 0; j < 9; j++) {
                            cell[y][j].setBackground(new Color(215, 240, 239));
                            cell[j][o].setBackground(new Color(215, 240, 239));
                        }

                        selected_cell = cell[y][o].getText();
                        int num_of_commonnumber = 1;
                        for (int i = 0; i < 9; i++) {
                            for (int j = 0; j < 9; j++) {
                                cell[i][j].setForeground(Color.BLACK);
                                if (selected_cell.equals("")) {
                                } else {
                                    if (selected_cell.equals(cell[i][j].getText())) {
                                        num_of_commonnumber++;
                                    }
                                    if (num_of_commonnumber > 1 && selected_cell.equals(cell[i][j].getText())) {
                                        cell[i][j].setForeground(Color.BLUE);
                                    }
                                }

                            }
                        }
                        focused_x = y;
                        focused_y = o;
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        cell[y][o].setForeground(Color.BLACK);
                        cell[y][o].setBorder(null);
                        for (int j = 0; j < 9; j++) {
                            cell[y][j].setBackground(Color.WHITE);
                            cell[j][o].setBackground(Color.WHITE);
                        }
                        for (int i = 0; i < 9; i++) {
                            for (int j = 0; j < 9; j++) {
                                if (j != 3 && j != 4 && j != 5) {
                                    if (i != 3 && i != 4 && i != 5) {
                                        cell[i][j].setBackground(new Color(gui.cell_color1, gui.cell_color2, gui.cell_color3));

                                    }

                                } else if (j == 3 || j == 4 || j == 5) {
                                    if (i == 3 || i == 4 || i == 5) {
                                        cell[i][j].setBackground(new Color(gui.cell_color1, gui.cell_color2, gui.cell_color3));

                                    }

                                }

                            }

                        }

                    }
                });

            }

        }

        background = new JLabel();
        row1 = new JLabel();
        row2 = new JLabel();
        col1 = new JLabel();
        col2 = new JLabel();
        timer_label = new JLabel();

        background.setIcon(
                new ImageIcon(gui.Background_image));
        row1.setIcon(
                new ImageIcon("row.png"));
        row2.setIcon(
                new ImageIcon("row.png"));
        col1.setIcon(
                new ImageIcon("0001.png"));
        col2.setIcon(
                new ImageIcon("0001.png"));

        panel.add(row1);

        panel.add(row2);

        panel.add(col1);

        panel.add(col2);

        panel.add(timer_label);

        panel.add(background);
        btn1 = new JButton("<html><h2>Solve Sudoku</h2></html>");
        btn2 = new JButton("<html><h2>Reset</h2></html>");
        back_btn = new JButton("<");
        btn1.setFont(
                new Font("Bradley Hand ITC", Font.BOLD + Font.ITALIC, 90));
        btn2.setFont(
                new Font("Bradley Hand ITC", Font.BOLD + Font.ITALIC, 90));
        btn1.setBorder(
                null);
        btn2.setBorder(
                null);
        back_btn.setBorder(null);
        btn1.setBackground(Color.WHITE);

        btn1.setForeground(
                new Color(gui.text_color1, gui.text_color2, gui.text_color3));
        btn2.setBackground(Color.WHITE);
        back_btn.setBackground(Color.WHITE);

        btn2.setForeground(
                new Color(gui.text_color1, gui.text_color2, gui.text_color3));
        btn3.setBackground(
                new Color(gui.text_color1, gui.text_color2, gui.text_color3));
        btn3.setForeground(Color.WHITE);
        back_btn.setForeground(new Color(gui.text_color1, gui.text_color2, gui.text_color3));

        btn3.setFont(
                new Font("Arial", Font.BOLD, 25));
        back_btn.setFont(new Font("Bradley Hand ITC", Font.BOLD, 30));
        btn3.setBorder(
                null);
        panel.add(back_btn);
        panel.add(btn1);

        panel.add(btn2);

        panel.add(btn3);

        panel.add(background);

        btn1.setBounds(
                50, 480, 175, 60);
        btn2.setBounds(
                470, 480, 175, 60);
        btn3.setBounds(
                650, 30, 20, 20);
        row1.setBounds(
                147, 180, 403, 10);
        row2.setBounds(
                147, 315, 403, 10);
        col1.setBounds(
                185, 50, 100, 403);
        col2.setBounds(
                320, 50, 100, 403);
        background.setBounds(
                0, 0, 700, 600);
        back_btn.setBounds(10, 10, 40, 40);
        timer_label.setForeground(Color.WHITE);

        timer_label.setBounds(
                300, 480, 175, 60);
        timer_label.setFont(
                new Font("Arial", Font.BOLD, 60));

        btn1.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e // this event for button solve sudoku
                    ) {
                        for (int i = 0; i < 9; i++) {
                            for (int j = 0; j < 9; j++) {
                                sudoku_data[i][j] = cell[i][j].getText();
                                // System.out.println(sudoku_data[i][j]);
                            }
                        }
                        int count = 0;
                        Check c = new Check();// use check to ensure the valid input
                        if (c.checkrows(sudoku_data) && c.checkcolumn(sudoku_data)) {
                            //System.out.println("frist");
                            int[] data = {2, 2, 2, 5, 2, 8, 5, 2, 5, 5, 5, 8, 8, 2, 8, 5, 8, 8};
                            for (int h = 0; h < 18; h = h + 2) {
                                if (c.checkboxdata(sudoku_data, data[h] - 2, data[h + 1] - 2, data[h], data[h + 1])) {
                                    //      System.out.println("second");
                                    count++;
                                } else {
                                    JOptionPane.showMessageDialog(null, "CHECK YOUR INPUT", "ERROR", JOptionPane.ERROR_MESSAGE);
                                    break;
                                }
                            }
                            //System.out.println(count);
                            if (count == 9) {// if count = 9 that means its valid sudoku
                                //  System.out.println("third");
                                Solve solve = new Solve(sudoku_data);//send the array sudoku_data to solve it
                                sudoku_data = solve.Solve();// the returned sudoku
                                if (Solve.Solve_flag == 1) {
                                    Timer timer_name = new Timer();// the delay of the output effect to show sudoku in the serial elements
                                    TimerTask task = new TimerTask() {
                                        int i = 0;
                                        int j = 0;

                                        public void run() {

                                            cell[i][j].setText(sudoku_data[i][j]);
                                            j++;
                                            if (j < 9) {
                                            } else {
                                                i++;
                                                j = 0;
                                            }

                                            if (i <= 8 && j <= 8) {

                                            } else {
                                                timer_name.cancel();
                                            }
                                        }
                                    };
                                    timer_name.scheduleAtFixedRate(task, 50, 50);

                                    count = 0;
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "CHECK YOUR INPUT", "ERROR", JOptionPane.ERROR_MESSAGE);

                        }

                        // solve.print();
                    }

                }
        );
        btn3.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e
                    ) {
                        System.exit(0);
                    }
                }
        );
        btn2.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e
                    ) {
                        for (int i = 0; i < 9; i++) {
                            for (int j = 0; j < 9; j++) {
                                cell[i][j].setText("");
                            }
                        }
                    }
                }
        );

        for (int counter1 = 0;
                counter1 < 9; counter1++) {
            for (int counter2 = 0; counter2 < 9; counter2++) {
                int i = counter1, j = counter2;
                cell[i][j].addKeyListener(new KeyListener() {

                    @Override
                    public void keyTyped(KeyEvent e) {// validation 
                        String pre_typed_char;

                        char typed_char = e.getKeyChar();

                        String compare = Character.toString(typed_char);
                        Pattern pat = Pattern.compile("[1-9]");
                        Matcher m = pat.matcher(compare);

                        if (m.find()) {
                            pre_typed_char = cell[focused_x][focused_y].getText();
                            if (pre_typed_char.length() == 1) {
                                cell[focused_x][focused_y].setText(Character.toString(typed_char));
                                e.consume();
                            }
                        } else {
                            e.consume();
                        }

                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                });
            }
        }

        btn1.addMouseListener(
                new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e
                    ) {
                    }

                    @Override
                    public void mouseEntered(MouseEvent e
                    ) {
                        btn1.setForeground(Color.WHITE);
                        btn1.setBackground(new Color(gui.text_color1, gui.text_color2, gui.text_color3));

                    }

                    @Override
                    public void mouseExited(MouseEvent e
                    ) {
                        btn1.setBackground(Color.WHITE);
                        btn1.setForeground(new Color(gui.text_color1, gui.text_color2, gui.text_color3));
                    }

                    @Override
                    public void mousePressed(MouseEvent e
                    ) {
                    }

                    @Override
                    public void mouseReleased(MouseEvent e
                    ) {
                    }
                });
        btn2.addMouseListener(
                new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e
                    ) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e
                    ) {
                        btn2.setForeground(Color.WHITE);
                        btn2.setBackground(new Color(gui.text_color1, gui.text_color2, gui.text_color3));

                    }

                    @Override
                    public void mouseExited(MouseEvent e
                    ) {
                        btn2.setBackground(Color.WHITE);
                        btn2.setForeground(new Color(gui.text_color1, gui.text_color2, gui.text_color3));
                    }

                    @Override
                    public void mousePressed(MouseEvent e
                    ) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e
                    ) {
                    }
                });

        btn3.addMouseListener(
                new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e
                    ) {
                    }

                    @Override
                    public void mouseExited(MouseEvent e
                    ) {
                        btn3.setForeground(Color.WHITE);
                        btn3.setBackground(new Color(gui.text_color1, gui.text_color2, gui.text_color3));
                    }

                    @Override
                    public void mouseEntered(MouseEvent e
                    ) {
                        btn3.setBackground(Color.WHITE);
                        btn3.setForeground(new Color(gui.text_color1, gui.text_color2, gui.text_color3));
                    }

                    @Override
                    public void mousePressed(MouseEvent e
                    ) {
                    }

                    @Override
                    public void mouseReleased(MouseEvent e
                    ) {
                    }
                }
        );
        back_btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                GUI frame = new GUI();
                frame.setVisible(true);
                setVisible(false);

            }
        });
    }
}
