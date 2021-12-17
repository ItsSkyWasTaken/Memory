package net.itsskywastaken.simon;

import net.itsskywastaken.simon.display.Window;
import net.itsskywastaken.simon.input.MouseListener;
import net.itsskywastaken.simon.objects.*;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas {
    
    private final Window window;
    private final Handler handler;
    private Thread thread;
    private boolean running = false;
    
    public static final byte WIDTH_MIN = 3;
    public static final byte WIDTH_DEFAULT = 3;
    public static final byte WIDTH_MAX = 5;
    public static final byte HEIGHT_MIN = 1;
    public static final byte HEIGHT_DEFAULT = 3;
    public static final byte HEIGHT_MAX = 5;
    public static final short SPEED_MIN = 60;
    public static final short SPEED_DEFAULT = 128;
    public static final short SPEED_MAX = 240;
    public static final Square.LabelType LABEL_DEFAULT = Square.LabelType.COORDINATES;
    
    public static byte width = Game.WIDTH_DEFAULT;
    public static byte height = Game.HEIGHT_DEFAULT;
    public static short speed = Game.SPEED_DEFAULT;
    public static Square.LabelType label = Game.LABEL_DEFAULT;
    
    public static Game.GameState state = Game.GameState.MENU;
    
    public Game() {
        this.handler = new Handler();
        this.addMouseListener(new MouseListener(this.handler));
        this.window = new Window(659, 528, "Memory", this, this.handler);
    
        this.handler.add(new GameMenu());
        this.handler.add(new GameOverScreen());
    
        for(byte square = 1; square <= 25; square++) {
            Square s = new Square(square);
            this.handler.add(s);
            Square.squares.add(s);
        }
        
        this.window.show();
    }
    
    public synchronized void start() {
        this.thread = new Thread(() -> {
            this.requestFocus();
            long lastTime = System.nanoTime();
            double amountOfTicks = 60D;
            double ns = 1.0e9D / amountOfTicks;
            double delta = 0D;
            long timer = System.currentTimeMillis();

            while(this.running) {
                long now = System.nanoTime();
                delta += (now - lastTime) / ns;
                lastTime = now;

                while(delta >= 1D) {
                    this.tick();
                    delta--;
                }

                if(this.running) {
                    this.render();
                }

                if(System.currentTimeMillis() - timer > 1000L) {
                    timer += 1000L;
                }
            }

            this.stop();
        });

        this.thread.start();
        this.running = true;
    }
    
    public synchronized void stop() {
        try {
            this.thread.join();
            this.running = false;
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    private void tick() {
        this.handler.tick();
    }
    
    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.window.width() + 1, this.window.height() + 1);
        this.handler.render(g);
        g.dispose();
        bs.show();
    }

    public static void main(String[] args) {
        new Game();
    }
    
    public enum GameState {
        MENU,
        SEQUENCING,
        WAITING,
        GAME_OVER;
    }
}
