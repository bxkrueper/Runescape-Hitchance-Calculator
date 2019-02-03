package rsHitchance;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

import images.Picture;
import images.ProxyPicture;

//contains whether or not a monster is vulnerable to poison,deflect,stun, and drain
//as well as special vulnerabilities that affect hitchance
public class Vulnerabilities {
    
    private static ProxyPicture poisonImmune;
    private static ProxyPicture deflectImmune;
    private static ProxyPicture stunImmune;
    private static ProxyPicture drainImmune;
    
    private static ProxyPicture poisonVuln;
    private static ProxyPicture deflectVuln;
    private static ProxyPicture stunVuln;
    private static ProxyPicture drainVuln;
    
    private boolean weakToPoison;
    private boolean weakToReflect;
    private boolean weakToStun;
    private boolean weakToDrain;
    private List<Buff> otherVulnerabilities;
    
    public Vulnerabilities(boolean weakToPoison, boolean weakToReflect, boolean weakToStun,boolean weakToDrain,List<Buff> otherVulns){
        this.weakToPoison = weakToPoison;
        this.weakToReflect = weakToReflect;
        this.weakToStun = weakToStun;
        this.weakToDrain = weakToDrain;
        this.otherVulnerabilities = otherVulns;
        
        if(poisonImmune==null){
            String directory = "images/RSHitchance/Vulnerabilities/";
            poisonImmune = new ProxyPicture(directory + "Poison Immune");
            poisonVuln = new ProxyPicture(directory + "Poisoned");
            deflectImmune = new ProxyPicture(directory + "Deflect Immune");
            deflectVuln = new ProxyPicture(directory + "Reflect");
            stunImmune = new ProxyPicture(directory + "Stun Immune");
            stunVuln = new ProxyPicture(directory + "Stunned");
            drainImmune = new ProxyPicture(directory + "Drain Immune");
            drainVuln = new ProxyPicture(directory + "Defence Rating Drained");
        }
        
    }
    public Vulnerabilities(boolean weakToPoison, boolean weakToReflect, boolean weakToStun,boolean weakToDrain,boolean weakToSlayerHelm, boolean weakToHexhunter){
        this(weakToPoison,weakToReflect,weakToStun,weakToDrain,new LinkedList<Buff>());
    }
    
    public boolean weakToPoison(){
        return weakToPoison;
    }
    public boolean weakToReflect(){
        return weakToReflect;
    }
    public boolean weakToStun(){
        return weakToStun;
    }
    public boolean weakToDrain(){
        return weakToDrain;
    }
    //returns true if it contains a buff which is equal or a superclass of the input
    public boolean weakToOther(Buff buff){
        for(Buff vuln: otherVulnerabilities){
            if(vuln.getClass().isInstance(buff)){
                return true;
            }
        }
        return false;
    }

    public void draw(Graphics g, int x, int y) {
        int yOffset = 0;
        if(weakToPoison){
            poisonVuln.draw(g, x+20, y+yOffset, 20, 20);
        }else{
            poisonImmune.draw(g, x, y+yOffset, 20, 20);
        }
        yOffset+=20;
        if(weakToReflect){
            deflectVuln.draw(g, x+20, y+yOffset, 20, 20);
        }else{
            deflectImmune.draw(g, x, y+yOffset, 20, 20);
        }
        yOffset+=20;
        if(weakToStun){
            stunVuln.draw(g, x+20, y+yOffset, 20, 20);
        }else{
            stunImmune.draw(g, x, y+yOffset, 20, 20);
        }
        yOffset+=20;
        if(weakToDrain){
            drainVuln.draw(g, x+20, y+yOffset, 20, 20);
        }else{
            drainImmune.draw(g, x, y+yOffset, 20, 20);
        }
        
        for(Buff buff: otherVulnerabilities){
            yOffset+=20;
            buff.draw(g, x+20, y+yOffset, 20, -1, CombatStyleFactory.getCombatStyle("None"));/////////////combat style not needed?
        }
    }
}
