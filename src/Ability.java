public class Ability {
    private String type;
    private int cooldown;
    private String name;
    private Enemy target;
    private Character ally;
    private Character caster;

    public Ability() {
        this.type = getType();
        this.cooldown = getCooldown();
        this.name = getName();
        this.target = getTarget();
        this.ally = getAlly();
        this.caster = getCaster();
    }

    public String getName() {
        return name;
    }

    public Character getAlly() {
        return ally;
    }

    public Character getCaster() {
        return caster;
    }

    public Enemy getTarget() {
        return target;
    }

    public String getType() {
        return type;
    }

    public int getCooldown() {
        return cooldown;
    }
}
