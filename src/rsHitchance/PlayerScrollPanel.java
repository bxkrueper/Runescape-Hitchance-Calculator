package rsHitchance;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import rsHitchance.BuffFactory.TopTierCurses;
import myAlgs.MyEvent;
import myAlgs.MyListener;

//gui for all of the player's buff options.
//some components only appear when appropriate
//sends messages about selected buffs to parent (player panel)
public class PlayerScrollPanel extends JPanel implements ActionListener, DocumentListener, ChangeListener, ItemListener{
    
    private int width;
    
    private JScrollPane scrollPane;
    private JPanel panelInScrollPane;
    
    private MyListener listener;
    
    private boolean curseSliderActive;
    private boolean reaperSliderActive;
    private boolean uniqueWeaponSelected;
    private boolean baneCheckboxActive;
    
    private JPanel weaponPanel;
    private JPanel prayerPanel;
    private JPanel necklacePanel;
    private JPanel necklaceReaperPanel;
    private JTextField accuracyLevelTextField;
    private JTextField weaponLevelTextField;
    private JSlider reaperSlider;
    private JSlider curseSlider;
    private JLabel curseStackLabel;
    private JLabel reaperLabel;
    private JLabel reaperStackLabel;
    private JCheckBox baneCheckBox;
    private JCheckBox defenderCheckBox;
    private JCheckBox nihilCheckBox;
    private JCheckBox sonicWaveCheckBox;
    private JCheckBox ultimateCheckBox;
    private JComboBox<String> weaponComboBox;
    private JComboBox<String> combatStylesComboBox;
    private String customCombatStyleSelection;
    private String customAccuracyLevelSelection;

