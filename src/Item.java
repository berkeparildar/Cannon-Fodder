import java.security.SecureRandom;
import java.util.ArrayList;

public class Item {
    SecureRandom random = new SecureRandom();
    private String name;
    private String type;
    private int value;
    private int weight;
    private String rarity;
    private int level;
    String randomID;
    ArrayList<String> ID = new ArrayList<>();

    public void idSetter(DataStructures data, String key) {
        ID = data.getItemID();
        randomID = ID.get(random.nextInt(ID.size() - 1));
    }

    public Item(DataStructures data, String key) {
        idSetter(data,key);
        if (randomID.contains("W")) {
            this.type = "WEAPON";
        } else {
            this.type = "ARMOR";
        }
        this.name = data.getItemData().get(0).get(randomID);
        this.value = Integer.parseInt(data.getItemData().get(1).get(randomID));
        this.weight = Integer.parseInt(data.getItemData().get(2).get(randomID));
        this.rarity = data.getItemData().get(3).get(randomID);
        this.level = Integer.parseInt(data.getItemData().get(4).get(randomID));
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public int getWeight() {
        return weight;
    }
 
    public String getRarity() {
        return rarity;
    }

    public void printItemInfo() {
        System.out.println("----------------------------");
        System.out.println("Name: " + this.name + "\nType: " + this.type +
                "\nValue: " + this.value
                + "\nWeight: " + this.weight + "\nRarity: " + this.rarity + "\nLevel: " +
                this.level);
    }

   public void setRandomID(String randomID) {
       this.randomID = randomID;
   }
}