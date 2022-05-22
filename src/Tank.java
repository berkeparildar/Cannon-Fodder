import java.util.ArrayList;

public class Tank extends Character {
    public Tank(DataStructures data){
        super(data);
        setIntelligence(calculateIntelligence()); 
        setStrength(calculateStrength()); ;
        setVitality(calculateVitality());
        setHP(calculateHP());
        setWeapon(new Weapons(data,"T"));
        setArmor(new Armor(data,"T"));
    }
    @Override
    public String nameSetter(DataStructures data) {
        ArrayList<String> healName = new ArrayList<>();
        characterNameArray = data.getCharacterName();
        for (int i = 0; i < characterNameArray.size(); i++) {
            if (data.getCharacterData().get(0).get(characterNameArray.get(i)).equals("Knight")) {
                healName.add(characterNameArray.get(i));
            }
        }
        return healName.get(random.nextInt(healName.size() - 1));
    }
    @Override
    public int calculateIntelligence(){
        return random.nextInt(1,3);
    }
    @Override
    public int calculateVitality(){
        return random.nextInt(6 ,10);
    }
    @Override
    public int calculateStrength(){
        return random.nextInt(1,5);
    }
    @Override
    public void characterPrintInfo(){
        super.characterPrintInfo();
        System.out.println("Intelligence: " + getIntelligence() + "\nStrength: " + getStrength() + "\nVitality: " + getVitality() + "\nHP: " + getHP() + "\nWeapon: " + getWeapon().weaponType + " " + getWeapon().getName() + "\nArmor: " + getArmor().getName());
    }
}