package presentationLayer;

import business.users.Client;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ClientView extends JFrame {
    Container container = getContentPane();

    String[] menuItems = {};
    String[] criteriaFilter = {"Name", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price"};

    private JList menuList = new JList(menuItems);
    private JScrollPane menuScrollPane = new JScrollPane(menuList);
    ;
    private JLabel menuLabel = new JLabel("Menu");
    private JButton addButton = new JButton("Add");
    private JButton orderButton = new JButton("Order");
    private JLabel searchLabel = new JLabel("Search menu: ");
    private JTextField search = new JTextField(5);
    private JComboBox criteria = new JComboBox(criteriaFilter);
    private JLabel byLabel = new JLabel("By :");
    private JButton searchButton = new JButton("Search");
    private JList searchResult = new JList();
    private JScrollPane searchResultScroll = new JScrollPane(searchResult);

    private Client client;

    public ClientView() {
        setPreferredSize(new Dimension(595, 265));
        setLayoutManag();
        setLocAndSize();
        addComponent();

        setTitle("Client");
        pack();

        pack();
    }

    public void setLayoutManag() {
        container.setLayout(null);
    }

    public void setLocAndSize() {
        //set component bounds (only needed by Absolute Positioning)
        menuScrollPane.setBounds(40, 55, 230, 110);
        menuLabel.setBounds(40, 30, 100, 25);
        addButton.setBounds(40, 170, 100, 25);
        orderButton.setBounds(170, 170, 100, 25);
        searchLabel.setBounds(315, 30, 100, 25);
        search.setBounds(315, 55, 230, 25);
        criteria.setBounds(340, 85, 105, 25);
        byLabel.setBounds(315, 85, 25, 25);
        searchButton.setBounds(455, 85, 90, 25);
        searchResultScroll.setBounds(310, 115, 235, 80);
    }

    public void addComponent() {
        //add components
        add(menuScrollPane);
        add(menuLabel);
        add(addButton);
        add(orderButton);
        add(searchLabel);
        add(search);
        add(criteria);
        add(byLabel);
        add(searchButton);
        add(searchResultScroll);
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void initializeClient(Client client) {
        this.client = client;
        setTitle("Client #" + this.client.getID());
        setVisible(true);
    }

    public void setMenuItems(String[] menuItems) {
        menuList.setListData(menuItems);
    }

    public int getSelectedItem() {
        return menuList.getSelectedIndex();
    }

    public String getSearchCriteria() { return (String) criteria.getSelectedItem(); }

    public String getSearchConditions() { return search.getText(); }

    public void displaySearchResults(String[] searchResults) {
        searchResult.setListData(searchResults);
    }

    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }


    public void addAddListener(ActionListener a) {
        addButton.addActionListener(a);
    }

    public void addOrderListener(ActionListener a) {
        orderButton.addActionListener(a);
    }

    public void addSearchListener(ActionListener a) {
        searchButton.addActionListener(a);}
}
