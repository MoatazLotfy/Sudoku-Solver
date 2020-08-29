package sudoku;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*screen oif you clicked on exit symbol "save - discard -cancel"*/
public class makesure extends JFrame{
    private JPanel panel;
    private JLabel lab;
    private JButton btn1;
    private JButton btn2;
    private JButton btn3;
    public makesure(){
        setUndecorated(true);
        setVisible(true);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int Width = (int) ((dimension.getWidth() - getWidth()) / 3.4);
        int Height = (int) ((dimension.getHeight() - getHeight()) / 3.9);
        setLocation(Width, Height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 250);
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(139, 69, 19));
        add(panel);
        validate();
        panel.setBounds(0, 0, 500, 250);
        btn1=new JButton("Save");
        btn2=new JButton("Discard");
        btn3=new JButton("Cancel");
        lab=new JLabel("Do you want to save?");
        btn1.setBackground(Color.WHITE);
        btn2.setBackground(Color.WHITE);
        btn3.setBackground(Color.WHITE);
        lab.setFont(new Font ("Arial",Font.BOLD,25));
        btn1.setForeground(new Color(139, 69, 19));
        btn2.setForeground(new Color(139, 69, 19));
        btn3.setForeground(new Color(139, 69, 19));
        lab.setForeground(Color.WHITE);
        panel.add(btn1);
        panel.add(btn2);
        panel.add(btn3);
        panel.add(lab);
        
        btn1.setFont(new Font("Arial", Font.BOLD, 25));
        btn2.setFont(new Font("Arial", Font.BOLD, 25));
        btn3.setFont(new Font("Arial", Font.BOLD, 25));
        btn1.setBounds(10, 150, 150, 50);
        btn2.setBounds(170, 150, 150, 50);
        btn3.setBounds(330, 150, 150, 50);
        lab.setBounds(100, 50, 450, 50);
        
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BufferedWriter bw=new BufferedWriter(new FileWriter("save.txt"));
                    bw.write("empty");
                    bw.close();
                    System.exit(0);
                } catch (IOException ex) {
                }
            }
        });
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                try {
                    BufferedWriter bw=new BufferedWriter(new FileWriter("save.txt"));
                    bw.write("empty");
                    bw.close();
                } catch (IOException ex) {
                }
            }
        });
    }
    
    
}
