import java.security.SecureRandom;
import java.util.ArrayList;

public class Character {
    SecureRandom random = new SecureRandom();
    String name;
    String role;
    int HP;
    Weapons weapon;
    Armor armor;
    int strength;
    int vitality;
    int intelligence;
    int money;
    ArrayList<String> characterNameArray = new ArrayList<>();

    public String nameSetter(DataStructures data) {
        characterNameArray = data.getCharacterName();
        return characterNameArray.get(random.nextInt(characterNameArray.size() - 1));
    }
    public Character(DataStructures data){
       this.name = nameSetter(data);
       this.role = data.getCharacterData().get(0).get(name);
       this.strength = calculateStrength();
       this.vitality = calculateVitality();
       this.intelligence = calculateIntelligence();
       this.money = setMoney();
    }
    public int calculateStrength(){
        return random.nextInt(1,5);
    }
    public int calculateIntelligence(){
        return random.nextInt(1,5);
    }
    public int calculateVitality(){
        return random.nextInt(1,5);
    }
    public int setMoney(){
        return random.nextInt(10,50);
    }
    public void characterPrintInfo(){
        System.out.println("Name: " + name + "\nClass: " + role);
    }
}
