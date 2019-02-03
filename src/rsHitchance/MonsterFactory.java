package rsHitchance;

import java.util.LinkedList;
import java.util.List;

import images.Picture;
import images.ProxyPicture;
import images.ResourceGetter;

public class MonsterFactory {
    
    //creates a monster out of one row of Monsters.csv
    public static Monster readMonster(String[] splitString){
        
        String name = splitString[0];
        String pictureName = splitString[1].equals("")? name:splitString[1];//if there is nothing in this column, the picture name is the same as the name. File extensions are found by ResourceGetter
        String wikiLink = replaceSemiColons(splitString[2]);
        
        int attackLevel = Integer.parseInt(splitString[3]);
        int rangedLevel = Integer.parseInt(splitString[4]);
        int magicLevel = Integer.parseInt(splitString[5]);
        int meleeAccuracy = Integer.parseInt(splitString[6]);
        int rangedAccuracy = Integer.parseInt(splitString[7]);
        int magicAccuracy = Integer.parseInt(splitString[8]);
        
        CombatStyle startCombatStyle = CombatStyleFactory.getCombatStyle(splitString[9]);
        
        int defenseLevel = Integer.parseInt(splitString[10]);
        int armour = Integer.parseInt(splitString[11]);
        int meleeAffinity = Integer.parseInt(splitString[12]);
        int rangedAffinity = Integer.parseInt(splitString[13]);
        int magicAffinity = Integer.parseInt(splitString[14]);
        
        AffinityWeakness affinityWeakness =  getAffinityWeaknessFromString(splitString[19]);
        
        AffinityWeaknesses affinityWeaknesses = new AffinityWeaknesses(meleeAffinity,rangedAffinity,magicAffinity,affinityWeakness);
        
        BaseStats bds = new BaseStats(attackLevel,meleeAccuracy,rangedLevel,rangedAccuracy,magicLevel,magicAccuracy,startCombatStyle,defenseLevel,armour,affinityWeaknesses);
        
        boolean weakToPoison = Boolean.parseBoolean(splitString[15]);
        boolean weakToReflect = Boolean.parseBoolean(splitString[16]);
        boolean weakToStun = Boolean.parseBoolean(splitString[17]);
        boolean weakToDrain = Boolean.parseBoolean(splitString[18]);
        List<Buff> otherVulns = getBuffVulnsFromString(splitString[20]);
        Vulnerabilities v = new Vulnerabilities(weakToPoison,weakToReflect,weakToStun,weakToDrain,otherVulns);
        
        
        
        
        
        
        return new FillinMonster(name,pictureName,wikiLink,bds,v);
    }
    
    private static String replaceSemiColons(String string) {
        // TODO Auto-generated method stub
        return string.replace(';', ',');
    }

    //returns a list of buffs or their superclasses that the monster is weak to
    //if there is more than one, they are seperated by spaces
    //format examples:
    //None
    //Balmung
    //SlayerMask Hexhunter
    private static List<Buff> getBuffVulnsFromString(String str) {
        String[] vulnStrings = str.split(" ");
        List<Buff> otherVulns = new LinkedList<>();
        if(vulnStrings[0].equals("None")){
            return otherVulns;
        }
        
        for(String vulnStr:vulnStrings){
            Buff vuln = BuffFactory.getBuff(vulnStr);
            if(vuln!=null){
                otherVulns.add(vuln);
            }
        }
        
        return otherVulns;
    }

    //format in file: either "None" or "<weakness> affinity value" ex: "Slash 90"
    private static AffinityWeakness getAffinityWeaknessFromString(String str){
        String[] weaknessStrings = str.split(" ");
        
        CombatStyle cs = CombatStyleFactory.getCombatStyle(weaknessStrings[0]);
        
        int affinity;
        if(weaknessStrings.length==1){
            affinity = -1;//for debugging. shouldn't happen
        }else{
            affinity = Integer.parseInt(weaknessStrings[1]);
        }
        
        return WeaknessFactory.getAffinityWeakness(cs, affinity);
    }
    
    
    
    public static class FillinMonster implements Monster{
        
        private String name;
        private ProxyPicture picture;//loaded in when needed
        private String wikiLink;
        
        private BaseStats baseStats;
        private Vulnerabilities vulnerabilities;
        
        public FillinMonster(String name, String pictureName, String wikiLink, BaseStats bds,Vulnerabilities vulnerabilities){
            this.name = name;
            this.picture = new ProxyPicture("images/RSHitchance/Monsters/"+pictureName);
            this.wikiLink = wikiLink;
            this.baseStats = bds;
            this.vulnerabilities = vulnerabilities;
        }
        
        @Override
        public String getName(){
            return name;
        }
        @Override
        public Picture getPicture(){
            return picture;
        }
        @Override
        public String getLink(){
            return wikiLink;
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
        
        @Override
        public String toString(){
            return "Monster: Name: " + name + "\n"
                    + "base stats: " + baseStats;
        }

        

        
    }
}

