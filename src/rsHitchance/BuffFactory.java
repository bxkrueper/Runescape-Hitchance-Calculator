package rsHitchance;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

import images.Picture;
import images.ProxyPicture;
import rsHitchance.BaseStats.EditableStats;
import rsHitchance.CombatStyleFactory.Magic;
import rsHitchance.CombatStyleFactory.Melee;
import rsHitchance.CombatStyleFactory.Ranged;
import images.ResourceGetter;

//this class has one public method to get a buff according to name.
//most of this class is defining individual buff classes
public class BuffFactory {
    
    private static Map<String,Buff> buffMap;
    public static final String PATH_TO_STAT_EFFECTS_PICS = "images/RSHitchance/Stat Effects/";

    public static Buff getBuff(String str){
        if(buffMap==null){
            makeMap();
        }
        
        Buff buff = buffMap.get(str);
        if(buff==null){
            System.out.println("unrecognized buff: " + str + " " + " ");
            return buffMap.get("None");
        }else{
            return buff;
        }
    }
    
    private static void makeMap() {
        buffMap = new HashMap<>();
        Buff buff;
        String name;
        String exclusiveCategory;
        BuffDrawer buffDrawer;
        
        name = "Armour Break";
        exclusiveCategory = "Armour Break";
        buffDrawer = new BuffDrawerPositive(new BasicBuffPicture(PATH_TO_STAT_EFFECTS_PICS+name));
        buff = new FillInBuff(name,exclusiveCategory,buffDrawer){
            @Override
            public void editStats(Combatent owner,EditableStats ownerEditableStats,Combatent opponent,EditableStats opponentEditableStats, int stackValue) {
                ownerEditableStats.getAffinityWeaknesses().addAffinityToAll(stackValue);
            }
        };
        buffMap.put(buff.getName(),buff);
        
        name = "Basic Stat Boost";
        exclusiveCategory = "Stat Boosting Potion";
        buffDrawer = new BuffDrawerNoStack(new CombatDependantPicture("Attack Potion","Ranging Potion","Magic Potion"));
        buff = new StatBoostPotion(name,.08,1,buffDrawer);
        buffMap.put(buff.getName(),buff);
        
        name = "Super Stat Boost";
        exclusiveCategory = "Stat Boosting Potion";
        buffDrawer = new BuffDrawerNoStack(new CombatDependantPicture("Super Attack Potion","Super Ranging Potion","Super Magic Potion"));
        buff = new StatBoostPotion(name,.12,2,buffDrawer);
        buffMap.put(buff.getName(),buff);
        
        name = "Extreme Stat Boost";
        exclusiveCategory = "Stat Boosting Potion";
        buffDrawer = new BuffDrawerNoStack(new CombatDependantPicture("Extreme Attack Potion","Extreme Ranging Potion","Extreme Magic Potion"));
        buff = new StatBoostPotion(name,.15,3,buffDrawer);
        buffMap.put(buff.getName(),buff);
        
        name = "Grand Stat Boost";
        exclusiveCategory = "Stat Boosting Potion";
        buffDrawer = new BuffDrawerNoStack(new CombatDependantPicture("Grand Attack Potion","Grand Ranging Potion","Grand Magic Potion"));
        buff = new StatBoostPotion(name,.14,2,buffDrawer);
        buffMap.put(buff.getName(),buff);
        
        name = "Supreme Stat Boost";
        exclusiveCategory = "Stat Boosting Potion";
        buffDrawer = new BuffDrawerNoStack(new CombatDependantPicture("Supreme Attack Potion","Supreme Ranging Potion","Supreme Magic Potion"));
        buff = new StatBoostPotion(name,.16,4,buffDrawer);
        buffMap.put(buff.getName(),buff);
        
        name = "Defense Stat";
        exclusiveCategory = "Stat Boosting Potion";
        buffDrawer = new BuffDrawerPosNeg(new BasicBuffPicture(PATH_TO_STAT_EFFECTS_PICS+name + " Boosted"),new BasicBuffPicture(PATH_TO_STAT_EFFECTS_PICS+name + " Reduced"));
        buff = new FillInBuff(name,exclusiveCategory,buffDrawer){
            @Override
            public void editStats(Combatent owner,EditableStats ownerEditableStats,Combatent opponent,EditableStats opponentEditableStats, int stackValue) {
                ownerEditableStats.addToDefenseLevel(stackValue);
            }
        };
        buffMap.put(buff.getName(),buff);
        
        name = "Reaper Necklace";
        exclusiveCategory = "Necklace";
        buffDrawer = new BuffDrawerPositive(new BasicBuffPicture(PATH_TO_STAT_EFFECTS_PICS+name));
        buff = new FillInBuff(name,exclusiveCategory,buffDrawer){
            @Override
            public double addToFinalHitChance(Combatent owner,Combatent opponent,int stackValue){
                return stackValue/10.0;
            }
        };
        buffMap.put(buff.getName(),buff);
        
        buff = new Overload();
        buffMap.put(buff.getName(),buff);
        
        buff = new SupremeOverload();
        buffMap.put(buff.getName(),buff);
        
        buff = new Chivalry();
        buffMap.put(buff.getName(),buff);
        
        buff = new PietyClassPrayer();
        buffMap.put(buff.getName(),buff);
        
        buff = new TurmoilClassCurse();
        buffMap.put(buff.getName(),buff);
        
        buff = new NexClassCurse();
        buffMap.put(buff.getName(),buff);
        
        buff = new BasicAccuracyAura();
        buffMap.put(buff.getName(),buff);
        
        buff = new GreaterAccuracyAura();
        buffMap.put(buff.getName(),buff);
        
        buff = new MasterAccuracyAura();
        buffMap.put(buff.getName(),buff);
        
        buff = new SupremeAccuracyAura();
        buffMap.put(buff.getName(),buff);
        
        buff = new ZerkAura();
        buffMap.put(buff.getName(),buff);
        
        buff = new TradableScrimshaw();
        buffMap.put(buff.getName(),buff);
        
        buff = new SuperiorScrimshaw();
        buffMap.put(buff.getName(),buff);
        
        name = "Defender";
        exclusiveCategory = "Defender";
        buffDrawer = new BuffDrawerNoStack(new CombatDependantPicture("Kalphite Defender","Kalphite Repriser","Kalphite Rebounder"));
        buff = new FillInBuff(name,exclusiveCategory,buffDrawer){
            @Override
            public double getAccuracyMultiplier(Combatent owner,Combatent opponent){
                return 1.03;
            }
        };
        buffMap.put(buff.getName(),buff);
        
        name = "Nihil";
        exclusiveCategory = "Nihil";
        buffDrawer = new BuffDrawerNoStack(new CombatDependantPicture("Blood Nihil","Shadow Nihil","Smoke Nihil"));
        buff = new FillInBuff(name,exclusiveCategory,buffDrawer){
            @Override
            public double getAccuracyMultiplier(Combatent owner,Combatent opponent){
                return 1.05;
            }
        };
        buffMap.put(buff.getName(),buff);
        
        buff = new BlackMaskBoost();
        buffMap.put(buff.getName(),buff);
        
        buff = new BlackMask();
        buffMap.put(buff.getName(),buff);
        
        buff = new ReinforcedSlayerHelm();
        buffMap.put(buff.getName(),buff);
        
        buff = new StrongSlayerHelm();
        buffMap.put(buff.getName(),buff);
        
        buff = new MightySlayerHelm();
        buffMap.put(buff.getName(),buff);
        
        buff = new CorruptedSlayerHelm();
        buffMap.put(buff.getName(),buff);
        
        name = "Ultimate Ability";
        exclusiveCategory = "Ultimate Ability";
        buffDrawer = new BuffDrawerNoStack(new CombatDependantPicture("Ultimate Melee","Ultimate Ranged","Ultimate Magic"));
        buff = new FillInBuff(name,exclusiveCategory,buffDrawer){
            @Override
            public double addToFinalHitChance(Combatent owner,Combatent opponent,int stackValue){
                return 25.0;
            }
        };
        buffMap.put(buff.getName(),buff);
        
        name = "Sonic Wave Buff";
        exclusiveCategory = "Previous Ability Buff";
        buffDrawer = new BuffDrawerNoStack(new BasicBuffPicture(PATH_TO_STAT_EFFECTS_PICS+name));
        buff = new FillInBuff(name,exclusiveCategory,buffDrawer){
            @Override
            public double addToFinalHitChance(Combatent owner,Combatent opponent,int stackValue){
                return 6.0;
            }
        };
        buffMap.put(buff.getName(),buff);
        
        buff = new Dragonbane();
        buffMap.put(buff.getName(),buff);
        
        name = "Hexhunter";
        exclusiveCategory = "Weapon";
        buffDrawer = new BuffDrawerNoStack(new BasicBuffPicture(PATH_TO_STAT_EFFECTS_PICS+name));
        buff = new FillInBuff(name,exclusiveCategory,buffDrawer){
            @Override
            public double addToFinalHitChance(Combatent owner,Combatent opponent,int stackValue){
                if(opponent.getVulnerabilities().weakToOther(this)){
                    return 10.0;
                }else{
                    return 0.0;
                }
            }
        };
        buffMap.put(buff.getName(),buff);
        
        name = "Keris";
        exclusiveCategory = "Weapon";
        buffDrawer = new BuffDrawerNoStack(new BasicBuffPicture(WeaponFileReader.PATH_TO_WEAPON_PICS+name));
        buff = new FillInBuff(name,exclusiveCategory,buffDrawer){
            @Override
            public double addToFinalHitChance(Combatent owner,Combatent opponent,int stackValue){
                if(opponent.getVulnerabilities().weakToOther(this)){
                    return 15.0;
                }else{
                    return 0.0;
                }
            }
        };
        buffMap.put(buff.getName(),buff);
        
        buff = new BalmungClass();
        buffMap.put(buff.getName(),buff);
        
        buff = new Balmung();
        buffMap.put(buff.getName(),buff);
        
        buff = new UpgradedBalmung();
        buffMap.put(buff.getName(),buff);
        
        buff = new SilverlightClass();
        buffMap.put(buff.getName(),buff);
        
        buff = new BaseSilverlightBuff();
        buffMap.put(buff.getName(),buff);
        
        buff = new UpgradedSilverlightBuff();
        buffMap.put(buff.getName(),buff);
        
        buff = new SalveAmuletClass();
        buffMap.put(buff.getName(),buff);
        
        buff = new SalveAmulet();
        buffMap.put(buff.getName(),buff);
        
        buff = new SalveAmuletE();
        buffMap.put(buff.getName(),buff);
    }
    
    
    
    
    //remember to override one of the buff default methods for the buff to actually have an effect on the calculation
    private static class FillInBuff implements Buff{
        
