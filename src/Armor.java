import java.util.ArrayList;

public class Armor extends Item {
    int damageReduction;

    @Override
    public void idSetter(DataStructures data, String Key) {
        ArrayList<String> WID = new ArrayList<>();
        String key = "A"+Key;
        ID = data.getItemID();
        for (int i = 0; i < ID.size(); i++) {
            if (ID.get(i).contains(key)) {
                WID.add(ID.get(i));
            }
        }
        setRandomID(WID.get(random.nextInt(WID.size() - 1)));
    }

    public Armor(DataStructures data, String key) {
        super(data , key);
        idSetter(data, key);
        damageReduction = Integer.parseInt(data.getItemData().get(7).get(getName()));
    }

    
    public int getDamageReduction() {
        return damageReduction;
    }

    @Override
    public void printItemInfo() {
        super.printItemInfo();
        System.out.println("Damage reduction: " + this.damageReduction);
    }
}
