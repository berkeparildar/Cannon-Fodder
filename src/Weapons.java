import java.util.ArrayList;


public class Weapons extends Item {
    int damage;
    String weaponType;

    public Weapons(DataStructures data, String key) {
        super(data, key);
        idSetter(data, key);
        if (getType().equals("WEAPON")) {
            damage = Integer.parseInt(data.getItemData().get(6).get(getName()));
        }
        this.weaponType = data.getItemData().get(5).get(randomID);
    }

    @Override
    public void printItemInfo() {
        super.printItemInfo();
        System.out.println("Damage: " + damage);
        System.out.println("Weapon type: " + weaponType);
    }
    @Override
    public void idSetter(DataStructures data, String code) {
        ArrayList<String> WID = new ArrayList<>();
        String key = "W"+code;
        ID = data.getItemID();
        for (int i = 0; i < ID.size(); i++) {
            if (ID.get(i).contains(key)) {
                WID.add(ID.get(i));
            }
        }
        setRandomID(WID.get(random.nextInt(WID.size() - 1)));
    }
}
