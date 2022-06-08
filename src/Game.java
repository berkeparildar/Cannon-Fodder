import java.util.ArrayList;
import java.util.Random;

public class Game {
    public void itemDrops(ArrayList<Item> droppedItems, DataStructures data) {
        String[] arr = { "T", "F", "H" };
        int idChoice = new Random().nextInt(arr.length);
        boolean val = new Random().nextInt(3) == 0;
        if (val) {
            droppedItems.add(new Potion(data, ""));
            System.out.println("Enemy dropped " + droppedItems.get(droppedItems.size() - 1).getName());
            System.out.println("Select inspect to pick up the item");
        }
        boolean weapon = new Random().nextInt(10) == 0;
        if (weapon) {
            droppedItems.add(new Weapons(data, arr[idChoice]));
            System.out.println("Enemy dropped " + droppedItems.get(droppedItems.size() - 1).getName());
            System.out.println("Select inspect to pick up the item");
        }
        int idChoice2 = new Random().nextInt(arr.length);
        boolean armor = new Random().nextInt(10) == 0;
        if(armor){
            droppedItems.add(new Armor(data, arr[idChoice2]));
            System.out.println("Enemy dropped " + droppedItems.get(droppedItems.size() - 1).getName());
            System.out.println("Select inspect to pick up the item");
        }
    }
    public void inventory(){
        
    }
}
