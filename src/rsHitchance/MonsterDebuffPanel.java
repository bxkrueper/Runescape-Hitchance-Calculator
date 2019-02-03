package rsHitchance;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

import myAlgs.MyEvent;
import myAlgs.MyListener;


//gui for modifying monster debuffs. Currently just affinity modifiers
public class MonsterDebuffPanel extends JPanel implements ItemListener{

    public static final int HEIGHT_OF = 30;
    
    private MyListener listener;
    
    private JCheckBox guthixStaffCheckBox;
    private JCheckBox quakeCheckBox;
    private JCheckBox swhCheckBox;
    
    public MonsterDebuffPanel(int width, MyListener listener){
        this.listener = listener;
        this.setBackground(Color.ORANGE);
        this.setPreferredSize(new Dimension(width,HEIGHT_OF));
        
        guthixStaffCheckBox = new JCheckBox("Guthix staff spec");
        quakeCheckBox = new JCheckBox("Quake");
        swhCheckBox = new JCheckBox("swh spec");
        
        guthixStaffCheckBox.addItemListener(this);
        quakeCheckBox.addItemListener(this);
        swhCheckBox.addItemListener(this);
        
        guthixStaffCheckBox.setOpaque(false);
        quakeCheckBox.setOpaque(false);
        swhCheckBox.setOpaque(false);
        
        add(guthixStaffCheckBox);
        add(quakeCheckBox);
        add(swhCheckBox);
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

    @Override
    public void itemStateChanged(ItemEvent arg0) {
        listener.eventHappened(new MyEvent(this,"affinity mod updated",getAffinity()));
    }

    private int getAffinity() {
        int affinity = 0;
        
        if(guthixStaffCheckBox.isSelected()){
            affinity += 2;
        }
        if(quakeCheckBox.isSelected()){
            affinity += 2;
        }
        if(swhCheckBox.isSelected()){
            affinity += 5;
        }
        
        return affinity;
    }
}

