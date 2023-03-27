package business;

import business.product.BaseProduct;
import business.product.CompositeItem;
import business.product.MenuItem;
import business.users.Admin;
import business.users.Client;
import business.users.Employee;
import business.users.User;

import java.awt.*;
import java.io.*;
import java.nio.channels.SelectableChannel;
import java.util.*;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DeliveryService extends Observable implements java.io.Serializable, IDeliveryService{
    private ArrayList<MenuItem> menuItemsList;
    private ArrayList<MenuItem> clientOrder;
    private ArrayList<BaseProduct> selectedItemsList;
    private ArrayList<User> userList;
    private HashMap<Order, ArrayList<MenuItem>> orderList;
    private ArrayList<Client> clientList;
    private Date creationDate;

    public DeliveryService() {
        this.menuItemsList = new ArrayList<MenuItem>();
        this.clientOrder = new ArrayList<MenuItem>();
        this.selectedItemsList = new ArrayList<BaseProduct>();
        this.userList = new ArrayList<User>();
        this.orderList = new HashMap<Order, ArrayList<MenuItem>>();
        this.clientList = new ArrayList<>();

        userList.add(new Admin("admin", "admin".toCharArray()));
        userList.add(new Employee("Employee", "emp".toCharArray()));

        creationDate = new Date();
    }

    public void importMenuItems(String path) {
        assert(path != null);
        ArrayList<MenuItem> inputList = new ArrayList<MenuItem>();
        try{
            File inputF = new File(path);
            InputStream inputFS = new FileInputStream(inputF);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));

            // skip the header of the csv
            inputList = (ArrayList<MenuItem>) br.lines().skip(1)
                    .map(mapToItem)
                    .collect(Collectors.toList());
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert(inputList != null);
        menuItemsList = inputList;
    }

    @Override
    public String createReceipt(Order newOrder, int clientID) {
        return null;
    }

    public void addMenuItem(MenuItem m) {
        menuItemsList.add(m);
    }
    public String createOrder(Client client) {
        Order newOrder = new Order(orderList.size() + 1, client);
        clientList.get(clientList.indexOf(client)).incrementTimesOrdered();
        orderList.put(newOrder, clientOrder);

        File textBill = new File("Receipt.txt");
        String receipt = createReceipt(newOrder, client);
        try {
            textBill.createNewFile();
            if(textBill.exists()) {
                textBill.delete();
            }
            FileWriter writer = new FileWriter(textBill);
            writer.write(receipt);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return receipt;
    }

    public String createReceipt(Order newOrder, Client clientID) {
        assert(newOrder != null && clientOrder != null);
        String receipt = "RECEIPT\n";
        receipt += "Order ID: " + newOrder.getOrderID() + "\n";
        receipt += "Client ID: " + clientID + "\n";
        receipt += "------------\n";
        for(MenuItem m : clientOrder) {
            receipt += m.getTitle() + " : " + m.computePrice() + " RON\n";
        }
        receipt += "------------\n";
        receipt += "TOTAL: " + computeOrderPrice();
        clientOrder = new ArrayList<MenuItem>();
        assert(receipt != null);
        return receipt;
    }

    public int computeOrderPrice() {
        int price = 0;
        for(MenuItem m : clientOrder) {
            price += m.computePrice();
        }
        return price;
    }
    public void addUser(User user) {
        userList.add(user);
        if(user instanceof Client) {
            clientList.add((Client) user);
        }
    }
    public void addSelectedItem(BaseProduct menuItem) {
        selectedItemsList.add(menuItem);
    }
    public void addCompositeItem(String title) {
        assert(title != null && selectedItemsList != null);
        menuItemsList.add(new CompositeItem(title, selectedItemsList));
        selectedItemsList = new ArrayList<BaseProduct>();
    }
    public static String[] getMenuItemsString(ArrayList<MenuItem> menuItemsList) {
        String[] menuItemsString = new String[menuItemsList.size()];
        int index = 0;
        for(MenuItem m : menuItemsList) {
            if(m instanceof BaseProduct) {
                menuItemsString[index] = m.getTitle() + " - " + m.computePrice() + " RON";
            }
            if(m instanceof CompositeItem) {
                String temp = m.getTitle() + ": ";
                CompositeItem c = (CompositeItem) m;
                for(BaseProduct b : c.getProductList()) {
                    temp += b.getTitle() + ", ";
                }
                temp += m.computePrice() + " RON";
                menuItemsString[index] = temp;
            }
            index++;
        }
        return menuItemsString;
    }
    public ArrayList<MenuItem> getMenuItemsList() {
        return menuItemsList;
    }
    public ArrayList<User> getUserList() {
        return userList;
    }
    public ArrayList<MenuItem> getClientOrder() {
        return clientOrder;
    }
    public HashMap<Order, ArrayList<MenuItem>> getOrderList() {
        return orderList;
    }
    public void setOrderList(HashMap<Order, ArrayList<MenuItem>> orderList) {
        this.orderList = orderList;
    }
    public ArrayList<BaseProduct> getSelectedItemsList() {
        return selectedItemsList;
    }

    public ArrayList<Client> getClientList() {
        return clientList;
    }

    private static Function<String, MenuItem> mapToItem = (line) -> {

        String[] productData = line.split(",");// a CSV has comma separated lines

        String title = productData[0];
        float rating = Float.parseFloat(productData[1]);
        int calories = Integer.parseInt(productData[2]);
        int protein = Integer.parseInt(productData[3]);
        int fat = Integer.parseInt(productData[4]);
        int sodium = Integer.parseInt(productData[5]);
        int price = Integer.parseInt(productData[6]);
        MenuItem menuItem = new BaseProduct(title, rating, calories, protein, fat, sodium, price);
        return menuItem;
    };
}