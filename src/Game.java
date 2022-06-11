import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Game {
    static SecureRandom random = new SecureRandom();
    static ArrayList<Character> currentPlayers = new ArrayList<>();
    static ArrayList<Enemy> currentEnemies = new ArrayList<>();
    static ArrayList<Item> droppedItems = new ArrayList<>();
    static int round = 1;
    static Scanner sc = new Scanner(System.in);

    public void initializeEnemies(int level) {
        for (int i = 0; i < Math.pow(2, level); i++) {
            Enemy enemy = new Enemy();
            currentEnemies.add(enemy);
        }
    }

    public void initializeChars(DataStructures data) {
        Healer heal = new Healer(data);
        Tank tank = new Tank(data);
        Fighter fighter = new Fighter(data);
        currentPlayers.add(tank);
        currentPlayers.add(heal);
        currentPlayers.add(fighter);
    }

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
        if (armor) {
            droppedItems.add(new Armor(data, arr[idChoice2]));
            System.out.println("Enemy dropped " + droppedItems.get(droppedItems.size() - 1).getName());
            System.out.println("Select inspect to pick up the item");
        }
    }

    public void Ability(int round, ArrayList<Character> currentPlayers, int index) {
        System.out.println("Special Ability");
        if (round >= currentPlayers.get(index).getAbility().getEndOfCooldown()) {
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
        for (int j = 0; j < currentPlayers.get(index).getInventory().size(); j++) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(j + ")" + currentPlayers.get(index).getInventory().get(j).getType() + " "
                    + currentPlayers.get(index).getInventory().get(j).getName());
        }
        System.out.println("Choose an item to choose actions");
        System.out.println("Enter 9 to go back to menu");
        int caseThreeAnswer = sc.nextInt();
        if (caseThreeAnswer == 9) {

        } else {
            currentPlayers.get(index).getInventory().get(caseThreeAnswer).printItemInfo();
            if (currentPlayers.get(index).getWeapon() == currentPlayers.get(index).getInventory().get(caseThreeAnswer)
                    || currentPlayers.get(index).getArmor() == currentPlayers.get(index).getInventory()
                            .get(caseThreeAnswer)) {
                System.out.println("This Item is equipped");
            } else {
                System.out.println("Enter 1 to equip this item");
                System.out.println("Enter 2 see additional information");
                System.out.println("Enter 3 to go back to menu");
                int thirdAnswerTwo = sc.nextInt();
                if (thirdAnswerTwo == 1) {
                    Item a = currentPlayers.get(index).getInventory().get(caseThreeAnswer);
                    if (currentPlayers.get(index).getInventory().get(caseThreeAnswer).getType().equals("WEAPON")) {
                        Weapons weap = (Weapons) a;
                        if (weap.getType().equals(currentPlayers.get(index).getWeapon().getType())) {
                            currentPlayers.get(index).setWeapon(weap);
                        } else {
                            System.out.println("You can not equip this type of weapon");
                        }
                    } else if (currentPlayers.get(index).getInventory().get(caseThreeAnswer).getType()
                            .equals("ARMOR")) {
                        Armor armor = (Armor) a;
                        if (armor.getType().equals(currentPlayers.get(index).getArmor().getType())) {
                            currentPlayers.get(index).setArmor(armor);
                        } else {
                            System.out.println("You can not equip this type of armor");
                        }
                    } else {
                        Potion potion = (Potion) a;
                        potion.consumePotion(currentPlayers.get(index), 0);
                    }
                    currentPlayers.get(index).setDamage(currentPlayers.get(index).calculateDamage());
                } else if (thirdAnswerTwo == 2) {
                    currentPlayers.get(index).getInventory().get(caseThreeAnswer).printItemInfo();
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

    public void collectItem(ArrayList<Item> droppedItems, ArrayList<Character> currentPlayers, int index) {
        int k = droppedItems.size();
        if (k >= 1) {
            System.out.println("One of the enemies dropped " + droppedItems.get(0).getName());
            currentPlayers.get(index).getInventory().add(droppedItems.get(0));
            System.out.println(droppedItems.get(0).getName() + " is added to your inventory");
            droppedItems.remove(0);
        } else {
            System.out.println("There is nothing to inspect at the moment");
        }
    }

    public void battle(int level, DataStructures data, boolean game) throws InterruptedException {
        int temp;
        int enemyTemp = 0;
        boolean fighting = true;
        int numberOfEnemies = currentEnemies.size();
        int numberOfPlayers = currentPlayers.size();
        System.out.println("------------LEVEL " + level + " --------------");
        System.out.println("Number of enemies are " + numberOfEnemies + "          |");
        System.out.println("The fight begins------------------");
        while (fighting) {
            boolean targeted = true;
            if (numberOfEnemies == 1) {
                temp = 0;
            } else {
                temp = random.nextInt(numberOfEnemies - 1);
            }
            while (targeted) {
                System.out.println();
                System.out.println("Round " + round);
                System.out.println();
                for (int i = 0; i < currentPlayers.size(); i++) {
                    if(currentEnemies.size()==0){
                        targeted = false;
                        break;
                    }
                    System.out.println(
                            "It is " + currentPlayers.get(i).getRole() + " " + currentPlayers.get(i).getName()
                                    + "'s turn.");
                    currentPlayers.get(i).setTarget(currentEnemies.get(temp));
                    boolean menu = true;
                    while (menu) {
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println("******************************************************");
                        System.out.println("Here are your player options");
                        System.out.println("Enter 1 to basic attack enemy");
                        System.out.println("Enter 2 to use special ability");
                        System.out.println("Enter 3 to display inventory");
                        System.out.println("Enter 4 to display character information and stats");
                        System.out.println("Enter 5 to inspect");
                        System.out.println("******************************************************");
                        int answer = sc.nextInt();
                        switch (answer) {
                            case 1:
                                currentPlayers.get(i).attack();
                                menu = false;
                                break;
                            case 2:
                                Ability(round,currentPlayers,i);
                                menu = false;
                                break;
                            case 3:
                                Inventory(currentPlayers,i);
                                break;
                            case 4:
                                currentPlayers.get(i).characterPrintInfo();
                                break;
                            case 5:
                                collectItem(droppedItems, currentPlayers, i);
                                break;
                        }
                        TimeUnit.SECONDS.sleep(1);
                        if (currentPlayers.get(i).getTarget().getHealthPoint() <= 0) {
                            currentPlayers.get(i).getTarget().setHealthPoint(0);
                        }
                        System.out.println(
                                "Current health of the enemy is " + currentPlayers.get(i).getTarget().getHealthPoint());
                        TimeUnit.SECONDS.sleep(1);
                        if (currentPlayers.get(i).getTarget().getHealthPoint() <= 0) {
                            System.out.println(currentEnemies.get(temp).getName() + " is dead");
                            currentEnemies.remove(temp);
                            numberOfEnemies = currentEnemies.size();
                            itemDrops(droppedItems, data);
                        }
                    }
                }
                for (int i = 0; i < currentEnemies.size(); i++) {
                    int enemyIndex = 0;
                    for (int j = 0; j < currentPlayers.size(); j++) {
                        if (currentPlayers.get(j).getRole().equals("Tank")) {
                            enemyTemp = j;
                        }
                    }
                    if (currentEnemies.get(i).getIsStunned()) {
                        System.out.println(currentEnemies.get(i).getName() + " is stunned.");
                        continue;
                    }
                    System.out.println("It is " + currentEnemies.get(i).getName() + "'s turn ");
                    TimeUnit.SECONDS.sleep(1);
                    currentEnemies.get(i).setTarget(currentPlayers.get(enemyIndex));
                    currentEnemies.get(i).attack();
                    if (currentEnemies.get(i).getTarget().getHP() <= 0) {
                        currentEnemies.get(i).getTarget().setHP(0);
                    }
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("Current health of the " + currentEnemies.get(i).getTarget().getName() + " is "
                            + currentEnemies.get(i).getTarget().getHP());
                    currentEnemies.get(i).setStunned(false);
                    TimeUnit.SECONDS.sleep(1);
                    if (currentEnemies.get(i).getTarget().getHP() <= 0) {
                        break;
                    }
                }

                if (currentPlayers.get(enemyTemp).getHP() <= 0) {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(currentPlayers.get(enemyTemp).getName() + " is dead");
                    currentPlayers.remove(enemyTemp);
                    numberOfPlayers = currentPlayers.size();
                    targeted = false;
                }
                round++;
            }
            if (numberOfEnemies == 0) {
                fighting = false;
                TimeUnit.SECONDS.sleep(1);
                System.out.println();
                System.out.println("All enemies are dead..");
                System.out.println("LEVEL CLEARED");
            } else if (numberOfPlayers == 0) {
                fighting = false;
                TimeUnit.SECONDS.sleep(1);
                System.out.println();
                System.out.println("All players are dead...");
                System.out.println("GAME OVER");
                game = false;
            }
        }
    }
}

