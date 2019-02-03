package rsHitchance;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import rsHitchance.BaseStats.EditableStats;
import myAlgs.MyEvent;
import myAlgs.MyListener;

//main parent panel. In charge of calculations and updating the HitchancePanel
public class RSHitchancePanel extends JPanel implements MyListener{
    
    PlayerPanel playerPanel;
    HitchancePanel hitchancePanel;
    MonsterPanel monsterPanel;
    
    public RSHitchancePanel(){
        this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        
        this.playerPanel = new PlayerPanel(this);
        this.hitchancePanel = new HitchancePanel();
        this.monsterPanel = new MonsterPanel(this);
        
        playerPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        hitchancePanel.setAlignmentY(Component.TOP_ALIGNMENT);
        monsterPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        
        add(playerPanel);
        add(Box.createHorizontalGlue());
        add(hitchancePanel);
        add(Box.createHorizontalGlue());
        add(monsterPanel);
        
    }

    @Override
    public void eventHappened(MyEvent e) {
        if(e.getCommand().equals("Calculate Hitchance")){
            Combatent monster = monsterPanel.getMonster();
            EditableStats monsterEditableStats = monster.getBaseStats().getEditableStats();
            BuffContainer monsterBuffs = monsterPanel.getMonsterBuffs();
            
            Combatent player = getPlayer();
            EditableStats playerEditableStats = player.getBaseStats().getEditableStats();
            BuffContainer playerBuffs = playerPanel.getPlayerBuffs();
            
            monsterBuffs.editStats(monster, monsterEditableStats, player, playerEditableStats);;
            playerBuffs.editStats(player, playerEditableStats, monster, monsterEditableStats);
            
            
            
            double monsterDefense = monsterEditableStats.getArmour()+Global.levelFunction(monsterEditableStats.getDefenseLevel());
            double playerAccuracy = Global.levelFunction(playerEditableStats.getAccuracyLevelFor(playerEditableStats.getCombatStyle()))
                                    + playerEditableStats.getAccuracyFor(playerEditableStats.getCombatStyle());
            
            double accuracyMultiplier = playerBuffs.getAccuracyMultiplier(player,monster);
            playerAccuracy*=accuracyMultiplier;
            
            double hitchance = calculateHitChanceFinalValues(playerAccuracy, monsterDefense, monsterEditableStats.getAffinityTo(playerEditableStats.getCombatStyle()));
            
            double hitchancePlayerAdd = playerBuffs.addToFinalHitChance(player, monster);
            
            double finalHitChance = hitchance+hitchancePlayerAdd;
            
            hitchancePanel.updateHitChance(finalHitChance);
            hitchancePanel.updateAccuracy((int) playerAccuracy);
            hitchancePanel.updateMonsterDefense((int) monsterDefense);
            hitchancePanel.updateMonsterAffinity((int) monsterEditableStats.getAffinityTo(playerEditableStats.getCombatStyle()));
            hitchancePanel.updateAddChance(finalHitChance-hitchance);
            
            playerPanel.editBoostedLevel(playerEditableStats.getAccuracyLevelFor(playerEditableStats.getCombatStyle()));
        }
    }
    
    public double calculateHitChanceFinalValues(double playerAccuracy,double monsterDefense, double monsterAffinity){
        double hitchance = playerAccuracy/monsterDefense*monsterAffinity;
//        System.out.println("player accuracy: " + playerAccuracy + " monsterDefense: " + monsterDefense + " accuracyAdd: " + " monsterAffinity: " + monsterAffinity + " hitchance: " + hitchance);
        return hitchance;
    }
    
    private Player getPlayer() {
        EditableStats editableStats = playerPanel.getPlayerBaseStats();
        return new Player(editableStats);
    }
}
