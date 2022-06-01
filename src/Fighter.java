import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Fighter extends Character {
    public Fighter(DataStructures data) {
        super(data);
        setIntelligence(calculateIntelligence());
        setStrength(calculateStrength());
        setVitality(calculateVitality());
        setHP(calculateHP());
        setWeapon(new Weapons(data, "F"));
        setArmor(new Armor(data, "F"));
        setDamage(calculateDamage());
        getInventory().add(getWeapon());
        getInventory().add(getArmor());
    }

    @Override
    public String nameSetter(DataStructures data) {
        ArrayList<String> healName = new ArrayList<>();
        characterNameArray = data.getCharacterName();
        for (int i = 0; i < characterNameArray.size(); i++) {
            if (data.getCharacterData().get(0).get(characterNameArray.get(i)).equals("Warrior")) {
                healName.add(characterNameArray.get(i));
            }
        }
        return healName.get(random.nextInt(healName.size() - 1));
    }

    @Override
    public int calculateIntelligence() {
        return random.nextInt(1, 3);
    }

    @Override
    public int calculateVitality() {
        return random.nextInt(1, 5);
    }

    @Override
    public int calculateStrength() {
        return random.nextInt(6, 10);
    }

    @Override
    public void characterPrintInfo() {
        super.characterPrintInfo();
        System.out.println("Intelligence: " + getIntelligence() + "\nStrength: " + getStrength() + "\nVitality: "
                + getVitality() + "\nHP: " + getHP() + "\nWeapon: " + getWeapon().weaponType + " "
                + getWeapon().getName() + "\nArmor: " + getArmor().getName());
                System.out.println("=========================================================");

    }

    @Override
    public int calculateDamage() {
        return getWeapon().getDamage() + getStrength();
    }

    @Override
    public void attack() throws InterruptedException {
        System.out.println(getName() + " is attacking " + getTarget().getName() + "...");
        getTarget().setHealthPoint(getTarget().getHealthPoint() - getDamage());
        TimeUnit.SECONDS.sleep(1);
        System.out.println(getName() + " damaged " + getTarget().getName() + " for " + getDamage() + " damage.");
    }

}