        private String name;
        private String exclusiveCategory;
        private BuffDrawer buffDrawer;
        public FillInBuff(String name,String exclusiveCategory,BuffDrawer buffDrawer){
            this.name = name;
            this.exclusiveCategory = exclusiveCategory;
            this.buffDrawer = buffDrawer;
        }
        @Override
        public void draw(Graphics g, int x, int y, int size, int stackValue, CombatStyle combatStyle) {
            buffDrawer.draw(g, x, y, size, stackValue, combatStyle);
        }
        @Override
        public String getName() {
            return name;
        }
        @Override
        public String getExclusiveCategory() {
            return exclusiveCategory;
        }
    }
    
    //most buff classes extend from this just to save code on the picture
    private abstract static class PictureBuff implements Buff{

        private BuffDrawer buffDrawer;
        
        public PictureBuff(){
            this.buffDrawer = getBuffDrawer();
        }
        
        public abstract BuffDrawer getBuffDrawer();

        @Override
        public void draw(Graphics g, int x, int y, int size, int stackValue, CombatStyle combatStyle) {
            buffDrawer.draw(g, x, y, size, stackValue, combatStyle);
        }
    }
    
    private static class StatBoostPotion extends PictureBuff{

        private String name;
        private double multLvl;
        private int addLvl;
        private BuffDrawer buffDrawer;
        
