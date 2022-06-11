import java.util.Scanner;

public class Main {

  
    public static void main(String[] args) throws Exception {
        int level = 1;
        boolean game = true;
        Scanner sc = new Scanner(System.in);
        DataStructures data = new DataStructures();
        Game run = new Game();
    
        System.out.println("Hello, World!");
        data.readCSV();
        run.initializeChars(data);
        while (game) {
            run.initializeEnemies(level);
            run.battle(level,data,game);
            System.out.println("Enter to go to next level");
            sc.next();
            level++;
        }
        sc.close();
    }
}
