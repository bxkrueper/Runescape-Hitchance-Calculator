package rsHitchance;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import myAlgs.MyEvent;
import myAlgs.MyListener;

//stores and shows the monster and its buffs and vulurabilities
public class MonsterProfilePanel extends JPanel{

    public static final int HEIGHT_OF = 300;
    
    private Monster monster;
    
    private MyListener listener;
    
    private BuffContainer buffContainer;
    
    public MonsterProfilePanel(Monster monster,int width,MyListener listener){
        this.listener = listener;
        this.monster = monster;
        this.setBackground(Color.DARK_GRAY);
        this.setPreferredSize(new Dimension(width,HEIGHT_OF));
        setLayout(new FlowLayout());
        
        this.buffContainer = new BuffContainer();
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
    
    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster newMonster) {
        this.monster = newMonster;
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        monster.getPicture().draw(g, 0, 0, getWidth(), getHeight());
        monster.getVulnerabilities().draw(g,0,40);
        monster.getBaseStats().getAffinityWeakness().draw(g, 0, 0, 40);
        buffContainer.draw(g, 350, 40,monster.getBaseStats().getCombatStyle());
    }

    public void setBuff(Buff buff,int stackValue) {
        buffContainer.setBuff(buff, stackValue);
        repaint();
    }
    
    public void removeBuff(Buff buff) {
        buffContainer.removeBuff(buff);
        repaint();
    }
    
    public BuffContainer getMonsterBuffs(){
        return buffContainer;
    }

    
}
