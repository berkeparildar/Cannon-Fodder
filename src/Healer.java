import java.util.ArrayList;

public class Healer extends Character {

    public Healer(DataStructures data) {
        super(data);
        this.intelligence = calculateIntelligence();
        this.strength = calculateStrength();
        this.vitality = calculateVitality();
        this.HP = calculateHP();
        this.weapon = new Weapons(data,"H");     
    }

    @Override
    public String nameSetter(DataStructures data) {
        ArrayList<String> healName = new ArrayList<>();
        characterNameArray = data.getCharacterName();
        for (int i = 0; i < characterNameArray.size(); i++) {
            if (data.getCharacterData().get(0).get(characterNameArray.get(i)).equals("Healer")) {
                healName.add(characterNameArray.get(i));
            }
        }
        return healName.get(random.nextInt(healName.size() - 1));
    }

    @Override
    public int calculateIntelligence(){
        return random.nextInt(6,10);
    }
    @Override
    public int calculateVitality(){
        return random.nextInt(1,5);
    }
    @Override
    public int calculateStrength(){
        return random.nextInt(3,7);
    }
    public int calculateHP(){
        return (int) Math.round(0.7 * vitality + 0.2 * strength + 0.1 * intelligence);
    }
    @Override
    public void characterPrintInfo(){
        super.characterPrintInfo();
        System.out.println("Intelligence: " + intelligence + "\nStrength: " + strength + "\nVitality: " + vitality + "\nHP: " + HP + "\nWeapon: " + weapon.weaponType + weapon.name);
    }
    
}
