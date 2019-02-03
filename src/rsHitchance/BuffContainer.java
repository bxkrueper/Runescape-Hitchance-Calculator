package rsHitchance;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

import rsHitchance.BaseStats.EditableStats;

//uses hash map. Only one buff from each exclusive group can be here at a time
public class BuffContainer {
    
    private Map<String,BuffWithStack> buffMap;
    
    public BuffContainer(){
        buffMap = new HashMap<>();
    }
    
    public void setBuff(Buff buff, int ammount){
        buffMap.put(buff.getExclusiveCategory(), new BuffWithStack(buff,ammount));
    }
    public void setBuff(Buff buff){
        setBuff(buff,-1);
    }
    
    public void removeBuff(Buff buff) {
        BuffWithStack bws = buffMap.get(buff.getExclusiveCategory());
        if(bws==null){
            return;
        }
        
        if(bws.buff==buff){
            buffMap.remove(buff.getExclusiveCategory());
        }
    }
    
    public void removeBuff(String exclusiveCategory) {
        BuffWithStack bws = buffMap.get(exclusiveCategory);
        if(bws==null){
            return;
        }
        
        buffMap.remove(exclusiveCategory);
    }
    
    public Buff getBuff(String exclusiveCategory){
        return buffMap.get(exclusiveCategory).buff;
    }
    
    //returns 0 if buff is not there
    public int getBuffStackValue(Buff buff){
        BuffWithStack bws = buffMap.get(buff.getExclusiveCategory());
        if(bws==null){
            return 0;
        }
        
        Buff buffInMap = bws.buff;
        if(buff==buffInMap){
            return bws.stackValue;
        }else{
            return 0;
        }
    }
    
    public void draw(Graphics g, int x, int y,CombatStyle combatStyle){
        int yStartDraw = y;
        for(BuffWithStack bws:buffMap.values()){
            bws.buff.draw(g, x, yStartDraw, 30, bws.stackValue,combatStyle);
            yStartDraw+=30;
        }
    }

    public void editStats(Combatent owner,EditableStats ownerEditableStats,Combatent opponent,EditableStats opponentEditableStats) {
        for(BuffWithStack bws:buffMap.values()){
            bws.buff.editStats(owner,ownerEditableStats,opponent,opponentEditableStats, bws.stackValue);
        }
    }

    public double addToFinalHitChance(Combatent owner,Combatent opponent) {
        double hitchanceAdd = 0.0;
        for(BuffWithStack bws:buffMap.values()){
            hitchanceAdd += bws.buff.addToFinalHitChance(owner,opponent,bws.stackValue);
        }
        return hitchanceAdd;
    }
    
    public double getAccuracyMultiplier(Combatent owner,Combatent opponent) {
        double multiplier = 1.0;
        for(BuffWithStack bws:buffMap.values()){
            multiplier *= bws.buff.getAccuracyMultiplier(owner, opponent);
        }
        return multiplier;
    }
    
    private class BuffWithStack{
        public Buff buff;
        public int stackValue;
        
        public BuffWithStack(Buff buff, int stackValue){
            this.buff = buff;
            this.stackValue = stackValue;
        }
        public BuffWithStack(Buff buff){
            this.buff = buff;
            this.stackValue = -1;
        }
    }

    

    

    
}
