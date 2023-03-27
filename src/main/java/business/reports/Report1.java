package business.reports;

import business.Order;
import business.product.MenuItem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Report1 {
    private static final int startHour = 8, endHour = 12;

    public Report1(HashMap<Order, ArrayList<MenuItem>> orderList) {
    }

    public static void report1(HashMap<Order, ArrayList<MenuItem>> orderList) {
        ArrayList<Order> reportResult = new ArrayList<Order>();

        reportResult = (ArrayList<Order>) orderList.keySet().stream()
                .filter(o -> o.getOrderDate().getHours() > startHour && o.getOrderDate().getHours() < endHour)
                .collect(Collectors.toList());

        String output = "Report I:" + "\n" + "Orders between " + startHour + " and " + endHour + ": ";
        for (Order o : reportResult) {
            output += "ID: " + o.getOrderID() + " - CLIENT ID: " + o.getClientID() + " - HOUR: " + o.getOrderDate().getHours() + "\n";
        }

        System.out.println(output);
        createReportTxtFile(output, 1);
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
