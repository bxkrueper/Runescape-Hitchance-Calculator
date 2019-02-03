package rsHitchance;

import rsHitchance.CombatStyleFactory.Magic;
import rsHitchance.CombatStyleFactory.Melee;
import rsHitchance.CombatStyleFactory.Ranged;

//groups up all affinity information
public class AffinityWeaknesses {
    
    private int meleeAffinity;
    private int rangedAffinity;
    private int magicAffinity;
    private AffinityWeakness affinityWeakness;
    
    public AffinityWeaknesses(int meleeAffinity, int rangedAffinity,int magicAffinity,AffinityWeakness affinityWeakness){
        this.meleeAffinity = meleeAffinity;
        this.rangedAffinity = rangedAffinity;
        this.magicAffinity = magicAffinity;
        this.affinityWeakness = affinityWeakness;
    }
    
    public int getMeleeAffinity(){
        return meleeAffinity;
    }
    public int getRangedAffinity(){
        return rangedAffinity;
    }
    public int getMagicAffinity(){
        return magicAffinity;
    }
    public AffinityWeakness getAffinityWeakness(){
        return affinityWeakness;
    }
    
    public void setMeleeAffinity(int newAffinity){
        this.meleeAffinity = newAffinity;
    }
    public void setRangedAffinity(int newAffinity){
        this.rangedAffinity = newAffinity;
    }
    public void setMagicAffinity(int newAffinity){
        this.magicAffinity = newAffinity;
    }
    public void setSpecialAffinity(int newAffinity){
        this.affinityWeakness.setAffinity(newAffinity);
    }
    
    public void addAffinityToAll(int affinityToAdd){
        this.meleeAffinity+=affinityToAdd;
        this.rangedAffinity+=affinityToAdd;
        this.magicAffinity+=affinityToAdd;
        this.affinityWeakness.setAffinity(this.affinityWeakness.getAffinity()+affinityToAdd);
    }

    public int getAffinityTo(CombatStyle cbs) {
        System.out.println(cbs);
        if(cbs.getName().equals(affinityWeakness.getCombatStyle().getName())){
            return affinityWeakness.getAffinity();
        }else{
            return getCombatTriangleAffinityTo(cbs);
        }
    }
    
    //ignores special styles, only looks at melee, range, or magic
    private int getCombatTriangleAffinityTo(CombatStyle cbs){
        if(cbs instanceof Melee){
            return meleeAffinity;
        }
        if(cbs instanceof Ranged){
            return rangedAffinity;
        }
        if(cbs instanceof Magic){
            return magicAffinity;
        }
        
        System.out.println("getCombatTriangleAffinityTo(): comat style is not melee,range,or magic. returned -1");
        return -1;
    }

    public AffinityWeaknesses copy() {
        return new AffinityWeaknesses(meleeAffinity,rangedAffinity,magicAffinity,WeaknessFactory.getAffinityWeakness(affinityWeakness.getCombatStyle(), affinityWeakness.getAffinity()));
    }
}
