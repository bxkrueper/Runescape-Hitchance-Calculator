package rsHitchance;

import java.awt.Graphics;

import images.Picture;
import rsHitchance.BaseStats.EditableStats;

public interface Buff{
    void draw(Graphics g, int x, int y,int size, int stackValue, CombatStyle combatStyle);
    String getName();
    String getExclusiveCategory();//buffs of the same exclusive category can't be in the same Buff container
    
    //override one or more of these to have an effect on hitchance. default is no effect
    default void editStats(Combatent owner,EditableStats ownerEditableStats,Combatent opponent,EditableStats opponentEditableStats, int stackValue){
        
    }
    default double addToFinalHitChance(Combatent owner,Combatent opponent,int stackValue){
        return 0.0;
    }
    default double getAccuracyMultiplier(Combatent owner,Combatent opponent){
        return 1.0;
    }
    
}
