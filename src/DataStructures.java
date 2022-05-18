import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DataStructures {
    private static BufferedReader fileBuffer1;
    private static BufferedReader fileBuffer2;
    static ArrayList<String> itemID = new ArrayList<>();
    static ArrayList<String> characterName = new ArrayList<>();
    static List<Map<String, String>> itemData = new LinkedList<>();
    static List<Map<String, String>> characterData = new LinkedList<>();
    Map<String, String> itemName = new HashMap<>();
    Map<String, String> itemValue = new HashMap<>();
    Map<String, String> itemWeight = new HashMap<>();
    Map<String, String> itemRarity = new HashMap<>();
    Map<String, String> itemLevel = new HashMap<>();
    Map<String, String> itemType = new HashMap<>();
    Map<String, String> weaponDamage = new HashMap<>();
    Map<String, String> armorReductionDamage = new HashMap<>();
    Map<String, String> characterClass = new HashMap<>();

    public ArrayList<String> getItemID() {
        return itemID;
    }
   
    public ArrayList<String> getCharacterName() {
        return characterName;
    }

    public List<Map<String, String>> getItemData() {
        itemData.add(itemName); // 1e 0
        itemData.add(itemValue);// 2e 1
        itemData.add(itemWeight);// 3e 2
        itemData.add(itemRarity);// 4e 3
        itemData.add(itemLevel);// 5e 4
        itemData.add(itemType);// 7e 5
        itemData.add(weaponDamage);// 8e 6
        itemData.add(armorReductionDamage);// 9e 7
        return itemData;
    }

    public List<Map<String, String>> getCharacterData() {
        characterData.add(characterClass);
        return characterData;
    }

    public void readCSV() {
        try {
            getItemData();
            fileBuffer1 = new BufferedReader(new FileReader("data\\Items.csv"));
            fileBuffer1.readLine();
            String line = "";
            while ((line = fileBuffer1.readLine()) != null) {
                String[] contents = line.split(",");
                Boolean a = contents[1].equals("WEAPON");
                itemID.add(contents[6]);
                itemData.get(0).put(contents[6], contents[0]);
                itemData.get(1).put(contents[6], contents[2]);
                itemData.get(2).put(contents[6], contents[3]);
                itemData.get(3).put(contents[6], contents[4]);
                itemData.get(4).put(contents[6], contents[5]);
                itemData.get(5).put(contents[6], contents[7]);
                if (a) {
                    itemData.get(6).put(contents[0], contents[8]);
                } else {
                    itemData.get(7).put(contents[0], contents[9]);
                }
            }
            getCharacterData();
            fileBuffer2 = new BufferedReader(new FileReader("data\\Characters.csv"));
            fileBuffer2.readLine();
            String line2 = "";
            while((line2 = fileBuffer2.readLine())!=null){
                String contents[] = line2.split(",");
                characterName.add(contents[0]);
                characterData.get(0).put(contents[0], contents[1]);
            }
        } catch (IOException e) {
            System.err.println("Couldn't find the input, try again.\nFor exapmle: 02-February.csv");
            System.exit(0);
        }
    }
}