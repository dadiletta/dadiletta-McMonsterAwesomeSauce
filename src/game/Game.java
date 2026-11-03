package game;
import java.util.ArrayList;

import gui.MonsterBattleGUI;

public class Game {
    
    // The window. You call its methods; you never need to open src/gui.
    private MonsterBattleGUI gui;
    
    // Game state: change these, then tell the gui.
    private ArrayList<Monster> monsters;
    private ArrayList<Item> inventory;
    private int playerHealth;
    private int shield = 0;
    private int SHIELD_UP = 20;
    private int maxHealth;
    
    public static void main(String[] args) {
        // main is static, so it makes one Game object to play (Unit 3.7).
        Game game = new Game();
        game.play();
    }
    
    public void play() {
        setupGame();
        gameLoop();
    }
    
    private void setupGame() {
        // Create the GUI
        gui = new MonsterBattleGUI("Mr. A's Monster Battle");
        
        maxHealth = 100; 
        playerHealth = 75;
        gui.setPlayerMaxHealth(maxHealth);
        gui.updatePlayerHealth(playerHealth);
        
        monsters = new ArrayList<>();
        monsters.add(new Monster());
        gui.updateMonsters(monsters);
        
        // TODO: Create starting items (GameDemo's addBomb() shows the shape)
        inventory = new ArrayList<>();
        gui.updateInventory(inventory);
        
        String[] buttons = {"Attack", "Defend", "Heal", "Use Item"};
        gui.setActionButtons(buttons);
        
        // Welcome message
        gui.displayMessage("Battle Start! Choose your action.");
    }
    
    // Player turn, then monster turn, until one side is gone.
    private void gameLoop() {
        // Keep playing while monsters alive and player alive
        while (countLivingMonsters() > 0 && playerHealth > 0) {
            
            // PLAYER'S TURN
            gui.displayMessage("Your turn! HP: " + playerHealth);
            int action = gui.waitForAction();  // Wait for button click (0-3)
            handlePlayerAction(action);
            gui.updateMonsters(monsters);
            gui.pause(500);
            
            // MONSTER'S TURN (if any alive and player alive)
            if (countLivingMonsters() > 0 && playerHealth > 0) {
                monsterAttack();
                gui.updateMonsters(monsters);
                gui.pause(500);
            }
        }
        
        // Game over!
        if (playerHealth <= 0) {
            gui.displayMessage("💀 DEFEAT! You have been defeated...");
        } else {
            gui.displayMessage("🎉 VICTORY! You defeated all monsters!");
        }
    }
    
    // The four buttons, left to right, are 0 to 3.
    private void handlePlayerAction(int action) {
        switch (action) {
            case 0: // Attack button
                attackMonster();
                break;
            case 1: // Defend button
                defend();
                break;
            case 2: // Heal button
                heal();
                break;
            case 3: // Use Item button
                useItem();
                break;
        }
    }
    
    // TODO: How much damage, and to which monster?
    private void attackMonster() {
        gui.displayMessage("JOHN WICK!");
        for(Monster m : monsters){
            // kill each one
        }
    }
    
    private void defend() {
        shield += SHIELD_UP;
        gui.displayMessage("SHIELD UP! Current shield: " + shield);
    }
    
    private void heal() {
        // random number 0 - 40 
        int healNumber = 20; // WRONG
        playerHealth += healNumber;
        // SUPER HEAL?
        if(playerHealth > maxHealth) {
            maxHealth = playerHealth;
            gui.setPlayerMaxHealth(maxHealth);
        }
    
        gui.updatePlayerHealth(playerHealth);
        gui.displayMessage("You healed for " + healNumber + " points!");
    }
    
    // Uses the first item in the inventory.
    private void useItem() {
        if (inventory.isEmpty()) {
            gui.displayMessage("No items in inventory!");
            return;
        }
        
        // Use first item
        Item item = inventory.remove(0);
        gui.updateInventory(inventory);
        item.use();  // The item knows what to do!
    }
    
    // TODO: Which monster attacks, and for how much? A special move goes here.
    private void monsterAttack() {
        gui.displayMessage("TODO: Implement monster attack!");
    }
    
    // Helpers. Add your own below these two.
    private int countLivingMonsters() {
        int count = 0;
        for (Monster m : monsters) {
            if (m.health() > 0) count++;
        }
        return count;
    }
    
    private Monster getRandomLivingMonster() {
        ArrayList<Monster> alive = new ArrayList<>();
        for (Monster m : monsters) {
            if (m.health() > 0) alive.add(m);
        }
        if (alive.isEmpty()) return null;
        return alive.get((int)(Math.random() * alive.size()));
    }
}