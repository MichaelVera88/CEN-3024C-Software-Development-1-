package DMS;
import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.ArrayList;
import java.util.List;

public class GUI
{
    // Instance Variable
    Inventory playerInventory;
    DefaultListModel<Accessory> accessoryListModel;

    // Initialization
    public GUI(Inventory playerInventory)
    {
        this.playerInventory = playerInventory;

        accessoryListModel = new DefaultListModel<>();
        homeFrame();
    }

    public void homeFrame()
    {
        JFrame frame = new JFrame("Data Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setResizable(false);

        JLabel title = new JLabel("Accessory Inventory");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(15, 25, 10, 0));
        title.setHorizontalAlignment(SwingConstants.LEFT);

        JPanel contentPanel = new JPanel();

        JLabel inventoryPanelTitle = new JLabel("Inventory:");
        inventoryPanelTitle.setFont(new Font("Arial", Font.BOLD, 16));
        inventoryPanelTitle.setBorder(BorderFactory.createEmptyBorder(5, 15, 10, 0));
        inventoryPanelTitle.setHorizontalAlignment(SwingConstants.LEFT);

        JPanel inventoryPanel = new JPanel(new BorderLayout());
        inventoryPanel.setPreferredSize(new Dimension(225, 395));
        inventoryPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(225, 395));
        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        JButton accessoryInfoButton = new JButton("Accessory Info");
        accessoryInfoButton.setPreferredSize(new Dimension(100,25));
        accessoryInfoButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        accessoryInfoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        accessoryInfoButton.addActionListener(e -> getAccessoryInfo());

        JButton addAccessoryButton = new JButton("Add Accessory");
        addAccessoryButton.setPreferredSize(new Dimension(100,25));
        addAccessoryButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        addAccessoryButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addAccessoryButton.addActionListener(e -> addAccessoryButtonClick());

        JButton removeAccessoryButton = new JButton("Remove Accessory");
        removeAccessoryButton.setPreferredSize(new Dimension(100,25));
        removeAccessoryButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        removeAccessoryButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        removeAccessoryButton.addActionListener(e -> removeAccessoryButtonClick());

        JButton updateAccessoryButton = new JButton("Update Accessory");
        updateAccessoryButton.setPreferredSize(new Dimension(100,25));
        updateAccessoryButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        updateAccessoryButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        updateAccessoryButton.addActionListener(e -> updateAccessoryButtonClick());

        JButton calculateValue = new JButton("Calculate Value");
        calculateValue.setPreferredSize(new Dimension(100,25));
        calculateValue.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        calculateValue.setAlignmentX(Component.CENTER_ALIGNMENT);
        calculateValue.addActionListener(e -> calculateInventoryValueClick());

        frame.add(title, BorderLayout.NORTH);
        frame.add(contentPanel, BorderLayout.CENTER);

        contentPanel.add(inventoryPanel, BorderLayout.WEST);
        contentPanel.add(buttonPanel, BorderLayout.EAST);

