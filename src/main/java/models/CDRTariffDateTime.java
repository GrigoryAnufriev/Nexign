package models;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CDRTariffDateTime {
    private String tariff;
    private ArrayList<LocalDateTime> dateTimeOfBegin;
    private ArrayList<LocalDateTime> dateTimeOfEnd;
    private ArrayList<String> typeOfCall;

    public CDRTariffDateTime(String tariff, ArrayList<LocalDateTime> dateTimeOfBegin, ArrayList<LocalDateTime> dateTimeOfEnd, ArrayList<String> typeOfCall) {
        this.tariff = tariff;
        this.dateTimeOfBegin = dateTimeOfBegin;
        this.dateTimeOfEnd = dateTimeOfEnd;
        this.typeOfCall = typeOfCall;
    }

    public String getTariff() {
        return tariff;
    }

    public ArrayList<LocalDateTime> getDateTimeOfBegin() {
        return dateTimeOfBegin;
    }

    public ArrayList<LocalDateTime> getDateTimeOfEnd() {
        return dateTimeOfEnd;
    }

    public ArrayList<String> getTypeOfCall() {
        return typeOfCall;
    }

    public void setTariff(String tariff) {
        this.tariff = tariff;
    }

    public void setDateTimeOfBegin(LocalDateTime dateTimeOfBegin) {
        this.dateTimeOfBegin.add(dateTimeOfBegin);
    }

    public void setDateTimeOfEnd(LocalDateTime dateTimeOfEnd) {
        this.dateTimeOfEnd.add(dateTimeOfEnd);
    }

    public void setTypeOfCall(String typeOfCall) {
        this.typeOfCall.add(typeOfCall);
    }

}
