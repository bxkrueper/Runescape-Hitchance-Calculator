package rsHitchance;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import images.Picture;
import myAlgs.MyEvent;
import myAlgs.MyListener;

//cell showing the monster's picture and name
public class MonsterSelectPanel extends JPanel implements MouseListener{
    
    public final static int HEIGHT_OF = 60;
    
    private Monster monster;
    private MyListener listener;
    private boolean mouseOver;
    
    public MonsterSelectPanel(Monster monster, MyListener listener){
        this.monster = monster;
        this.listener = listener;
        mouseOver = false;
        
        addMouseListener(this);
        
        this.setBackground(Color.DARK_GRAY);
        this.setPreferredSize(new Dimension(MonsterPanel.WIDTH_OF,HEIGHT_OF));
        this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        this.setBorder(BorderFactory.createLineBorder(Color.black,1));
    }
    
    public Monster getMonster() {
        return monster;
    }
    
    
    
    @Override
    public Dimension getMaximumSize(){
        Dimension size = getPreferredSize();
        size.height = HEIGHT_OF;/////////////////make a copy instead?
        return size;
    }
    
    @Override
    public Dimension getMinimumSize(){
        Dimension size = getPreferredSize();
        size.height = HEIGHT_OF;
        return size;
    }



    @Override
    public void mouseClicked(MouseEvent arg0) {
        listener.eventHappened(new MyEvent(this,"Monster Selected",monster));
    }



    @Override
    public void mouseEntered(MouseEvent arg0) {
        mouseOver = true;
        repaint();
    }



    @Override
    public void mouseExited(MouseEvent arg0) {
        mouseOver = false;
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        //change color if mouse is over
        if(mouseOver){
            g.setColor(new Color(100,100,100,100));
            g.fillRect(0, 0, getWidth(), getHeight());
        }
        
        Picture picture = monster.getPicture();
        double width = picture.getWidth()/(picture.getHeight()/((double) HEIGHT_OF));
        picture.draw(g, 0, 0, (int) width, HEIGHT_OF);
        
        String monsterName;
        if(monster instanceof MonsterGroup){
            MonsterGroup mg = (MonsterGroup) monster;
            monsterName = mg.getGroupName();
        }else{
            monsterName = monster.getName();
        }
        
        g.setColor(Color.BLACK);
        g.setFont(new Font(g.getFont().getName(),Font.BOLD,30));
        g.drawString(monsterName, 80, HEIGHT_OF-10);
    }


    @Override
    public void mousePressed(MouseEvent arg0) {
    }
    @Override
    public void mouseReleased(MouseEvent arg0) {
    }



    
}
