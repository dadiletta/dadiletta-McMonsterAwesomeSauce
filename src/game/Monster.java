package game;

import java.util.ArrayList;

import gui.MonsterBattleGUI;

public class Monster {
    // INSTANCE VARIABLES (properties)
    private int health;
    private double damage;
    private int speed;
    private String special; 

    // CONSTRUCTOR
    public Monster(){
        // randomly generate health, damage, speed
        health = (int)(Math.random() * 80 + 1) + 20;
        // random 10 - 50
        damage = (Math.random() * 41) + 10;
        // speed: random 1-10
        speed = (int)(Math.random() * 10) + 1;
        // by default, the monster doesn't have a special move
        special = "";
    }
    // OVERLOADED CONSTRUCTOR
    public Monster(String special){
        this();
        this.special = special;
    }
    
    // ACCESSOR METHODS
    public int health() { return this.health; }
    public double damage() { return Math.round(damage * 100.0) / 100.0; }
    public int speed() { return speed; }
    public String special() { return this.special; }

    // MUTATOR METHODS
    public void takeDamage(int dmg){ health -= dmg; }
    public void heal(int heal){ health += heal; }

    // UTILITY METHODS
    public void heal(Monster friend, int heal){
        friend.heal(heal);
    }

    public void jan6(ArrayList<Monster> monsters, MonsterBattleGUI gui){
        // TODO: loop through all monsters and apply twoFast2furious
        gui.displayMessage("Let's go beserk! It's gonna be wild!");
    }

    // beserker mode
    public void twoFast2furious(MonsterBattleGUI gui){
        // lowers health to 25%, not less than 5
        health /= 4;
        if(health < 5) health = 5;

        // damage and speed increase by 4x
        damage *= 4;
        speed *= 4;

        // can we post a message? 
        gui.displayMessage("WRARR!");
    }
}