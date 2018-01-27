package com.company;

import java.awt.GridLayout;
import javax.swing.JFrame;


/**
 * Created by kevin on 1/21/2017.
 */
public class Frame extends JFrame {
    Screen s;
    public Frame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200,1260);
        setResizable(false);
        setTitle("Graphics");
        init();
    }
    public void init() {
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1, 1, 0, 0));
        s = new Screen();
        add(s);
        setVisible(true);

    }
    public static void main(String[] args) {
        new Frame();
    }
}
