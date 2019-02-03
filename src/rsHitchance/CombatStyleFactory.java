package rsHitchance;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import images.Picture;
import images.ProxyPicture;
import images.ResourceGetter;

//flyweight pattern.
//the three main combat styles are Melee, Ranged, and Magic.
//These each have more specific subclasses based on what weapon type or spell you are using
public class CombatStyleFactory {
    
    private static Map<String,CombatStyle> cbsMap;
    
    public static CombatStyle getCombatStyle(String str){
        if(cbsMap==null){
            makeMap();
        }
        
        CombatStyle cbs = cbsMap.get(str);
        if(cbs==null){
            System.out.println("unrecognized combat style: " + str + " " + " Using None instead");
            return cbsMap.get("None");
        }else{
            return cbs;
        }
    }
    
    private static void makeMap() {
        cbsMap = new HashMap<>();
        
        CombatStyle cbs;
        
        cbs = new Melee();
        cbsMap.put(cbs.getName(),cbs);
        
        cbs = new Ranged();
        cbsMap.put(cbs.getName(),cbs);
        
        cbs = new Magic();
        cbsMap.put(cbs.getName(),cbs);
        
        cbs = new None();
        cbsMap.put(cbs.getName(),cbs);
        
        cbs = new Stab();
        cbsMap.put(cbs.getName(),cbs);
        
        cbs = new Slash();
        cbsMap.put(cbs.getName(),cbs);
        
        cbs = new Crush();
        cbsMap.put(cbs.getName(),cbs);
        
        cbs = new Arrows();
        cbsMap.put(cbs.getName(),cbs);
        
        cbs = new Bolts();
        cbsMap.put(cbs.getName(),cbs);
        
        cbs = new Thrown();
        cbsMap.put(cbs.getName(),cbs);
        
        cbs = new Air();
        cbsMap.put(cbs.getName(),cbs);
        
        cbs = new Water();
        cbsMap.put(cbs.getName(),cbs);
        
        cbs = new Earth();
        cbsMap.put(cbs.getName(),cbs);
        
        cbs = new Fire();
        cbsMap.put(cbs.getName(),cbs);
    }

    //Yes there is a static nested class in java. When you declare a nested class static
    //, it automatically becomes a stand alone class which can be instantiated without
    //having to instantiate the outer class it belongs to.
    
    
    private static abstract class CombatStyleImp implements CombatStyle{

        private ProxyPicture picture;
        
        private CombatStyleImp(){
            picture = new ProxyPicture("images/RSHitchance/Combat Styles/" + getName());
        }
        

        @Override
        public void draw(Graphics g, int x, int y,int size) {
            picture.draw(g, x, y, size, size);
        }
        
    }
    
    
    
    public static class Melee extends CombatStyleImp{
        
        private static final Color color = new Color(255,0,0);
        
        @Override
        public String getName() {
            return "Melee";
        }

        @Override
        public Color getColor() {
            return color;
        }
    }
    
    public static class Ranged extends CombatStyleImp{
        
        private static final Color color = new Color(0,255,0);
        
        @Override
        public String getName() {
            return "Ranged";
        }
        
        @Override
        public Color getColor() {
            return color;
        }
    }
    
    public static class Magic extends CombatStyleImp{
        
        private static final Color color = new Color(0,0,255);
        
        @Override
        public String getName() {
            return "Magic";
        }
        
        @Override
        public Color getColor() {
            return color;
        }
    }
    
    public static class None extends CombatStyleImp{
        @Override
        public String getName() {
            return "None";
        }
        
        @Override
        public Color getColor() {
            return new Color(0,0,0);
        }
    }
    
    private static class Stab extends Melee{
        @Override
        public String getName() {
            return "Stab";
        }
    }
    
    private static class Slash extends Melee{
        @Override
        public String getName() {
            return "Slash";
        }
    }
    
    private static class Crush extends Melee{
        @Override
        public String getName() {
            return "Crush";
        }
    }
    
    private static class Arrows extends Ranged{
        @Override
        public String getName() {
            return "Arrows";
        }
    }
    
    private static class Bolts extends Ranged{
        @Override
        public String getName() {
            return "Bolts";
        }
    }
    
    private static class Thrown extends Ranged{
        @Override
        public String getName() {
            return "Thrown";
        }
    }
    
    private static class Air extends Magic{
        @Override
        public String getName() {
            return "Air";
        }
    }
    
    private static class Water extends Magic{
        @Override
        public String getName() {
            return "Water";
        }
    }
    
    private static class Earth extends Magic{
        @Override
        public String getName() {
            return "Earth";
        }
    }
    
    private static class Fire extends Magic{
        @Override
        public String getName() {
            return "Fire";
        }
    }
}
