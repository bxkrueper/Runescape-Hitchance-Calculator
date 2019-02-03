package rsHitchance;

public class Global {
    //converts a level to its raw value for accuracy or defense
    public static double levelFunction(int level){
        return 0.0008 *Math.pow(level, 3) + 4 * level + 40;
    }
}
