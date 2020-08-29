package sudoku;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//Easy - mediem - hard Gui
public class LevelScreen extends JFrame {

    private JPanel panel;
    private JLabel background;
    private JButton btn1;
    private JButton btn2;
    private JButton btn3;
    private JButton btn4;
    private JButton back_btn;

    public LevelScreen() throws IOException {
        setUndecorated(true);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int a = (int) ((dimension.getWidth() - getWidth()) / 4.5);
        int b = (int) ((dimension.getHeight() - getHeight()) / 13);
        setLocation(a, b);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 600);
        panel = new JPanel();
        panel.setLayout(null);

        add(panel);
        validate();
        panel.setBounds(0, 0, 700, 600);
        background = new JLabel();
        GUI gui = new GUI();
        background.setIcon(new ImageIcon(gui.Background_image));

        btn1 = new JButton("Easy");
        btn2 = new JButton("Normal");
        btn3 = new JButton("Hard");
        btn4 = new JButton("Resume");
        back_btn = new JButton("<");
        btn1.setBorder(null);
        btn2.setBorder(null);
        btn3.setBorder(null);
        btn4.setBorder(null);
        back_btn.setBorder(null);

        btn1.setBackground(Color.WHITE);
        btn2.setBackground(Color.WHITE);
        btn3.setBackground(Color.WHITE);
        btn4.setBackground(Color.WHITE);
        back_btn.setBackground(Color.WHITE);

        btn1.setFont(new Font("Arial", Font.BOLD, 50));
        btn2.setFont(new Font("Arial", Font.BOLD, 50));
        btn3.setFont(new Font("Arial", Font.BOLD, 50));
        btn4.setFont(new Font("Arial", Font.BOLD, 50));
        back_btn.setFont(new Font("Bradley Hand ITC", Font.BOLD, 30));

        btn1.setForeground(new Color(gui.text_color1, gui.text_color2, gui.text_color3));
        btn2.setForeground(new Color(gui.text_color1, gui.text_color2, gui.text_color3));
        btn3.setForeground(new Color(gui.text_color1, gui.text_color2, gui.text_color3));
        btn4.setForeground(new Color(gui.text_color1, gui.text_color2, gui.text_color3));
        back_btn.setForeground(new Color(gui.text_color1, gui.text_color2, gui.text_color3));

        panel.add(btn1);
        panel.add(btn2);
        panel.add(btn3);
        panel.add(btn4);
        panel.add(back_btn);

        try {
            BufferedReader br = new BufferedReader(new FileReader("save.txt"));
            String check = br.readLine();
            if (check.equals("empty")) {
                btn4.setEnabled(false);
            }
            br.close();
        } catch (FileNotFoundException ex) {
        }

        btn1.setBounds(220, 185, 250, 80);
        btn2.setBounds(220, 310, 250, 80);
        btn3.setBounds(220, 435, 250, 80);
        btn4.setBounds(220, 60, 250, 80);
        back_btn.setBounds(10, 10, 40, 40);
        panel.add(background);
        background.setBounds(0, 0, 700, 600);

        btn1.addActionListener(new ActionListener() {//easy button
            @Override
            public void actionPerformed(ActionEvent e) {
                Game g1 = new Game();
                try {
                    g1.create_game(28);
                } catch (IOException ex) {

                }
                PlayScreen frame = new PlayScreen();
                frame.setVisible(true);
                setVisible(false);
            }
        });
        btn2.addActionListener(new ActionListener() {//normal button
            @Override
            public void actionPerformed(ActionEvent e) {
                Game g1 = new Game();
                try {
                    g1.create_game(44);
                } catch (IOException ex) {

                }
                PlayScreen frame = new PlayScreen();
                frame.setVisible(true);
                setVisible(false);
            }
        });
        btn3.addActionListener(new ActionListener() {// hard button
            @Override
            public void actionPerformed(ActionEvent e) {
                Game g1 = new Game();
                try {
                    g1.create_game(58);
                } catch (IOException ex) {

                }
                PlayScreen frame = new PlayScreen();
                frame.setVisible(true);
                setVisible(false);
            }
        });
        btn4.addActionListener(new ActionListener() {// resume button
            @Override
            public void actionPerformed(ActionEvent e) {
                Game g1 = new Game();
                try {
                    g1.Resume();
                } catch (IOException ex) {

                }
                PlayScreen frame = new PlayScreen();
                frame.setVisible(true);
                setVisible(false);
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
