package net.itsskywastaken.simon.objects;

import net.itsskywastaken.simon.display.Window;

import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;

public class Handler {
    
    public Window window;
    
    private final LinkedList<GameObject> objects = new LinkedList<>();
    
    public void tick() {
        for(GameObject object : this.objects) {
            object.tick();
        }
    }

    public void render(Graphics g) {
        for(GameObject object : this.objects) {
            object.render(g);
        }
    }
    
    public void click(Point p) {
        for(GameObject object : this.objects) {
            object.click(p);
        }
    }
    
    public void setWindow(Window window) {
        if(this.window == null) {
            this.window = window;
        }
    }
    
    public void add(GameObject object) {
        this.objects.add(object);
        object.handler = this;
    }
}
