package rsHitchance;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import rsHitchance.BaseStats.EditableStats;
import myAlgs.MyEvent;
import myAlgs.MyListener;

//acts as a MVC
//the entire left side of the frame
public class PlayerPanel extends JPanel implements MyListener{
    
    public static final int WIDTH_OF = 400;
    public static final int HEIGHT_OF = 700;
    
    private MyListener listener;
    
    private JPanel playerNamePanel;
    private PlayerProfilePanel playerProfilePanel;
    private PlayerScrollPanel playerScrollPanel;
    
    private EditableStats editableStats;
    private BuffContainer buffContainer;//this stores the actual buffs. player profile panel is given buffs just for display
    
    public PlayerPanel(MyListener listener){
        this.listener = listener;
        
        this.setBackground(Color.RED);
        this.setPreferredSize(new Dimension(WIDTH_OF,HEIGHT_OF));
        
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        
        this.setPreferredSize(new Dimension(WIDTH_OF,this.getHeight()));
        
        
        this.playerNamePanel = new JPanel();
        JLabel playerNameLabel = new JLabel("Player");
        playerNameLabel.setFont(new Font(playerNameLabel.getFont().getName(),Font.BOLD,30));
        playerNameLabel.setOpaque(true);
        playerNamePanel.add(playerNameLabel);
        
        
        //make player scroll panel options line up with what the initial info for the player
        //2577 is F(92)
        BaseStats defaultStats = new BaseStats(99,2577,99,2577,99,2577,CombatStyleFactory.getCombatStyle("Melee"),0,0,new AffinityWeaknesses(40,40,40,WeaknessFactory.getAffinityWeakness(CombatStyleFactory.getCombatStyle("None"), -1)));
        this.editableStats = defaultStats.getEditableStats();
        this.buffContainer = new BuffContainer();
        
        
        
        this.playerScrollPanel = new PlayerScrollPanel(0,99,92,WIDTH_OF,this);
        this.playerProfilePanel = new PlayerProfilePanel(editableStats,WIDTH_OF,this);
        
        add(playerNamePanel);
        add(playerProfilePanel);
        add(playerScrollPanel);
        
        
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

    //reacts to changes in the PlayerScrollPanel by updating the player's true and display buffs
    @Override
    public void eventHappened(MyEvent e) {
        if(e.getCommand().equals("Combat Style Changed")){
            editableStats.setCombatStyle((CombatStyle) e.getData());
        }else if(e.getCommand().equals("Weapon Buff Changed")){
            Buff buff = (Buff) e.getData();
            if(buff==null){
                buffContainer.removeBuff("Weapon");//exclusive category string
                playerProfilePanel.removeBuff("Weapon");//
            }else{
                buffContainer.setBuff(buff);
                playerProfilePanel.setBuff(buff);
            }
            
        }else if(e.getCommand().equals("Potion Changed")){
            Buff buff = (Buff) e.getData();
            if(buff==null){
                buffContainer.removeBuff("Stat Boosting Potion");//exclusive category string
                playerProfilePanel.removeBuff("Stat Boosting Potion");//
            }else{
                buffContainer.setBuff(buff);
                playerProfilePanel.setBuff(buff);
            }
            
        }else if(e.getCommand().equals("Prayer Changed")){
            Buff buff = (Buff) e.getData();
            if(buff==null){
                buffContainer.removeBuff("Stat Boosting Prayers");//exclusive category string
                playerProfilePanel.removeBuff("Stat Boosting Prayers");//
            }else{
                buffContainer.setBuff(buff,playerScrollPanel.getCurseStack());
                playerProfilePanel.setBuff(buff,playerScrollPanel.getCurseStack());
            }
        }else if(e.getCommand().equals("Curse Stack Changed")){
            int newStack = (Integer) e.getData();
            Buff currentPrayerBuff = buffContainer.getBuff("Stat Boosting Prayers");
            buffContainer.setBuff(currentPrayerBuff,newStack);
            playerProfilePanel.setBuff(currentPrayerBuff,newStack);
        }else if(e.getCommand().equals("Aura Changed")){
            Buff buff = (Buff) e.getData();
            if(buff==null){
                buffContainer.removeBuff("Aura");//exclusive category string
                playerProfilePanel.removeBuff("Aura");//
            }else{
                buffContainer.setBuff(buff);
                playerProfilePanel.setBuff(buff);
            }
        }else if(e.getCommand().equals("Scrimshaw Changed")){
            Buff buff = (Buff) e.getData();
            if(buff==null){
                buffContainer.removeBuff("Pocket");//exclusive category string
                playerProfilePanel.removeBuff("Pocket");//
            }else{
                buffContainer.setBuff(buff);
                playerProfilePanel.setBuff(buff);
            }
        }else if(e.getCommand().equals("Slayer Mask Changed")){
            Buff buff = (Buff) e.getData();
            if(buff==null){
                buffContainer.removeBuff("Helmet");//exclusive category string
                playerProfilePanel.removeBuff("Helmet");//
            }else{
                buffContainer.setBuff(buff);
                playerProfilePanel.setBuff(buff);
            }
        }else if(e.getCommand().equals("Accuracy Level Changed")){
            int newLevel = (Integer) e.getData();
            editableStats.setAttackLevel(newLevel);
            editableStats.setRangedLevel(newLevel);
            editableStats.setMagicLevel(newLevel);
        }else if(e.getCommand().equals("Weapon Level Changed")){
            int level = (Integer) e.getData();
            int accuracy = (int) (2.5*Global.levelFunction(level));
            editableStats.setMeleeAccuracy(accuracy);
            editableStats.setRangedAccuracy(accuracy);
            editableStats.setMagicAccuracy(accuracy);
        }else if(e.getCommand().equals("Reaper Stack Changed")){
            Buff buff = BuffFactory.getBuff("Reaper Necklace");
            int stackValue = (Integer) e.getData();
            if(stackValue==0){
                buffContainer.removeBuff(buff);
                playerProfilePanel.removeBuff(buff);
            }else{
                buffContainer.setBuff(buff,stackValue);
                playerProfilePanel.setBuff(buff,stackValue);
            }
        }else if(e.getCommand().equals("Necklace Changed")){
            Buff buff = (Buff) e.getData();
            
            if(buff==null){
                buffContainer.removeBuff("Necklace");//exclusive category string
                playerProfilePanel.removeBuff("Necklace");//
            }else if(buff.getName().equals("Reaper Necklace")){
                
                int stackValue = playerScrollPanel.getReaperStack();
                if(stackValue==0){
                    buffContainer.removeBuff("Necklace");//exclusive category string
                    playerProfilePanel.removeBuff("Necklace");//
                }else{
                    buffContainer.setBuff(buff,stackValue);
                    playerProfilePanel.setBuff(buff,stackValue);
                }
            }else{
                buffContainer.setBuff(buff);
                playerProfilePanel.setBuff(buff);
            }
        }else if(e.getCommand().equals("Defender Changed")){
            Buff buff = BuffFactory.getBuff("Defender");
            boolean has = (Boolean) e.getData();
            if(has){
                buffContainer.setBuff(buff);
                playerProfilePanel.setBuff(buff);
            }else{
                buffContainer.removeBuff(buff);
                playerProfilePanel.removeBuff(buff);
            }
        }else if(e.getCommand().equals("Nihil Changed")){
            Buff buff = BuffFactory.getBuff("Nihil");
            boolean has = (Boolean) e.getData();
            if(has){
                buffContainer.setBuff(buff);
                playerProfilePanel.setBuff(buff);
            }else{
                buffContainer.removeBuff(buff);
                playerProfilePanel.removeBuff(buff);
            }
        }else if(e.getCommand().equals("Sonic Wave Changed")){
            Buff buff = BuffFactory.getBuff("Sonic Wave Buff");
            boolean has = (Boolean) e.getData();
            if(has){
                buffContainer.setBuff(buff);
                playerProfilePanel.setBuff(buff);
            }else{
                buffContainer.removeBuff(buff);
                playerProfilePanel.removeBuff(buff);
            }
        }else if(e.getCommand().equals("Ultimate Changed")){
            Buff buff = BuffFactory.getBuff("Ultimate Ability");
            boolean has = (Boolean) e.getData();
            if(has){
                buffContainer.setBuff(buff);
                playerProfilePanel.setBuff(buff);
            }else{
                buffContainer.removeBuff(buff);
                playerProfilePanel.removeBuff(buff);
            }
        }else if(e.getCommand().equals("Using Bane Ammo Changed")){
            Buff buff = BuffFactory.getBuff("Dragonbane");
            boolean has = (Boolean) e.getData();
            if(has){
                buffContainer.setBuff(buff);
                playerProfilePanel.setBuff(buff);
            }else{
                buffContainer.removeBuff(buff);
                playerProfilePanel.removeBuff(buff);
            }
        }else{
            System.out.println("Player panel recieved unrecognized event: " + e.getCommand());
        }
        
        playerProfilePanel.repaint();
        listener.eventHappened(new MyEvent(this,"Calculate Hitchance",null));
    }

    public EditableStats getPlayerBaseStats() {
        return editableStats;
    }

    public BuffContainer getPlayerBuffs() {
        return buffContainer;
    }

    //Updates the player's boosted level from information obtained after the buffs were applied
    public void editBoostedLevel(int accuracyLevelFor) {
        playerProfilePanel.editBoostedLevel(accuracyLevelFor);
    }
}
