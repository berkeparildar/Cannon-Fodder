import java.util.ArrayList;

public class Potion extends Item {
    private int amount;
    public Potion(DataStructures data, String key){
        super(data, key);
        idSetter(data, "");
        if (getType().equals("POTION")) {
            amount = Integer.parseInt(data.getItemData().get(8).get(getName()));
        }
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void consumePotion(Character c, int round){
        if(getName().contains("Health")){
            int current = c.getHP();
            c.setHP(current + amount);
        }
        else if(getName().contains("Intelligence")){
            int current = c.getIntelligence();
            if(getName().contains("Enhanced")){
                 c.setIntelligence(current + amount);
            }
        }
        else if(getName().contains("Strength")){
            int current = c.getStrength();
            c.setStrength(current + amount);
        }
        else if(getName().contains("Vitality")){
            int current = c.getVitality();
            c.setVitality(current + amount);
        }
    }


    @Override
    public void idSetter(DataStructures data, String code) {
        ArrayList<String> PID = new ArrayList<>();
        String key = "P";
        ID = data.getItemID();
        for (int i = 0; i < ID.size(); i++) {
            if (ID.get(i).contains(key)) {
                PID.add(ID.get(i));
            }
        }
        setRandomID(PID.get(random.nextInt(PID.size() - 1)));
    }
}
