import models.CDRTable;
import models.CDRTariffDateTime;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class GenerateReport {

    public static void GenerateReports() {
        CDRTable cdrTable = Parser.parse();
        List<String> number = new ArrayList<>(Parser.parse().getCDRTable().keySet());

        try(FileWriter writer = new FileWriter("src/main/resources/report.txt", false)) {
            for (String s : number) {
                CDRTariffDateTime cdrTariffDateTime = cdrTable.get(s);

                ArrayList<Long> duration = duration(cdrTariffDateTime.getDateTimeOfBegin(), cdrTariffDateTime.getDateTimeOfEnd());
                String sw = cdrTariffDateTime.getTariff();
                ArrayList<Double> pay = new ArrayList<>();
                switch (sw) {
                    case "06" -> pay = payUnlimited(duration);
                    case "03" -> pay = payMinutes(duration);
                    case "11" -> pay = payCommon(duration, cdrTariffDateTime.getTypeOfCall());
                }

                int len = cdrTariffDateTime.getDateTimeOfBegin().size();
                String report = "";
                report += "Tariff index: " + sw + "\n";
                report += "----------------------------------------------------------------------------\n" +
                        "Report for phone number " + s + ":\n" +
                        "----------------------------------------------------------------------------\n" +
                        "| Call Type |   Start Time        |     End Time        | Duration | Cost  |\n" +
                        "----------------------------------------------------------------------------\n";
                for (int i = 0; i < len; i++) {
                    report += "|     " + cdrTariffDateTime.getTypeOfCall().get(i) +
                            "    | " + cdrTariffDateTime.getDateTimeOfBegin().get(i).toString().replace("T", " ") +
                            " | " + cdrTariffDateTime.getDateTimeOfEnd().get(i).toString().replace("T", " ") +
                            " | " + generateTime(duration.get(i)) +
                            " |  " + pay.get(i) + " |\n";
                }
                report += "----------------------------------------------------------------------------\n" +
                        "|                                           Total Cost: |     " + pay.get(pay.size() - 1) + " rubles |\n" +
                        "----------------------------------------------------------------------------\n\n";

                writer.append(report);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<Double> payUnlimited(ArrayList<Long> calls) {
        ArrayList<Double> res = new ArrayList<>();
        int MaxMinutes = 300;
        double totalSum = 100;
        for (Long l : calls){
            int i = (int) (long) l;
            i = i / 60;
            if (i > MaxMinutes){
                res.add((double) i - MaxMinutes);
                totalSum += i - MaxMinutes;
                MaxMinutes = 0;
            } else {
                res.add(0.0);
                MaxMinutes -= i;
            }
        }
        res.add(totalSum);
        return res;
    }

    private static ArrayList<Double> payMinutes(ArrayList<Long> calls) {
        ArrayList<Double> res = new ArrayList<>();
        double totalSum = 0;
        for (Long l : calls) {
            int i = (int) (long) l;
            i = i / 60;
            res.add(i * 1.5);
            totalSum += i * 1.5;
        }
        res.add(totalSum);
        return res;
    }

    private static ArrayList<Double> payCommon(ArrayList<Long> calls, ArrayList<String> typeOfCall) {
        ArrayList<Double> res = new ArrayList<>();
        double totalSum = 0;
        int index = typeOfCall.size();
        int MaxMinutes = 100;
        for (int i = 0; i < index; i++){
            if (typeOfCall.get(i).equals("01")){
                int m = (int) (long) calls.get(i) / 60;
                if (m > MaxMinutes){
                    res.add((double) (m - MaxMinutes) * 1.5);
                    totalSum += (m - MaxMinutes) * 1.5;
                    MaxMinutes = 0;
                } else {
                    res.add(m * 0.5);
                    totalSum += m * 0.5;
                    MaxMinutes -= m;
                }
            } else {
                res.add(0.0);
            }
        }
        res.add(totalSum);

        return res;
    }

    private static ArrayList<Long> duration(ArrayList<LocalDateTime> dateTimeOfBegin, ArrayList<LocalDateTime> dateTimeOfEnd){
        ArrayList<Long> res = new ArrayList<>();
        int col = dateTimeOfBegin.size();
        for (int i = 0; i < col; i++){
            res.add(ChronoUnit.SECONDS.between(dateTimeOfBegin.get(i), dateTimeOfEnd.get(i)));
        }
        return res;
    }

    private static String generateTime(long time){
        String res = "";
        long hour = time / 3600;
        time -= hour * 3600;
        if (hour < 10)
            res += "0" + hour + ":";
        else
            res += hour + ":";
        long minute = time / 60;
        time -= minute * 60;
        if (minute < 10)
            res += "0" + minute + ":";
        else
            res += minute + ":";

        if (time < 10)
            res += "0" + time;
        else
            res += time;

        return res;
    }
}
