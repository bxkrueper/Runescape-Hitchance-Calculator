package rsHitchance;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import myAlgs.MyEvent;
import myAlgs.MyListener;

//the entire right side of the frame
public class MonsterPanel extends JPanel implements MyListener{
    
    public static final int WIDTH_OF = 400;
    public static final int HEIGHT_OF = 700;
    
    private MyListener listener;
    
    private MonsterNamePanel monsterNamePanel;
    private MonsterProfilePanel monsterProfilePanel;
    private StatPanel monsterStatPanel;
    private MonsterScrollPanel monsterScrollPanel;
    private MonsterDebuffPanel monsterDebuffPanel;
    
    private JPanel topPanel;
    
    public MonsterPanel(MyListener listener){
        this.listener = listener;
        this.setBackground(Color.BLUE);
        this.setPreferredSize(new Dimension(WIDTH_OF,HEIGHT_OF));
        
        this.setLayout(new GridBagLayout());
        
        this.topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel,BoxLayout.Y_AXIS));

        
        topPanel.setPreferredSize(new Dimension(WIDTH_OF,this.getHeight()-MonsterDebuffPanel.HEIGHT_OF));
        topPanel.setBackground(Color.CYAN);
        
        
        Monster nullMonster = new NullMonster();
        this.monsterNamePanel = new MonsterNamePanel(nullMonster,this);
        topPanel.add(monsterNamePanel);
        
        this.monsterProfilePanel = new MonsterProfilePanel(nullMonster,WIDTH_OF,this);
        topPanel.add(monsterProfilePanel);
        
        this.monsterStatPanel = new StatPanel(nullMonster.getBaseStats());
        topPanel.add(monsterStatPanel);
        
        this.monsterScrollPanel = new MonsterScrollPanel(WIDTH_OF,this);
        topPanel.add(monsterScrollPanel);
        
        GridBagConstraints c = new GridBagConstraints();
        
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = .5;
        c.weighty = .5;
        add(topPanel,c);
        
        this.monsterDebuffPanel = new MonsterDebuffPanel(WIDTH_OF,this);
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.PAGE_END;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0;
        c.weighty = 0;
        add(monsterDebuffPanel,c);
    }
    
    @Override
    public Dimension getMaximumSize(){
        Dimension size = getPreferredSize();
        size.height = 7000;
        return size;
    }
    
    @Override
    public Dimension getMinimumSize(){
        Dimension size = getPreferredSize();
        size.height = HEIGHT_OF;
        return size;
    }

    //mediates between the child panels and keeps them and the hitchance updated
    @Override
    public void eventHappened(MyEvent e) {
        if(e.getCommand().equals("Monster Selected")){
            Monster monster = (Monster) e.getData();
            monsterProfilePanel.setMonster(monster);
            monsterStatPanel.setStats(monster.getBaseStats());
            if(e.getSource()==monsterNamePanel){
                monsterScrollPanel.redrawMonsterSelectPanel((Monster) e.getData());
            }else if(e.getSource()==monsterScrollPanel){
                monsterNamePanel.setMonster(monster);
            }
        }else if(e.getCommand().equals("affinity mod updated")){//from monster debuff panel
            Buff armourBreak = BuffFactory.getBuff("Armour Break");
            int stackValue = (Integer) e.getData();
            if(stackValue==0){
                monsterProfilePanel.removeBuff(armourBreak);
            }else{
                monsterProfilePanel.setBuff(armourBreak,stackValue);
            }
            
        }
        
        listener.eventHappened(new MyEvent(this,"Calculate Hitchance",null));
    }

    public Monster getMonster() {
        return monsterProfilePanel.getMonster();
    }

    public BuffContainer getMonsterBuffs() {
        return monsterProfilePanel.getMonsterBuffs();
    }


}