    //startCombatStyleNumber: 0 is melee, 1 is range, 2 is magic
    public PlayerScrollPanel(int startCombatStyleNumber,int startLevel,int startWeaponLevel,int width,MyListener listener){
        this.width = width;
        this.listener = listener;
        this.setBackground(Color.BLACK);
        this.setLayout(new BorderLayout());//so the scroll pane will fill the panel
        
        this.panelInScrollPane = new JPanel();
        panelInScrollPane.setLayout(new BoxLayout(panelInScrollPane,BoxLayout.Y_AXIS));
        
        this.curseSliderActive = false;
        this.reaperSliderActive = false;
        this.uniqueWeaponSelected = false;
        this.baneCheckboxActive = false;
        
        JPanel accuracyLevelPanel = new JPanel();
        JLabel accuracyLevelLabel = new JLabel("Accuracy Level: ");
        this.accuracyLevelTextField = new JTextField(4);
        accuracyLevelTextField.setText(Integer.toString(startLevel));
        accuracyLevelTextField.getDocument().addDocumentListener(this);
        accuracyLevelPanel.add(accuracyLevelLabel);
        accuracyLevelPanel.add(accuracyLevelTextField);
        panelInScrollPane.add(accuracyLevelPanel);
        
        this.weaponPanel = new JPanel();
        JLabel weaponLabel = new JLabel("Weapon: ");
        String[] weaponStrings = {"Custom","Hexhunter","Balmung","Upgraded Balmung","Keris","Silverlight","Darklight","Upgraded Darklight"};
        this.weaponComboBox = new JComboBox<>(weaponStrings);
        weaponComboBox.setActionCommand("Weapon");
        weaponComboBox.setSelectedIndex(0);
        weaponComboBox.addActionListener(this);
        weaponPanel.add(weaponLabel);
        weaponPanel.add(weaponComboBox);
        //
        JLabel combatStyleLabel = new JLabel("Combat Style: ");
        String[] combatStyles = {"Melee","Ranged","Magic","Stab","Slash","Crush","Arrows","Bolts","Thrown","Air","Water","Earth","Fire"};
        this.combatStylesComboBox = new JComboBox<>(combatStyles);
        combatStylesComboBox.setActionCommand("Combat Style");
        combatStylesComboBox.setSelectedIndex(startCombatStyleNumber);
        customCombatStyleSelection = combatStyles[startCombatStyleNumber];
        combatStylesComboBox.addActionListener(this);
        weaponPanel.add(combatStyleLabel);
        weaponPanel.add(combatStylesComboBox);
        panelInScrollPane.add(weaponPanel);
        //
        JLabel weaponLevelLabel = new JLabel("Weapon Level: ");
        this.weaponLevelTextField = new JTextField(4);
        weaponLevelTextField.setText(Integer.toString(startWeaponLevel));
        customAccuracyLevelSelection = weaponLevelTextField.getText();
        weaponLevelTextField.getDocument().addDocumentListener(this);
        weaponPanel.add(weaponLevelLabel);
        weaponPanel.add(weaponLevelTextField);
        this.baneCheckBox = new JCheckBox("Bane Ammo");
        baneCheckBox.addItemListener(this);
        panelInScrollPane.add(weaponPanel);
        
        JPanel potionPanel = new JPanel();
        JLabel potionLabel = new JLabel("Potion: ");
        String[] potionTypes = {"None","Basic Stat Boost","Super Stat Boost","Grand Stat Boost","Extreme Stat Boost","Supreme Stat Boost","Overload","Supreme Overload"};
        JComboBox<String> potionComboBox = new JComboBox<>(potionTypes);
        potionComboBox.setActionCommand("Potion");
        potionComboBox.setSelectedIndex(0);
        potionComboBox.addActionListener(this);
        potionPanel.add(potionLabel);
        potionPanel.add(potionComboBox);
        panelInScrollPane.add(potionPanel);
        
        this.prayerPanel = new JPanel();
        JLabel prayerLabel = new JLabel("Prayer: ");
        String[] prayerTypes = {"None","Chivalry","Piety Class Prayer","Turmoil Class Curse","Nex Class Curse"};
        JComboBox<String> prayerComboBox = new JComboBox<>(prayerTypes);
        prayerComboBox.setActionCommand("Prayer");
        prayerComboBox.setSelectedIndex(0);
        prayerComboBox.addActionListener(this);
        this.curseSlider = new JSlider(JSlider.HORIZONTAL,0, 4, 1);
        curseSlider.addChangeListener(this);
        curseSlider.setPaintTicks(true);
        curseSlider.setMajorTickSpacing(1);
        this.curseStackLabel = new JLabel("0");
        prayerPanel.add(prayerLabel);
        prayerPanel.add(prayerComboBox);
        panelInScrollPane.add(prayerPanel);
        
        JPanel auraPanel = new JPanel();
        JLabel auraLabel = new JLabel("Aura: ");
        String[] auraTypes = {"None","Basic Accuracy Aura","Greater Accuracy Aura","Master Accuracy Aura","Supreme Accuracy Aura","Zerk Aura"};
        JComboBox<String> auraComboBox = new JComboBox<>(auraTypes);
        auraComboBox.setActionCommand("Aura");
        auraComboBox.setSelectedIndex(0);
        auraComboBox.addActionListener(this);
        auraPanel.add(auraLabel);
        auraPanel.add(auraComboBox);
        panelInScrollPane.add(auraPanel);
        
        JPanel scrimshawPanel = new JPanel();
        JLabel scrimshawLabel = new JLabel("Scrimshaw: ");
        String[] scrimshawTypes = {"None","Tradable Scrimshaw","Superior Scrimshaw"};
        JComboBox<String> scrimshawComboBox = new JComboBox<>(scrimshawTypes);
        scrimshawComboBox.setActionCommand("Scrimshaw");
        scrimshawComboBox.setSelectedIndex(0);
        scrimshawComboBox.addActionListener(this);
        scrimshawPanel.add(scrimshawLabel);
        scrimshawPanel.add(scrimshawComboBox);
        panelInScrollPane.add(scrimshawPanel);
        
        this.necklacePanel = new JPanel();
        necklacePanel.setLayout(new BoxLayout(necklacePanel,BoxLayout.Y_AXIS));
        JPanel necklaceLabelAndComboBoxPanel = new JPanel();
        JLabel necklaceLabel = new JLabel("Necklace: ");
        String[] necklaceTypes = {"None","Reaper Necklace","Salve Amulet", "Salve Amulet (e)"};
        JComboBox<String> necklaceComboBox = new JComboBox<>(necklaceTypes);
        necklaceComboBox.setActionCommand("Necklace");
        necklaceComboBox.setSelectedIndex(0);
        necklaceComboBox.addActionListener(this);
        this.reaperLabel = new JLabel("Reaper Stacks: ");
        this.reaperSlider = new JSlider(JSlider.HORIZONTAL,0, 30, 0);
        reaperSlider.addChangeListener(this);
        reaperSlider.setPaintTicks(true);
        reaperSlider.setMajorTickSpacing(1);
        this.reaperStackLabel = new JLabel("0");
        necklaceLabelAndComboBoxPanel.add(necklaceLabel);
        necklaceLabelAndComboBoxPanel.add(necklaceComboBox);
        necklacePanel.add(necklaceLabelAndComboBoxPanel);
        this.necklaceReaperPanel = new JPanel();
        necklaceReaperPanel.add(reaperLabel);
        necklaceReaperPanel.add(reaperSlider);
        necklaceReaperPanel.add(reaperStackLabel);
        panelInScrollPane.add(necklacePanel);
        
        JPanel checkBoxPanel = new JPanel();
        this.defenderCheckBox = new JCheckBox("Defender");
        defenderCheckBox.addItemListener(this);
        checkBoxPanel.add(defenderCheckBox);
        this.nihilCheckBox = new JCheckBox("Nihl");
        nihilCheckBox.addItemListener(this);
        checkBoxPanel.add(nihilCheckBox);
        this.sonicWaveCheckBox = new JCheckBox("Sonic Wave Boost");
        sonicWaveCheckBox.addItemListener(this);
        checkBoxPanel.add(sonicWaveCheckBox);
        this.ultimateCheckBox = new JCheckBox("Ultimate Ability");
        ultimateCheckBox.addItemListener(this);
        checkBoxPanel.add(ultimateCheckBox);
        panelInScrollPane.add(checkBoxPanel);
        
        JPanel slayerHelmPanel = new JPanel();
        JLabel slayerHelmLabel = new JLabel("SlayerHelm: ");
        String[] slayerHelmTypes = {"None","Black Mask","Reinforced Slayer Helmet","Strong Slayer Helmet","Mighty Slayer Helmet","Corrupted Slayer Helmet"};
        JComboBox<String> slayerHelmComboBox = new JComboBox<>(slayerHelmTypes);
        slayerHelmComboBox.setActionCommand("Slayer Helm");
        slayerHelmComboBox.setSelectedIndex(0);
        slayerHelmComboBox.addActionListener(this);
        slayerHelmPanel.add(slayerHelmLabel);
        slayerHelmPanel.add(slayerHelmComboBox);
        panelInScrollPane.add(slayerHelmPanel);
        
        panelInScrollPane.setPreferredSize(new Dimension(width,500));//////////////
        
        this.scrollPane = new JScrollPane(panelInScrollPane);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        add(scrollPane);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getActionCommand().equals("Weapon")){
            JComboBox<String> cb = (JComboBox<String>) ae.getSource();
            String weaponString = (String) cb.getSelectedItem();
            
            if(weaponString.equals("Custom")){
                this.uniqueWeaponSelected = false;
                ///////set combat style cb and weapon level and send events
                this.combatStylesComboBox.setEnabled(true);
                this.weaponLevelTextField.setEnabled(true);
                
                this.combatStylesComboBox.setSelectedItem(customCombatStyleSelection);
                this.weaponLevelTextField.setText(customAccuracyLevelSelection);
                
                listener.eventHappened(new MyEvent(this,"Combat Style Changed",CombatStyleFactory.getCombatStyle(customCombatStyleSelection)));
                listener.eventHappened(new MyEvent(this,"Weapon Level Changed",Integer.parseInt(customAccuracyLevelSelection)));
                listener.eventHappened(new MyEvent(this,"Weapon Buff Changed",null));
            }else{
                //save custom selection values if coming from custom
                if(!uniqueWeaponSelected){
                    customCombatStyleSelection = (String) combatStylesComboBox.getSelectedItem();
                    customAccuracyLevelSelection = weaponLevelTextField.getText();
                }
                uniqueWeaponSelected = true;
                
                
                Weapon weapon = WeaponFactory.getWeapon(weaponString);
                //disable combat style cb and weapon level box, set the right values, send events and buff
                this.combatStylesComboBox.setEnabled(false);
                this.weaponLevelTextField.setEnabled(false);
                
                this.combatStylesComboBox.setSelectedItem(weapon.getCombatStyle().getName());
                this.weaponLevelTextField.setText(Integer.toString(weapon.getAccuracyLevel()));
                
                listener.eventHappened(new MyEvent(this,"Combat Style Changed",weapon.getCombatStyle()));
                listener.eventHappened(new MyEvent(this,"Weapon Level Changed",weapon.getAccuracyLevel()));
                listener.eventHappened(new MyEvent(this,"Weapon Buff Changed",weapon.getBuff()));
            }
//            listener.eventHappened(new MyEvent(this,"Combat Style Changed",combatStyle));
        }else if(ae.getActionCommand().equals("Combat Style")){
            JComboBox<String> cb = (JComboBox<String>) ae.getSource();
            String combatStyleString = (String) cb.getSelectedItem();
            CombatStyle combatStyle = CombatStyleFactory.getCombatStyle(combatStyleString);
            if(combatStyleString.equals("Ranged") || combatStyleString.equals("Arrows") || combatStyleString.equals("Bolts")){
                if(!baneCheckboxActive){
                    //add bane checkbox
                    weaponPanel.add(baneCheckBox);
                    baneCheckboxActive = true;
                    weaponPanel.revalidate();
                    
                    listener.eventHappened(new MyEvent(this,"Using Bane Ammo Changed",baneCheckBox.isSelected()));
                }
            }else{
                if(baneCheckboxActive){
                    //delete bane check box
                    weaponPanel.remove(baneCheckBox);
                    baneCheckboxActive = false;
                    weaponPanel.revalidate();
                    
                    listener.eventHappened(new MyEvent(this,"Using Bane Ammo Changed",false));
                }
            }
            
            
            listener.eventHappened(new MyEvent(this,"Combat Style Changed",combatStyle));
        }else if(ae.getActionCommand().equals("Potion")){
            JComboBox<String> cb = (JComboBox<String>) ae.getSource();
            String potionString = (String) cb.getSelectedItem();
            Buff potionBuff = BuffFactory.getBuff(potionString);
            listener.eventHappened(new MyEvent(this,"Potion Changed",potionBuff));
        }else if(ae.getActionCommand().equals("Prayer")){
            JComboBox<String> cb = (JComboBox<String>) ae.getSource();
            String prayerString = (String) cb.getSelectedItem();
            Buff prayerBuff = BuffFactory.getBuff(prayerString);
            if(prayerBuff instanceof TopTierCurses){
                if(!curseSliderActive){
                    prayerPanel.add(curseSlider);
                    prayerPanel.add(curseStackLabel);
                    curseSliderActive = true;
                    prayerPanel.revalidate();
                }
            }else{
                if(curseSliderActive){
                    prayerPanel.remove(curseSlider);
                    prayerPanel.remove(curseStackLabel);
                    curseSliderActive = false;
                    prayerPanel.revalidate();
                }
            }
            
            listener.eventHappened(new MyEvent(this,"Prayer Changed",prayerBuff));
        }else if(ae.getActionCommand().equals("Aura")){
            JComboBox<String> cb = (JComboBox<String>) ae.getSource();
            String auraString = (String) cb.getSelectedItem();
            Buff auraBuff = BuffFactory.getBuff(auraString);
            listener.eventHappened(new MyEvent(this,"Aura Changed",auraBuff));
        }else if(ae.getActionCommand().equals("Necklace")){
            JComboBox<String> cb = (JComboBox<String>) ae.getSource();
            String necklaceString = (String) cb.getSelectedItem();
            if(necklaceString.equals("Reaper Necklace")){
                if(reaperSliderActive){
                    //do nothing
                }else{
                    necklacePanel.add(necklaceReaperPanel);
                    reaperSliderActive = true;
                    necklacePanel.revalidate();
                }
            }else{
                if(reaperSliderActive){
                    necklacePanel.remove(necklaceReaperPanel);
                    reaperSliderActive = false;
                    necklacePanel.revalidate();
                }else{
                    //do nothing
                }
            }
            
            
            Buff necklaceBuff = BuffFactory.getBuff(necklaceString);
            listener.eventHappened(new MyEvent(this,"Necklace Changed",necklaceBuff));
        }else if(ae.getActionCommand().equals("Scrimshaw")){
            JComboBox<String> cb = (JComboBox<String>) ae.getSource();
            String scrimshawString = (String) cb.getSelectedItem();
            Buff scrimshawBuff = BuffFactory.getBuff(scrimshawString);
            listener.eventHappened(new MyEvent(this,"Scrimshaw Changed",scrimshawBuff));
        }else if(ae.getActionCommand().equals("Slayer Helm")){
            JComboBox<String> cb = (JComboBox<String>) ae.getSource();
            String slayerHelmString = (String) cb.getSelectedItem();
            Buff slayerHelmBuff = BuffFactory.getBuff(slayerHelmString);
            listener.eventHappened(new MyEvent(this,"Slayer Mask Changed",slayerHelmBuff));
        }
        
    }

    @Override
    public void changedUpdate(DocumentEvent arg0) {
        docUpdate(arg0);
    }

    @Override
    public void insertUpdate(DocumentEvent arg0) {
        docUpdate(arg0);
    }

    @Override
    public void removeUpdate(DocumentEvent arg0) {
        docUpdate(arg0);
    }
    
    public void docUpdate(DocumentEvent de){
        //get text in text field
        String textFieldText;
        try {
            textFieldText=de.getDocument().getText(0, de.getDocument().getLength());
        } catch (BadLocationException e) {
            e.printStackTrace();
            return;
        }
        
        //get number it represents
        int numberInField;
        try{
            numberInField = Integer.parseInt(textFieldText);
        }catch(NumberFormatException e){
            numberInField = getIntegerFromString(textFieldText);
        }
        
        //do something with the number
        if(de.getDocument()==accuracyLevelTextField.getDocument()){
            listener.eventHappened(new MyEvent(this,"Accuracy Level Changed",numberInField));
        }else if(de.getDocument()==weaponLevelTextField.getDocument()){
            listener.eventHappened(new MyEvent(this,"Weapon Level Changed",numberInField));
        }
    }
    
    
    
    
    
    private int getIntegerFromString(String str) {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<str.length();i++){
            char c = str.charAt(i);
            if(Character.isDigit(c)){
                sb.append(c);
            }
        }
        
        String numString = sb.toString();
        int ans;
        if(numString.equals("")){
            ans = 0;
        }else{
            try{
                ans = Integer.parseInt(numString);
            }catch(NumberFormatException e){
                System.out.println("still having trouble with getIntegerFromString(String str)");
                ans = 0;
            }
        }
        
        return ans;
    }

    @Override
    public void stateChanged(ChangeEvent arg0) {
        if(arg0.getSource()==curseSlider){
            int curseStack = curseSlider.getValue();
            curseStackLabel.setText(Integer.toString(curseStack));
            listener.eventHappened(new MyEvent(this,"Curse Stack Changed",curseStack+6));//+6: to match levels drained
        }else if(arg0.getSource()==reaperSlider){
            int reaperStack = reaperSlider.getValue();
            reaperStackLabel.setText(Integer.toString(reaperStack));
            listener.eventHappened(new MyEvent(this,"Reaper Stack Changed",reaperStack));
        }else{
            System.out.println("unrecognized slider");
        }
        
    }

    @Override
    public void itemStateChanged(ItemEvent ie) {
        Object source = ie.getItemSelectable();
        if(source == defenderCheckBox){
            listener.eventHappened(new MyEvent(this,"Defender Changed",defenderCheckBox.isSelected()));
        }else if (source == nihilCheckBox){
            listener.eventHappened(new MyEvent(this,"Nihil Changed",nihilCheckBox.isSelected()));
        }else if (source == sonicWaveCheckBox){
            listener.eventHappened(new MyEvent(this,"Sonic Wave Changed",sonicWaveCheckBox.isSelected()));
        }else if (source == ultimateCheckBox){
            listener.eventHappened(new MyEvent(this,"Ultimate Changed",ultimateCheckBox.isSelected()));
        }else if (source == baneCheckBox){
            listener.eventHappened(new MyEvent(this,"Using Bane Ammo Changed",baneCheckBox.isSelected()));
        }
    }

    public int getCurseStack() {
        int curseStack = curseSlider.getValue();
        return curseStack+6;//to match the levels drained
    }
    
    public int getReaperStack() {
        return reaperSlider.getValue();
    }
}
