package com.zervladpy.tallerpaco.Core.Entities.Invoice;

import com.zervladpy.tallerpaco.Core.Entities.Car.CarPlateDTO;
import com.zervladpy.tallerpaco.Core.Entities.Client.ClientNameDTO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor @AllArgsConstructor
@Setter @Getter @ToString
public class InvoiceTableDTO {

    private LocalDate date;
    private CarPlateDTO plate;
    private ClientNameDTO client;
    private double totalPrice;

    public ObservableValue<String> getPlateProperty() {
        return new SimpleStringProperty(plate.getPlate());
    }

    public ObservableValue<String> getClientNameProperty() {
        return client.nameProperty();
    }
}
