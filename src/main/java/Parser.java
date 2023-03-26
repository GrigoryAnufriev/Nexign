import models.CDR;
import models.CDRTable;
import models.CDRTariffDateTime;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class Parser {


    public static CDRTable parse() {

        CDRTable table = new CDRTable(new HashMap<>());

        try(FileReader r = new FileReader("src/main/resources/cdr.txt")) {
            String s;
            BufferedReader reader = new BufferedReader(r);
            while ((s = reader.readLine()) != null){
                CDR cdr = parseCDR(s);
                Object value = null;
                try {
                    value = table.getCDRTable().get(cdr.getNumber());
                }
                catch (Exception e){}

                if (value == null){
                    CDRTariffDateTime cTDT = new CDRTariffDateTime("", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
                    cTDT.setDateTimeOfBegin(cdr.getDateTimeOfBegin());
                    cTDT.setDateTimeOfEnd(cdr.getDateTimeOfEnd());
                    cTDT.setTypeOfCall(cdr.getTypeOfCall());
                    cTDT.setTariff(cdr.getTypeOfTariff());
                    table.setCDRTable(cdr.getNumber(), cTDT);
                } else {
                    table.get(cdr.getNumber()).setTypeOfCall(cdr.getTypeOfCall());
                    table.get(cdr.getNumber()).setDateTimeOfBegin(cdr.getDateTimeOfBegin());
                    table.get(cdr.getNumber()).setDateTimeOfEnd(cdr.getDateTimeOfEnd());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return table;
    }


    private static CDR parseCDR (String arg) {
        arg = arg.replace("\s","");
        String[] argMas = arg.split(",");

        CDR res = new CDR();

        ArrayList<Long> date = new ArrayList<>();
        for (String s : argMas){
            if (s.length() == 14)
                date.add(Long.parseLong(s));
        }

        addDate(res, date);

        for (String s : argMas) {
            if (s.equals("01") || (s.equals("02"))) {
                res.setTypeOfCall(s);
                continue;
            }
            if (s.length() == 11) {
                res.setNumber(s);
                continue;
            }

            if(s.length() != 14){
                res.setTypeOfTariff(s);
            }

        }
        return res;
    }

    private static void addDate (CDR cdr, ArrayList<Long> date) {

        DateTimeFormatter formatter_date = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        if (date.get(0) > date.get(1)){
            cdr.setDateTimeOfBegin(LocalDateTime.parse(date.get(1).toString(), formatter_date));
            cdr.setDateTimeOfEnd(LocalDateTime.parse(date.get(0).toString(), formatter_date));
        } else {
            cdr.setDateTimeOfBegin(LocalDateTime.parse(date.get(0).toString(), formatter_date));
            cdr.setDateTimeOfEnd(LocalDateTime.parse(date.get(1).toString(), formatter_date));
        }
    }
}
