package com.zervladpy.tallerpaco.Core.Entities.Client;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Setter @Getter
public class ClientNameDTO {
    private int id;
    private String fullName;

    @Override
    public String toString() {
        return fullName;
    }

    public ObservableValue<String> nameProperty() {
        return new SimpleStringProperty(fullName);
    }
}
