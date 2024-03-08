package com.zervladpy.tallerpaco.Controllers.Parts;

import com.zervladpy.tallerpaco.Controllers.Car.CreateCarController;
import com.zervladpy.tallerpaco.Core.DAO.PartDAO;
import com.zervladpy.tallerpaco.Core.Entities.Parts.Part;
import com.zervladpy.tallerpaco.Core.Entities.Reciep.ReceiptTableViewDTO;
import com.zervladpy.tallerpaco.Core.Utils.Factories.DialogFactory;
import com.zervladpy.tallerpaco.Core.Utils.Managers.DependencyManager;
import jakarta.persistence.Table;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Window;

import java.util.List;

import static com.zervladpy.tallerpaco.Core.Utils.Factories.DialogFactory.DialogType.CREATE_CAR;
import static com.zervladpy.tallerpaco.Core.Utils.Factories.DialogFactory.DialogType.CREATE_PART;

public class PartsController {

    private PartDAO dao;
    private List<Part> parts;

    @FXML private TableView<Part> tableView;
    @FXML private Button addButton;
    @FXML private TextField filterTextField;

    public void initialize() {
        dao = DependencyManager.getInstance().get(PartDAO.class);
        parts = dao.getAll();
        createTable();
    }

    private void createTable() {

        // --- ID --- //
        TableColumn<Part, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        // --- Reference --- //
        TableColumn<Part, String> refernceColumn = new TableColumn<>("Referencia");
        refernceColumn.setCellValueFactory(new PropertyValueFactory<>("reference"));

        // --- Description --- //
        TableColumn<Part, String> descriptionColumn = new TableColumn<>("Descripción");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        // --- Stock --- //
        TableColumn<Part, String> stockColumn = new TableColumn<>("En stock");
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));

        // --- Price --- //
        TableColumn<Part, Double> priceColumn = new TableColumn<>("Precio");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        List<TableColumn<Part, ?>> columns = tableView.getColumns();

        columns.addAll(List.of(idColumn, refernceColumn, descriptionColumn, stockColumn, priceColumn));

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_NEXT_COLUMN);

        tableView.getItems().addAll(parts);

    }

    @FXML private void launchCreateDialog() {

        Window root = addButton.getScene().getWindow();
        CreatePartController controller = new CreatePartController();
        Dialog<ButtonType> dialog = DialogFactory.createDialog(CREATE_PART, root, controller);

        dialog.showAndWait();

    }
}
