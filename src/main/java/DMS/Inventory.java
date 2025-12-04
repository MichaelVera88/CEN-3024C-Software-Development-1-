package DMS;
import java.util.List;
import java.util.Map;

public class Inventory
{
    // Instance Variable
    List<Accessory> accessoryList;
    List<Accessory> totalAccessories;
    public int totalMP;
    public int totalValue;

    // Initialization
    public Inventory(List<Accessory> accessoryList, List<Accessory> totalAccessories)
    {
        this.accessoryList = accessoryList;
        this.totalAccessories = totalAccessories;
    }

    // Create Function
    public void addAccessory(Accessory accessory)
    {
        accessoryList.add(accessory);
        totalMP += accessory.getMP();
        totalValue += accessory.getPrice();
    }

    // Read Functions
    public void getAccessoryList()
    {
        if (accessoryList.isEmpty())
        {
            System.out.println("No accessories collected");
        }
        else
        {
            int counter = 1;
            for (Accessory acs : accessoryList)
            {
                System.out.print(counter + ": ");
                System.out.println(acs.getName());
                counter++;
            }
        }
    }

    public void getAccessoryInfo(Accessory accessory)
    {
        System.out.println("Name: " + accessory.getName());
        System.out.println("Rarity: " + accessory.getRarity());

        System.out.println("Stats: ");
        Map<String, Integer> stat = accessory.getStats();
        stat.forEach((k, v) -> System.out.println("  " + k + ": " + v));

        System.out.println("Description: " + accessory.getDescription());
        System.out.println("Requirements: " + accessory.getRequirements());

        if (accessory.getUpgrade() == 0)
        {
            System.out.println("Upgrade: None");
        }
        else
        {
            int upgradeID = accessory.getUpgrade();
            System.out.println("Upgrade: " + totalAccessories.get(upgradeID-1).getName());
        }

        System.out.println("Source: " + accessory.getSource());
        System.out.println("Magic Power: " + accessory.getMP());
        System.out.println("Price: " + accessory.getPrice());
    }

    // Update Function
    public void updateAccessory(Accessory accessory)
    {
        if (accessory.getUpgrade() > 0)
        {
            int upgradeID = accessory.getUpgrade();
            for (Accessory acs : totalAccessories)
            {
                if (acs.getID() == upgradeID)
                {
                    addAccessory(acs);
                    removeAccessory(accessory);
                    break;
                }
            }
        }
    }

    // Delete Function
    public void removeAccessory(Accessory accessory)
    {
        accessoryList.remove(accessory);
        totalMP -= accessory.getMP();
        totalValue -= accessory.getPrice();
    }

    // Extra Functions
    public int getTotalMP()
    {
        return totalMP;
    }

    public int getTotalValue()
    {
        return totalValue;
    }
}