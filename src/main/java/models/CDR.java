package models;

import java.time.LocalDateTime;

public class CDR {
    private String number;
    private String typeOfCall;
    private LocalDateTime dateTimeOfBegin;
    private LocalDateTime dateTimeOfEnd;
    private String typeOfTariff;


    public String getNumber() {
        return number;
    }

    public String getTypeOfCall() {
        return typeOfCall;
    }

    public LocalDateTime getDateTimeOfBegin() {
        return dateTimeOfBegin;
    }

    public LocalDateTime getDateTimeOfEnd() {
        return dateTimeOfEnd;
    }

    public String getTypeOfTariff() {
        return typeOfTariff;
    }


    public void setNumber(String number) {
        this.number = number;
    }

    public void setTypeOfCall(String typeOfCall) {
        this.typeOfCall = typeOfCall;
    }

    public void setDateTimeOfBegin(LocalDateTime dateTimeOfBegin) {
        this.dateTimeOfBegin = dateTimeOfBegin;
    }

    public void setDateTimeOfEnd(LocalDateTime dateTimeOfEnd) {
        this.dateTimeOfEnd = dateTimeOfEnd;
    }

    public void setTypeOfTariff(String typeOfTariff) {
        this.typeOfTariff = typeOfTariff;
    }
}
