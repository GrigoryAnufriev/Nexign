import models.CDR;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        GenerateReport.GenerateReports();
//        List<String> s = new ArrayList<>(Parser.parse().getCDRTable().keySet());
//        for (String st : s)
//            System.out.println(st);
//        CDR cdr = Parser.parse("02, 79876543221, 20230321160455, 20230321163211, 11");
//        System.out.println(cdr.getDateTimeOfBegin());
//        System.out.println(cdr.getDateTimeOfEnd());
//
//        System.out.println(ChronoUnit.SECONDS.between(cdr.getDateTimeOfBegin(), cdr.getDateTimeOfEnd()));
//        System.out.println(cdr.getNumber());
    }
}
