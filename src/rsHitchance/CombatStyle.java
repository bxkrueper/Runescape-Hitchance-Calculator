package rsHitchance;

import java.awt.Color;
import java.awt.Graphics;

public interface CombatStyle {
    public void draw(Graphics g, int x, int y,int size);
    public String getName();
    public Color getColor();
}