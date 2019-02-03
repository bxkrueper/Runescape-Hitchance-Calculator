package rsHitchance;

import java.io.FileNotFoundException;

import objectReader.ObjectFileReader;

//reads monsters from Monsters.csv
//lines that single the start of a group begin with the number of monsters and have the group name in the next cell. The next n monsters are part of that group and are returned as one MonsterGroup
//the name of the file must be the same as the name of the monster
public class MonsterFileReader extends ObjectFileReader{
    
    private boolean passedFirstLine;
    
    public MonsterFileReader(String fileName) throws FileNotFoundException {
        super(fileName);
    }

    @Override
    protected Object nextObject() {
        //first line is just labels to make the csv file more readable
        if(!passedFirstLine){
            nextLine();
            passedFirstLine = true;
        }
        
        String rowString;
        rowString = nextLine();
        if(rowString==null || rowString.equals("")){
            return null;//end of file reached
        }
        
        //if the first word is a number that means the bosses on the following (number) lines
        //are part of the same boss and should be grouped up
        String[] splitString = rowString.split(",");
        int groupCount = -1;
        try{
            groupCount = Integer.parseInt(splitString[0]);
        }catch(NumberFormatException e){
            
        }
        
        if(groupCount<1){//this a normal one line monster
            return MonsterFactory.readMonster(splitString);
        }else{//this monster is composed of multiple monsters
            String groupName = splitString[1];
            Monster[] monsterArray = new Monster[groupCount];
            for(int i=0;i<groupCount;i++){
                
                String subMonsterRowString = nextLine();
                if(subMonsterRowString==null || subMonsterRowString.equals("")){
                    System.out.println("not enough subMonster lines to fill monster group!");
                    return null;//end of file reached
                }
                
                String[] subMonsterSplitString = subMonsterRowString.split(",");
                monsterArray[i] = MonsterFactory.readMonster(subMonsterSplitString);
            }
            
            return new MonsterGroup(groupName,monsterArray);
        }
        
    }

}
