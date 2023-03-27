package business.reports;

import business.product.MenuItem;
import business.users.Client;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Report3 {
    private static final int clientOrders = 3;

    public static void report3(ArrayList<Client> clientList) {
        ArrayList<Client> reportResult = new ArrayList<>();

        reportResult = (ArrayList<Client>) clientList.stream().filter(c -> c.getTimesOrdered() > clientOrders).collect(Collectors.toList());

        String output = "Clients that have ordered more than " + clientOrders + " times: ";
        for(Client c : reportResult) {
            output += "Client #" + c.getID() + "\n";
            System.out.println(c.getTimesOrdered());
        }

        createReportTxtFile(output, 3);
    }

    private static void createReportTxtFile(String reportText, int reportNo) {
        String pathname = "Report" + reportNo + ".txt";
        File report = new File(pathname);
        try {
            report.createNewFile();
            if (report.exists()) {
                report.delete();
            }
            FileWriter writer = new FileWriter(report);
            writer.write(reportText);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
