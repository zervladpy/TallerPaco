package com.zervladpy.tallerpaco.Controllers.Customer;

import com.zervladpy.tallerpaco.Core.DAO.IDAO;
import com.zervladpy.tallerpaco.Core.Entities.Customer.Customer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

public class CustomerController {

    private final IDAO<Customer> dao;
    private  List<Customer> customers, filtered;
    @FXML private TableView<Customer> tableView;
    @FXML private Button addButton;
    @FXML private TextField filterTextField;

    public CustomerController(IDAO<Customer> dao) {
        this.dao = dao;
    }

    public void initialize() {
        customers = new ArrayList<>();
        customers.addAll(dao.getAll());
        filtered.addAll(customers);
    }

    private void createTable() {
        // --- ID Column --- //
        TableColumn<Customer, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        // --- FirstName Column --- //
        TableColumn<Customer, String> firstNameColumn = new TableColumn<>("Nombre");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        // --- LastName Column --- //
        TableColumn<Customer, String> lastNameColumn = new TableColumn<>("Apellidos");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("last_name"));

        // --- Phone Number --- //
        TableColumn<Customer, String> phoneColumn = new TableColumn<>("Teléfono");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        // --- Phone Number --- //
        TableColumn<Customer, String> emailColumn = new TableColumn<>("Email");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        List<TableColumn<Customer, ?>> columns = tableView.getColumns();
        columns.addAll(List.of(idColumn,firstNameColumn, lastNameColumn, phoneColumn, emailColumn));

        tableView.getItems().addAll(filtered);
    }

    private void filter() {
        String filter = filterTextField.getText();
        filtered = customers.stream().filter(customer -> customer.getName().contains(filter)).toList();
    }
}
