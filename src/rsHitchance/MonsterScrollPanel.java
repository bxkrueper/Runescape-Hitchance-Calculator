package rsHitchance;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import myAlgs.MyEvent;
import myAlgs.MyListener;
import objectReader.ObjectFileReader;

public class MonsterScrollPanel extends JPanel implements MyListener{

    private int width;
    
    private JScrollPane scrollPane;
    private JPanel panelInScrollPane;
    private List<MonsterSelectPanel> monsterSelectPanelList;
    
    private MyListener listener;

    public MonsterScrollPanel(int width,MyListener listener){
        this.width = width;
        this.listener = listener;
        this.setBackground(Color.BLACK);
        this.setLayout(new BorderLayout());//so the scroll pane will fill the panel
        
        this.panelInScrollPane = new JPanel();
        panelInScrollPane.setLayout(new BoxLayout(panelInScrollPane,BoxLayout.Y_AXIS));
        
        
        this.monsterSelectPanelList = new ArrayList<>();
        ObjectFileReader obr;
        try {
            obr = new MonsterFileReader("Monsters.csv");
            for(Object o:obr){
                Monster m = (Monster) o;
                MonsterSelectPanel mp = new MonsterSelectPanel(m,this);
                monsterSelectPanelList.add(mp);
                panelInScrollPane.add(mp);
            }
        } catch (FileNotFoundException e1) {
            System.out.println("Experiments file not found");
        }
        
        
        panelInScrollPane.setPreferredSize(new Dimension(width,monsterSelectPanelList.size()*MonsterSelectPanel.HEIGHT_OF));
        
        this.scrollPane = new JScrollPane(panelInScrollPane);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        add(scrollPane);
    }

    @Override
    public void eventHappened(MyEvent e) {
        if(e.getCommand().equals("Monster Selected")){
            listener.eventHappened(new MyEvent(this,"Monster Selected",e.getData()));
        }
    }

    //looks for the monster panel with the monster in it and updates it
    public void redrawMonsterSelectPanel(Monster monster) {
        for(MonsterSelectPanel msp:monsterSelectPanelList){
            if(msp.getMonster()==monster){
                msp.repaint();
                return;
            }
        }
    }
    
    
    
    //don't put these. caused scroll bar not to scroll
//    @Override
//    public Dimension getMaximumSize(){
//        Dimension size = getPreferredSize();
//        return size;
//    }
//    
//    @Override
//    public Dimension getMinimumSize(){
//        Dimension size = getPreferredSize();
//        return size;
//    }
}
