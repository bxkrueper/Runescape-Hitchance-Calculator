package rsHitchance;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;


//displays the hit chance along with some intermediate values which are told to change by the parent
public class HitchancePanel extends JPanel{
    
    public static final int WIDTH_OF = 150;
    public static final int HEIGHT_OF = 120;
    
    private JLabel hitchanceLabel;
    private JLabel accuracyLabel;
    private JLabel monsterDefenseLabel;
    private JLabel monsterAffinityLabel;
    private JLabel addChanceLabel;
    
    public HitchancePanel(){
        this.setBackground(Color.YELLOW);
        this.setPreferredSize(new Dimension(WIDTH_OF,HEIGHT_OF));
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        
        this.hitchanceLabel = new JLabel("0%");
        this.accuracyLabel = new JLabel("Accuracy: ");
        this.monsterDefenseLabel = new JLabel("Monster Defense: ");
        this.monsterAffinityLabel = new JLabel("Monster Affinity: ");
        this.addChanceLabel = new JLabel("Add Hit Chance: ");
        
        hitchanceLabel.setFont(new Font(hitchanceLabel.getFont().getName(),Font.BOLD,30));
        accuracyLabel.setFont(new Font(accuracyLabel.getFont().getName(),Font.PLAIN,12));
        monsterDefenseLabel.setFont(new Font(monsterDefenseLabel.getFont().getName(),Font.PLAIN,12));
        monsterAffinityLabel.setFont(new Font(monsterAffinityLabel.getFont().getName(),Font.PLAIN,12));
        addChanceLabel.setFont(new Font(addChanceLabel.getFont().getName(),Font.PLAIN,12));
        
        add(hitchanceLabel);
        add(accuracyLabel);
        add(monsterDefenseLabel);
        add(monsterAffinityLabel);
        add(addChanceLabel);
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
    
    public void updateHitChance(double hc){
        String str = String.format("%.2f", hc) + "%";
        hitchanceLabel.setText(str);
    }
    
    public void updateAccuracy(int accuracy){
        String str = String.format("%,d", accuracy);
        accuracyLabel.setText("Accuracy: " + str);
    }
    
    public void updateMonsterDefense(int defense){
        String str = String.format("%,d", defense);
        monsterDefenseLabel.setText("Monster Defense: " + str);
    }
    
    public void updateMonsterAffinity(int affinity){
        String str = String.format("%,d", affinity);
        monsterAffinityLabel.setText("Monster Affinity: " + str);
    }
    
    public void updateAddChance(double addChance){
        String str = String.format("%,.2f", addChance);
        addChanceLabel.setText("Add Hit Chance: " + str);
    }
}