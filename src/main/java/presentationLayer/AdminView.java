package presentationLayer;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AdminView extends JFrame{

    Container container = getContentPane();

    String[] menuLItems = {};

    private JLabel baseLabel = new JLabel ("Base Product");
    private JLabel nameLabel = new JLabel ("Name:");
    private JTextField nameField = new JTextField (5);
    private JLabel caloriesLabel = new JLabel ("Calories:");
    private JTextField caloriesField = new JTextField (5);
    private JLabel proteinLabel = new JLabel ("Protein:");
    private JTextField proteinField = new JTextField (5) ;
    private JLabel fatsLabel = new JLabel ("Fat:");
    private JTextField fatsField = new JTextField (5);
    private JLabel sodiumLabel = new JLabel ("Sodium:");
    private JTextField sodiumField = new JTextField (5);
    private JLabel priceLabel = new JLabel ("Price:");
    private JTextField priceField = new JTextField (5);;
    private JButton addBaseProduct = new JButton ("Add Base Product");
    private JList menuL = new JList (menuLItems);
    private JScrollPane menuScrollPane = new JScrollPane (menuL);
    private JButton selectButton = new JButton ("Select");
    private JButton addCompositeProduct = new JButton ("Add Composite Product");
    private JLabel compositeProductNameLabel = new JLabel ("Name:");
    private JTextField compositeNameField = new JTextField (5);
    private JLabel compositeProductLabel = new JLabel ("Composite Product");
    private JButton deleteButton = new JButton ("Delete");
    private JButton report1Button = new JButton ("Report #1");
    private JButton report2Button = new JButton ("Report #2");
    private JButton report3Button = new JButton ("Report #3");
    private JButton report4Button = new JButton ("Report #4");
    private JButton importMenuButton = new JButton ("Import");


    public AdminView() {

        //adjust size and set layout
        setPreferredSize (new Dimension (625, 315));
        setLayout (null);

        setLayoutManag();
        setLocAndSize();
        addComponent();

        baseLabel.setFont(baseLabel.getFont().deriveFont(15.0f));
        compositeProductLabel.setFont(baseLabel.getFont());


        setTitle("Admin");
        pack();
    }

    public void setLayoutManag() {
        container.setLayout(null);
    }

    public void setLocAndSize() {
        //set component bounds (only needed by Absolute Positioning)
        //set component bounds (only needed by Absolute Positioning)
        baseLabel.setBounds (35, 30, 100, 25);
        nameLabel.setBounds (35, 60, 100, 25);
        nameField.setBounds (75, 60, 100, 25);
        caloriesLabel.setBounds (35, 85, 100, 25);
        caloriesField.setBounds (90, 85, 85, 25);
        proteinLabel.setBounds (35, 110, 100, 25);
        proteinField.setBounds (85, 110, 90, 25);
        fatsLabel.setBounds (35, 135, 100, 25);
        fatsField.setBounds (60, 135, 115, 25);
        sodiumLabel.setBounds (35, 160, 100, 25);
        sodiumField.setBounds (85, 160, 90, 25);
        priceLabel.setBounds (35, 185, 100, 25);
        priceField.setBounds (70, 185, 105, 25);
        addBaseProduct.setBounds (30, 220, 145, 30);
        menuScrollPane.setBounds (205, 85, 250, 95);
        selectButton.setBounds (240, 220, 80, 30);
        addCompositeProduct.setBounds (240, 185, 170, 30);
        compositeProductNameLabel.setBounds (205, 60, 100, 25);
        compositeNameField.setBounds (245, 60, 210, 25);
        compositeProductLabel.setBounds (205, 30, 140, 25);
        deleteButton.setBounds (330, 220, 80, 30);
        report1Button.setBounds (485, 60, 90, 30);
        report2Button.setBounds (485, 100, 90, 30);
        report3Button.setBounds (485, 140, 90, 30);
        report4Button.setBounds (485, 180, 90, 30);
        importMenuButton.setBounds (485, 220, 90, 30);

    }

    public void addComponent() {
        //add components
        add (baseLabel);
        add (nameLabel);
        add (nameField);
        add (caloriesLabel);
        add (caloriesField);
        add (proteinLabel);
        add (proteinField);
        add (fatsLabel);
        add (fatsField);
        add (sodiumLabel);
        add (sodiumField);
        add (priceLabel);
        add (priceField);
        add (addBaseProduct);
        add (menuScrollPane);
        add (selectButton);
        add (addCompositeProduct);
        add (compositeProductNameLabel);
        add (compositeNameField);
        add (compositeProductLabel);
        add (deleteButton);
        add (report1Button);
        add (report2Button);
        add (report3Button);
        add (report4Button);
        add (importMenuButton);

    }


    public void setMenuItems(String[] menuItems) {
        menuL.setListData(menuItems);
    }

    public String[] getProductInfo() {
        String[] productInfo = new String[6];
        productInfo[0] = nameField.getText();
        productInfo[1] = caloriesField.getText();
        productInfo[2] = proteinField.getText();
        productInfo[3] = fatsField.getText();
        productInfo[4] = sodiumField.getText();
        productInfo[5] = priceField.getText();
        return productInfo;
    }

    public int getSelectedIndex() {
        return menuL.getSelectedIndex();
    }

    public String getCompositeTitle() {
        return compositeNameField.getText();
    }
    public void addImportListener(ActionListener a) {
        importMenuButton.addActionListener(a);
    }
    public void addAddBaseListener(ActionListener a) {
        addBaseProduct.addActionListener(a);
    }
    public void addAddCompositeListener(ActionListener a) {
        addCompositeProduct.addActionListener(a);
    }
    public void addSelectListener(ActionListener a) {
        selectButton.addActionListener(a);
    }
    public void addDeleteListener(ActionListener a) {
        deleteButton.addActionListener(a);
    }
    public void addReport1Listener(ActionListener a) {
        report1Button.addActionListener(a);
    }
    public void addReport2Listener(ActionListener a) {
        report2Button.addActionListener(a);
    }
    public void addReport3Listener(ActionListener a) {
        report3Button.addActionListener(a);
    }
    public void addReport4Listener(ActionListener a) {
        report4Button.addActionListener(a);
    }

    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
