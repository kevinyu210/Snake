package com.company;

import java.awt.GridLayout;
import javax.swing.JFrame;


/**
 * Created by kevin on 1/21/2017.
 */
public class Frame extends JFrame {
    Screen s;

    private int square_size = 25;
    private int width = 22;
//    private int length = 1260;
    private int length = 22;



    public Frame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize((width+2) * square_size, (length+2) * square_size + 10);
        setResizable(true);
        setTitle("Graphics");

        init();
    }

    public void init() {
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1, 1, 0, 0));
        s = new Screen(width, length, square_size);
//        getContentPane().add(s);
        add(s);
        pack();


        setVisible(true);

    }

    public static void main(String[] args) {
        new Frame();
    }
}
