import java.security.SecureRandom;
import java.util.ArrayList;

public class Character {
    SecureRandom random = new SecureRandom();
    private String name;
    private String role;
    private int HP;
    private int damage;
    private Weapons weapon;
    private Armor armor;
    private int strength;
    private int vitality;
    private int intelligence;
    private int money;
    private Enemy target;
    ArrayList<String> characterNameArray = new ArrayList<>();
    ArrayList<ArrayList<Item>> inventory = new ArrayList<>();
    ArrayList<Item> weaponsArray = new ArrayList<>();
    ArrayList<Item> armorArray = new ArrayList<>();

    public String nameSetter(DataStructures data) {
        characterNameArray = data.getCharacterName();
        return characterNameArray.get(random.nextInt(characterNameArray.size() - 1));
    }

    public Character(DataStructures data) {
        this.name = nameSetter(data);
        this.role = data.getCharacterData().get(0).get(name);
        this.strength = calculateStrength();
        this.vitality = calculateVitality();
        this.intelligence = calculateIntelligence();
        this.money = setMoney();
        this.inventory = getInventory();
        this.damage = getDamage();
        this.target = getTarget();
    }

    public void attack() {

    }

    public Enemy getTarget() {
        return target;
    }

    public void setTarget(Enemy target) {
        this.target = target;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public int calculateDamage() {
        return 0;
    }

    public int calculateStrength() {
        return random.nextInt(5);
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getStrength() {
        return strength;
    }

    public String getName() {
        return name;
    }

    public int calculateIntelligence() {
        return random.nextInt(1, 5);
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int calculateVitality() {
        return random.nextInt(1, 5);
    }

    public void setVitality(int vitality) {
        this.vitality = vitality;
    }

    public int getVitality() {
        return vitality;
    }

    public int setMoney() {
        return random.nextInt(10, 50);
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int hP) {
        HP = hP;
    }

    public Weapons getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapons weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public ArrayList<ArrayList<Item>> getInventory() {
        inventory.add(weaponsArray);
        inventory.add(armorArray);
        return inventory;
    }

    public int calculateHP() {
        return (int) Math.round(7 * getVitality() + 2 * getStrength() + 1 * getIntelligence());
    }

    public void characterPrintInfo() {
        System.out.println("Name: " + name + "\nClass: " + role + "\nMoney: " + money);
    }

    public String getRole() {
        return role;
    }
}
