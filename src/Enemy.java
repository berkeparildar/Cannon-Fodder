import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

public class Enemy {
    private String name;
    private int healthPoint;
    private int damage;
    private Character target;
    private boolean isStunned = false;

    public Enemy() {
        this.name = nameSetter();
        this.healthPoint = 50;
        this.damage = 10;
        this.target = getTarget();
    }

    private String nameSetter() {
        SecureRandom random = new SecureRandom();
        List<String> adjectives = Arrays.asList("Small", "Crazy", "Warrior", "Enraged", "Big", "Scary");
        return adjectives.get(random.nextInt(5)) + " Goblin";
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

    public boolean getIsStunned(){
        return isStunned;
    }

    public void setStunned(boolean isStunned) {
        this.isStunned = isStunned;
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

    public void attack() throws InterruptedException {
        int y = getTarget().getHP();
        int x = (int) Math.round(getTarget().getHP() - (getDamage() - getTarget().getArmor().getDamageReduction() * 0.1));
        getTarget().setHP(x);
        int z = y - x;
        System.out.println(getName() + " attacked " + getTarget().getName() + " for " + z + " damage.");
    }
}
