package sudoku;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

public class GUI extends JFrame {

    private JPanel panel;
    private JLabel lab1;
    private JLabel background;
    private JButton btn1;
    private JButton btn2;
    private JButton btn3;
    private JButton ground1;
    private JButton ground2;
    private JButton ground3;

    public static String Background_image = "sDk3wK.jpg";
    public static int text_color1 = 139;
    public static int text_color2 = 69;
    public static int text_color3 = 16;
    public static int cell_color1 = 229;
    public static int cell_color2 = 212;
    public static int cell_color3 = 161;

    //the main gui  
    public GUI() {
        super("title");
        setUndecorated(true);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int Width = (int) ((dimension.getWidth() - getWidth()) / 4.5);
        int Height = (int) ((dimension.getHeight() - getHeight()) / 13);
        setLocation(Width, Height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 600);
        panel = new JPanel();
        panel.setLayout(null);

        add(panel);
        validate();
        panel.setBounds(0, 0, 700, 600);

        btn1 = new JButton("<html><h2>Enter Sudoku</h2></html>");
        btn2 = new JButton("<html><h2>Check Solve !</h2></html>");
        btn3 = new JButton("<html><h1>X</h1></html>");
        lab1 = new JLabel("Sudoku");
        ground1 = new JButton("");
        ground2 = new JButton("");
        ground3 = new JButton("");
        background = new JLabel();
        background.setIcon(new ImageIcon(Background_image));

        lab1.setFont(new Font("monospaced", Font.BOLD + Font.ITALIC, 90));
        lab1.setForeground(Color.WHITE);
        btn1.setForeground(new Color(text_color1, text_color2, text_color3));
        btn1.setFont(new Font("Bradley Hand ITC", Font.BOLD + Font.ITALIC, 90));
        btn1.setBackground(Color.WHITE);
        btn2.setForeground(new Color(text_color1, text_color2, text_color3));
        btn2.setFont(new Font("Bradley Hand ITC", Font.BOLD + Font.ITALIC, 90));
        btn2.setBackground(Color.WHITE);
        btn3.setBackground(new Color(text_color1, text_color2, text_color3));
        btn3.setForeground(Color.white);
        ground1.setBackground(new Color(139, 69, 16));
        ground2.setBackground(Color.GRAY);
        ground3.setBackground(Color.BLACK);

        btn1.setBorder(null);
        btn2.setBorder(null);
        btn3.setBorder(null);
        ground1.setBorder(new LineBorder(Color.WHITE));
        ground2.setBorder(new LineBorder(Color.WHITE));
        ground3.setBorder(new LineBorder(Color.WHITE));

        panel.add(lab1);
        panel.add(btn1);
        panel.add(btn2);
        panel.add(btn3);
        panel.add(ground1);
        panel.add(ground2);
        panel.add(ground3);
        panel.add(background);

        background.setBounds(0, 0, 700, 600);
        lab1.setBounds(180, 90, 350, 100);

        btn1.setBounds(100, 400, 200, 80);
        btn2.setBounds(400, 400, 200, 80);
        btn3.setBounds(650, 30, 20, 20);
        ground1.setBounds(10, 550, 35, 35);
        ground2.setBounds(55, 550, 35, 35);
        ground3.setBounds(100, 550, 35, 35);
        lab1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LevelScreen frame;
                try {
                    frame = new LevelScreen();
                    frame.setVisible(true);
                } catch (IOException ex) {
                }
                setVisible(false);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                lab1.setText("PLAY!");
                lab1.setBounds(220, 90, 350, 100);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                lab1.setText("Sudoku");
                lab1.setBounds(180, 90, 350, 100);

            }
        });
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new scr1().setVisible(true); // Main Form to show after the Login Form..
            }
        });
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new scr2().setVisible(true); // Main Form to show after the Login Form..
            }
        });
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btn1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btn1.setForeground(Color.WHITE);
                btn1.setBackground(new Color(text_color1, text_color2, text_color3));//for the selected background
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn1.setBackground(Color.WHITE);
                btn1.setForeground(new Color(text_color1, text_color2, text_color3));//for the selected background
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
                btn2.setBackground(new Color(text_color1, text_color2, text_color3));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn2.setBackground(Color.WHITE);
                btn2.setForeground(new Color(text_color1, text_color2, text_color3));
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
                btn3.setBackground(new Color(text_color1, text_color2, text_color3));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btn3.setBackground(Color.WHITE);
                btn3.setForeground(new Color(text_color1, text_color2, text_color3));
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }
        });
        ground1.addActionListener(new ActionListener() {//design of the first background

            @Override
            public void actionPerformed(ActionEvent ae) {
                text_color1 = 139;
                text_color2 = 69;
                text_color3 = 16;
                btn1.setForeground(new Color(text_color1, text_color2, text_color3));
                btn1.setFont(new Font("Bradley Hand ITC", Font.BOLD + Font.ITALIC, 90));
                btn1.setBackground(Color.WHITE);
                btn2.setForeground(new Color(text_color1, text_color2, text_color3));
                btn2.setFont(new Font("Bradley Hand ITC", Font.BOLD + Font.ITALIC, 90));
                btn2.setBackground(Color.WHITE);
                btn3.setBackground(new Color(text_color1, text_color2, text_color3));
                btn3.setForeground(Color.white);
                Background_image = "sDk3wK.jpg";
                background.setIcon(new ImageIcon(Background_image));
                panel.add(background);
                cell_color1 = 229;
                cell_color2 = 212;
                cell_color3 = 161;
            }
        });
        ground2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {//design of the second background
                text_color1 = 69;
                text_color2 = 90;
                text_color3 = 100;
                btn1.setForeground(new Color(text_color1, text_color2, text_color3));
                btn1.setFont(new Font("Bradley Hand ITC", Font.BOLD + Font.ITALIC, 90));
                btn1.setBackground(Color.WHITE);
                btn2.setForeground(new Color(text_color1, text_color2, text_color3));
                btn2.setFont(new Font("Bradley Hand ITC", Font.BOLD + Font.ITALIC, 90));
                btn2.setBackground(Color.WHITE);
                btn3.setBackground(new Color(text_color1, text_color2, text_color3));
                btn3.setForeground(Color.white);

                Background_image = "Grey1.jpg";
                background.setIcon(new ImageIcon(Background_image));
                panel.add(background);
                cell_color1 = 144;
                cell_color2 = 164;
                cell_color3 = 174;
            }
        });
        ground3.addActionListener(new ActionListener() {//design of the third background

            @Override
            public void actionPerformed(ActionEvent ae) {
                text_color1 = 0;
                text_color2 = 0;
                text_color3 = 0;
                btn1.setForeground(new Color(text_color1, text_color2, text_color3));
                btn1.setFont(new Font("Bradley Hand ITC", Font.BOLD + Font.ITALIC, 90));
                btn1.setBackground(Color.WHITE);
                btn2.setForeground(new Color(text_color1, text_color2, text_color3));
                btn2.setFont(new Font("Bradley Hand ITC", Font.BOLD + Font.ITALIC, 90));
                btn2.setBackground(Color.WHITE);
                btn3.setBackground(new Color(text_color1, text_color2, text_color3));
                btn3.setForeground(Color.white);

                Background_image = "black.jpg";
                background.setIcon(new ImageIcon(Background_image));
                panel.add(background);
                cell_color1 = 189;
                cell_color2 = 189;
                cell_color3 = 189;
            }
        });

    }
}
