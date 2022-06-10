import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

public class Character {
    Random random = new SecureRandom();
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
    private int maxHP;
    private Enemy target;
    private Ability ability;
    ArrayList<String> characterNameArray = new ArrayList<>();
    private ArrayList<Item> inventory = new ArrayList<>();

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
        this.ability = new Ability(this);
    }
    

    public void attack() throws InterruptedException{}

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
        return random.nextInt(5 - 0);
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int calculateVitality() {
        return random.nextInt(5 - 1) + 1;
    }

    public void setVitality(int vitality) {
        this.vitality = vitality;
    }

    public int getVitality() {
        return vitality;
    }

    public int setMoney() {
        return random.nextInt(50 - 10) + 10;
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

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public int calculateHP() {
        int a = (int) Math.round(7 * getVitality() + 2 * getStrength() + 1 * getIntelligence());
        this.maxHP = a;
        return a;
    }

    public Ability getAbility() {
        ability.setTarget(getTarget());
        return ability;
    }

    public int getMaxHP(){
        return maxHP;
    }

    public void characterPrintInfo() {
        System.out.println("=========================================================");
        System.out.println("Name: " + name + "\t\tClass: " + role + "\t\tMoney: " + money);
    }

    public String getRole() {
        return role;
    }
    public void consumePotion(){
        if(getName().contains("Health")){
            
        }
    }
}
