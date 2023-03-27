package business.reports;

import business.Order;
import business.product.MenuItem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Report2 {
    private static final int timesOrdered = 3;

    public static void report2(ArrayList<MenuItem> menuItemsList) {
        ArrayList<MenuItem> reportResult = new ArrayList<MenuItem>();

        reportResult = (ArrayList<MenuItem>) menuItemsList.stream()
                .filter(m -> m.getTimesOrdered() > timesOrdered)
                .collect(Collectors.toList());

        String output = "Items ordered more than " + timesOrdered + " times:\n";
        for(MenuItem m : reportResult) {
            output += m.getTitle() + " was orered " + m.getTimesOrdered() + " times" + "\n";
        }

        createReportTxtFile(output, 2);
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
