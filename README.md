## Monster Battle Game - GUI Edition

Build your own turn-based monster battle game using a pre-built GUI!

## Quick Start

1. **Run `GameDemo.java`** to see a complete working example
   - Shows character selection, combat, items, and win/loss
   - Study this code to understand the pattern

2. **Build your game in `Game.java`**
   - Fill in the `TODO` sections
   - Start simple, add features gradually

## Essential GUI Commands

Your `MonsterBattleGUI` object has these methods:

### Display Updates
```java
gui.updateMonsters(monsters);           // Show your monster list
gui.updatePlayerHealth(playerHealth);   // Update player HP
gui.updateInventory(inventory);         // Show your items
gui.displayMessage("Your message");     // Show text to player
```

### Button Controls
```java
String[] buttons = {"Attack", "Defend", "Heal", "Item"};
gui.setActionButtons(buttons);          // Label the 4 buttons

int choice = gui.waitForAction();       // Wait for click, returns 0-3
```

### Visual Effects
```java
gui.highlightMonster(2);                // Flash monster #2
gui.pause(500);                         // Wait 500ms
```

### One-Time Setup
```java
gui.setPlayerMaxHealth(100);            // Set max HP (call once)
```

## Game Structure Pattern

1. **Setup**: Create GUI, monsters, items, set starting health
2. **Game Loop**: 
   - Display message → wait for action → do action
   - Monster turn
   - Check win/loss
3. **Your Code**: Fill in what each action does!

## Your own Monster

Your unit page has you write your own `Monster` class first, from nothing.
`src/game/Monster.java` is the starter's finished one: **leave it closed until
yours runs**, then compare the two.

When yours replaces it, keep these four methods, spelled exactly like this.
The display calls them by name, so they are a promise your class makes to code
you did not write:

```java
public int health()       // above 0 means alive
public double damage()
public int speed()
public String special()   // "" for no special move
```

Any other accessor you add follows the usual `getX()` convention.

## Creating Items

```java
// Items use lambdas to define what they do
inventory.add(new Item("Potion", "🧪", () -> {
    playerHealth += 30;
    gui.updatePlayerHealth(playerHealth);
}));
```

## Handling Monster Special Abilities

Check the monster's special ability and add custom behavior:
```java
Monster target = monsters.get(0);

// Check if monster has a special ability
if (!target.special().isEmpty()) {
    
    if (target.special().equals("Fire Breath")) {
        int extraDamage = 10;
        playerHealth -= extraDamage;
        gui.displayMessage("🔥 Fire Breath burns you for " + extraDamage + " damage!");
    }
    else if (target.special().equals("Poison")) {
        // Poison could reduce damage over time
        gui.displayMessage("☠️ You've been poisoned!");
    }
    else if (target.special().equals("Regeneration")) {
        // Monster heals itself
        gui.displayMessage("💚 Monster regenerates health!");
    }
}
```

## Tips

- **Start simple**: Get basic attack/heal working first
- **Use GameDemo.java**: Copy patterns from the working example
- **Test often**: Run after each small change
- **Be creative**: Customize monsters, items, and abilities!