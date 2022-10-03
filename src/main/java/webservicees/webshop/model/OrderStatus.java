package webservicees.webshop.model;

import com.fasterxml.jackson.annotation.JsonValue;
public enum OrderStatus {
    NEW("NEW"), CONFIRMT("CONFIRM"), SEND("SEND"), DELIVERED("DELIVERED"), CANCELED("CANCELED");
    //TODO: wieso war JsonValue nötig, bei post order hat es auch funktioniert, vll. weil als response und nicht im body mitgegen
   @JsonValue
   private String status;

    OrderStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
    OrderStatus() {
    }
}
