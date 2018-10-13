package com.company;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Point;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;
import javax.swing.*;

/**
 * Created by kevin on 1/21/2017.
 */

/**
 * Screen is
 */
public class Screen extends JPanel implements ActionListener {
    private boolean gameover;
    private Random rand = new Random();
    //represent snake as a linked list of coordinates.
    private LinkedList snake;
    private Point head;
    //snake food
    private Point food;
    //current direction of snake
    private int currentDir;
    //direction that input will manipulate. Represented by a point
    private Point dir;
    //directional code.
    private int dirCode;
    //39 is right, 37 is left, 38 is up, 40 is down
    private int right = 39;
    private int left = 37;
    private int up = 38;
    private int down = 40;

    private Timer timer;
    private final int delay = 100;
    //dimensions of the game.
    private int width;
    //height
    private int length;
    private int square_size;
    //gap size is the gap between squares. So we'll see
    //gaps of size gap_size*2 because space between two squares
    private int gap_size;
    //boolean to check if snake is eating itself
    private boolean self_cannibalize = false;

    public Screen(int x, int y, int squareSize) {
        width = x;
        length = y;
        square_size = squareSize;
        gap_size = square_size / 20;
        setPreferredSize(new Dimension((width + 2) * squareSize, (length + 2) * squareSize));
        initScreen();
    }

    public Screen() {
        initScreen();
    }

    //initialize screen.
    public void initScreen() {
        JLabel words = new JLabel("WHAT");
        add(words);
        addKeyListener(new Adapter());
        setFocusable(true);
        gameover = false;
        timer = new Timer(delay, this);
        snake = new LinkedList();
        //starts at the center.
        head = new Point(width / 2, length / 2);
        snake.add(head);
        //start direction as up
        dirCode = up;
        currentDir = up;
        dir = new Point(0, 0);
        //nextint Range of values for any point is...(1:width)
        food = new Point((rand.nextInt(width) + 1), (rand.nextInt(length) + 1));
        //timer is like refresh rate. Snake moves according to timer
        //if timer doesnt stop on each game, snake moves faster.
        timer.start();
    }

    private class Adapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            dirCode = e.getKeyCode();
            self_cannibalize = false;
            //if gameover is true, new game on key press.
            if (gameover == true) {
                initScreen();
            }
            //positive number == positive directions
            //right if not currently going left
            else if (dirCode == right && (currentDir != left || snake.size() == 1)) {
                dir.setLocation(1, 0);
                //left if not currently going right
            } else if (dirCode == left && (currentDir != right || snake.size() == 1)) {
                dir.setLocation(-1, 0);
                //up if not currently going down
            } else if (dirCode == up && (currentDir != down || snake.size() == 1)) {
                dir.setLocation(0, -1);
                //down if not currently going up
            } else if (dirCode == down && (currentDir != up || snake.size() == 1)) {
                dir.setLocation(0, 1);
            }
            //if none... then its going in the reverse direction. Can't do that.
            else {
                self_cannibalize = true;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            //Nothing on key release
        }
    }

    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, (width + 2) * square_size, (length + 2) * square_size);
        g.setColor(Color.white);
        g.drawRect(square_size, square_size, width * square_size, length * square_size);
        //print gameover
        if (gameover == true) {
            Font newFont = new Font("Times new Roman", Font.PLAIN, square_size * 2);
            g.setFont(newFont);
            FontMetrics metrics = g.getFontMetrics(newFont);
            String message = "GAME OVER";
            g.drawString(message, ((width + 2) * square_size - metrics.stringWidth(message)) / 2,
                    ((length + 2) * square_size - metrics.getHeight()) / 2);
        }
        //paint the snake... with gaps.
        ListIterator<Point> body = snake.listIterator();
        while (body.hasNext()) {
            Point temp = body.next();
            //(x-coord,y-coord,width,height)
            g.fillRect((int) temp.getX() * square_size + gap_size,
                    (int) temp.getY() * square_size + gap_size,
                    square_size - 2 * gap_size,
                    square_size - 2 * gap_size);
        }
        //food
        g.fillRect((int) food.getX() * square_size + gap_size,
                (int) food.getY() * square_size + gap_size,
                square_size - 2 * gap_size,
                square_size - 2 * gap_size);

//        Keeping score:
//        Font newFont = new Font("Times new Roman", Font.PLAIN, square_size);
//        g.setFont(newFont);
//        FontMetrics metrics = g.getFontMetrics(newFont);
//        String message = "GAME OVER";
//        String score = Integer.toString(snake.size());
//        g.drawString(score, metrics.stringWidth(score),
//                metrics.getHeight());

    }

    //this is called every 10 ms. Shifts the snake at this speed.
    @Override
    public void actionPerformed(ActionEvent e) {
        //can change direction but not eat yourself
        if (!self_cannibalize) {
            currentDir = dirCode;
        }
        //if dir ==0 then it hasnt started moving yet
        if (dir.getY() == 0 && dir.getX() == 0) {
            return;
        }
        //get the next point which is the head shifted in dir's direction.
        Point next = new Point((int) head.getX() + (int) dir.getX(), (int) head.getY() + (int) dir.getY());
        //test death of snake
        if (next.getX() < 1 || next.getX() > width || next.getY() < 1 || next.getY() > length || snake.contains(next)) {
            gameover = true;
            repaint();
            timer.stop();
            return;
        }
        head.setLocation(next.getX(), next.getY());
        snake.addFirst(next);
        //it got the food
        if (snake.contains(food)) {
            food = new Point((rand.nextInt(width) + 1), (rand.nextInt(length) + 1));
        }
        else {
            //removes tail of snake as snake moves to simulate movement
            snake.removeLast();
        }
        repaint();
    }

}
