package net.itsskywastaken.simon.objects;

import net.itsskywastaken.simon.Game;

import java.awt.*;
import java.util.*;

public class Square extends GameObject {
    
    public static ArrayList<Square> sequence = new ArrayList<>();
    public static int current = 0;
    
    public static final ArrayList<Square> squares = new ArrayList<>(25);
    public static final Random random = new Random();
    public static final char[] charset = {'a', 'b', 'c', 'd', 'e'};
    
    private boolean visible;
    private byte flashing;
    
    private final byte id;
    
    private long lastTime = 0L;
    
    public Square(byte id) {
        super();
        this.id = id;
        this.visible = false;
        this.flashing = -1;
    }
    
    public static void addToSequence() {
        Square.sequence.add(Square.squares.get(Square.random.nextInt(Game.width * Game.height - 1)));
    }
    
    public static void beginSequencing() {
        Square.current = 0;
        Game.state = Game.GameState.SEQUENCING;
        Square.sequence.get(Square.current).flash();
    }
    
    public static void next() {
        if(Square.current + 1 < Square.sequence.size()) {
            Square.sequence.get(++Square.current).flash();
        } else {
            Square.current = 0;
            Game.state = Game.GameState.WAITING;
        }
    }

    public static void resetSequence() {
        Square.sequence.clear();
        Square.addToSequence();
    }
    
    public void flash() {
        this.flashing = 0;
    }

    @Override
    public void tick() {
        this.visible = this.id <= Game.width * Game.height && !Game.state.equals(Game.GameState.MENU) && !Game.state.equals(Game.GameState.GAME_OVER);
        
        if(!this.visible) {
            return;
        }

        int x = this.getCenter().x + Math.round(((this.id % Game.width == 0 ? Game.width : this.id % Game.width) - (Game.width / 2F + 0.5F)) * this.scale(48));
        int y = (int) (this.getCenter().y + (Math.ceil((double) this.id / Game.width) - (Game.height / 2F + 0.5F)) * this.scale(-48));
        this.setLocation(x, y);
        this.hitbox.setBounds(this.getX() - this.scale(16), this.getY() - this.scale(16), this.scale(32), this.scale(32));
        
        if(Game.state.equals(Game.GameState.SEQUENCING) && this.flashing != -1) {
            switch(this.flashing) {
                case 0:
                    this.flashing = 1;
                    this.lastTime = System.currentTimeMillis();
                    break;
                case 1:
                    if(System.currentTimeMillis() >= this.lastTime + Math.round(60D / Game.speed / 2D * 1000)) {
                        this.flashing = 2;
                        this.lastTime = System.currentTimeMillis();
                    }
                    break;
                case 2:
                    if(System.currentTimeMillis() >= this.lastTime + Math.round(60D / Game.speed / 2D * 1000) || Square.current == Square.sequence.size() - 1) {
                        this.flashing = -1;
                        Square.next();
                    }
                    break;
                default:
                    this.flashing = -1;
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if(!this.visible) {
            return;
        }

        g.setColor(this.flashing == 1 ? Color.BLUE : Game.state.equals(Game.GameState.SEQUENCING) ? new Color(6250335) : Color.BLACK);
        g.fillRect(this.getX() - this.scale(16), this.getY() - this.scale(16), this.scale(32), this.scale(32));

        g.setColor(Color.WHITE);
        Font font = new Font("Arial", Font.BOLD, this.scale(16));
        
        if(Game.label.equals(Square.LabelType.NUMBERS)) {
            this.drawCenteredString(g, String.valueOf(this.id), this.location, font);
        } else if(Game.label.equals(Square.LabelType.COORDINATES)) {
            char letter = Square.charset[Math.floorDiv(this.id - 1, Game.width)];
            int number = (this.id % Game.width == 0) ? Game.width : this.id % Game.width;
            this.drawCenteredString(g, String.valueOf(letter).concat(String.valueOf(number)), this.location, font);
        }
    }

    @Override
    public void click(Point p) {
        if(!this.visible || !Game.state.equals(Game.GameState.WAITING) || !this.hitbox.contains(p)) {
            return;
        }
        
        if(Square.sequence.get(Square.current).equals(this)) {
            if(Square.current++ >= Square.sequence.size() - 1) {
                Square.addToSequence();
                Square.beginSequencing();
            }
        } else {
            Game.state = Game.GameState.GAME_OVER;
        }
    }
    
    public enum LabelType {
        NUMBERS("Numbers"),
        COORDINATES("Numbers and Letters"),
        NONE("No Labels");
        
        private final String displayName;
        
        LabelType(String displayName) {
            this.displayName = displayName;
        }
        
        @Override
        public String toString() {
            return this.displayName;
        }
    }
}
