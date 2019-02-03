package rsHitchance;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import images.Picture;
import images.ProxyPicture;
import objectReader.ObjectFileReader;

//flyweight pattern for weapons
public class WeaponFactory {
    
    private static Map<String,Weapon> weaponMap;
    public static final String WEAPONS_FILE_NAME = "Weapons.csv";

    public static Weapon getWeapon(String str){
        if(weaponMap==null){
            makeMap();
        }
        
        Weapon weapon = weaponMap.get(str);
        if(weapon==null){
            System.out.println("unrecognized weapon: " + str);
            return null;
        }else{
            return weapon;
        }
    }
    
    private static void makeMap() {
        weaponMap = new HashMap<>();
        
        ObjectFileReader obr;
        try {
            obr = new WeaponFileReader(WEAPONS_FILE_NAME);
            for(Object o:obr){
                Weapon w = (Weapon) o;
                weaponMap.put(w.getName(), w);
            }
        } catch (FileNotFoundException e1) {
            System.out.println("Weapons file not found at " + WEAPONS_FILE_NAME);
        }
    }

    
}
