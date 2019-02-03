package rsHitchance;

import images.Picture;
import images.ProxyPicture;

//the monster displayed when the program launches
public class NullMonster implements Monster{
    
    private ProxyPicture picture;
    private BaseStats baseStats;
    private Vulnerabilities vulnerabilities;
    private String link;
    
    public NullMonster(){
        this.picture = new ProxyPicture("images/RSHitchance/Monsters/Null Monster");
        this.baseStats = new BaseStats(0,0,0,0,0,0,CombatStyleFactory.getCombatStyle("Melee"),0,0,new AffinityWeaknesses(0,0,0,WeaknessFactory.getAffinityWeakness(CombatStyleFactory.getCombatStyle("None"), -1)));
        this.vulnerabilities = new Vulnerabilities(false,false,false,false,false,false);
        this.link = "http://runescape.wikia.com/wiki/Boss";
    }

    @Override
    public String getName() {
        return "no monster selected";
    }

    @Override
    public Picture getPicture() {
        return picture;
    }
    
    @Override
    public String getLink(){
        return link;
    }
    
    @Override
    public BaseStats getBaseStats() {
        return baseStats;
    }

    @Override
    public Vulnerabilities getVulnerabilities() {
        return vulnerabilities;
    }

    @Override
    public int getAffinityTo(CombatStyle cbs) {
        return 0;
    }
}
