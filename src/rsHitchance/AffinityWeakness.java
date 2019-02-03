package rsHitchance;

import java.awt.Graphics;

//this discribes a monster's special affinity weakness to a certain type of a combat style
//for example, Ahrim's affinity is 90 when using arrows, but only 65 when using other ranged styles
public interface AffinityWeakness{
    CombatStyle getCombatStyle();
    int getAffinity();
    void setAffinity(int newAffinity);//use with a copy made in BaseStats.EditableStats for when affinity is modified in hitchance calculations
    void draw(Graphics g, int x, int y,int size);
}
