package com.company;

import java.awt.GridLayout;
import javax.swing.JFrame;


/**
 * Created by kevin on 1/21/2017.
 */
public class Frame extends JFrame {
    Screen s;
    private static int square_size = 25;
    private static int width = 22;
    private static int length = 22;

//    Default constructor
    public Frame() {
        this(square_size, width, length);
    }

//    secondary constructor that takes in square size, and dimensions of screen
    public Frame(int sq, int w, int l) {
        square_size = sq;
        width = w;
        length = l;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize((width+2) * square_size, (length+2) * square_size + 10);
        setResizable(true);
        setTitle("Snake");
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
