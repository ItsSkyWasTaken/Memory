package net.itsskywastaken.simon.objects;

import java.awt.*;

public abstract class GameObject {

    public Handler handler;
    
    protected Point location;
    protected Rectangle hitbox;
    
    public GameObject() {
        this(new Point());
    }
    
    public GameObject(Point location) {
        this.location = location;
        this.hitbox = new Rectangle();
    }
    
    public void drawCenteredString(Graphics g, String text, Point p, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        g.setFont(font);
        g.drawString(text, p.x - metrics.stringWidth(text) / 2, p.y - metrics.getHeight() / 2 + metrics.getAscent());
    }

    public void setLocation(Point location) {
        this.location.move(location.x, location.y);
    }
    
    public void setLocation(int x, int y) {
        this.location.move(x, y);
    }
    
    public int getX() {
        return this.location.x;
    }
    
    public int getY() {
        return this.location.y;
    }

    protected int getWindowWidth() {
        return this.handler.window.width();
    }
    
    protected int getWindowHeight() {
        return this.handler.window.height();
    }
 
    protected Point getCenter() {
        return this.handler.window.center();
    }

    protected int scale(int base) {
        return this.handler.window.scale(base);
    }
 
    public abstract void tick();
    
    public abstract void render(Graphics g);
    
    public void click(Point p) {}
}
