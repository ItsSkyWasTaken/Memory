package net.itsskywastaken.simon.objects;

import net.itsskywastaken.simon.Game;

import java.awt.*;

public class GameOverScreen extends GameObject {
    
    public GameOverScreen() {
        super();
    }
    
    @Override
    public void tick() {
        this.setLocation(this.getCenter());
        this.hitbox.setBounds(this.getWindowWidth() / 2 - this.scale(60), this.getWindowHeight() / 2 + this.scale(12), this.scale(120), this.scale(60));
    }
    
    @Override
    public void render(Graphics g) {
        if(!Game.state.equals(Game.GameState.GAME_OVER)) {
            return;
        }
    
        Font font = new Font("Arial", Font.BOLD, this.scale(24));
    
        g.setColor(Color.RED);
        this.drawCenteredString(g, "Game Over!", new Point(this.getWindowWidth() / 2, this.getWindowHeight() / 2 - this.scale(72)), font);
        
        g.setColor(Color.BLACK);
        this.drawCenteredString(g, ("Final Score: ").concat(String.valueOf(Square.sequence.size() - 1)), new Point(this.getWindowWidth() / 2, this.getWindowHeight() / 2 - this.scale(36)), font);
        
        g.setColor(Color.GREEN);
        g.fillRect(this.hitbox.x, this.hitbox.y, this.hitbox.width, this.hitbox.height);
        g.setColor(Color.BLACK);
        this.drawCenteredString(g, "Retry", new Point((int) this.hitbox.getCenterX(), (int) this.hitbox.getCenterY()), font);
    }
    
    @Override
    public void click(Point p) {
        if(this.hitbox.contains(p) && Game.state.equals(Game.GameState.GAME_OVER)) {
            Game.state = Game.GameState.MENU;
        }
    }
}
