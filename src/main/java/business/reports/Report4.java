package business.reports;

import business.product.MenuItem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Report4 {
    private static final int specifiedDay = 21, orderNo = 3;

    public static void report4(ArrayList<MenuItem> menuItems) {
        List<MenuItem> reportResult = new ArrayList<>();

        reportResult = menuItems.stream().filter(m -> m.getTimesOrdered() > orderNo).collect(Collectors.toList());

        String output = "Items ordered in day " + specifiedDay + " more than " + orderNo + " times: ";
        for(MenuItem m : reportResult) {
            output += m.getTitle() + "\n";
        }

        createReportTxtFile(output, 4);
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