        public StatBoostPotion(String name,double multLvl,int addLvl,BuffDrawer buffDrawer){
            this.name = name;
            this.multLvl = multLvl;
            this.addLvl = addLvl;
            this.buffDrawer = buffDrawer;
        }
        
        @Override
        public String getName() {
            return name;
        }

        @Override
        public String getExclusiveCategory() {
            return "Stat Boosting Potion";
        }
        
        @Override
        public void editStats(Combatent owner,EditableStats ownerEditableStats,Combatent opponent,EditableStats opponentEditableStats, int stackValue) {
            CombatStyle ownerCombatStyle = owner.getBaseStats().getCombatStyle();
            ownerEditableStats.addAccuracyLevelFor(ownerCombatStyle, owner.getBaseStats().getAccuracyLevelFor(ownerCombatStyle)*multLvl+addLvl);
        }

        @Override
        public BuffDrawer getBuffDrawer() {
            return buffDrawer;
        }
        
        //constructor issues in super class. use this reference instead
        @Override
        public void draw(Graphics g, int x, int y, int size, int stackValue, CombatStyle combatStyle) {
            buffDrawer.draw(g, x, y, size, stackValue, combatStyle);
        }
        
    }
    
    private static abstract class OverloadClass extends PictureBuff{
        
        private double multLvl;
        private int addLvl;
        
