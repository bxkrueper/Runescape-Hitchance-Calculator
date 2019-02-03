package rsHitchance;

import rsHitchance.BaseStats.EditableStats;

//stores the player's information
public class Player implements Combatent{

    private BaseStats baseStats;
    private Vulnerabilities vulnerabilities;
    
    //new base stats are made from editable stats from the player panel
    public Player(EditableStats editableStats){
        this.baseStats = new BaseStats(editableStats.getAttackLevel(),editableStats.getMeleeAccuracy(),editableStats.getRangedLevel(),editableStats.getRangedAccuracy(),editableStats.getMagicLevel(),editableStats.getMagicAccuracy(),editableStats.getCombatStyle(),editableStats.getDefenseLevel(),editableStats.getArmour(),editableStats.getAffinityWeaknesses());
        this.vulnerabilities = new Vulnerabilities(true,true,true,true,false,false);////hexhunter works if using magic???
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
        return baseStats.getAffinityTo(cbs);
    }

}
