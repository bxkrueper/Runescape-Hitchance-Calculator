package rsHitchance;

import images.Picture;

public class Weapon {
    private String name;
    private Picture picture;
    private int accuracyLevel;
    private CombatStyle combatStyle;
    private Buff buff;//the associated buff (like balmung)
    
    public Weapon(String name, Picture picture, int accuracy, CombatStyle combatStyle, Buff buff){
        this.name = name;
        this.picture = picture;
        this.accuracyLevel = accuracy;
        this.combatStyle = combatStyle;
        this.buff = buff;
    }
    
    public String getName(){
        return name;
    }
    public Picture getPicture(){
        return picture;
    }
    public int getAccuracyLevel(){
        return accuracyLevel;
    }
    public CombatStyle getCombatStyle(){
        return combatStyle;
    }
    public Buff getBuff(){
        return buff;
    }
}
