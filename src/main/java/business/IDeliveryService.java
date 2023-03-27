package business;

import business.product.MenuItem;
import business.users.User;

import java.io.FileReader;
import java.util.ArrayList;

public interface IDeliveryService {

    /**
     * @param path
     * @pre path != null
     * @post inputList != null
     */
    public void importMenuItems(String path);

    /**
     * @param newOrder,clientID
     * @pre newOrder != null, clientOder != null
     * @post receipt != null
     * @return receipt
     */
    public String createReceipt(Order newOrder, int clientID);

    /**
     * @param title
     * @pre title != null, selectedItemsList != null
     */
    public void addCompositeItem(String title);
}
