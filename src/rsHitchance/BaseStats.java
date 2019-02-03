package rsHitchance;

import images.Picture;
import rsHitchance.CombatStyleFactory.Magic;
import rsHitchance.CombatStyleFactory.Melee;
import rsHitchance.CombatStyleFactory.Ranged;

//groups up all information about a combatant.
//BaseStats is immutable to protect monster data, but can give an editable version with copies of infomation for hitchance calculations
public class BaseStats {
    
    private int attackLevel;
    private int meleeAccuracy;//for players, this is the accuracy from their weapon
    private int rangedLevel;
    private int rangedAccuracy;//for players, this is the accuracy from their weapon
    private int magicLevel;
    private int magicAccuracy;//for players, this is the accuracy from their weapon
    private CombatStyle combatStyle;
    
    private int defenseLevel;
    private int armour;
    private AffinityWeaknesses affinityWeaknesses;
    
    public BaseStats(int attackLevel,int meleeAccuracy,int rangedLevel,int rangedAccuracy, int magicLevel,int magicAccuracy,CombatStyle combatStyle, int defence,int armour,AffinityWeaknesses affinityWeaknesses){
        this.attackLevel = attackLevel;
        this.meleeAccuracy = meleeAccuracy;
        this.rangedLevel = rangedLevel;
        this.rangedAccuracy = rangedAccuracy;
        this.magicLevel = magicLevel;
        this.magicAccuracy = magicAccuracy;
        this.combatStyle = combatStyle;
        
        this.defenseLevel = defence;
        this.armour = armour;
        this.affinityWeaknesses = affinityWeaknesses;
    }
    
    public int getAttackLevel(){
        return attackLevel;
    }
    public int getMeleeAccuracy(){
        return meleeAccuracy;
    }
    public int getRangedLevel(){
        return rangedLevel;
    }
    public int getRangedAccuracy(){
        return rangedAccuracy;
    }
    public int getMagicLevel(){
        return magicLevel;
    }
    public int getMagicAccuracy(){
        return magicAccuracy;
    }
    public CombatStyle getCombatStyle(){
        return combatStyle;
    }
    
    public void setCombatStyle(CombatStyle combatStyle){
        this.combatStyle = combatStyle;
    }
    
    public int getDefenseLevel(){
        return defenseLevel;
    }
    public int getArmour(){
        return armour;
    }
    public int getMeleeAffinity() {
        return affinityWeaknesses.getMeleeAffinity();
    }
    public int getRangedAffinity() {
        return affinityWeaknesses.getRangedAffinity();
    }
    public int getMagicAffinity() {
        return affinityWeaknesses.getMagicAffinity();
    }
    public AffinityWeakness getAffinityWeakness(){
        return affinityWeaknesses.getAffinityWeakness();
    }
    public int getAffinityTo(CombatStyle cbs) {
        return affinityWeaknesses.getAffinityTo(cbs);
    }
    
    public int getAccuracyLevelFor(CombatStyle cbs) {
        if(cbs instanceof Melee){
            return attackLevel;
        }
        if(cbs instanceof Ranged){
            return rangedLevel;
        }
        if(cbs instanceof Magic){
            return magicLevel;
        }
        
        System.out.println("getAccuracyLevelFor(): combat style is not melee,range,or magic. returned -1");
        return -1;
    }
    
    
    //returns an editable version of the information
    public EditableStats getEditableStats(){
        return new EditableStats(attackLevel,meleeAccuracy,rangedLevel,rangedAccuracy,magicLevel,magicAccuracy,combatStyle,defenseLevel,armour,affinityWeaknesses);
    }

    
    
    
    
    @Override
    public String toString() {
        return "BaseStats [attackLevel=" + attackLevel + ", meleeAccuracy=" + meleeAccuracy + ", rangedLevel="
                + rangedLevel + ", rangedAccuracy=" + rangedAccuracy + ", magicLevel=" + magicLevel + ", magicAccuracy="
                + magicAccuracy + ", combatStyle=" + combatStyle + ", defenseLevel=" + defenseLevel + ", armour="
                + armour + ", affinityWeaknesses=" + affinityWeaknesses + "]";
    }




    //editable version of information in BaseStats
    public static class EditableStats{
        //stored as doubles, but returns integer after hitchance calculations are done
        private double attackLevel;
        private double meleeAccuracy;
        private double rangedLevel;
        private double rangedAccuracy;
        private double magicLevel;
        private double magicAccuracy;
        
        private CombatStyle combatStyle;
        
        private double defenseLevel;
        private double armour;
        private AffinityWeaknesses affinityWeaknesses;
        
        public EditableStats(double attackLevel,double meleeAccuracy,double rangedLevel,double rangedAccuracy, double magicLevel,double magicAccuracy,CombatStyle combatStyle,double defence,double armour,AffinityWeaknesses affinityWeaknesses){
            this.setAttackLevel(attackLevel);
            this.setMeleeAccuracy(meleeAccuracy);
            this.setRangedLevel(rangedLevel);
            this.setRangedAccuracy(rangedAccuracy);
            this.setMagicLevel(magicLevel);
            this.setMagicAccuracy(magicAccuracy);
            
            this.setCombatStyle(combatStyle);
            
            this.setDefenseLevel(defence);
            this.setArmour(armour);
            this.setAffinityWeaknesses(affinityWeaknesses.copy());
        }

