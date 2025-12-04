package DMS;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

// Test the Main CRUD functions of the program.
public class InventoryTest {

    // Test Create Function
    @Test
    public void addAccessory() {
        List<Accessory> accessoryList = new ArrayList<>();
        List<Accessory> totalAccessories = new ArrayList<>();

        Inventory inv = new Inventory(accessoryList, totalAccessories);

        String[] sn = {"Strength"};
        int[] sv = {5};
        Accessory acs = new Accessory(4,"Magic Talisman","UNCOMMON",sn,sv,"n/a","n/a",0,"shop",5,10);

        inv.addAccessory(acs);

        assertSame(acs, inv.accessoryList.getFirst());
    }

    // Test Read Function
    @Test
    public void getAccessoryList() {
        List<Accessory> accessoryList = new ArrayList<>();
        List<Accessory> totalAccessories = new ArrayList<>();

        Inventory inv = new Inventory(accessoryList, totalAccessories);

        String[] sn = {"Strength"};
        int[] sv = {5};
        Accessory acs = new Accessory(4,"Magic Talisman","UNCOMMON",sn,sv,"n/a","n/a",0,"shop",5,10);

        inv.addAccessory(acs);

        assertEquals(1, inv.accessoryList.size());
    }

    // Test Update Function
    @Test
    public void updateAccessory() {
        List<Accessory> accessoryList = new ArrayList<>();
        List<Accessory> totalAccessories = new ArrayList<>();

        Inventory inv = new Inventory(accessoryList, totalAccessories);

        String[] sn1 = {"Strength"};
        int[] sv1 = {5};
        String[] sn2 = {"Strength"};
        int[] sv2 = {10};
        Accessory acs = new Accessory(4,"Magic Talisman","UNCOMMON",sn1,sv1,"n/a","n/a",5,"shop",5,10);
        Accessory uacs = new Accessory(5,"Super Magic Talisman","RARE",sn2,sv2,"n/a","n/a",0,"shop",8,50);

        inv.addAccessory(acs);
        inv.totalAccessories.add(uacs);

        inv.updateAccessory(acs);

        assertEquals(1, inv.accessoryList.size());
        assertSame(uacs, inv.accessoryList.getFirst());
    }

    // Test Delete Function
    @Test
    public void removeAccessory() {
        List<Accessory> accessoryList = new ArrayList<>();
        List<Accessory> totalAccessories = new ArrayList<>();

        Inventory inv = new Inventory(accessoryList, totalAccessories);

        String[] sn1 = {"Strength"};
        int[] sv1 = {5};
        String[] sn2 = {"Strength"};
        int[] sv2 = {10};
        Accessory acs = new Accessory(4,"Magic Talisman","UNCOMMON",sn1,sv1,"n/a","n/a",5,"shop",5,10);
        Accessory uacs = new Accessory(5,"Super Magic Talisman","RARE",sn2,sv2,"n/a","n/a",0,"shop",8,50);

        inv.addAccessory(acs);
        inv.addAccessory(uacs);

        inv.removeAccessory(acs);

        assertEquals(1, inv.accessoryList.size());
        assertSame(uacs, inv.accessoryList.getFirst());
    }

    // Test Custom Function
    @Test
    public void getTotalMP() {
        List<Accessory> accessoryList = new ArrayList<>();
        List<Accessory> totalAccessories = new ArrayList<>();

        Inventory inv = new Inventory(accessoryList, totalAccessories);

        String[] sn1 = {"Strength"};
        int[] sv1 = {5};
        String[] sn2 = {"Strength"};
        int[] sv2 = {10};
        Accessory acs = new Accessory(4,"Magic Talisman","UNCOMMON",sn1,sv1,"n/a","n/a",5,"shop",5,10);
        Accessory uacs = new Accessory(5,"Super Magic Talisman","RARE",sn2,sv2,"n/a","n/a",0,"shop",8,50);

        inv.addAccessory(acs);
        inv.addAccessory(uacs);

        assertEquals(13, inv.totalMP);
    }

    // Test Custom Function
    @Test
    public void getTotalValue() {
        List<Accessory> accessoryList = new ArrayList<>();
        List<Accessory> totalAccessories = new ArrayList<>();

        Inventory inv = new Inventory(accessoryList, totalAccessories);

        String[] sn1 = {"Strength"};
        int[] sv1 = {5};
        String[] sn2 = {"Strength"};
        int[] sv2 = {10};
        Accessory acs = new Accessory(4,"Magic Talisman","UNCOMMON",sn1,sv1,"n/a","n/a",5,"shop",5,10);
        Accessory uacs = new Accessory(5,"Super Magic Talisman","RARE",sn2,sv2,"n/a","n/a",0,"shop",8,50);

        inv.addAccessory(acs);
        inv.addAccessory(uacs);

        assertEquals(60, inv.totalValue);
    }
}