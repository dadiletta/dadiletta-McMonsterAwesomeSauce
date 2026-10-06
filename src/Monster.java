public class Monster {
    // INSTANCE VARS
    private int health;
    private int maxDmg;
    
    // CONSTRUCTOR
    public Monster() {
        health = 100;
        // 10 - 25 as max dmg
        maxDmg = (int)(Math.random() * 15 + 1) + 10;
    }

    // ACCESORS
    public int health() { return health; }
    public int maxDmg() { return maxDmg; }

    // MUTATORS
    public void takeDmg(int dmg){
        health -= dmg;
    }

}
