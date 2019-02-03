package rsHitchance;

import java.awt.Font;
import java.awt.Graphics;

import rsHitchance.CombatStyleFactory.None;

public class WeaknessFactory {

    private static AffinityWeakness noWeakness;//one copy of a weakness to none
    
    public static AffinityWeakness getAffinityWeakness(CombatStyle combatStyle,int affinityValue){
        if(combatStyle.getName().equals("None")){
            if(noWeakness==null){
                noWeakness = new FillInAffinityWeakness(combatStyle,-1);
            }
            return noWeakness;
        }else{
            return new FillInAffinityWeakness(combatStyle,affinityValue);
        }
    }
    
    public static AffinityWeakness getAffinityWeakness(String combatStyle,int affinityValue){
        return getAffinityWeakness(CombatStyleFactory.getCombatStyle(combatStyle),affinityValue);
    }
    
    
    
    
    private static class FillInAffinityWeakness implements AffinityWeakness{
        
        private CombatStyle combatStyle;
        private int affinityValue;
        
        public FillInAffinityWeakness(CombatStyle combatStyle,int affinityValue){
            this.combatStyle = combatStyle;
            this.affinityValue = affinityValue;
        }
        
        @Override
        public CombatStyle getCombatStyle(){
            return combatStyle;
        }

        @Override
        public void draw(Graphics g, int x, int y, int size) {
            combatStyle.draw(g, x, y, size);
            if(!(combatStyle instanceof None)){
                g.setColor(combatStyle.getColor());
                g.setFont(new Font(g.getFont().getName(),Font.BOLD,size));
                g.drawString(Integer.toString(affinityValue), x+size+2, y+size);
            }
            
        }

        @Override
        public int getAffinity() {
            return affinityValue;
        }

        @Override
        public void setAffinity(int newAffinity) {
            affinityValue = newAffinity;
        }
        
        @Override
        public String toString(){
            return "combatStyle: " + combatStyle + " affinityValue: " + affinityValue;
        }
    }
}