        public int getAffinityTo(CombatStyle cbs) {
            return getAffinityWeaknesses().getAffinityTo(cbs);
        }

        public int getAccuracyLevelFor(CombatStyle cbs) {
            if(cbs instanceof Melee){
                return (int) getAttackLevel();
            }
            if(cbs instanceof Ranged){
                return (int) getRangedLevel();
            }
            if(cbs instanceof Magic){
                return (int) getMagicLevel();
            }
            
            System.out.println("getAccuracyLevelFor(): combat style is not melee,range,or magic. returned -1");
            return -1;
        }
        
        public void addAccuracyLevelFor(CombatStyle cbs,double toAdd) {
            if(cbs instanceof Melee){
                attackLevel+=toAdd;
                return;
            }
            if(cbs instanceof Ranged){
                rangedLevel+=toAdd;
                return;
            }
            if(cbs instanceof Magic){
                magicLevel+=toAdd;
                return;
            }
            
            System.out.println("addAccuracyLevelFor(): combat style is not instance of melee,range,or magic.");
        }
        
        public int getAccuracyFor(CombatStyle cbs) {
            if(cbs instanceof Melee){
                return (int) getMeleeAccuracy();
            }
            if(cbs instanceof Ranged){
                return (int) getRangedAccuracy();
            }
            if(cbs instanceof Magic){
                return (int) getMagicAccuracy();
            }
            
            System.out.println("getAccuracyFor(): combat style is not melee,range,or magic. returned -1");
            return -1;
        }
        
        public void multiplyAccuracyFor(CombatStyle cbs,double toMult){
            if(cbs instanceof Melee){
                meleeAccuracy*=toMult;
                return;
            }
            if(cbs instanceof Ranged){
                rangedAccuracy*=toMult;
                return;
            }
            if(cbs instanceof Magic){
                magicAccuracy*=toMult;
                return;
            }
            
            System.out.println("multiplyAccuracyFor(): combat style is not instance of melee,range,or magic.");
        }

        public AffinityWeaknesses getAffinityWeaknesses() {
            return affinityWeaknesses;
        }

        public void setAffinityWeaknesses(AffinityWeaknesses affinityWeaknesses) {
            this.affinityWeaknesses = affinityWeaknesses;
        }

        public int getAttackLevel() {
            return (int) attackLevel;
        }

        public void setAttackLevel(double attackLevel) {
            this.attackLevel = attackLevel;
        }
        
        public void addToAttackLevel(double toAdd){
            attackLevel += toAdd;
        }

        public int getMeleeAccuracy() {
            return (int) meleeAccuracy;
        }

        public void setMeleeAccuracy(double meleeAccuracy) {
            this.meleeAccuracy = meleeAccuracy;
        }
        
        public void multiplyMeleeAccuracy(double toMult){
            meleeAccuracy *= toMult;
        }

        public int getRangedLevel() {
            return (int) rangedLevel;
        }

        public void setRangedLevel(double rangedLevel) {
            this.rangedLevel = rangedLevel;
        }
        
        public void addToRangedLevel(double toAdd){
            rangedLevel += toAdd;
        }

        public int getRangedAccuracy() {
            return (int) rangedAccuracy;
        }

        public void setRangedAccuracy(double rangedAccuracy) {
            this.rangedAccuracy = rangedAccuracy;
        }
        
        public void multiplyRangedAccuracy(double toMult){
            rangedAccuracy *= toMult;
        }

        public int getMagicLevel() {
            return (int) magicLevel;
        }

        public void setMagicLevel(double magicLevel) {
            this.magicLevel = magicLevel;
        }
        
        public void addToMagicLevel(double toAdd){
            magicLevel += toAdd;
        }

        public int getMagicAccuracy() {
            return (int) magicAccuracy;
        }

        public void setMagicAccuracy(double magicAccuracy) {
            this.magicAccuracy = magicAccuracy;
        }
        
        public void multiplyMagicAccuracy(double toMult){
            magicAccuracy *= toMult;
        }

        public int getDefenseLevel() {
            return (int) defenseLevel;
        }

        public void setDefenseLevel(double defenseLevel) {
            this.defenseLevel = defenseLevel;
        }
        
        public void addToDefenseLevel(double toAdd){
            defenseLevel += toAdd;
        }

        public int getArmour() {
            return (int) armour;
        }

        private void setArmour(double armour) {
            this.armour = armour;
        }
        
        public void multiplyArmour(double toMult){
            armour *= toMult;
        }

        public CombatStyle getCombatStyle() {
            return combatStyle;
        }

        public void setCombatStyle(CombatStyle combatStyle) {
            this.combatStyle = combatStyle;
        }
    }





    
}
