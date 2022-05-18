import java.util.ArrayList;

public class Armor extends Item {
    int damageReduction;

    @Override
    public void idSetter(DataStructures data, String Key) {
        ArrayList<String> WID = new ArrayList<>();
        ID = data.getItemID();
        for (int i = 0; i < ID.size(); i++) {
            if (ID.get(i).contains("A")) {
                WID.add(ID.get(i));
            }
        }
        randomID = WID.get(random.nextInt(WID.size() - 1));
    }

    public Armor(DataStructures data) {
        super(data);
        damageReduction = Integer.parseInt(data.getItemData().get(7).get(name));
    }

    @Override
    public void printItemInfo() {
        super.printItemInfo();
        System.out.println("Damage reduction: " + this.damageReduction);
    }
}
