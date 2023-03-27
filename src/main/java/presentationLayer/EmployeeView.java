package presentationLayer;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class EmployeeView extends JFrame implements Observer {

    Container container = getContentPane();


    private JLabel employeeL;
    private JTextArea orderTA = new JTextArea();
    private JScrollPane orderSP = new JScrollPane(orderTA);

    public EmployeeView(String username) {

        //construct components
        employeeL = new JLabel ("Employee: " + username);

        //adjust size and set layout
        setPreferredSize (new Dimension(273, 300));
        setLayoutManag();
        setLocAndSize();
        addComponent();

        employeeL.setFont(employeeL.getFont().deriveFont(15.0f));

        setTitle(username);
        pack();
        setVisible (true);
    }
    public void setLayoutManag() {
        container.setLayout(null);
    }

    public void setLocAndSize() {
        //set component bounds (only needed by Absolute Positioning)
        employeeL.setBounds (20, 20, 200, 35);
        orderSP.setBounds (20, 60, 215, 180);
    }

    public void addComponent() {
        //add components
        add (employeeL);
        add (orderSP);
    }

    public void notifyEmployee(String recipt) {
        orderTA.setText(recipt);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
