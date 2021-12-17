package net.itsskywastaken.simon.display;

import net.itsskywastaken.simon.Game;
import net.itsskywastaken.simon.objects.Handler;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Point;

public class Window {
    
    private final JFrame frame;

    private final Game game;
    
    public Window(int width, int height, String title, Game game, Handler handler) {
        this.frame = new JFrame(title);
        this.game = game;
        this.frame.setMinimumSize(new Dimension(width, height));
        this.frame.setPreferredSize(new Dimension(width, height));
        this.frame.setMaximumSize(new Dimension(1927, 1100));
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(true);
        this.frame.setLocationRelativeTo(null);
        this.frame.add(game);
        handler.setWindow(this);
    }
    
    public void show() {
        this.frame.setVisible(true);
        this.game.start();
    }

    public int width() {
        return Math.max(129, this.frame.getWidth() - 19);
    }

    public int height() {
        return Math.max(9, this.frame.getHeight() - 48);
    }

    public Point center() {
        return new Point(this.width() / 2, this.height() / 2);
    }
    
    public int scale(int base) {
        return Math.round((float) Math.ceil(base * Math.min(this.width() / 640F, this.height() / 480F)));
    }
}
