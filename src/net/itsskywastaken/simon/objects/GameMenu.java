package net.itsskywastaken.simon.objects;

import net.itsskywastaken.simon.Game;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class GameMenu extends GameObject {

    private final Rectangle[] screenQuarters = new Rectangle[] {
        new Rectangle(),
        new Rectangle(),
        new Rectangle(),
        new Rectangle(),
        new Rectangle()
    };
    
    private final Point[] centers = new Point[] {
        new Point(),
        new Point(),
        new Point(),
        new Point(),
        new Point()
    };
    
    private final Rectangle startButton = new Rectangle();
    
    private final Rectangle widthDecrement = new Rectangle();
    
    private final Rectangle widthIncrement = new Rectangle();
    
    private final Rectangle heightDecrement = new Rectangle();
    
    private final Rectangle heightIncrement = new Rectangle();
    
    private final Rectangle speedDecrement = new Rectangle();
    
    private final Rectangle speedIncrement = new Rectangle();
    
    private final Rectangle labelCycleLeft = new Rectangle();
    
    private final Rectangle labelCycleRight = new Rectangle();

    public GameMenu() {
        super();
    }

    @Override
    public void tick() {
        this.setLocation(this.getCenter());
        
        this.screenQuarters[0].setBounds(0, 0, this.getWindowWidth(), Math.round(this.getWindowHeight() / 6F));
        this.screenQuarters[1].setBounds(0, Math.round(this.getWindowHeight() / 6F), this.getWindowWidth(), Math.round(this.getWindowHeight() / 6F));
        this.screenQuarters[2].setBounds(0, Math.round(this.getWindowHeight() / 6F * 2), this.getWindowWidth(), Math.round(this.getWindowHeight() / 6F));
        this.screenQuarters[3].setBounds(0, Math.round(this.getWindowHeight() / 6F * 3), this.getWindowWidth(), Math.round(this.getWindowHeight() / 6F));
        this.screenQuarters[4].setBounds(0, Math.round(this.getWindowHeight() / 6F * 4), this.getWindowWidth(), Math.round(this.getWindowHeight() / 3F));
        
        for(int i = 0; i < 5; i++) {
            this.centers[i] = new Point((int) this.screenQuarters[i].getCenterX(), (int) this.screenQuarters[i].getCenterY());
        }
        
        this.startButton.setBounds(this.centers[4].x - this.scale(72), this.centers[4].y - this.scale(24), this.scale(144), this.scale(48));
        this.widthDecrement.setBounds(this.centers[0].x - this.scale(116), this.centers[0].y + this.scale(2), this.scale(16), this.scale(16));
        this.widthIncrement.setBounds(this.centers[0].x + this.scale(100), this.centers[0].y + this.scale(2), this.scale(16), this.scale(16));
        this.heightDecrement.setBounds(this.centers[1].x - this.scale(116), this.centers[1].y + this.scale(2), this.scale(16), this.scale(16));
        this.heightIncrement.setBounds(this.centers[1].x + this.scale(100), this.centers[1].y + this.scale(2), this.scale(16), this.scale(16));
        this.speedDecrement.setBounds(this.centers[2].x - this.scale(116), this.centers[2].y + this.scale(2), this.scale(16), this.scale(16));
        this.speedIncrement.setBounds(this.centers[2].x + this.scale(100), this.centers[2].y + this.scale(2), this.scale(16), this.scale(16));
        this.labelCycleLeft.setBounds(this.centers[3].x - this.scale(116), this.centers[3].y + this.scale(2), this.scale(16), this.scale(16));
        this.labelCycleRight.setBounds(this.centers[3].x + this.scale(100), this.centers[3].y + this.scale(2), this.scale(16), this.scale(16));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(new Color(225, 225, 225));
        g.fillRect(this.getX() - this.scale(320), this.getY() - this.scale(240), this.scale(641), this.scale(481));
        
        if(!Game.state.equals(Game.GameState.MENU)) {
            return;
        }
        
        Font font = new Font("Arial", Font.PLAIN, this.scale(18));
        FontMetrics metrics = g.getFontMetrics(font);
        int height = metrics.getHeight() / 2 + 1;
        
        g.setColor(Color.BLACK);
        this.drawCenteredString(g, "Grid Width", new Point(this.centers[0].x, this.centers[0].y - height), font);
        this.drawCenteredString(g, String.valueOf(Game.width), new Point(this.centers[0].x, this.centers[0].y + height), font);
        g.setColor(Game.width > Game.WIDTH_MIN ? Color.BLACK : Color.GRAY);
        g.fillRect(this.widthDecrement.x, this.widthDecrement.y + this.widthDecrement.height / 2 - this.scale(2), this.widthDecrement.width, this.scale(4));
        g.setColor(Game.width < Game.WIDTH_MAX ? Color.BLACK : Color.GRAY);
        g.fillRect(this.widthIncrement.x, this.widthIncrement.y + this.widthIncrement.height / 2 - this.scale(2), this.widthIncrement.width, this.scale(4));
        g.fillRect(this.widthIncrement.x + this.widthIncrement.width / 2 - this.scale(2), this.widthIncrement.y, this.scale(4), this.widthIncrement.height);
        
        g.setColor(Color.BLACK);
        this.drawCenteredString(g, "Grid Height", new Point(this.centers[1].x, this.centers[1].y - height), font);
        this.drawCenteredString(g, String.valueOf(Game.height), new Point(this.centers[1].x, this.centers[1].y + height), font);
        g.setColor(Game.height > Game.HEIGHT_MIN ? Color.BLACK : Color.GRAY);
        g.fillRect(this.heightDecrement.x, this.heightDecrement.y + this.heightDecrement.height / 2 - this.scale(2), this.heightDecrement.width, this.scale(4));
        g.setColor(Game.height < Game.HEIGHT_MAX ? Color.BLACK : Color.GRAY);
        g.fillRect(this.heightIncrement.x, this.heightIncrement.y + this.heightIncrement.height / 2 - this.scale(2), this.heightIncrement.width, this.scale(4));
        g.fillRect(this.heightIncrement.x + this.heightIncrement.width / 2 - this.scale(2), this.heightIncrement.y, this.scale(4), this.heightIncrement.height);
    
        g.setColor(Color.BLACK);
        this.drawCenteredString(g, "Speed (BPM)", new Point(this.centers[2].x, this.centers[2].y - height), font);
        this.drawCenteredString(g, String.valueOf(Game.speed), new Point(this.centers[2].x, this.centers[2].y + height), font);
        g.setColor(Game.speed > Game.SPEED_MIN ? Color.BLACK : Color.GRAY);
        g.fillRect(this.speedDecrement.x, this.speedDecrement.y + this.speedDecrement.height / 2 - this.scale(2), this.speedDecrement.width, this.scale(4));
        g.setColor(Game.speed < Game.SPEED_MAX ? Color.BLACK : Color.GRAY);
        g.fillRect(this.speedIncrement.x, this.speedIncrement.y + this.speedIncrement.height / 2 - this.scale(2), this.speedIncrement.width, this.scale(4));
        g.fillRect(this.speedIncrement.x + this.speedIncrement.width / 2 - this.scale(2), this.speedIncrement.y, this.scale(4), this.speedIncrement.height);
    
        g.setColor(Color.BLACK);
        this.drawCenteredString(g, "Label Type", new Point(this.centers[3].x, this.centers[3].y - height), font);
        this.drawCenteredString(g, Game.label.toString(), new Point(this.centers[3].x, this.centers[3].y + height), font);
        
        int[] xLeft = new int[] {
            this.labelCycleLeft.x + this.labelCycleLeft.width,
            this.labelCycleLeft.x + this.labelCycleLeft.width,
            this.labelCycleLeft.x
        };
        
        int[] yLeft = new int[] {
            this.labelCycleLeft.y,
            this.labelCycleLeft.y + this.labelCycleLeft.height,
            this.labelCycleLeft.y + this.labelCycleLeft.height / 2
        };
    
        int[] xRight = new int[] {
            this.labelCycleRight.x,
            this.labelCycleRight.x,
            this.labelCycleRight.x + this.labelCycleRight.width
        };
        
        int[] yRight = new int[] {
            this.labelCycleRight.y,
            this.labelCycleRight.y + this.labelCycleRight.height,
            this.labelCycleRight.y + this.labelCycleRight.height / 2
        };
        
        g.fillPolygon(xLeft, yLeft, 3);
        g.fillPolygon(xRight, yRight, 3);
    
        g.setColor(Color.GREEN);
        g.fillRect(this.startButton.x, this.startButton.y, this.startButton.width, this.startButton.height);
        g.setColor(Color.BLACK);
        this.drawCenteredString(g, "Start!", this.centers[4], font);
    }

    @Override
    public void click(Point p) {
        if(!Game.state.equals(Game.GameState.MENU)) {
            return;
        }
        
        if(this.startButton.contains(p)) {
            Square.resetSequence();
            Square.beginSequencing();
        }
        
        if(this.widthDecrement.contains(p) && Game.width > Game.WIDTH_MIN) {
            Game.width -= 1;
        }
    
        if(this.widthIncrement.contains(p) && Game.width < Game.WIDTH_MAX) {
            Game.width += 1;
        }
    
        if(this.heightDecrement.contains(p) && Game.height > Game.HEIGHT_MIN) {
            Game.height -= 1;
        }
    
        if(this.heightIncrement.contains(p) && Game.height < Game.HEIGHT_MAX) {
            Game.height += 1;
        }
    
        if(this.speedDecrement.contains(p) && Game.speed > Game.SPEED_MIN) {
            Game.speed -= 4;
        }
    
        if(this.speedIncrement.contains(p) && Game.speed < Game.SPEED_MAX) {
            Game.speed += 4;
        }
        
        if(this.labelCycleLeft.contains(p)) {
            ArrayList<Square.LabelType> labelTypes = new ArrayList<>(Arrays.asList(Square.LabelType.values()));
            int ordinal = labelTypes.indexOf(Game.label);
            Game.label = ordinal == 0 ? labelTypes.get(labelTypes.size() - 1) : labelTypes.get(ordinal - 1);
        }
    
        if(this.labelCycleRight.contains(p)) {
            ArrayList<Square.LabelType> labelTypes = new ArrayList<>(Arrays.asList(Square.LabelType.values()));
            int ordinal = labelTypes.indexOf(Game.label);
            Game.label = ordinal == labelTypes.size() - 1 ? labelTypes.get(0) : labelTypes.get(ordinal + 1);
        }
    }
}
