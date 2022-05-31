import java.security.SecureRandom;
import java.util.ArrayList;

public class Main {
    static SecureRandom random = new SecureRandom();
    static ArrayList<Character> currentPlayers = new ArrayList<>();
    static ArrayList<Enemy> currentEnemies = new ArrayList<>();
    static int level = 1;
    static boolean game = true;

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        DataStructures data = new DataStructures();
        data.readCSV();
        initializeChars(data);
        while (game) {
            initializeEnemies();
            battle();
            level++;
        }
    }

    static void initializeChars(DataStructures data) {
        Healer heal = new Healer(data);
        // heal.characterPrintInfo();
        Tank tank = new Tank(data);
        // tank.characterPrintInfo();
        Fighter fighter = new Fighter(data);
        // fighter.characterPrintInfo();
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

    static void battle() {
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
                            "It is " + currentPlayers.get(i).getName() + "'s turn of role "
                                    + currentPlayers.get(i).getRole());
                    currentPlayers.get(i).setTarget(currentEnemies.get(temp));
                    currentPlayers.get(i).attack();
                    if (currentPlayers.get(i).getTarget().getHealthPoint() <= 0) {
                        currentPlayers.get(i).getTarget().setHealthPoint(0);
                    }
                    System.out.println(
                            "Current health of the enemy is " + currentPlayers.get(i).getTarget().getHealthPoint());
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
                    currentEnemies.get(i).setTarget(currentPlayers.get(enemyIndex));
                    currentEnemies.get(i).attack();
                    if (currentEnemies.get(i).getTarget().getHP() <= 0) {
                        currentEnemies.get(i).getTarget().setHP(0);
                    }
                    System.out.println("Current health of the " + currentEnemies.get(i).getTarget().getName() + " is "
                            + currentEnemies.get(i).getTarget().getHP());
                    if (currentEnemies.get(i).getTarget().getHP() <= 0) {
                        break;
                    }
                }

                if (currentPlayers.get(enemyTemp).getHP() <= 0) {
                    System.out.println(currentPlayers.get(enemyTemp).getName() + " is dead");
                    currentPlayers.remove(enemyTemp);
                    numberOfPlayers = currentPlayers.size();
                    targeted = false;
                }

            }

            if (numberOfEnemies == 0) {
                fighting = false;
                System.out.println("All enemies are dead..");
                System.out.println("LEVEL CLEARED");
            } else if (numberOfPlayers == 0) {
                fighting = false;
                System.out.println("All players are dead...");
                System.out.println("GAME OVER");
                game = false;
            }
        }
    }
}
