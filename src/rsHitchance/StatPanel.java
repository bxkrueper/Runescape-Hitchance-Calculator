package rsHitchance;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import images.ResourceGetter;

//displayes the given base stats
public class StatPanel extends JPanel{
    
    private BaseStats baseStats;
    
    private JLabel attackLevelLabel;
    private JLabel rangedLevelLabel;
    private JLabel magicLevelLabel;
    private JLabel defenseLevelLabel;
    private JLabel meleeAccuracyLabel;
    private JLabel rangedAccuracyLabel;
    private JLabel magicAccuracyLabel;
    private JLabel armourLabel;
    private JLabel meleeAffinityLabel;
    private JLabel rangedAffinityLabel;
    private JLabel magicAffinityLabel;
    
    public StatPanel(BaseStats baseStats){
        this.attackLevelLabel = new JLabel();
        this.rangedLevelLabel = new JLabel();
        this.magicLevelLabel = new JLabel();
        this.defenseLevelLabel = new JLabel();
        this.meleeAccuracyLabel = new JLabel();
        this.rangedAccuracyLabel = new JLabel();
        this.magicAccuracyLabel = new JLabel();
        this.armourLabel = new JLabel();
        this.meleeAffinityLabel = new JLabel();
        this.rangedAffinityLabel = new JLabel();
        this.magicAffinityLabel = new JLabel();
        
        setStats(baseStats);
        
        meleeAccuracyLabel.setOpaque(true);
        rangedAccuracyLabel.setOpaque(true);
        magicAccuracyLabel.setOpaque(true);
        armourLabel.setOpaque(true);
        meleeAffinityLabel.setOpaque(true);
        rangedAffinityLabel.setOpaque(true);
        magicAffinityLabel.setOpaque(true);
        
        meleeAccuracyLabel.setBackground(Color.RED);
        rangedAccuracyLabel.setBackground(Color.GREEN);
        magicAccuracyLabel.setBackground(Color.CYAN);
        armourLabel.setBackground(Color.GRAY);
        meleeAffinityLabel.setBackground(Color.GRAY);
        rangedAffinityLabel.setBackground(Color.GRAY);
        magicAffinityLabel.setBackground(Color.GRAY);
        meleeAffinityLabel.setForeground(Color.RED);
        rangedAffinityLabel.setForeground(Color.GREEN);
        magicAffinityLabel.setForeground(Color.BLUE);
        
        Font levelFont = new Font(meleeAccuracyLabel.getFont().getName(),Font.BOLD,20);
        Font accFont = new Font(meleeAccuracyLabel.getFont().getName(),Font.PLAIN,15);
        Font affinityFont = new Font(meleeAccuracyLabel.getFont().getName(),Font.PLAIN,20);
        
        attackLevelLabel.setFont(levelFont);
        rangedLevelLabel.setFont(levelFont);
        magicLevelLabel.setFont(levelFont);
        defenseLevelLabel.setFont(levelFont);
        meleeAccuracyLabel.setFont(accFont);
        rangedAccuracyLabel.setFont(accFont);
        magicAccuracyLabel.setFont(accFont);
        armourLabel.setFont(accFont);
        meleeAffinityLabel.setFont(affinityFont);
        rangedAffinityLabel.setFont(affinityFont);
        magicAffinityLabel.setFont(affinityFont);
        
        meleeAccuracyLabel.setHorizontalAlignment(JLabel.CENTER);
        rangedAccuracyLabel.setHorizontalAlignment(JLabel.CENTER);
        magicAccuracyLabel.setHorizontalAlignment(JLabel.CENTER);
        armourLabel.setHorizontalAlignment(JLabel.CENTER);
        meleeAffinityLabel.setHorizontalAlignment(JLabel.CENTER);
        rangedAffinityLabel.setHorizontalAlignment(JLabel.CENTER);
        magicAffinityLabel.setHorizontalAlignment(JLabel.CENTER);
        
//        attackLevelLabel.setToolTipText("Attack Level");
//        rangedLevelLabel.setToolTipText("Ranged Level");
//        magicLevelLabel.setToolTipText("Magic Level");
//        defenseLevelLabel.setToolTipText("Defence Level");
        meleeAccuracyLabel.setToolTipText("Melee Accuracy");
        rangedAccuracyLabel.setToolTipText("Ranged Accuracy");
        magicAccuracyLabel.setToolTipText("Magic Accuracy");
        armourLabel.setToolTipText("Armour");
        meleeAffinityLabel.setToolTipText("Melee Affinity");
        rangedAffinityLabel.setToolTipText("Ranged Affinity");
        magicAffinityLabel.setToolTipText("Magic Affinity");
        
        rangedAccuracyLabel.setBorder(BorderFactory.createLineBorder(Color.black,1));
        magicAccuracyLabel.setBorder(BorderFactory.createLineBorder(Color.black,1));
        armourLabel.setBorder(BorderFactory.createLineBorder(Color.black,1));
        meleeAffinityLabel.setBorder(BorderFactory.createLineBorder(Color.black,1));
        rangedAffinityLabel.setBorder(BorderFactory.createLineBorder(Color.black,1));
        magicAffinityLabel.setBorder(BorderFactory.createLineBorder(Color.black,1));
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        JPanel attackLevelPanel = new JPanel();
        attackLevelPanel.setBackground(Color.RED);
        attackLevelPanel.setBorder(BorderFactory.createLineBorder(Color.black,1));
        attackLevelPanel.setToolTipText("Attack Level");
        Image attackImage = ResourceGetter.getPicture("images/RSHitchance/Attack Icon").getBufferedImage();
        JLabel attackLevelLabelPic = new JLabel(new ImageIcon(attackImage));
        attackLevelPanel.add(attackLevelLabelPic);
        attackLevelPanel.add(attackLevelLabel);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = .5;
        c.weighty = .5;
        add(attackLevelPanel,c);
        
        JPanel rangedLevelPanel = new JPanel();
        rangedLevelPanel.setBackground(Color.GREEN);
        rangedLevelPanel.setBorder(BorderFactory.createLineBorder(Color.black,1));
        rangedLevelPanel.setToolTipText("Ranged Level");
        Image rangedImage = ResourceGetter.getPicture("images/RSHitchance/Ranged Icon").getBufferedImage();
        JLabel rangedLevelLabelPic = new JLabel(new ImageIcon(rangedImage));
        rangedLevelPanel.add(rangedLevelLabelPic);
        rangedLevelPanel.add(rangedLevelLabel);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = .5;
        c.weighty = .5;
        add(rangedLevelPanel,c);
        
        JPanel magicLevelPanel = new JPanel();
        magicLevelPanel.setBackground(Color.CYAN);
        magicLevelPanel.setBorder(BorderFactory.createLineBorder(Color.black,1));
        magicLevelPanel.setToolTipText("Magic Level");
        Image magicImage = ResourceGetter.getPicture("images/RSHitchance/Magic Icon").getBufferedImage();
        JLabel magicLevelLabelPic = new JLabel(new ImageIcon(magicImage));
        magicLevelPanel.add(magicLevelLabelPic);
        magicLevelPanel.add(magicLevelLabel);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 2;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = .5;
        c.weighty = .5;
        add(magicLevelPanel,c);
        
        JPanel defenseLevelPanel = new JPanel();
        defenseLevelPanel.setBorder(BorderFactory.createLineBorder(Color.black,1));
        defenseLevelPanel.setBackground(Color.GRAY);
        defenseLevelPanel.setToolTipText("Defence Level");
        Image defenseImage = ResourceGetter.getPicture("images/RSHitchance/Defence Level Icon").getBufferedImage();
        JLabel defenseLevelLabelPic = new JLabel(new ImageIcon(defenseImage));
        defenseLevelPanel.add(defenseLevelLabelPic);
        defenseLevelPanel.add(defenseLevelLabel);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 3;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = .5;
        c.weighty = .5;
        add(defenseLevelPanel,c);
        
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = .5;
        c.weighty = .5;
        add(meleeAccuracyLabel,c);
        
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = .5;
        c.weighty = .5;
        add(rangedAccuracyLabel,c);
        
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 2;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = .5;
        c.weighty = .5;
        add(magicAccuracyLabel,c);
        
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 3;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = .5;
        c.weighty = .5;
        add(armourLabel,c);
        
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 4;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 2;
        c.weightx = .5;
        c.weighty = .5;
        add(meleeAffinityLabel,c);
        
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 5;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 2;
        c.weightx = .5;
        c.weighty = .5;
        add(rangedAffinityLabel,c);
        
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 6;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 2;
        c.weightx = .5;
        c.weighty = .5;
        add(magicAffinityLabel,c);
    }
    
    public void setStats(BaseStats baseStats){
        this.baseStats = baseStats;
        
        attackLevelLabel.setText(Integer.toString(baseStats.getAttackLevel()));
        rangedLevelLabel.setText(Integer.toString(baseStats.getRangedLevel()));
        magicLevelLabel.setText(Integer.toString(baseStats.getMagicLevel()));
        defenseLevelLabel.setText(Integer.toString(baseStats.getDefenseLevel()));
        meleeAccuracyLabel.setText(Integer.toString(baseStats.getMeleeAccuracy()));
        rangedAccuracyLabel.setText(Integer.toString(baseStats.getRangedAccuracy()));
        magicAccuracyLabel.setText(Integer.toString(baseStats.getMagicAccuracy()));
        armourLabel.setText(Integer.toString(baseStats.getArmour()));
        meleeAffinityLabel.setText(Integer.toString(baseStats.getMeleeAffinity()));
        rangedAffinityLabel.setText(Integer.toString(baseStats.getRangedAffinity()));
        magicAffinityLabel.setText(Integer.toString(baseStats.getMagicAffinity()));
    }
}
