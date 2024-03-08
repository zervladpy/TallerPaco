package com.zervladpy.tallerpaco.Controllers.Customer;

import com.zervladpy.tallerpaco.Core.DAO.CustomerDAO;
import com.zervladpy.tallerpaco.Core.Entities.Customer.CustomerTableViewDTO;
import com.zervladpy.tallerpaco.Core.Utils.Factories.DialogFactory;
import com.zervladpy.tallerpaco.Core.Utils.Managers.DependencyManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Window;

import java.util.List;

import static com.zervladpy.tallerpaco.Core.Utils.Factories.DialogFactory.DialogType.CREATE_CUSTOMER;

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
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        // --- LastName Column --- //
        TableColumn<CustomerTableViewDTO, String> lastNameColumn = new TableColumn<>("Apellidos");
        lastNameColumn.setCellValueFactory( new PropertyValueFactory<>("lastName"));

        // --- Phone Number --- //
        TableColumn<CustomerTableViewDTO, String> phoneColumn = new TableColumn<>("Teléfono");
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        // --- Email --- //
        TableColumn<CustomerTableViewDTO, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        // --- Total Cars --- //
        TableColumn<CustomerTableViewDTO, Integer> totalCarsColumn = new TableColumn<>("Coches");
        totalCarsColumn.setCellValueFactory(new PropertyValueFactory<>("totalCars"));

        // --- Total Cars --- //
        TableColumn<CustomerTableViewDTO, Integer> totalReceiptsColumn = new TableColumn<>("Facturas");
        totalReceiptsColumn.setCellValueFactory(new PropertyValueFactory<>("totalReceipts"));

        List<TableColumn<CustomerTableViewDTO, ?>> columns = tableView.getColumns();
        columns.addAll(List.of(idColumn,firstNameColumn, lastNameColumn, phoneColumn, emailColumn, totalReceiptsColumn, totalCarsColumn));

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_NEXT_COLUMN);

        tableView.getItems().addAll(customers);
    }

    @FXML private void launchCreateDialog() {

        Window root = addButton.getScene().getWindow();
        CustomerFormController controller = new CustomerFormController();
        Dialog<ButtonType> dialog = DialogFactory.createDialog(CREATE_CUSTOMER, root, controller);

        dialog.showAndWait();

    }
}
