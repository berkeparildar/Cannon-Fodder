import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

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
    public void ability(int round, ArrayList<Character> currentPlayers, int index){
        System.out.println("Special Ability");
        Scanner sc = new Scanner(System.in);
        if (round == currentPlayers.get(index).getAbility().getEndOfCooldown()) {
            currentPlayers.get(index).getAbility().setAbilityReady(true);
        }
        if (currentPlayers.get(index).getAbility().getabilityReady()) {
            if (currentPlayers.get(index).getRole().equals("Healer")) {
                System.out.println("Please select an ally to heal");
                for (int j = 0; j < currentPlayers.size(); j++) {
                    System.out
                            .println("Enter " + j + " to heal "
                                    + currentPlayers.get(j).getName());
                }
                int caseTwoChoice = sc.nextInt();
                if (caseTwoChoice == 0) {
                    currentPlayers.get(index).getAbility().setAlly(currentPlayers.get(0));
                } else if (caseTwoChoice == 1) {
                    currentPlayers.get(index).getAbility().setAlly(currentPlayers.get(1));
                } else if (caseTwoChoice == 2) {
                    currentPlayers.get(index).getAbility().setAlly(currentPlayers.get(2));
                }
            }
            currentPlayers.get(index).getAbility().cast();
            currentPlayers.get(index).getAbility()
                    .setEndOfCooldown(round + currentPlayers.get(index).getAbility().getCooldown());
            System.out.println("Ability will be ready on round "
                    + currentPlayers.get(index).getAbility().getEndOfCooldown());
        } else {
            System.out.println("Ability not ready yet");
        }
    }
    public void Inventory(ArrayList<Character> currentPlayers, int index) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        for (int j = 0; j < currentPlayers.get(index).getInventory().size(); j++) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(j + ")" + currentPlayers.get(index).getInventory().get(j).getType() + " "
                    + currentPlayers.get(index).getInventory().get(j).getName());
        }
        System.out.println("Choose an item to choose actions");
        System.out.println("Enter 9 to go back to menu");
        int caseThreeAnswer = sc.nextInt();
        currentPlayers.get(index).getInventory().get(caseThreeAnswer).printItemInfo();
        if(currentPlayers.get(index).getWeapon()==currentPlayers.get(index).getInventory().get(caseThreeAnswer)|| currentPlayers.get(index).getArmor()==currentPlayers.get(index).getInventory().get(caseThreeAnswer)){
            System.out.println("This Item is equipped");
        }
        else{
            System.out.println("Enter 1 to equip this item");
            System.out.println("Enter 2 to go back to menu");
            int thirdAnswerTwo = sc.nextInt();
            if(thirdAnswerTwo==1){
                Item a = currentPlayers.get(index).getInventory().get(caseThreeAnswer);
                if(currentPlayers.get(index).getInventory().get(caseThreeAnswer).getType().equals("WEAPON")){
                    Weapons weap = (Weapons) a;
                    if(weap.getType().equals("Wand")){
                        currentPlayers.get(index).setWeapon(weap);
                    }else{
                        System.out.println("You can not equip this type of weapon");
                    }
                }
                else if(currentPlayers.get(index).getInventory().get(caseThreeAnswer).getType().equals("ARMOR")){
                }
                else{
                    Potion potion = (Potion) a;
                    potion.consumePotion(currentPlayers.get(index), 0);
                }
            }
        }
        if (caseThreeAnswer == 1) {
            for (int j = 0; j < currentPlayers.get(index).getInventory().size(); j++) {
                TimeUnit.SECONDS.sleep(1);
                currentPlayers.get(index).getInventory().get(j).printItemInfo();
            }
        }
    }
}
