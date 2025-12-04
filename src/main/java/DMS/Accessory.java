package DMS;
import java.util.HashMap;
import java.util.Map;

public class Accessory
{
    // Instance Variables
    public int ID;
    public String name;
    public String rarity;
    public String[] statNames;
    public int[] statValues;
    public Map<String, Integer> stats;
    public String description;
    public String requirements;
    public int upgrade;
    public String source;
    public int mp;
    public int price;

    // Initialization
    public Accessory(
            int ID,
            String name,
            String rarity,
            String[] statNames,
            int[] statValues,
            String description,
            String requirements,
            int upgrade,
            String source,
            int mp,
            int price)
    {
        this.ID = ID;
        this.name = name;
        this.rarity = rarity;
        this.statNames = statNames;
        this.statValues = statValues;
        this.description = description;
        this.requirements = requirements;
        this.upgrade = upgrade;
        this.source = source;
        this.mp = mp;
        this.price = price;

        setStats();
    }

    // Set Accessory Stats
    private void setStats()
    {
        if (statNames.length != statValues.length)
        {
            throw new ArrayIndexOutOfBoundsException("statNames.length and statValues.length are not equal.");
        }
        else
        {
            Map<String, Integer> accessoryStats = new HashMap<>();
            for (int i = 0; i < statNames.length; i++)
            {
                accessoryStats.put(statNames[i], statValues[i]);
            }
            stats = accessoryStats;
        }
    }

    // Accessory Getters
    public int getID() {return ID;}
    public String getName() {return name;}
    public String getRarity() {return rarity;}
    public Map<String, Integer> getStats() {return stats;}
    public String getDescription() {return description;}
    public String getRequirements() {return requirements;}
    public int getUpgrade() {return upgrade;}
    public String getSource() {return source;}
    public int getMP() {return mp;}
    public int getPrice() {return price;}
}