import java.util.ArrayList;

public class Main {
    static ArrayList<Character> currentPlayers = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        DataStructures data = new DataStructures();
        data.readCSV();
        initializeChars(data);
        Enemy enemy = new Enemy();
        System.out.println(currentPlayers.get(0).getHP());
        enemy.setTarget(currentPlayers.get(0));
        enemy.attack();
        System.out.println(currentPlayers.get(0).getHP());
    }

    static void initializeChars(DataStructures data) {
        Healer heal = new Healer(data);
        heal.characterPrintInfo();
        Tank tank = new Tank(data);
        tank.characterPrintInfo();
        Fighter fighter = new Fighter(data);
        fighter.characterPrintInfo();
        currentPlayers.add(heal);
        currentPlayers.add(tank);
        currentPlayers.add(fighter);
    }
}
