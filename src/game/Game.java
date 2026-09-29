package game;
import java.util.ArrayList;

import gui.MonsterBattleGUI;

/**
 * Your game. GameDemo.java is a finished one to copy patterns from.
 * Fill in each TODO, and run this file after every change.
 */
public class Game {
    
    // The window. You call its methods; you never need to open src/gui.
    private MonsterBattleGUI gui;
    
    // Game state: change these, then tell the gui.
    private ArrayList<Monster> monsters;
    private ArrayList<Item> inventory;
    private int playerHealth;
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
    
    // TODO: How much health, how many monsters, which items? Your choice.
    private void setupGame() {
        // Create the GUI
        gui = new MonsterBattleGUI("Monster Battle - MY GAME");
        
        // TODO: Setup player health
        maxHealth = 100;  // Change this if you want
        playerHealth = 100;
        gui.setPlayerMaxHealth(maxHealth);
        gui.updatePlayerHealth(playerHealth);
        
        // TODO: Create monsters - how many do you want?
        monsters = new ArrayList<>();
        monsters.add(new Monster());  // Add more monsters here!
        monsters.add(new Monster());
        gui.updateMonsters(monsters);
        
        // TODO: Create starting items (GameDemo's addBomb() shows the shape)
        inventory = new ArrayList<>();
        gui.updateInventory(inventory);
        
        // TODO: Customize button labels
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
        gui.displayMessage("TODO: Implement attack!");
    }
    
    // TODO: What does defending change? Less damage next turn? A blocked hit?
    private void defend() {
        gui.displayMessage("TODO: Implement defend!");
    }
    
    // TODO: How much health back, and can it go past maxHealth?
    private void heal() {
        gui.displayMessage("TODO: Implement heal!");
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