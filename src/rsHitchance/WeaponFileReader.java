package rsHitchance;

import java.io.FileNotFoundException;

import images.Picture;
import images.ProxyPicture;
import objectReader.ObjectFileReader;

public class WeaponFileReader extends ObjectFileReader{
    
    private boolean passedFirstLine;
    public static final String PATH_TO_WEAPON_PICS = "images/RSHitchance/Weapons/";
    
    public WeaponFileReader(String fileName) throws FileNotFoundException {
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
        
        
        String[] splitString = rowString.split(",");
        
        return makeWeapon(splitString);
    }
    
    private Object makeWeapon(String[] splitString) {
        String name = splitString[0];
        Picture picture = new ProxyPicture(PATH_TO_WEAPON_PICS+name);
        int accuracyLevel = Integer.parseInt(splitString[1]);
        CombatStyle cbs = CombatStyleFactory.getCombatStyle(splitString[2]);
        Buff buff = BuffFactory.getBuff(splitString[3]);
        return new Weapon(name,picture,accuracyLevel,cbs,buff);
    }
}
