package DMS;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class UI
{
    // Instance Variable
    Inventory playerInventory;

    // Initialization
    public UI(Inventory playerInventory)
    {
        this.playerInventory = playerInventory;

        mainMenu();
    }

    public void mainMenu()
    {
        boolean running = true;
        Scanner input = new Scanner(System.in);

        while (running)
        {
            System.out.println("----------- Main Menu -----------");
            System.out.println("1: Show Current Inventory");
            System.out.println("2: Add Accessory");
            System.out.println("3: Upgrade Accessory");
            System.out.println("4: Delete Accessory");
            System.out.println("5: Show Total Value");
            System.out.println("6: Exit");
            System.out.print(">>> ");

            int choice = input.nextInt();
            switch (choice)
            {
                case 1:
                    accessoryListMenu();
                    break;
                case 2:
                    addAccessoryMenu();
                    break;
                case 3:
                    upgradeAccessoryMenu();
                    break;
                case 4:
                    deleteAccessoryMenu();
                    break;
                case 5:
                    totalValueMenu();
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice");
            }

            System.out.println();
        }
    }

    public void accessoryListMenu()
    {
        Scanner input = new Scanner(System.in);

        while (true)
        {
            System.out.println("---------- Accessory List Menu -----------");
            playerInventory.getAccessoryList();
            System.out.println();
            System.out.println("1: Show Accessory Info");
            System.out.println("2: Back");
            System.out.print(">>> ");

            int choice = input.nextInt();
            switch (choice)
            {
                case 1:
                    accessoryInfoMenu();
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Invalid choice");
            }
            System.out.println();
        }
    }

    public void accessoryInfoMenu()
    {
        Scanner input = new Scanner(System.in);

        while (true)
        {
            if (playerInventory.accessoryList.isEmpty())
            {
                System.out.println("No Viewable Accessories.");
                break;
            }
            else
            {
                int back = playerInventory.accessoryList.size() + 1;
                System.out.println("---------- Choose Accessory -----------");
                playerInventory.getAccessoryList();
                System.out.println(back + ": Back");
                System.out.println();
                System.out.print(">>> ");

                int choice = input.nextInt();
                if (choice == back)
                {
                    return;
                }
                else if (choice > playerInventory.accessoryList.size() || choice < 0)
                {
                    System.out.println("Invalid Accessory");
                }
                else
                {
                    playerInventory.getAccessoryInfo(playerInventory.accessoryList.get(choice-1));
                }
            }
        }
    }

    public void addAccessoryMenu()
    {
        Scanner input = new Scanner(System.in);

        while (true)
        {
            System.out.println("---------- Add Accessory -----------");
            for (Accessory acs : playerInventory.totalAccessories)
            {
                System.out.print(acs.getID() + ": ");
                System.out.println(acs.getName());
            }
            System.out.println("21: Back");
            System.out.println();
            System.out.print(">>> ");

            int choice = input.nextInt();
            if (choice == 21)
            {
                return;
            }
            else if (choice < 1 || choice > 20)
            {
                System.out.println("Invalid Accessory");
            }
            else
            {
                playerInventory.addAccessory(playerInventory.totalAccessories.get(choice-1));
                System.out.println(playerInventory.totalAccessories.get(choice-1).getName() + " added!");
                return;
            }
        }
    }

    public void upgradeAccessoryMenu()
    {
        Scanner input = new Scanner(System.in);
        List<Accessory> upgradableAccessories = new ArrayList<>();
        List<Accessory> upgrades = new ArrayList<>();

        while (true)
        {
            for (Accessory acs : playerInventory.accessoryList)
            {
                int upgradeID = acs.getUpgrade();
                if (upgradeID > 0)
                {
                    upgradableAccessories.add(acs);
                    upgrades.add(playerInventory.totalAccessories.get(upgradeID-1));
                }
            }
            int back = upgradableAccessories.size() + 1;
            System.out.println("---------- Choose Upgradable Accessory -----------");
            for (int i = 0; i < upgradableAccessories.size(); i++)
            {
                System.out.println((i + 1) + ": " +  upgradableAccessories.get(i).getName() + " ---> " + upgrades.get(i).getName());
            }
            System.out.println(back + ": Back");
            System.out.println();
            System.out.print(">>> ");

            int choice = input.nextInt();
            if (choice == back)
            {
                return;
            }
            else if (choice > upgradableAccessories.size() || choice < 1)
            {
                System.out.println("Invalid Accessory");
            }
            else
            {
                playerInventory.updateAccessory(upgradableAccessories.get(choice-1));
                System.out.println(upgradableAccessories.get(choice-1).getName() + " updated to " + upgrades.get(choice-1).getName() + "!");
                return;
            }
        }
    }

    public void deleteAccessoryMenu()
    {
        Scanner input = new Scanner(System.in);

        while (true)
        {
            int back = playerInventory.accessoryList.size() + 1;
            System.out.println("---------- Delete Accessory -----------");
            playerInventory.getAccessoryList();
            System.out.println(back + ": Back");
            System.out.println();
            System.out.print(">>> ");

            int choice = input.nextInt();
            if (choice == back)
            {
                return;
            }
            else if (choice > playerInventory.accessoryList.size() || choice < 0)
            {
                System.out.println("Invalid Accessory");
            }
            else
            {
                System.out.println(playerInventory.accessoryList.get(choice-1).getName() + " removed!");
                playerInventory.removeAccessory(playerInventory.accessoryList.get(choice-1));
                return;
            }
        }
    }

    public void totalValueMenu()
    {
        System.out.println("---------- Inventory Value -----------");
        System.out.println("Magic Power: " + playerInventory.getTotalMP());
        System.out.println("Total Value: " + playerInventory.getTotalValue());
    }
}