package com.zervladpy.tallerpaco.Controllers.Customer;

import com.zervladpy.tallerpaco.Controllers.Common.FormController;
import com.zervladpy.tallerpaco.Core.Entities.Customer.Customer;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CustomerFormController implements FormController<Customer> {

    @FXML private TextField name, lastName, email, phone;

    @Override
    public void initialize() {

    }

    @Override
    public Customer getData() {
        return null;
    }

    @Override
    public void setData(Customer data) {

    }

    @Override
    public boolean isInputValid() {
        return false;
    }
}
