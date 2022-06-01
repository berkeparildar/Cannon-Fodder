public class Ability {
    private String type;
    private int cooldown;
    private String name;
    private Enemy target;
    private Character ally;
    private Character caster;

    public Ability(Character caster) {
        this.caster = caster;
        setAbility();
        this.type = getType();
        this.cooldown = getCooldown();
        this.name = getName();
        this.target = getTarget();
        this.ally = getAlly();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Character getAlly() {
        return ally;
    }

    public void setCaster(Character caster) {
        this.caster = caster;
    }

    public Character getCaster() {
        return caster;
    }

    public Enemy getTarget() {
        return target;
    }

    public void setTarget(Enemy target) {
        this.target = target;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public void setAlly(Character ally) {
        this.ally = ally;
    }

    public void setAbility() {
        if (getCaster().getRole().equals("Knight")) {
            setName("Rightous Shield");
            setType("Stun");
            setCooldown(2);
        }
        else if (getCaster().getRole().equals("Healer")) {
            setName("Touch of Angel");
            setType("Heal");
            setCooldown(2);
        }
        else if (getCaster().getRole().equals("Warrior")) {
            setName("Bladestorm");
            setType("Damage");
            setCooldown(1);
        }
    }

    public void cast() {
        if (getCaster().getRole().equals("Knight")) {
        System.out.println("Casting " + getName() + " on " + getTarget().getName());
        getTarget().setStunned(true);
        }
        else if (getCaster().getRole().equals("Healer")) {
            System.out.println("Casting " + getName() + " on " + getTarget().getName());
            getAlly().setHP(getAlly().getMaxHP());
        }
        else if (getCaster().getRole().equals("Warrior")) {
            System.out.println("Casting " + getName() + " on " + getTarget().getName());
            getTarget().setHealthPoint(getTarget().getHealthPoint()-30);
        }
    }
}
