package com.zervladpy.tallerpaco.Controllers.Customer;

import com.zervladpy.tallerpaco.Core.DAO.CustomerDAO;
import com.zervladpy.tallerpaco.Core.DAO.IDAO;
import com.zervladpy.tallerpaco.Core.Entities.Customer.Customer;
import com.zervladpy.tallerpaco.Core.Entities.Customer.CustomerTableViewDTO;
import com.zervladpy.tallerpaco.Core.Utils.Managers.DependencyManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

public class CustomerController {

    private final CustomerDAO dao;
    private List<CustomerTableViewDTO> customers;
    @FXML private TableView<CustomerTableViewDTO> tableView;
    @FXML private Button addButton;
    @FXML private TextField filterTextField;

    public CustomerController() {
        dao = DependencyManager.getInstance().get(CustomerDAO.class);
    }

    public void initialize() {
        customers = dao.getTableViewDTOs();
        createTable();

    }

    private void createTable() {
        // --- ID Column --- //
        TableColumn<CustomerTableViewDTO, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        // --- FirstName Column --- //
        TableColumn<CustomerTableViewDTO, String> firstNameColumn = new TableColumn<>("Nombre");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        // --- LastName Column --- //
        TableColumn<CustomerTableViewDTO, String> lastNameColumn = new TableColumn<>("Apellidos");
        idColumn.setCellValueFactory( new PropertyValueFactory<>("lastName"));

        // --- Phone Number --- //
        TableColumn<CustomerTableViewDTO, String> phoneColumn = new TableColumn<>("Teléfono");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        // --- Email --- //
        TableColumn<CustomerTableViewDTO, String> emailColumn = new TableColumn<>("Email");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        // --- Total Cars --- //
        TableColumn<CustomerTableViewDTO, Integer> totalCarsColumn = new TableColumn<>("Coches");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("totalCars"));

        // --- Total Cars --- //
        TableColumn<CustomerTableViewDTO, Integer> totalReceiptsColumn = new TableColumn<>("Facturas");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("totalReceipts"));

        List<TableColumn<CustomerTableViewDTO, ?>> columns = tableView.getColumns();
        columns.addAll(List.of(idColumn,firstNameColumn, lastNameColumn, phoneColumn, emailColumn));

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_NEXT_COLUMN);

        tableView.getItems().addAll(customers);
    }

    private void filter() {
    }
}
