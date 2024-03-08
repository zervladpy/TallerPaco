package com.zervladpy.tallerpaco.Controllers.Car;

import com.zervladpy.tallerpaco.Core.Entities.Car.CarBrand;
import com.zervladpy.tallerpaco.Core.Entities.Customer.Customer;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;

public class CreateCarController {

    @FXML private TextField plate, mileage;
    @FXML private ColorPicker color;
    @FXML private ChoiceBox<CarBrand> carBrand;
    @FXML private ChoiceBox<Customer> customer;

    public void initialize() {}
}
