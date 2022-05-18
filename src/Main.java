public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        DataStructures data = new DataStructures();
        data.readCSV();

      
            Weapons weapon = new Weapons(data,"H");
            weapon.printItemInfo();
            Armor armor = new Armor(data);
            armor.printItemInfo();
            Healer heal = new Healer(data);
            heal.characterPrintInfo();
        
    }

   
}
