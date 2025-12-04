package DMS;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Handler
{
    public static void main(String[] args)
    {
        String dbPath = "jdbc:sqlite::resource:accessories.db";

        try (Connection conn = DriverManager.getConnection(dbPath))
        {

            String sql = "SELECT * FROM Accessories";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            List<Accessory> accessoryList = new ArrayList<>();
            List<Accessory> accessories = loadData(rs);

            Inventory playerInventory = new Inventory(accessoryList, accessories);
            GUI gui = new GUI(playerInventory);
        } catch (SQLException e)
        {
            System.err.println("Error reading database: " + e.getMessage());
        }
    }

    public static List<Accessory> loadData(ResultSet rs) throws SQLException {
        List<Accessory> accessories = new ArrayList<>();

        while (rs.next()) {

            int ID = rs.getInt("ID");
            String name = rs.getString("Name");

            String stringStatNames = rs.getString("StringArray");
            String stringStatVals = rs.getString("IntegerArray");

            String rarity = rs.getString("Rarity");
            String description = rs.getString("Description");
            String requirements = rs.getString("Requirements");

            int upgrade = rs.getInt("Upgrade");
            String source = rs.getString("Sources");

            int mp = rs.getInt("MagicPower");
            int price = rs.getInt("Price");

            String[] statNames = stringStatNames.split(",");
            String[] arrayStatVals = stringStatVals.split(",");

            int[] statVals = new int[arrayStatVals.length];
            for (int i = 0; i < arrayStatVals.length; i++)
            {
                statVals[i] = Integer.parseInt(arrayStatVals[i].trim());
            }

            accessories.add(new Accessory(ID, name, rarity, statNames, statVals, description, requirements, upgrade, source, mp, price));
            }
        return accessories;
    }
}