package com.zervladpy.tallerpaco.Core.Entities.Car;

import com.zervladpy.tallerpaco.Core.Entities.Brand.BrandNameDTO;
import com.zervladpy.tallerpaco.Core.Entities.Client.ClientNameDTO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
public class CarTableViewDTO {
    private String plate;
    private String color;
    private int mileage;
    private BrandNameDTO brand;
    private ClientNameDTO client;

    public ObservableValue<String> brandNameProperty() {
        return new SimpleStringProperty(brand.getName());
    }

    public ObservableValue<String> clientNameProperty() {
        return new SimpleStringProperty(client.getFullName());
    }
}
