package rsHitchance;

import java.awt.Dimension;

import javax.swing.JFrame;

public class RSHitchanceFrame extends JFrame{

private static RSHitchanceFrame instance;
    
    private RSHitchancePanel hitchancePanel;
    
    private RSHitchanceFrame(){
        setTitle("RuneScape Hit-Chance Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setIconImage(new ImageIcon("images/puzzle piece.png").getImage());////////////
        
        
        this.hitchancePanel = new RSHitchancePanel();
        add(hitchancePanel);
        
        pack();
        setLocationRelativeTo(null);  
        setVisible(true);
    }
    

    public static RSHitchanceFrame getInstance(){
        if(instance==null){
            instance = new RSHitchanceFrame();
        }
        
        return instance;
    }
    
    @Override
    public Dimension getPreferredSize(){
        return new Dimension(950,900);
    }

}