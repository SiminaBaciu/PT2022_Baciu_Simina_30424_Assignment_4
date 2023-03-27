package dataLayer;


import business.DeliveryService;

import java.io.*;

public class Serializator {

    public Serializator() {
    }

    public static void serialize(DeliveryService deliveryService){
        try {
            FileOutputStream fileOut =
                    new FileOutputStream("file.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.reset();
            out.writeObject(deliveryService);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in file.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static DeliveryService deserialize(){
        try {
            DeliveryService deliveryService;
            FileInputStream fileIn = new FileInputStream("file.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            deliveryService = (DeliveryService) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Deserialized");
            return deliveryService;
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
            return null;
        }
    }
}
