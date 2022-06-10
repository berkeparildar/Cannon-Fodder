import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Tank extends Character {
    public Tank(DataStructures data) {
        super(data);
        setIntelligence(calculateIntelligence());
        setStrength(calculateStrength());
        setVitality(calculateVitality());
        setHP(calculateHP());
        setWeapon(new Weapons(data, "T"));
        setArmor(new Armor(data, "T"));
        setDamage(calculateDamage());
        getInventory().add(getWeapon());
        getInventory().add(getArmor());
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
    public int calculateIntelligence() {
        return random.nextInt(3-1) + 1;
    }

    @Override
    public int calculateVitality() {
        return random.nextInt(10 - 6 ) + 6;
    }

    @Override
    public int calculateStrength() {
        return random.nextInt(5 - 1 ) + 1;
    }

    @Override
    public void characterPrintInfo() {
        super.characterPrintInfo();
        System.out.println("Intelligence: " + getIntelligence() + "\t\tStrength:" + getStrength() + "\t\tVitality:"
                + getVitality() + "\nHP: " + getHP() + "\t\t\tWeapon: " 
                + getWeapon().getName() + "\tArmor: " + getArmor().getName());
                System.out.println("=========================================================");
    }

    @Override
    public int calculateDamage() {
        return getWeapon().getDamage() + getVitality();
    }

    @Override
    public void attack() throws InterruptedException {
        System.out.println(getName() + " is attacking " + getTarget().getName() + "...");
        getTarget().setHealthPoint(getTarget().getHealthPoint() - getDamage());
        TimeUnit.SECONDS.sleep(1);
        System.out.println(getName() + " damaged " + getTarget().getName() + " for " + getDamage() + " damage.");
    }
}
