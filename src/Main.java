public class Main {
    public static void main(String[] args) throws Exception {
        DataStructures data = new DataStructures();
        data.readCSV();
        System.out.println();
        Healer heal = new Healer(data);
        heal.characterPrintInfo();
        Fighter fight = new Fighter(data);
        fight.characterPrintInfo();
        Tank tank = new Tank(data);
        tank.characterPrintInfo();
    }
}
