import java.util.Scanner;

public class BattleMonster {
    private static Puppy puppy;
    private static Monster[] monsters = new Monster[5];

    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        String input = "";
        
        System.out.println("Your first choice: Fight or puppy?");
        do {
            System.out.print("INPUT: ");
            input = s.nextLine().toLowerCase().trim();

            // our turn
            if(input.equals("puppy") && puppy == null){
                puppy = new Puppy();
            }
            
            

            // their turn
            
        } while(!input.equals("quit"));
    }

    public static boolean noMonsters() {
        // loop and check for monsters
        for(int i = 0; i < monsters.length; i++){
            if(monsters[i] != null) return false;
        }
        return true;
    }
}
