import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    static SecureRandom random = new SecureRandom();
    static ArrayList<Character> currentPlayers = new ArrayList<>();
    static ArrayList<Enemy> currentEnemies = new ArrayList<>();
    static int level = 1;
    static boolean game = true;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        DataStructures data = new DataStructures();
        data.readCSV();
        initializeChars(data);
        while (game) {
            initializeEnemies();
            battle();
            System.out.println("Enter 'continue' to go to next level");
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
                    targeted = false;
                }

                for (int i = 0; i < currentEnemies.size(); i++) {
                    int enemyIndex = 0;
                    for (int j = 0; j < currentPlayers.size(); j++) {
                        if (currentPlayers.get(j).getRole().equals("Tank")) {
                            enemyTemp = j;
                        }
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

            }

            if (numberOfEnemies == 0) {
                fighting = false;
                TimeUnit.SECONDS.sleep(1);
                System.out.println("All enemies are dead..");
                System.out.println("LEVEL CLEARED");
            } else if (numberOfPlayers == 0) {
                fighting = false;
                TimeUnit.SECONDS.sleep(1);
                System.out.println("All players are dead...");
                System.out.println("GAME OVER");
                game = false;
            }
        }
    }
}