        public OverloadClass(){
            this.multLvl = getMultLvl();
            this.addLvl = getAddLvl();
        }
        @Override
        public String getExclusiveCategory() {
            return "Stat Boosting Potion";
        }

        @Override
        public void editStats(Combatent owner, EditableStats ownerEditableStats, Combatent opponent,EditableStats opponentEditableStats, int stackValue) {
            BaseStats ownerBaseStats = owner.getBaseStats();
            ownerEditableStats.addToAttackLevel(((ownerBaseStats.getAttackLevel()*multLvl))+addLvl);
            ownerEditableStats.addToRangedLevel(((ownerBaseStats.getRangedLevel()*multLvl))+addLvl);
            ownerEditableStats.addToMagicLevel(((ownerBaseStats.getMagicLevel()*multLvl))+addLvl);
            ownerEditableStats.addToDefenseLevel(((ownerBaseStats.getDefenseLevel()*multLvl))+addLvl);
        }
        
        public abstract double getMultLvl();
        public abstract int getAddLvl();
        
        @Override
        public BuffDrawer getBuffDrawer() {
            return new BuffDrawerNoStack(new BasicBuffPicture(PATH_TO_STAT_EFFECTS_PICS+getName()));
        }
    }
    
    private static class Overload extends OverloadClass{
        
        @Override
        public String getName() {
            return "Overload";
        }

        @Override
        public double getMultLvl() {
            return 0.15;
        }

        @Override
        public int getAddLvl() {
            return 3;
        }
    }
    
    private static class SupremeOverload extends OverloadClass{
        
        @Override
        public String getName() {
            return "Supreme Overload";
        }

        @Override
        public double getMultLvl() {
            return 0.16;
        }

        @Override
        public int getAddLvl() {
            return 4;
        }
    }
    
    private static abstract class Prayer extends PictureBuff{
        
        @Override
        public void editStats(Combatent owner, EditableStats ownerEditableStats, Combatent opponent,EditableStats opponentEditableStats, int stackValue) {
            CombatStyle ownerCombatStyle = owner.getBaseStats().getCombatStyle();
            
            ownerEditableStats.addAccuracyLevelFor(ownerCombatStyle, getLevelMod());//add levels to right accuracy level
            ownerEditableStats.addToDefenseLevel(getLevelMod());;//add defense levels
        }
        
        public abstract int getLevelMod();

        @Override
        public String getExclusiveCategory() {
            return "Stat Boosting Prayers";
        }
        
    }
    
    //melee only
    private static class Chivalry extends Prayer{
        
        //melee only
        @Override
        public void editStats(Combatent owner, EditableStats ownerEditableStats, Combatent opponent,EditableStats opponentEditableStats, int stackValue) {
            CombatStyle ownerCombatStyle = owner.getBaseStats().getCombatStyle();
            
            ownerEditableStats.addToAttackLevel(getLevelMod());//add to melee accuracy level only
            
            ownerEditableStats.addToDefenseLevel(getLevelMod());;//add defense levels
        }
        
        @Override
        public String getName() {
            return "Chivalry";
        }
        
        @Override
        public int getLevelMod() {
            return 7;
        }

        @Override
        public BuffDrawer getBuffDrawer() {
            return new BuffDrawerNoStack(new BasicBuffPicture(PATH_TO_STAT_EFFECTS_PICS+getName()));
        }
    }
    
    private static class PietyClassPrayer extends Prayer{
        
        @Override
        public String getName() {
            return "Piety Class Prayer";
        }
        
        @Override
        public int getLevelMod() {
            return 8;
        }

        @Override
        public BuffDrawer getBuffDrawer() {
            return new BuffDrawerNoStack(new CombatDependantPicture("Piety","Rigour","Augury"));
        }
        
    }
    
    public static abstract class TopTierCurses extends Prayer{
        
