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
        System.out.println("Monster takes " + dmg + " damage.");
        // check if dead
        if(health <= 0) System.out.println("Aww, monster ded :(");
    }

    public String status(){
        String answer = "Monster: " + health + " / Damage: " + maxDmg;
        if(maxDmg > 50) {
            answer += " [DANGER]";
        }
        return answer;
        
    }

}
