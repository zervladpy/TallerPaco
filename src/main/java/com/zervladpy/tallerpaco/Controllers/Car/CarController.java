package com.zervladpy.tallerpaco.Controllers.Car;

import com.zervladpy.tallerpaco.Core.DAO.CarDAO;
import com.zervladpy.tallerpaco.Core.Entities.Car.CarTableViewDTO;
import com.zervladpy.tallerpaco.Core.Utils.Managers.DependencyManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class CarController {

    private final CarDAO dao;

    private List<CarTableViewDTO> cars;

    @FXML private TableView<CarTableViewDTO> tableView;
    @FXML private TextField searchField;
    @FXML private Button addButton;

    public CarController() {
        dao = DependencyManager.getInstance().get(CarDAO.class);
    }

    public void initialize() {
        cars = dao.getCarTableViewDTO();
        cars.forEach(System.out::println);
        createTable();
    }

    public void createTable() {

        // --- ID Column --- //
        TableColumn<CarTableViewDTO, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        // --- License Plate Column --- //
        TableColumn<CarTableViewDTO, String> licensePlateColumn = new TableColumn<>("Matrícula");
        licensePlateColumn.setCellValueFactory(new PropertyValueFactory<>("plate"));

        // --- Color Column --- //
        TableColumn<CarTableViewDTO, String> colorColumn = new TableColumn<>("Color");
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));

        // --- Lineage Column --- //
        TableColumn<CarTableViewDTO, String> mileageColumn = new TableColumn<>("Kilometraje");
        mileageColumn.setCellValueFactory(new PropertyValueFactory<>("mileage"));

        // --- Car Brand Column --- //
        TableColumn<CarTableViewDTO, String> carBrandColumn = new TableColumn<>("Marca");
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("carBrandName"));

        // --- Car Owner Column --- //
        TableColumn<CarTableViewDTO, String> ownerNameColumn = new TableColumn<>("Dueño");
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("ownerFullName"));

        // --- Add columns to table --- //
        List<TableColumn<CarTableViewDTO, ?>> columns = tableView.getColumns();
        columns.addAll(List.of(idColumn, licensePlateColumn, colorColumn, mileageColumn, carBrandColumn, ownerNameColumn));

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_NEXT_COLUMN);

        // --- Set items to the table --- //
        tableView.getItems().addAll(cars);

    }
}
