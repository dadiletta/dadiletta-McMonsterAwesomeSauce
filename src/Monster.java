public class Monster {
    public static int specialMonsters = 1;
    
    // CONSTRUCTOR
    public Monster() {
        if(Monster.specialMonsters > 0) {
            System.out.println("I'm special");
            Monster.specialMonsters--;
        }
        else System.out.println("I'm just an average monster");
    }

}
