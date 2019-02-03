package rsHitchance;

import images.Picture;

//composite pattern for monster
//only one is selected at a time
public class MonsterGroup implements Monster{

    private String groupName;
    private Monster[] monsterArray;
    private int selected;
    
    public MonsterGroup(String groupName, Monster[] monsterArray){
        this.groupName = groupName;
        this.monsterArray = monsterArray;
        this.selected = 0;
    }
    
    public void setSelected(int newSelected){
        this.selected = newSelected;
    }
    
    public int getSelected(){
        return selected;
    }
    
    public int getNumberOfMonsters(){
        return monsterArray.length;
    }
    
    public String getGroupName(){
        return groupName;
    }
    
    public int getSize(){
        return monsterArray.length;
    }
    
    @Override
    public String getName() {
        return monsterArray[selected].getName();
    }

    @Override
    public Picture getPicture() {
        return monsterArray[selected].getPicture();
    }
    
    @Override
    public String getLink(){
        return monsterArray[selected].getLink();
    }
    
    @Override
    public BaseStats getBaseStats() {
        return monsterArray[selected].getBaseStats();
    }

    @Override
    public Vulnerabilities getVulnerabilities() {
        return monsterArray[selected].getVulnerabilities();
    }

    @Override
    public int getAffinityTo(CombatStyle cbs) {
        return monsterArray[selected].getAffinityTo(cbs);
    }
    
    @Override
    public String toString(){
        return monsterArray[selected].toString();
    }
    
    public void nextMonster(){
        selected++;
        if(selected>=monsterArray.length){
            selected=0;
        }
    }

    public void prevMonster(){
        selected--;
        if(selected<0){
            selected=monsterArray.length-1;
        }
    }
}
