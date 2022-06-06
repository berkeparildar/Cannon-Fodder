import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.xml.transform.Source;

public class Main {
    static SecureRandom random = new SecureRandom();
    static ArrayList<Character> currentPlayers = new ArrayList<>();
    static ArrayList<Enemy> currentEnemies = new ArrayList<>();
    static ArrayList<Item> droppedItems = new ArrayList<>();
    static int level = 1;
    static int round = 1;
    static boolean game = true;
    static Scanner sc = new Scanner(System.in);
    static DataStructures data = new DataStructures();


    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        data.readCSV();
        initializeChars(data);
        while (game) {
            initializeEnemies();
            battle();
            System.out.println("Enter to go to next level");
            sc.next();
            level++;
        }
        sc.close();
    }

    static void initializeChars(DataStructures data) {
        Healer heal = new Healer(data);
        Tank tank = new Tank(data);
        Fighter fighter = new Fighter(data);
        currentPlayers.add(tank);
        currentPlayers.add(heal);
        currentPlayers.add(fighter);
    }

    static void initializeEnemies() {
        for (int i = 0; i < Math.pow(2, level); i++) {
            Enemy enemy = new Enemy();
            currentEnemies.add(enemy);
        }
    }

    static void battle() throws InterruptedException {
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
                temp = random.nextInt(0, numberOfEnemies - 1);
            }
            while (targeted) {
                for (int i = 0; i < currentPlayers.size(); i++) {
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
                        System.out.println("******************************************************");
                        int answer = sc.nextInt();
                        switch (answer) {
                            case 1:
                                currentPlayers.get(i).attack();
                                menu = false;
                                break;
                            case 2:
                                System.out.println("Special Ability");
                                if (round == currentPlayers.get(i).getAbility().getEndOfCooldown()) {
                                    currentPlayers.get(i).getAbility().setAbilityReady(true);
                                }
                                if (currentPlayers.get(i).getAbility().getabilityReady()) {
                                    if (currentPlayers.get(i).getRole().equals("Healer")) {
                                        System.out.println("Please select an ally to heal");
                                        for (int j = 0; j < currentPlayers.size(); j++) {
                                            System.out
                                                    .println("Enter " + j + " to heal "
                                                            + currentPlayers.get(j).getName());
                                        }
                                        int caseTwoChoice = sc.nextInt();
                                        if (caseTwoChoice == 0) {
                                            currentPlayers.get(i).getAbility().setAlly(currentPlayers.get(0));
                                        } else if (caseTwoChoice == 1) {
                                            currentPlayers.get(i).getAbility().setAlly(currentPlayers.get(1));
                                        } else if (caseTwoChoice == 2) {
                                            currentPlayers.get(i).getAbility().setAlly(currentPlayers.get(2));
                                        }
                                    }
                                    currentPlayers.get(i).getAbility().cast();
                                    currentPlayers.get(i).getAbility()
                                            .setEndOfCooldown(round + currentPlayers.get(i).getAbility().getCooldown());
                                } else {
                                    System.out.println("Ability not ready yet");
                                }
                                menu = false;
                                break;
                            case 3:
                                for (int j = 0; j < currentPlayers.get(i).getInventory().size(); j++) {
                                    TimeUnit.SECONDS.sleep(1);
                                    System.out.println(currentPlayers.get(i).getInventory().get(j).getType() + " "
                                            + currentPlayers.get(i).getInventory().get(j).getName());
                                }
                                System.out.println("Enter 1 to see additional information about the items");
                                System.out.println("Enter 2 to go back to menu");
                                int caseThreeAnswer = sc.nextInt();
                                if (caseThreeAnswer == 1) {
                                    for (int j = 0; j < currentPlayers.get(i).getInventory().size(); j++) {
                                        TimeUnit.SECONDS.sleep(1);
                                        currentPlayers.get(i).getInventory().get(j).printItemInfo();
                                    }
                                }
                                break;
                            case 4:
                                currentPlayers.get(i).characterPrintInfo();
                                break;
                                case 5: 
                                int k = droppedItems.size();
                                if(k>=1){
                                    System.out.println("One of the enemies dropped" + droppedItems.get(0).getName());
                                }else{
                                    System.out.println("There is nothing to inspect at the moment");
                                }
                        }
                    }
                    TimeUnit.SECONDS.sleep(1);
                    if (currentPlayers.get(i).getTarget().getHealthPoint() <= 0) {
                        currentPlayers.get(i).getTarget().setHealthPoint(0);
                    }
                    System.out.println(
                            "Current health of the enemy is " + currentPlayers.get(i).getTarget().getHealthPoint());
                    TimeUnit.SECONDS.sleep(1);
                    if (currentPlayers.get(i).getTarget().getHealthPoint() <= 0) {
                        break;
                    }
                }

                if (currentEnemies.get(temp).getHealthPoint() <= 0) {
                    System.out.println(currentEnemies.get(temp).getName() + " is dead");
                    currentEnemies.remove(temp);
                    numberOfEnemies = currentEnemies.size();
                    droppedItems.add(new Potion(data, ""));
                    System.out.println("Enemy dropped" + droppedItems.get(droppedItems.size()-1).getName());
                    targeted = false;
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
