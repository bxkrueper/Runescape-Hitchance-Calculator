package rsHitchance;

//anyone that plans on fighting. Intended subclasses are player and monster
public interface Combatent {
    BaseStats getBaseStats();
    Vulnerabilities getVulnerabilities();
    int getAffinityTo(CombatStyle cbs);
}
