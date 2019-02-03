package rsHitchance;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import images.Picture;
import images.ResourceGetter;
import rsHitchance.BaseStats.EditableStats;
import rsHitchance.CombatStyleFactory.Magic;
import rsHitchance.CombatStyleFactory.Melee;
import rsHitchance.CombatStyleFactory.Ranged;
import myAlgs.MyEvent;
import myAlgs.MyListener;

//shows the player's stats and buffs
//the buffs may be displayed differently, so a seperate BuffContainer is used
public class PlayerProfilePanel extends JPanel{
    
    public static final int HEIGHT_OF = 300;
    
    private MyListener listener;
    
    private EditableStats playerStats;//copy of player panels's. do not edit
    private BuffContainer displayBuffs;//for display only. Complete list of player buffs is in player panel
    private int boostedLevel;
    
    private Picture meleePic;
    private Picture rangedPic;
    private Picture magicPic;
    
    public PlayerProfilePanel(EditableStats playerStats,int width,MyListener listener){
        this.listener = listener;
        this.setBackground(Color.DARK_GRAY);
        this.setPreferredSize(new Dimension(width,HEIGHT_OF));
        
        this.playerStats = playerStats;
        this.displayBuffs = new BuffContainer();
        this.boostedLevel = playerStats.getAccuracyLevelFor(playerStats.getCombatStyle());
        
        this.meleePic = ResourceGetter.getPicture("images/RSHitchance/Attack Icon");
        this.rangedPic = ResourceGetter.getPicture("images/RSHitchance/Ranged Icon");
        this.magicPic = ResourceGetter.getPicture("images/RSHitchance/Magic Icon");
    }
    
    @Override
    public Dimension getMaximumSize(){
        Dimension size = getPreferredSize();
        return size;
    }
    
    @Override
    public Dimension getMinimumSize(){
        Dimension size = getPreferredSize();
        return size;
    }
    
    public void setBuff(Buff buff, int stackValue){
        displayBuffs.setBuff(buff,stackValue);
    }
    public void setBuff(Buff buff){
        displayBuffs.setBuff(buff);
    }
    public void removeBuff(Buff buff) {
        displayBuffs.removeBuff(buff);
    }
    public void removeBuff(String string) {
        displayBuffs.removeBuff(string);
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        drawLevels(g);
        displayBuffs.draw(g, 350, 20,playerStats.getCombatStyle());
    }

    private void drawLevels(Graphics g) {
        Picture pic;
        if(playerStats.getCombatStyle() instanceof Melee){
            pic = meleePic;
        }else if(playerStats.getCombatStyle() instanceof Ranged){
            pic = rangedPic;
        }else if(playerStats.getCombatStyle() instanceof Magic){
            pic = magicPic;
        }else{
            pic = ResourceGetter.getDefaultPicture();
            System.out.println("unrecognized combat style in PlayerProfilePanel:drawLevels()");
        }
        
        pic.draw(g, 5, 5, 20, 20);
        
        g.setColor(Color.BLACK);
        g.setFont(new Font(g.getFont().getName(),Font.BOLD,20));
        g.drawString(boostedLevel + "/" + Integer.toString(playerStats.getAccuracyLevelFor(playerStats.getCombatStyle())), 25, 25);
        
        g.setColor(Color.BLACK);
        g.setFont(new Font(g.getFont().getName(),Font.BOLD,15));
        g.drawString(Integer.toString(playerStats.getAccuracyFor(playerStats.getCombatStyle())), 25, 40);
    }

    public void editBoostedLevel(int accuracyLevelFor) {
        this.boostedLevel = accuracyLevelFor;
    }

    

    
}