        public abstract int getLevelMod();
        
        //stack value should be between 6 and 10
        @Override
        public void editStats(Combatent owner,EditableStats ownerEditableStats,Combatent opponent,EditableStats opponentEditableStats, int stackValue) {
            super.editStats(owner, ownerEditableStats, opponent, opponentEditableStats, stackValue);
            
            CombatStyle ownerCombatStyle = owner.getBaseStats().getCombatStyle();
            if(opponent.getVulnerabilities().weakToDrain()){
                opponentEditableStats.addAccuracyLevelFor(ownerCombatStyle, -stackValue);//subtracts 6-10 levels
                opponentEditableStats.addToDefenseLevel(-stackValue);//subtracts 6-10 levels
            }
            
        }
        
    }
    
    private static class TurmoilClassCurse extends TopTierCurses{
        
        @Override
        public int getLevelMod(){
            return 10;
        }

        @Override
        public String getName() {
            return "Turmoil Class Curse";
        }

        @Override
        public BuffDrawer getBuffDrawer() {
            return new BuffDrawerPositive(new CombatDependantPicture("Turmoil","Anguish","Torment"));
        }
    }
    
    private static class NexClassCurse extends TopTierCurses{
        
        @Override
        public int getLevelMod(){
            return 12;
        }
        
        @Override
        public String getName() {
            return "Nex Class Curse";
        }

        @Override
        public BuffDrawer getBuffDrawer() {
            return new BuffDrawerPositive(new CombatDependantPicture("Malevolence","Desolation","Affliction"));
        }
    }
    
    
    
    private static abstract class Aura extends PictureBuff{
        @Override
        public String getExclusiveCategory() {
            return "Aura";
        }
    }
    
    private static abstract class AccuracyAura extends Aura{
        
        @Override
        public double getAccuracyMultiplier(Combatent owner,Combatent opponent){
            return getBoost();
        }
        
        public abstract double getBoost();
    }
    
    private static class BasicAccuracyAura extends AccuracyAura{
        
        @Override
        public String getName() {
            return "Basic Accuracy Aura";
        }

        @Override
        public double getBoost() {
            return 1.03;
        }

        @Override
        public BuffDrawer getBuffDrawer() {
            return new BuffDrawerNoStack(new CombatDependantPicture("Brawler Aura","Sharpshooter Aura","Runic Accuracy Aura"));
        }
    }
    
    private static class GreaterAccuracyAura extends AccuracyAura{
        
        @Override
        public String getName() {
            return "Greater Accuracy Aura";
        }

        @Override
        public double getBoost() {
            return 1.05;
        }
        
        @Override
        public BuffDrawer getBuffDrawer() {
            return new BuffDrawerNoStack(new CombatDependantPicture("Greater Brawler Aura","Greater Sharpshooter Aura","Greater Runic Accuracy Aura"));
        }
    }

    private static class MasterAccuracyAura extends AccuracyAura{
    
    @Override
    public String getName() {
        return "Master Accuracy Aura";
    }

    @Override
    public double getBoost() {
        return 1.07;
    }
    
    @Override
    public BuffDrawer getBuffDrawer() {
        return new BuffDrawerNoStack(new CombatDependantPicture("Master Brawler Aura","Master Sharpshooter Aura","Master Runic Accuracy Aura"));
    }
}

    private static class SupremeAccuracyAura extends AccuracyAura{
    
    @Override
    public String getName() {
        return "Supreme Accuracy Aura";
    }

    @Override
    public double getBoost() {
        return 1.1;
    }
    
    @Override
    public BuffDrawer getBuffDrawer() {
        return new BuffDrawerNoStack(new CombatDependantPicture("Supreme Brawler Aura","Supreme Sharpshooter Aura","Supreme Runic Accuracy Aura"));
    }
}
    
    private static class ZerkAura extends Aura{
        
        @Override
        public double getAccuracyMultiplier(Combatent owner,Combatent opponent){
            return 1.1;
        }
        
        @Override
        public void editStats(Combatent owner, EditableStats ownerEditableStats, Combatent opponent,EditableStats opponentEditableStats, int stackValue) {
            BaseStats ownerBaseStats = owner.getBaseStats();
            CombatStyle cbs = ownerEditableStats.getCombatStyle();
            double baseAccuracyLevel = ownerBaseStats.getAccuracyLevelFor(cbs);
            ownerEditableStats.addAccuracyLevelFor(cbs, baseAccuracyLevel*0.1);
            ownerEditableStats.addToDefenseLevel(-ownerBaseStats.getDefenseLevel()*.15);
        }
        
