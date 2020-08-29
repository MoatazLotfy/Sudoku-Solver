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
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class scr2 extends JFrame {

    static int valid = 0;
    JTable table;

    private JButton btn1;
    private JButton btn2;
    private JButton btn3;
    private JLabel background;
    private JPanel panel;
    private JLabel row1;
    private JLabel row2;
    private JLabel col1;
    private JLabel col2;
    private JButton back_btn;
    private final String[][] data;
    private JTextField[][] cell;
    private JTextField acell;
    public static String[][] array_of_data = new String[9][9];
    int focused_x, focused_y;

    // this gui for checking the sudoku
    public scr2() {
        super("enter su");
        GUI gui = new GUI();
        this.data = new String[][]{{"5", "2", "9", "6", "4", "8", "3", "7", "1"},//sudoku solved 
        {"3", "8", "1", "7", "9", "2", "4", "5", "6"},
        {"7", "4", "6", "5", "3", "1", "8", "9", "2"},
        {"2", "5", "7", "1", "8", "3", "6", "4", "9"},
        {"8", "6", "3", "9", "7", "4", "1", "2", "5"},
        {"9", "1", "4", "2", "5", "6", "7", "3", "8"},
        {"4", "3", "2", "8", "1", "9", "5", "6", "7"},
        {"1", "9", "5", "3", "6", "7", "2", "8", "4"},
        {"6", "7", "8", "4", "2", "5", "9", "1", "3"}};
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) ((dimension.getWidth() - getWidth()) / 2);
        int height = (int) ((dimension.getHeight() - getHeight()) / 3);
        setLocation(width, height);
        panel = new JPanel();
        panel.setLayout(null);

        add(panel);
        panel.setBounds(0, 0, 700, 600);
        btn3 = new JButton("X\n");

        String[] columns = new String[]{
            "1", "2", "3", "4", "5", "6", "7", "8", "9"
        };
        cell = new JTextField[9][9];
        int x = 1;
        int innerbox_row_position = 50;
        int innerbox_col_position = 147;
        for (int i = 0; i < 9; i++) {
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

                cell[i][j].setText(data[i][j]);
                innerbox_col_position += 44;
            }
            innerbox_row_position += 44;

        }

        for (int ere = 0; ere < 9; ere++) {
            for (int rer = 0; rer < 9; rer++) {
                int y = ere;
                int o = rer;
                cell[ere][rer].addFocusListener(new FocusListener() {// to color the the focused cell

                    @Override
                    public void focusGained(FocusEvent e) {
                        cell[y][o].setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(117, 176, 174)));
                        for (int j = 0; j < 9; j++) {
                            cell[y][j].setBackground(new Color(215, 240, 239));
                            cell[j][o].setBackground(new Color(215, 240, 239));
                        }
                        focused_x = y;
                        focused_y = o;
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
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

        background.setIcon(new ImageIcon(gui.Background_image));
        row1.setIcon(new ImageIcon("row.png"));
        row2.setIcon(new ImageIcon("row.png"));
        col1.setIcon(new ImageIcon("0001.png"));
        col2.setIcon(new ImageIcon("0001.png"));

        panel.add(row1);
        panel.add(row2);
        panel.add(col1);
        panel.add(col2);
        //panel.add(table);

        panel.add(background);
        btn1 = new JButton("<html><h2>Check Solution</h2></html>");
        btn2 = new JButton("<html><h2>Reset</h2></html>");
        back_btn = new JButton("<");
        btn1.setFont(new Font("Bradley Hand ITC", Font.BOLD + Font.ITALIC, 90));
        btn2.setFont(new Font("Bradley Hand ITC", Font.BOLD + Font.ITALIC, 90));
        back_btn.setFont(new Font("Bradley Hand ITC", Font.BOLD, 30));
        btn1.setBorder(null);
        btn2.setBorder(null);
        back_btn.setBorder(null);
        btn1.setBackground(Color.WHITE);
        btn1.setForeground(new Color(gui.text_color1, gui.text_color2, gui.text_color3));
        btn2.setBackground(Color.WHITE);
        btn2.setForeground(new Color(gui.text_color1, gui.text_color2, gui.text_color3));
        btn3.setBackground(new Color(gui.text_color1, gui.text_color2, gui.text_color3));
        btn3.setForeground(Color.WHITE);
        btn3.setFont(new Font("Arial", Font.BOLD, 25));
        btn3.setBorder(null);
        back_btn.setBackground(Color.WHITE);
        back_btn.setForeground(new Color(gui.text_color1, gui.text_color2, gui.text_color3));

        panel.add(btn1);
        panel.add(btn2);
        panel.add(btn3);
        panel.add(background);
        panel.add(back_btn);
        btn1.setBounds(100, 480, 200, 80);
        btn2.setBounds(400, 480, 200, 80);
        btn3.setBounds(650, 30, 20, 20);
        row1.setBounds(147, 180, 403, 10);
        row2.setBounds(147, 315, 403, 10);
        col1.setBounds(185, 50, 100, 403);
        col2.setBounds(320, 50, 100, 403);
        back_btn.setBounds(10, 10, 40, 40);
        background.setBounds(0, 0, 700, 600);
        btn1.addActionListener((ActionEvent e) -> { // the button of check solution

            valid++;
            // System.out.println(c);
            if (valid < 15) {
                int start_r = 0, start_c = 0;
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        array_of_data[i][j] = cell[i][j].getText();
                    }
                }
                int flag = 0;
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (array_of_data[i][j].equals("")) { //if there are empty cell
                            JOptionPane.showMessageDialog(null, "Empty Cell", "ERROR", JOptionPane.ERROR_MESSAGE);
                            flag = 1;
                            break;
                        }
                    }
                    if (flag == 1) {
                        break;
                    }
                }

                /*3 thread created (row,column and Box)*/
                Thread thread_column = new Thread(new Column_Thread(array_of_data));
                Thread thread_row = new Thread(new Row_Thread(array_of_data));
                Thread[] Box_Threads = new Thread[9];

                for (int i = 0; i < 9; i++) {
                    Box_Threads[i] = new Thread(new Box_Thread(array_of_data, i, start_r, start_c));
                    Box_Threads[i].start();
                    start_c = start_c + 3;
                    if (start_c == 9) {
                        start_c = 0;
                        start_r = start_r + 3;
                    }
                }
                thread_column.start();
                thread_row.start();
                Check c = new Check();
                // join the all threads
                try {
                    thread_column.join();
                    thread_row.join();
                    for (int i = 0; i < 9; i++) {
                        Box_Threads[i].join();
                    }

                } catch (InterruptedException ex) {
                }
                // the code below to color the wrong element
                c.print_error();
                int flag2 = 0;
                for (int m = 0; m < c.ie; m++) {
                    if (!c.A[m].equals("")) {
                        flag2 = 1;
                        break;
                    }
                }
                for (int y = 0; y < 9; y++) {
                    for (int n = 0; n < 9; n++) {
                        cell[y][n].setForeground(Color.black);
                    }
                }
                if (flag2 == 0 && flag == 0) {
                    JOptionPane.showMessageDialog(null, "Valid Sudoku", "OK", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Sudoku", "ERROR", JOptionPane.ERROR_MESSAGE);
                    for (int i = 0; i < 9; i++) {
                        for (int j = 0; j < 9; j++) {
                            String s = i + "," + j;
                            for (int k = 0; k < c.ie; k++) {
                                if (s.equals(c.A[k])) {
                                    cell[i][j].setForeground(Color.RED);
                                }
                            }
                        }
                    }
                }
                c.ie = 0;
                c.Repeated.clear();
                System.out.println("Done");
            } else {
                valid = 0;
                JOptionPane.showMessageDialog(null, "You Have many times Clicked Check Without Changes", "ERROR", JOptionPane.ERROR_MESSAGE);
            }

        });
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        cell[i][j].setText("");
                        cell[i][j].setForeground(Color.black);
                    }
                }
                Check c = new Check();
                c.ie = 0;
            }
        });
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        for (int counter1 = 0; counter1 < 9; counter1++) {
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
        btn1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btn1.setForeground(Color.WHITE);
                btn1.setBackground(new Color(gui.text_color1, gui.text_color2, gui.text_color3));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn1.setBackground(Color.WHITE);
                btn1.setForeground(new Color(gui.text_color1, gui.text_color2, gui.text_color3));
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }
        });
        btn2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btn2.setForeground(Color.WHITE);
                btn2.setBackground(new Color(gui.text_color1, gui.text_color2, gui.text_color3));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn2.setBackground(Color.WHITE);
                btn2.setForeground(new Color(gui.text_color1, gui.text_color2, gui.text_color3));
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }
        });

        btn3.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn3.setForeground(Color.WHITE);
                btn3.setBackground(new Color(gui.text_color1, gui.text_color2, gui.text_color3));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btn3.setBackground(Color.WHITE);
                btn3.setForeground(new Color(gui.text_color1, gui.text_color2, gui.text_color3));
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }
        });
        back_btn.addActionListener(new ActionListener() {// back button

            @Override
            public void actionPerformed(ActionEvent ae) {

                GUI frame = new GUI();
                frame.setVisible(true);
                setVisible(false);

            }
        });
    }
}