        inventoryPanel.add(inventoryPanelTitle, BorderLayout.NORTH);
        inventoryPanel.add(getAccessoryList(), BorderLayout.CENTER);

        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(accessoryInfoButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(addAccessoryButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(removeAccessoryButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(updateAccessoryButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(calculateValue);

        frame.setVisible(true);
    }

    public JList<Accessory> getAccessoryList()
    {
        for (Accessory acs : playerInventory.accessoryList)
        {
            accessoryListModel.addElement(acs);
        }

        return new JList<>(accessoryListModel);
    }

    public JList<String> getTotalAccessoryList()
    {
        List<String> totalAccessoriesNames = new ArrayList<String>();

        for (Accessory acs : playerInventory.totalAccessories)
        {
            totalAccessoriesNames.add(acs.getID() + ": " + acs.getName());
        }

        return new JList<>(totalAccessoriesNames.toArray(new String[0]));
    }

    public void getAccessoryInfo()
    {
        JFrame accessoryInfoFrame = new JFrame("Accessory Info");
        accessoryInfoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        accessoryInfoFrame.setSize(350, 475);
        accessoryInfoFrame.setResizable(false);

        JLabel accessoryInfoTitle = new JLabel("Accessories:");
        accessoryInfoTitle.setFont(new Font("Arial", Font.BOLD, 16));
        accessoryInfoTitle.setBorder(BorderFactory.createEmptyBorder(5, 15, 10, 0));
        accessoryInfoTitle.setHorizontalAlignment(SwingConstants.LEFT);

        JSpinner accessoryInfoSpinner = new JSpinner(
                new SpinnerNumberModel(1, 1, 20, 1)
        );
        JPanel accessoryInfoSpinnerWrapper = new JPanel();
        accessoryInfoSpinnerWrapper.add(accessoryInfoSpinner);
        accessoryInfoSpinnerWrapper.setPreferredSize(new Dimension(100, 100));

        JButton infoButton = new JButton("Info");
        infoButton.setPreferredSize(new Dimension(75,25));
        infoButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        infoButton.addActionListener(e -> infoButtonClick(accessoryInfoFrame, accessoryInfoSpinner));

        accessoryInfoFrame.add(accessoryInfoTitle, BorderLayout.NORTH);
        accessoryInfoFrame.add(getTotalAccessoryList(), BorderLayout.WEST);
        accessoryInfoFrame.add(accessoryInfoSpinnerWrapper, BorderLayout.EAST);
        accessoryInfoFrame.add(infoButton, BorderLayout.SOUTH);

        accessoryInfoFrame.setVisible(true);
    }

    public void infoButtonClick(JFrame accessoryInfoFrame, JSpinner accessoryInfoSpinner)
    {
        int choice = (int) accessoryInfoSpinner.getValue();

        if (choice >= 1 && choice <= 20)
        {
            Accessory picked = playerInventory.totalAccessories.get(choice - 1);
            accessoryInfoFrame.dispose();

            JFrame accessoryFrame = new JFrame(picked.getName());
            accessoryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            accessoryFrame.setSize(350, 475);
            accessoryFrame.setResizable(false);

            JPanel accessoryPanel = new JPanel();
            accessoryPanel.setLayout(new BoxLayout(accessoryPanel, BoxLayout.Y_AXIS));

            JLabel name = new JLabel("Name: " + picked.getName());
            name.setFont(new Font("Arial", Font.BOLD, 12));

            JLabel rarity = new JLabel("Rarity: " + picked.getRarity());
            rarity.setFont(new Font("Arial", Font.BOLD, 12));

            JLabel stats = new JLabel("Stats: " + picked.getStats());
            stats.setFont(new Font("Arial", Font.BOLD, 12));

            JLabel description = new JLabel("Description: " + picked.getDescription());
            description.setFont(new Font("Arial", Font.BOLD, 12));

            JLabel requirement = new JLabel("Requirements: " + picked.getRequirements());
            requirement.setFont(new Font("Arial", Font.BOLD, 12));

            JLabel source = new JLabel("Source: " + picked.getSource());
            source.setFont(new Font("Arial", Font.BOLD, 12));

            JLabel mp = new JLabel("MP: " + picked.getMP());
            mp.setFont(new Font("Arial", Font.BOLD, 12));

            JLabel price = new JLabel("Price: " + picked.getPrice());
            price.setFont(new Font("Arial", Font.BOLD, 12));

            accessoryFrame.add(accessoryPanel, BorderLayout.CENTER);

            accessoryPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            accessoryPanel.add(name);
            accessoryPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            accessoryPanel.add(rarity);
            accessoryPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            accessoryPanel.add(stats);
            accessoryPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            accessoryPanel.add(description);
            accessoryPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            accessoryPanel.add(requirement);
            accessoryPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            accessoryPanel.add(source);
            accessoryPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            accessoryPanel.add(mp);
            accessoryPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            accessoryPanel.add(price);

            accessoryFrame.setVisible(true);
        }
    }

    public void addAccessoryButtonClick()
    {
        JFrame addAccessoryFrame = new JFrame("Add Accessory");
        addAccessoryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addAccessoryFrame.setSize(350, 475);
        addAccessoryFrame.setResizable(false);

        JLabel addAccessoryTitle = new JLabel("Accessories:");
        addAccessoryTitle.setFont(new Font("Arial", Font.BOLD, 16));
        addAccessoryTitle.setBorder(BorderFactory.createEmptyBorder(5, 15, 10, 0));
        addAccessoryTitle.setHorizontalAlignment(SwingConstants.LEFT);

        JSpinner addAccessorySpinner = new JSpinner(
                new SpinnerNumberModel(1, 1, 20, 1)
        );
        JPanel addAccessorySpinnerWrapper = new JPanel();
        addAccessorySpinnerWrapper.add(addAccessorySpinner);
        addAccessorySpinnerWrapper.setPreferredSize(new Dimension(100, 100));

        JButton addButton = new JButton("Add");
        addButton.setPreferredSize(new Dimension(75,25));
        addButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        addButton.addActionListener(e -> addButtonClick(addAccessoryFrame, addAccessorySpinner));

        addAccessoryFrame.add(addAccessoryTitle, BorderLayout.NORTH);
        addAccessoryFrame.add(getTotalAccessoryList(), BorderLayout.WEST);
        addAccessoryFrame.add(addAccessorySpinnerWrapper, BorderLayout.EAST);
        addAccessoryFrame.add(addButton, BorderLayout.SOUTH);

        addAccessoryFrame.setVisible(true);
    }

    public void addButtonClick(JFrame addAccessoryFrame, JSpinner addAccessorySpinner)
    {
        int choice = (int) addAccessorySpinner.getValue();

        if (choice >= 1 && choice <= 20)
        {
            Accessory picked = playerInventory.totalAccessories.get(choice - 1);
            playerInventory.addAccessory(picked);
            accessoryListModel.addElement(picked);
            addAccessoryFrame.dispose();
        }
    }

    public void removeAccessoryButtonClick()
    {
        if (!playerInventory.accessoryList.isEmpty()) {
            JFrame removeAccessoryFrame = new JFrame("Remove Accessory");
            removeAccessoryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            removeAccessoryFrame.setSize(350, 475);
            removeAccessoryFrame.setResizable(false);

            JLabel removeAccessoryTitle = new JLabel("Accessories:");
            removeAccessoryTitle.setFont(new Font("Arial", Font.BOLD, 16));
            removeAccessoryTitle.setBorder(BorderFactory.createEmptyBorder(5, 15, 10, 0));
            removeAccessoryTitle.setHorizontalAlignment(SwingConstants.LEFT);

            List<String> removeAccesoriesList = new ArrayList<String>();

            int counter = 1;
            for (Accessory acs : playerInventory.accessoryList) {
                removeAccesoriesList.add(counter + ": " + acs.getName());
                counter++;
            }

            JList<String> removeAccessoriesJList = new JList<>(removeAccesoriesList.toArray(new String[0]));

            JSpinner removeAccessorySpinner = new JSpinner(
                    new SpinnerNumberModel(1, 1, playerInventory.accessoryList.size(), 1)
            );
            JPanel removeAccessorySpinnerWrapper = new JPanel();
            removeAccessorySpinnerWrapper.add(removeAccessorySpinner);
            removeAccessorySpinnerWrapper.setPreferredSize(new Dimension(100, 100));

            JButton removeButton = new JButton("Remove");
            removeButton.setPreferredSize(new Dimension(75, 25));
            removeButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            removeButton.addActionListener(e -> removeButtonClick(removeAccessoryFrame, removeAccessorySpinner));

            removeAccessoryFrame.add(removeAccessoryTitle, BorderLayout.NORTH);
            removeAccessoryFrame.add(removeAccessoriesJList, BorderLayout.WEST);
            removeAccessoryFrame.add(removeAccessorySpinnerWrapper, BorderLayout.EAST);
            removeAccessoryFrame.add(removeButton, BorderLayout.SOUTH);

            removeAccessoryFrame.setVisible(true);
        }
    }

    public void removeButtonClick(JFrame removeAccessoryFrame, JSpinner removeAccessorySpinner)
    {
        int choice = (int) removeAccessorySpinner.getValue();

        if (choice >= 1 && choice <= playerInventory.accessoryList.size())
        {
            Accessory picked = playerInventory.accessoryList.get(choice - 1);
            playerInventory.removeAccessory(picked);
            accessoryListModel.removeElement(picked);
            removeAccessoryFrame.dispose();
        }
    }

    public void updateAccessoryButtonClick()
    {
        if (!playerInventory.accessoryList.isEmpty())
        {
            List<String> updateAccessoriesList = new ArrayList<String>();

            int counter = 1;
            for (Accessory acs : playerInventory.accessoryList) {
                if (acs.getUpgrade() > 0)
                {
                    updateAccessoriesList.add(counter + ": " + acs.getName());
                    counter++;
                }
            }

            if (!updateAccessoriesList.isEmpty())
            {
                JFrame updateAccessoryFrame = new JFrame("Update Accessory");
                updateAccessoryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                updateAccessoryFrame.setSize(350, 475);
                updateAccessoryFrame.setResizable(false);

                JLabel updateAccessoryTitle = new JLabel("Accessories:");
                updateAccessoryTitle.setFont(new Font("Arial", Font.BOLD, 16));
                updateAccessoryTitle.setBorder(BorderFactory.createEmptyBorder(5, 15, 10, 0));
                updateAccessoryTitle.setHorizontalAlignment(SwingConstants.LEFT);

                JList<String> updateAccessoriesJlist = new JList<>(updateAccessoriesList.toArray(new String[0]));

                JSpinner updateAccessorySpinner = new JSpinner(
                        new SpinnerNumberModel(1, 1, updateAccessoriesList.size(), 1)
                );
                JPanel updateAccessorySpinnerWrapper = new JPanel();
                updateAccessorySpinnerWrapper.add(updateAccessorySpinner);
                updateAccessorySpinnerWrapper.setPreferredSize(new Dimension(100, 100));

                JButton updateButton = new JButton("Update");
                updateButton.setPreferredSize(new Dimension(75, 25));
                updateButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                updateButton.addActionListener(e -> updateButtonClick(updateAccessoryFrame, updateAccessorySpinner, updateAccessoriesList));

                updateAccessoryFrame.add(updateAccessoryTitle, BorderLayout.NORTH);
                updateAccessoryFrame.add(updateAccessoriesJlist, BorderLayout.WEST);
                updateAccessoryFrame.add(updateAccessorySpinnerWrapper, BorderLayout.EAST);
                updateAccessoryFrame.add(updateButton, BorderLayout.SOUTH);

                updateAccessoryFrame.setVisible(true);
            }
        }
    }

    public void updateButtonClick(JFrame updateAccessoryFrame, JSpinner updateAccessorySpinner, List<String> updateAccessoriesList)
    {
        int choice = (int) updateAccessorySpinner.getValue();

        if (choice >= 1 && choice <= updateAccessoriesList.size())
        {
            String pickedString = updateAccessoriesList.get(choice - 1);
            String[] cutString = pickedString.split(":");
            String accessoryName = cutString[1].trim();
            for (Accessory acs : playerInventory.accessoryList)
            {
                if (acs.getName().equals(accessoryName))
                {
                    Accessory upgrade = playerInventory.totalAccessories.get(acs.getUpgrade());
                    playerInventory.updateAccessory(acs);
                    accessoryListModel.addElement(upgrade);
                    accessoryListModel.removeElement(acs);
                }
            }
            updateAccessoryFrame.dispose();
        }
    }

    public void calculateInventoryValueClick()
    {
        JFrame inventoryValueFrame = new JFrame("Inventory Value");
        inventoryValueFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        inventoryValueFrame.setSize(450, 200);
        inventoryValueFrame.setResizable(false);

        JLabel mpLabel = new JLabel("Magic Power: " + playerInventory.getTotalMP());
        mpLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mpLabel.setBorder(BorderFactory.createEmptyBorder(25, 15, 10, 0));
        mpLabel.setHorizontalAlignment(SwingConstants.LEFT);

        JLabel coinLabel = new JLabel ("Coins: " + playerInventory.getTotalValue());
        coinLabel.setFont(new Font("Arial", Font.BOLD, 18));
        coinLabel.setBorder(BorderFactory.createEmptyBorder(25, 0, 10, 15));
        coinLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        inventoryValueFrame.add(mpLabel, BorderLayout.WEST);
        inventoryValueFrame.add(coinLabel, BorderLayout.EAST);

        inventoryValueFrame.setVisible(true);
    }
}