        @Override
        public BuffDrawer getBuffDrawer() {
            return new BuffDrawerNoStack(new CombatDependantPicture("Berserker Aura","Reckless Aura","Maniacal Aura"));
        }

        @Override
        public String getName() {
            return "Zerk Aura";
        }
    }
    
    
    private abstract static class Scrimshaw extends PictureBuff{

        @Override
        public String getExclusiveCategory() {
            return "Pocket";
        }

        @Override
        public double getAccuracyMultiplier(Combatent owner,Combatent opponent){
            return getHitChanceBoost();
        }
        
        public abstract double getHitChanceBoost();
    }
    
    private static class TradableScrimshaw extends Scrimshaw{

        @Override
        public String getName() {
            return "Tradable Scrimshaw";
        }

        @Override
        public double getHitChanceBoost() {
            return 1.02;
        }

        @Override
        public BuffDrawer getBuffDrawer() {
            return new BuffDrawerNoStack(new CombatDependantPicture("Scrimshaw of Attack","Scrimshaw of Ranging","Scrimshaw of Magic"));
        }
        
    }
    
    private static class SuperiorScrimshaw extends Scrimshaw{

        @Override
        public String getName() {
            return "Superior Scrimshaw";
        }
        
        @Override
        public BuffDrawer getBuffDrawer() {
            return new BuffDrawerNoStack(new CombatDependantPicture("Superior Scrimshaw of Attack","Superior Scrimshaw of Ranging","Superior Scrimshaw of Magic"));
        }

        @Override
        public double getHitChanceBoost() {
            return 1.04;
        }
        
    }
    
    
    //this class is only used to put in the monster's weakness list
    //the subclasses are used to put in the player buff list
    private static class BlackMaskBoost extends PictureBuff{

        @Override
        public String getExclusiveCategory() {
            return "Helmet";
        }
        
        //applies to final accuracy, not just weapon accuracy
        @Override
        public double getAccuracyMultiplier(Combatent owner,Combatent opponent){
            if(opponent.getVulnerabilities().weakToOther(this)){
                return getMultiplier();
            }else{
                return 1.0;
            }
        }
        
        
        
//////////to override
        public double getMultiplier(){
            return 1.125;
        }
//////////to override
        @Override
        public String getName() {
            return "SlayerMask";
        }
//////////to override
        @Override
        public BuffDrawer getBuffDrawer() {
            return new BuffDrawerNoStack(new BasicBuffPicture(PATH_TO_STAT_EFFECTS_PICS+"Black Mask"));
        }
        
    }
    
    private static class BlackMask extends BlackMaskBoost{
        
        @Override
        public String getName() {
            return "Black Mask";
        }
        
        @Override
        public BuffDrawer getBuffDrawer() {
            return new BuffDrawerNoStack(new CombatDependantPicture("Black Mask","Focus Sight","Hexcrest"));
        }

        @Override
        public double getMultiplier() {
            return 1.125;
        }
        
    }
    
    private abstract static class SlayerHelm extends BlackMaskBoost{
        
        @Override
        public BuffDrawer getBuffDrawer() {
            return new BuffDrawerNoStack(new BasicBuffPicture(PATH_TO_STAT_EFFECTS_PICS+getName()));
        }
        
    }
    
    
    private static class ReinforcedSlayerHelm extends SlayerHelm{

        @Override
        public String getName() {
            return "Reinforced Slayer Helmet";
        }
    
        @Override
        public double getMultiplier() {
            return 1.13;
        }
        
    }
    
    private static class StrongSlayerHelm extends SlayerHelm{

        @Override
        public String getName() {
            return "Strong Slayer Helmet";
        }
    
        @Override
        public double getMultiplier() {
            return 1.135;
        }
        
    }
    
    private static class MightySlayerHelm extends SlayerHelm{

        @Override
        public String getName() {
            return "Mighty Slayer Helmet";
        }
    
        @Override
        public double getMultiplier() {
            return 1.14;
        }
        
    }
    
    private static class CorruptedSlayerHelm extends SlayerHelm{

        @Override
        public String getName() {
            return "Corrupted Slayer Helmet";
        }
    
