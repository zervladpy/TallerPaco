package com.zervladpy.tallerpaco.Core.Entities.Reciep;

import com.zervladpy.tallerpaco.Core.Entities.Client.ClientNameDTO;
import com.zervladpy.tallerpaco.Core.Utils.Enums.ServiceType;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor @AllArgsConstructor
@Setter @Getter
public class ReceiptTableViewDTO {
    private int id;
    private LocalDate date;
    private ClientNameDTO client;
    private ServiceType serviceType;
    private int parts;
    private double laborPrice;
    private double totalPrice;

    public ObservableValue<String> clientProperty() {
        return client.nameProperty();
    }

}
