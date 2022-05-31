import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

public class Enemy {
    private String name;
    private int healthPoint;
    private int damage;
    private Character target;

    public Enemy() {
        this.name = nameSetter();
        this.healthPoint = 50;
        this.damage = 10;
        this.target = getTarget();
    }

    private String nameSetter() {
        SecureRandom random = new SecureRandom();
        List<String> adjectives = Arrays.asList("Small", "Crazy", "Warrior", "Enraged", "Big", "Scary");
        return adjectives.get(random.nextInt(0, 5)) + " Goblin";
    }

    public void setTarget(Character target) {
        this.target = target;
    }

    public void setHealthPoint(int healthPoint) {
        this.healthPoint = healthPoint;
    }

    public Character getTarget() {
        return target;
    }

    public String getName() {
        return name;
    }

    public int getHealthPoint() {
        return healthPoint;
    }

    public int getDamage() {
        return damage;
    }

    public void attack() {
        getTarget().setHP(getTarget().getHP() - getDamage());
        System.out.println(getName() + " attacked " + getTarget().getName() + " for " + getDamage() + " damage.");
    }
}