        @Override
        public double getMultiplier() {
            return 1.145;
        }
        
    }
    
    //this class for monster weakness only. Subclasses are for player buffs
    public static class BalmungClass extends PictureBuff{
        
        @Override
        public String getExclusiveCategory() {
            return "Weapon";
        }
        
        @Override
        public double addToFinalHitChance(Combatent owner,Combatent opponent,int stackValue){
            if(opponent.getVulnerabilities().weakToOther(this)){
                return getAddHitChance();
            }else{
                return 0.0;
            }
        }

        //override in subclass
        public double getAddHitChance(){
            return 30.0;
        }
        //override in subclass
        @Override
        public String getName() {
            return "BalmungClass";
        }
        
        @Override
        public BuffDrawer getBuffDrawer() {
            return new BuffDrawerNoStack(new BasicBuffPicture(WeaponFileReader.PATH_TO_WEAPON_PICS+getName()));
        }
        
    }
    
    public static class Balmung extends BalmungClass{

        @Override
        public String getName() {
            return "Balmung";
        }

        @Override
        public double getAddHitChance() {
            return 30.0;
        }
        
    }
    
    public static class UpgradedBalmung extends BalmungClass{

        @Override
        public String getName() {
            return "Upgraded Balmung";
        }

        @Override
        public double getAddHitChance() {
            return 45.0;
        }
        
    }
    
    public static abstract class Bane extends PictureBuff{
        
        @Override
        public String getExclusiveCategory() {
            return "Bane";
        }
        
        @Override
        public double addToFinalHitChance(Combatent owner,Combatent opponent,int stackValue){
            if(opponent.getVulnerabilities().weakToOther(this)){
                return 30.0;
            }else{
                return 0.0;
            }
        }
        
    }
    
    public static class Dragonbane extends Bane{

        @Override
        public String getName() {
            return "Dragonbane";
        }

        @Override
        public BuffDrawer getBuffDrawer() {
            return new BuffDrawerNoStack(new BasicBuffPicture(PATH_TO_STAT_EFFECTS_PICS+"Bane"));
        }
        
    }
    
    //this class is for monsters to see if they are weak to it. Subclasses are for players to use against
    private static class SilverlightClass extends PictureBuff{
        
        //override
        @Override
        public String getName() {
            return "SilverlightClass";
        }
        
        //override
        public double getAddToHitChance() {
            return 20;
        }

        @Override
        public String getExclusiveCategory() {
            return "Weapon";
        }
        
        @Override
        public double addToFinalHitChance(Combatent owner,Combatent opponent,int stackValue){
            if(opponent.getVulnerabilities().weakToOther(this)){
                return getAddToHitChance();
            }else{
                return 0.0;
            }
        }

        @Override
        public BuffDrawer getBuffDrawer() {
            return new BuffDrawerNoStack(new BasicBuffPicture(PATH_TO_STAT_EFFECTS_PICS+getName()));
        }
        
    }
    
    private static class BaseSilverlightBuff extends SilverlightClass {
        @Override
        public String getName() {
            // TODO Auto-generated method stub
            return "Base Silverlight Buff";
        }
        @Override
        public double getAddToHitChance() {
            return 20.0;
        }
    }
    private static class UpgradedSilverlightBuff extends SilverlightClass {
        @Override
        public String getName() {
            // TODO Auto-generated method stub
            return "Upgraded Silverlight Buff";
        }
        @Override
        public double getAddToHitChance() {
            return 30.0;
        }
    }
    
    
    //this class is for monsters to see if they are weak to it. Subclasses are for players to use against
    private static class SalveAmuletClass extends PictureBuff{
        
        //override
        @Override
        public String getName() {
            return "SalveAmuletClass";
        }
        
        //override
        public double getMultToHitChance() {
            return 1.15;
        }
        
        @Override
        public double getAccuracyMultiplier(Combatent owner,Combatent opponent){
            if(opponent.getVulnerabilities().weakToOther(this)){
                return getMultToHitChance();
            }else{
                return 1.0;
            }
        }

        

        @Override
        public String getExclusiveCategory() {
            return "Necklace";
        }

        @Override
        public BuffDrawer getBuffDrawer() {
            return new BuffDrawerNoStack(new BasicBuffPicture(PATH_TO_STAT_EFFECTS_PICS+getName()));
        }
        
    }
    
