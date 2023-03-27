package controller;

import business.DeliveryService;
import business.Filter;
import business.product.BaseProduct;
import business.product.MenuItem;
import business.reports.Report1;
import business.reports.Report2;
import business.reports.Report3;
import business.reports.Report4;
import business.users.Client;
import business.users.Roles;
import business.users.User;
import dataLayer.Serializator;
import presentationLayer.AdminView;
import presentationLayer.ClientView;
import presentationLayer.EmployeeView;
import presentationLayer.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class Controller {
    private DeliveryService deliveryService;

    private LoginView loginView;
    private AdminView adminView;
    private ClientView clientView;
    private ArrayList<EmployeeView> employeeViews;

    public Controller() {
        deliveryService = new DeliveryService();
        loginView = new LoginView();
        adminView = new AdminView();
        clientView = new ClientView();
        employeeViews = new ArrayList<EmployeeView>();

        loginView.addLoginListener(new LoginListener());
        loginView.addRegisterListener(new RegisterListener());

        adminView.addImportListener(new ImportListener());
        adminView.addAddBaseListener(new AddBaseListener());
        adminView.addSelectListener(new SelectListener());
        adminView.addAddCompositeListener(new AddCompositeListener());
        adminView.addDeleteListener(new DeleteListener());
        adminView.addReport1Listener(new Report1Listener());
        adminView.addReport2Listener(new Report2Listener());
        adminView.addReport3Listener(new Report3Listener());
        adminView.addReport4Listener(new Report4Listener());

        clientView.addAddListener(new AddListener());
        clientView.addOrderListener(new OrderListener());
        clientView.addSearchListener(new SearchListener());
        clientView.setMenuItems(deliveryService.getMenuItemsString(deliveryService.getMenuItemsList()));

        deliveryService = Serializator.deserialize();
    }

    //login view
    class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String username = loginView.getUsername();
            char[] password = loginView.getPassword();
            for (User u : deliveryService.getUserList()) {
                if (u.getUsername().equals(username) && Arrays.equals(u.getPassword(), password)) {
                    if (u.getRole().equals(Roles.ADMIN)) {
                        adminView.setVisible(true);
                    } else if (u.getRole().equals(Roles.EMPLOYEE)) {
                        employeeViews.add(new EmployeeView(u.getUsername()));
                    } else if (u.getRole().equals(Roles.CLIENT)) {
                        clientView.initializeClient((Client) u);
                    }
                }
            }
            // deliveryService = Serializator.deserialize();
        }
    }

    class RegisterListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int clientID = deliveryService.getUserList().size();
            String username = loginView.getUsername();
            char[] password = loginView.getPassword();
            deliveryService.addUser(new Client(clientID, username, password));
            loginView.displayMessage("Succesfully registered");
            Serializator.serialize(deliveryService);
        }
    }

    //admin view
    class ImportListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            deliveryService.importMenuItems("products.csv");
            updateViews();
            Serializator.serialize(deliveryService);
            // deliveryService = Serializator.deserialize();
        }
    }

    class AddBaseListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String[] productInfo = adminView.getProductInfo();
            String title = productInfo[0];
            int calories = Integer.parseInt(productInfo[1]);
            int protein = Integer.parseInt(productInfo[2]);
            int fat = Integer.parseInt(productInfo[3]);
            int sodium = Integer.parseInt(productInfo[4]);
            int price = Integer.parseInt(productInfo[5]);
            deliveryService.addMenuItem(new BaseProduct(title, 0, calories, protein, fat, sodium, price));
            updateViews();
            // deliveryService = Serializator.deserialize();
            Serializator.serialize(deliveryService);
        }
    }
    class SelectListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            BaseProduct selectedItem = (BaseProduct) deliveryService.getMenuItemsList().get(adminView.getSelectedIndex());
            deliveryService.addSelectedItem(selectedItem);
            // deliveryService = Serializator.deserialize();
        }
    }
    class AddCompositeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (!deliveryService.getSelectedItemsList().isEmpty()) {
                deliveryService.addCompositeItem(adminView.getCompositeTitle());
                // deliveryService = Serializator.deserialize();
            }
            updateViews();
            Serializator.serialize(deliveryService);
        }
    }
    class DeleteListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int index = adminView.getSelectedIndex();
            deliveryService.getMenuItemsList().remove(index);
            updateViews();
            //  deliveryService = Serializator.deserialize();
            Serializator.serialize(deliveryService);
        }
    }

    //client view
    class AddListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            MenuItem selectedItem = deliveryService.getMenuItemsList().get(clientView.getSelectedItem());
            deliveryService.getMenuItemsList().get(clientView.getSelectedItem()).incrementTimesOrdered();
            deliveryService.getClientOrder().add(selectedItem);
            System.out.println(deliveryService.getClientOrder());
            deliveryService.getMenuItemsList().get(clientView.getSelectedItem()).setTimesOrdered((deliveryService.getMenuItemsList().get(clientView.getSelectedItem()).getTimesOrdered() + 1));
            //   deliveryService = Serializator.deserialize();
            Serializator.serialize(deliveryService);
        }
    }

    class OrderListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String receipt = deliveryService.createOrder(clientView.getClient());
            for (EmployeeView ev : employeeViews) {
                ev.notifyEmployee(receipt);
            }
        }
    }

    class SearchListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String criteria = clientView.getSearchCriteria();
            String conditions = clientView.getSearchConditions();
            clientView.displaySearchResults(Filter.searchMenuItems(criteria, conditions, deliveryService.getMenuItemsList()));
        }
    }

    class Report1Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Report1.report1(deliveryService.getOrderList());
        }
    }

    class Report2Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Report2.report2(deliveryService.getMenuItemsList());
        }
    }
    class Report3Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Report3.report3(deliveryService.getClientList());
        }
    }
    class Report4Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Report4.report4(deliveryService.getMenuItemsList());
        }
    }

    public void updateViews() {
        clientView.setMenuItems(deliveryService.getMenuItemsString(deliveryService.getMenuItemsList()));
        adminView.setMenuItems(deliveryService.getMenuItemsString(deliveryService.getMenuItemsList()));
    }
}