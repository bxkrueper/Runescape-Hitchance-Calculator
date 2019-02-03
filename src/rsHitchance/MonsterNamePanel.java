package rsHitchance;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import myAlgs.MyEvent;
import myAlgs.MyListener;
import images.ResourceGetter;

import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;

//displays the monster's name and a link to its wiki page.
//if the monster is a monster group, it isplays the currently selected monster an
//two buttons to cycle between the options
//todo. fix wiki button being cropped
public class MonsterNamePanel extends JPanel implements ActionListener{
    
    private MyListener listener;
    private Monster monster;
    
    private JPanel leftPanel;
    private JLabel monsterLabel;
    private JButton leftButton;
    private JLabel groupSelectDisplay;
    private JButton rightButton;
    private JButton wikiButton;
    
    public MonsterNamePanel(Monster monster, MyListener listener){
        this.monster = monster;
        this.listener = listener;
        this.monsterLabel = new JLabel("Monster");
        
        this.setLayout(new BorderLayout());
        
        this.leftPanel = new JPanel();
        leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        monsterLabel.setFont(new Font(monsterLabel.getFont().getName(),Font.BOLD,30));
        monsterLabel.setOpaque(true);
        
//        monsterLabel.setBackground(Color.CYAN);
//        leftPanel.setBackground(Color.BLUE);
        
        
        this.leftButton = new JButton("<");
        this.groupSelectDisplay = new JLabel("");
        this.rightButton = new JButton(">");
        leftButton.addActionListener(this);
        rightButton.addActionListener(this);
        leftButton.setMargin(new Insets(0, 0, 0, 0));
        rightButton.setMargin(new Insets(0, 0, 0, 0));
        leftButton.setPreferredSize(new Dimension(30,25));
        groupSelectDisplay.setFont(new Font(groupSelectDisplay.getFont().getName(),Font.PLAIN,15));
        rightButton.setPreferredSize(new Dimension(30,25));
        
        leftPanel.add(monsterLabel);
        
        
        this.wikiButton = new JButton();
        Image img;
        img = ResourceGetter.getPicture("images/RSHitchance/Wiki").getBufferedImage();
        wikiButton.setIcon(new ImageIcon(img));
        wikiButton.setMargin(new Insets(0, 0, 0, 0));
        wikiButton.setBorder(null);
        wikiButton.setPreferredSize(new Dimension(30,30));
        wikiButton.addActionListener(this);
        
        
        add(leftPanel,BorderLayout.LINE_START);
        add(wikiButton,BorderLayout.LINE_END);
    }

    public void setMonster(Monster newMonster) {
        leftPanel.remove(leftButton);
        leftPanel.remove(groupSelectDisplay);
        leftPanel.remove(rightButton);
        leftPanel.remove(monsterLabel);
        
        this.monster = newMonster;
        
        if(monster instanceof MonsterGroup){
            MonsterGroup group = (MonsterGroup) monster;
            leftPanel.add(leftButton);
            groupSelectDisplay.setText(Integer.toString(group.getSelected()+1)+"/"+Integer.toString(group.getNumberOfMonsters()));
            leftPanel.add(groupSelectDisplay);
            leftPanel.add(rightButton);
            setToolTips(group);
        }
        setMonsterName(monster.getName());
        leftPanel.add(monsterLabel);
        
        revalidate();
        repaint();
    }

    private void setMonsterName(String name) {
        if(name.length()<17){
            monsterLabel.setFont(new Font(monsterLabel.getFont().getName(),Font.BOLD,30));
        }else{
            monsterLabel.setFont(new Font(monsterLabel.getFont().getName(),Font.BOLD,15));
        }
        monsterLabel.setText(name);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==wikiButton){
            if (Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().browse(new URI(monster.getLink()));
                } catch (IOException | URISyntaxException e) {
                    e.printStackTrace();
                }
            }
            return;
        }
        
        //only remaining action event is from arrow buttons
        //left and right buttons should only be there if there is a monster group
        MonsterGroup group = (MonsterGroup) monster;
        if(ae.getSource()==leftButton){
            group.prevMonster();
        }else if(ae.getSource()==rightButton){//right button
            group.nextMonster();
        }else{
            System.out.println("unknown action event in MonsterNamePanel!");
            return;
        }
        setToolTips(group);
        groupSelectDisplay.setText(Integer.toString(group.getSelected()+1)+"/"+Integer.toString(group.getNumberOfMonsters()));
        setMonsterName(monster.getName());
        listener.eventHappened(new MyEvent(this,"Monster Selected",group));
    }

    //temporarily switches the selected monster to update the tool tips on the arrow buttons
    private void setToolTips(MonsterGroup group) {
        group.prevMonster();
        leftButton.setToolTipText(group.getName());
        group.nextMonster();
        group.nextMonster();
        rightButton.setToolTipText(group.getName());
        group.prevMonster();
    }
    
    
}