    private static class SalveAmulet extends SalveAmuletClass {
        @Override
        public String getName() {
            return "Salve Amulet";
        }
        @Override
        public double getMultToHitChance() {
            return 1.15;
        }
    }
    private static class SalveAmuletE extends SalveAmuletClass {
        @Override
        public String getName() {
            return "Salve Amulet (e)";
        }
        @Override
        public double getMultToHitChance() {
            return 1.2;
        }
    }
    
    //bridge pattern to give to give buffs flexibility to how they are drawn.
    private static abstract class BuffDrawer{
        
        private BuffPicture buffPicture;
        
        public BuffDrawer(BuffPicture buffPicture){
            this.buffPicture = buffPicture;
        }
        
        public abstract void draw(Graphics g, int x, int y, int size, int stackValue,CombatStyle combatStyle);
        
        public void drawPicture(Graphics g, int x, int y, int size, int stackValue,CombatStyle combatStyle){
            buffPicture.getPicture(combatStyle).draw(g, x, y, size, size);
        }
        
        public void drawStackValue(Graphics g, int x, int y, int size, int stackValue){
            g.setColor(Color.WHITE);
            g.setFont(new Font(g.getFont().getName(),Font.BOLD,12));
            g.drawString(Integer.toString(stackValue), x+2, y+size-2);
        }
    }
    
    private static class BuffDrawerPositive extends BuffDrawer{
        
        public BuffDrawerPositive(BuffPicture buffPicture) {
            super(buffPicture);
        }

        @Override
        public void draw(Graphics g, int x, int y, int size, int stackValue, CombatStyle combatStyle) {
            drawPicture(g,x,y,size,stackValue,combatStyle);
            drawStackValue(g,x,y,size,stackValue);
        }
        
    }
    
    private static class BuffDrawerPosNeg extends BuffDrawer{

        private BuffPicture buffPictureNeg;
        
        public BuffDrawerPosNeg(BuffPicture buffPicture,BuffPicture buffPictureNeg) {
            super(buffPicture);
            this.buffPictureNeg = buffPictureNeg;
        }
        
        @Override
        public void draw(Graphics g, int x, int y, int size, int stackValue, CombatStyle combatStyle) {
            if(stackValue>=0){
                drawPicture(g,x,y,size,stackValue,combatStyle);
            }else{
                buffPictureNeg.getPicture(combatStyle).draw(g, x, y, size, size);
            }
            
            drawStackValue(g,x,y,size,Math.abs(stackValue));
        }
        
    }

    private static class BuffDrawerNoStack extends BuffDrawer{

        public BuffDrawerNoStack(BuffPicture buffPicture) {
            super(buffPicture);
        }
        
        @Override
        public void draw(Graphics g, int x, int y, int size, int stackValue, CombatStyle combatStyle) {
            drawPicture(g,x,y,size,stackValue,combatStyle);
        }
    
    }
    
    private static class BasicBuffPicture implements BuffPicture{
        private ProxyPicture picture;
        
        public BasicBuffPicture(String fileName){
            this.picture = new ProxyPicture(fileName);
        }

        @Override
        public Picture getPicture(CombatStyle combatStyle) {
            return picture;
        }
    }
    
    private static class CombatDependantPicture implements BuffPicture{
        
        private ProxyPicture meleePicture;
        private ProxyPicture rangedPicture;
        private ProxyPicture magicPicture;
        
        public CombatDependantPicture(String meleeFileName, String rangedFileName,String magicFileName){
            this.meleePicture = new ProxyPicture(PATH_TO_STAT_EFFECTS_PICS+meleeFileName);
            this.rangedPicture = new ProxyPicture(PATH_TO_STAT_EFFECTS_PICS+rangedFileName);
            this.magicPicture = new ProxyPicture(PATH_TO_STAT_EFFECTS_PICS+magicFileName);
        }
        
        public Picture getPicture(CombatStyle combatStyle){
            if(combatStyle instanceof Melee){
                return meleePicture;
            }
            if(combatStyle instanceof Ranged){
                return rangedPicture;
            }
            if(combatStyle instanceof Magic){
                return magicPicture;
            }
            
            System.out.println("BuffFactory.CombatDepandantPicture: combat style is not melee,range,or magic. returned default picture");
            return ResourceGetter.getDefaultPicture();
        }
    }
    
}
