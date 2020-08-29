package sudoku;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

public class PlayScreen extends JFrame {

    //the gui of the game
    private JButton btn1;
    private JButton btn2;
    private JButton btn3;

    private JButton hint_btn;
    private JLabel background;
    private JPanel panel;
    private JLabel row1;
    private JLabel row2;
    private JLabel col1;
    private JLabel col2;
    private JTextField[][] cell;
    private JLabel timer_label;
    String selected_cell = null;
    int second = 0;
    int minute = 0;
    int hint_counter = 4;
    public static String sol_mat[][] = new String[9][9];
    int focused_x, focused_y;

    public PlayScreen() {
        Game g1 = new Game();
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
                innerbox_col_position += 44;
            }
            innerbox_row_position += 44;

        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (g1.mat[i][j].equals("0")) {
                    g1.mat[i][j] = "";
                } else {
                    if (g1.mat[i][j].equals(g1.sodo_Game[i][j])) {
                        cell[i][j].setEditable(false);
                    }
                }
                cell[i][j].setText(g1.mat[i][j]);
            }
        }
//the two loops for every cell ..have focused to colored it or if i want use a hint to detect which cell must solved
        for (int ere = 0; ere < 9; ere++) {
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
                        hint_btn.addActionListener(new ActionListener() {// code of the hint button

                            @Override
                            public void actionPerformed(ActionEvent ae) {
                                BufferedReader br;
                                try {
                                    br = new BufferedReader(new FileReader("hint.txt"));
                                    String check = br.readLine();
                                    br.close();

                                    if (Integer.parseInt(check) > 0) {
                                        hint_counter = Integer.parseInt(check);
                                        Game g = new Game();
                                        Solve sol = new Solve(g.sodo_Game);
                                        g.sodo_Game = sol.Solve();
                                        cell[y][o].setText(g.sodo_Game[y][o]);
                                        hint_counter--;

                                        BufferedWriter bw;
                                        try {
                                            bw = new BufferedWriter(new FileWriter("hint.txt"));
                                            bw.write(Integer.toString(hint_counter));
                                            bw.close();
                                        } catch (IOException ex) {

                                        }

                                    }
                                } catch (FileNotFoundException ex) {
                                } catch (IOException ex) {
                                }
                            }
                        });
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
        hint_btn = new JButton();
        timer_label = new JLabel();

        background.setIcon(new ImageIcon(gui.Background_image));
        hint_btn.setIcon(new ImageIcon("bbbb.JPG"));
        row1.setIcon(new ImageIcon("row.png"));
        row2.setIcon(new ImageIcon("row.png"));
        col1.setIcon(new ImageIcon("0001.png"));
        col2.setIcon(new ImageIcon("0001.png"));

        panel.add(row1);
        panel.add(row2);
        panel.add(col1);
        panel.add(col2);
        panel.add(timer_label);
        panel.add(hint_btn);
        panel.add(background);
        btn1 = new JButton("<html><h2>Submit</h2></html>");
        btn2 = new JButton("<html><h2>High Score</h2></html>");
        btn1.setFont(new Font("Bradley Hand ITC", Font.BOLD + Font.ITALIC, 90));

        btn2.setFont(new Font("Bradley Hand ITC", Font.BOLD + Font.ITALIC, 90));

        btn1.setBorder(null);
        btn2.setBorder(null);
        btn1.setBackground(Color.WHITE);
        btn1.setForeground(new Color(gui.text_color1, gui.text_color2, gui.text_color3));
        btn2.setBackground(Color.WHITE);
        btn2.setForeground(new Color(gui.text_color1, gui.text_color2, gui.text_color3));
        btn3.setBackground(new Color(gui.text_color1, gui.text_color2, gui.text_color3));
        btn3.setForeground(Color.WHITE);

        btn3.setFont(new Font("Arial", Font.BOLD, 25));
        btn3.setBorder(null);

        panel.add(btn1);
        panel.add(btn2);
        panel.add(btn3);

        panel.add(background);

        btn1.setBounds(50, 480, 175, 60);
        btn2.setBounds(470, 480, 175, 60);
        btn3.setBounds(650, 30, 20, 20);

        row1.setBounds(147, 180, 403, 10);
        row2.setBounds(147, 315, 403, 10);
        col1.setBounds(185, 50, 100, 403);
        col2.setBounds(320, 50, 100, 403);
        background.setBounds(0, 0, 700, 600);
        hint_btn.setBounds(600, 250, 58, 61);
        timer_label.setForeground(Color.WHITE);
        timer_label.setBounds(300, 480, 175, 60);
        timer_label.setFont(new Font("Arial", Font.BOLD, 60));
        Timer timer_name = new Timer();//the timer on the game screen
        TimerTask task = new TimerTask() {

            public void run() {
                second++;
                if (second == 59) {
                    second = 0;
                    minute++;
                }
                timer_label.setText(minute + ":" + Integer.toString(second));

            }
        };
        timer_name.scheduleAtFixedRate(task, 1000, 1000);

        btn3.addActionListener(new ActionListener() { // this action if i want to save my game and resume it later on 
            @Override
            public void actionPerformed(ActionEvent e) {

                String temp = "";

                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (cell[i][j].getText().equals("")) {
                            temp += Integer.toString(0);
                        }
                        temp += cell[i][j].getText();

                    }
                }
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (g1.sodo_Game[i][j].equals("")) {
                            temp += Integer.toString(0);
                        }
                        temp += g1.sodo_Game[i][j];

                    }
                }
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter("save.txt"));
                    bw.write(temp);
                    bw.close();
                } catch (FileNotFoundException ex) {
                } catch (IOException ex) {
                }
                makesure frame = new makesure();// this the frame of save - discard -cancel

            }
        });
        for (int counter1 = 0; counter1 < 9; counter1++) {// this action for validation
            for (int counter2 = 0; counter2 < 9; counter2++) {
                int i = counter1, j = counter2;
                cell[i][j].addKeyListener(new KeyListener() {
                    /*
                    Here in our sudoku you can't write charcter or 0 only number from 
                    [1-9] and the code won't show you what you write if and only if match pattern [1-9]
                    */
                    @Override
                    public void keyTyped(KeyEvent e) {
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
        btn1.addMouseListener(new MouseListener() {// submit button
            @Override
            public void mouseClicked(MouseEvent e) {
                Game g = new Game();
                Solve sol = new Solve(g.sodo_Game);
                g.sodo_Game = sol.Solve();
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        sol_mat[i][j] = cell[i][j].getText();
                    }
                }
                int solving_status = 0;
                for (int i = 0; i < 9; i++) {  // for colored wrong cells 
                    for (int j = 0; j < 9; j++) {
                        System.out.print(g.mat[i][j]);
                        if (!sol_mat[i][j].equals(g.sodo_Game[i][j])) {
                            cell[i][j].setForeground(Color.red);
                            solving_status = 1;
                        }

                    }
                    System.out.println("");
                }
                if (solving_status == 0) {// flag for check if the player finish without any wrong
                    try {// if ture saving his time if less than the previous high score
                        minute = minute * 60;
                        int Time = minute + second;
                        String t = g.getHighScore();
                        if (Time < Integer.parseInt(t)) {
                            g.setHighScore(Time);

                        }
                        minute = minute / 60;
                    } catch (Exception ex) {
                    }
                    JOptionPane.showMessageDialog(null, "You succeeded", "Notification", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                }

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

        btn2.addActionListener(new ActionListener() {// button high score only to get high score from file
            String hscore;

            @Override
            public void actionPerformed(ActionEvent e) {
                Game g1 = new Game();
                try {
                    hscore = g1.getHighScore();
                } catch (IOException ex) {
                }
                System.out.println(g1.highscorefile);
                int min = Integer.parseInt(hscore) / 60;
                int sec = Integer.parseInt(hscore) % 60;
                JOptionPane.showMessageDialog(null, min + " Minutes " + sec + " Second ", "Notification", JOptionPane.INFORMATION_MESSAGE);
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

    }
}
