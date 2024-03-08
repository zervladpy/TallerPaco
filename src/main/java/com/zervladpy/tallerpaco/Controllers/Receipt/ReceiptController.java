package com.zervladpy.tallerpaco.Controllers.Receipt;

import com.zervladpy.tallerpaco.Controllers.Car.CreateCarController;
import com.zervladpy.tallerpaco.Core.DAO.ReceiptDAO;
import com.zervladpy.tallerpaco.Core.Entities.Customer.CustomerTableViewDTO;
import com.zervladpy.tallerpaco.Core.Entities.Reciep.Receipt;
import com.zervladpy.tallerpaco.Core.Entities.Reciep.ReceiptTableViewDTO;
import com.zervladpy.tallerpaco.Core.Utils.Factories.DialogFactory;
import com.zervladpy.tallerpaco.Core.Utils.Managers.DependencyManager;
import jakarta.persistence.Table;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Window;

import java.time.LocalDate;
import java.util.List;

import static com.zervladpy.tallerpaco.Core.Utils.Factories.DialogFactory.DialogType.CREATE_CAR;
import static com.zervladpy.tallerpaco.Core.Utils.Factories.DialogFactory.DialogType.CREATE_RECEIPT;

public class ReceiptController {

    private ReceiptDAO dao;
    private List<ReceiptTableViewDTO> receipts;

    @FXML private TableView<ReceiptTableViewDTO> tableView;
    @FXML private Button addButton;
    @FXML private TextField filterTextField;

    public void initialize() {
        dao = DependencyManager.getInstance().get(ReceiptDAO.class);
        // receipts = dao.getTableViewDTOs();
        createTable();
    }

    public void createTable() {
        // --- ID column --- //
        TableColumn<ReceiptTableViewDTO, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        // --- Date Column --- //
        TableColumn<ReceiptTableViewDTO, LocalDate> dateColumn = new TableColumn<>("Fecha");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        // --- Customer Column --- //
        TableColumn<ReceiptTableViewDTO, String> customerColumn = new TableColumn<>("Cliente");
        customerColumn.setCellValueFactory(new PropertyValueFactory<>("customerFullName"));

        // --- Service Type --- //
        TableColumn<ReceiptTableViewDTO, String> serviceTypeColumn = new TableColumn<>("Servicio");
        serviceTypeColumn.setCellValueFactory(new PropertyValueFactory<>("serviceType"));

        // --- Parts Column --- //
        TableColumn<ReceiptTableViewDTO, Integer> partsColumn = new TableColumn<>("Piezas");
        partsColumn.setCellValueFactory(new PropertyValueFactory<>("partsCount"));

        // --- Labor Price Column --- //
        TableColumn<ReceiptTableViewDTO, Double> laborPriceColumn = new TableColumn<>("Mano de Obra");
        laborPriceColumn.setCellValueFactory(new PropertyValueFactory<>("laborPrice"));

        // --- Total Price --- //
        TableColumn<ReceiptTableViewDTO, Double> totalPriceColum = new TableColumn<>("Precio Total");
        totalPriceColum.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        List<TableColumn<ReceiptTableViewDTO, ?>> columns = tableView.getColumns();

        columns.addAll(List.of(idColumn, dateColumn, customerColumn, serviceTypeColumn, partsColumn, laborPriceColumn, totalPriceColum));

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_NEXT_COLUMN);

        // tableView.getItems().addAll(receipts);
    }

    @FXML private void launchCreateDialog() {

        Window root = addButton.getScene().getWindow();
        CreateReceiptController controller = new CreateReceiptController();
        Dialog<ButtonType> dialog = DialogFactory.createDialog(CREATE_RECEIPT, root, controller);

        dialog.showAndWait();

    }
}
