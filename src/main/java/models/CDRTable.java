package models;

import java.util.HashMap;

public class CDRTable {
    public HashMap<String, CDRTariffDateTime> CDRTable;

    public CDRTable(HashMap<String, CDRTariffDateTime> CDRTable) {
        this.CDRTable = CDRTable;
    }

    public HashMap<String, CDRTariffDateTime> getCDRTable() {
        return CDRTable;
    }

    public void setCDRTable(String s, CDRTariffDateTime c) {
        this.CDRTable.put(s, c);
    }

    public CDRTariffDateTime get(String s){
        return CDRTable.get(s);
    }
}
