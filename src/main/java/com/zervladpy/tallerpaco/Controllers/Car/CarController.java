package com.zervladpy.tallerpaco.Controllers.Car;

import com.zervladpy.tallerpaco.Core.DAO.CarDAO;
import com.zervladpy.tallerpaco.Core.DAO.IDAO;
import com.zervladpy.tallerpaco.Core.Entities.Car.Car;
import com.zervladpy.tallerpaco.Core.Entities.Car.CarBrand;
import com.zervladpy.tallerpaco.Core.Entities.Car.CarWithUserFullNameDTO;
import com.zervladpy.tallerpaco.Core.Entities.Customer.Customer;
import com.zervladpy.tallerpaco.Core.Session.SessionManager;
import jakarta.persistence.EntityManagerFactory;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class CarController {

    private EntityManagerFactory emf;
    private IDAO<Car> dao;

    private List<CarWithUserFullNameDTO> cars;

    @FXML private TableView<CarWithUserFullNameDTO> tableView;
    @FXML private TextField searchField;
    @FXML private Button addButton;

    public CarController() {
        // --- Initialize the EntityManagerFactory --- //
        this.emf = SessionManager.getInstance();
        // --- Initialize the DAO --- //
        this.dao = new CarDAO(emf);
    }

    public void initialize() {
        System.out.println("CarListViewController initialized");
        createTable();

        cars = ((CarDAO) dao).getCarWithUserFullNameDTO(Customer.class, CarBrand.class);
    }

    public void createTable() {

        // --- ID Column --- //
        TableColumn<CarWithUserFullNameDTO, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        // --- License Plate Column --- //
        TableColumn<CarWithUserFullNameDTO, String> licensePlateColumn = new TableColumn<>("Matrícula");
        licensePlateColumn.setCellValueFactory(new PropertyValueFactory<>("plate"));

        // --- Lineage Column --- //
        TableColumn<CarWithUserFullNameDTO, String> lineageColumn = new TableColumn<>("Kilometraje");
        lineageColumn.setCellValueFactory(new PropertyValueFactory<>("mileage"));

        // --- Color Column --- //
        TableColumn<CarWithUserFullNameDTO, String> colorColumn = new TableColumn<>("Color");
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));

        // --- Color Column --- //
        TableColumn<CarWithUserFullNameDTO, String> carBrandColumn = new TableColumn<>("Marca");
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("carBrandName"));

        // --- Color Column --- //
        TableColumn<CarWithUserFullNameDTO, String> ownerNameColumn = new TableColumn<>("Dueño");
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("ownerFullName"));

        // --- Add columns to table --- //
        List<TableColumn<CarWithUserFullNameDTO, ?>> columns = tableView.getColumns();
        columns.addAll(List.of(idColumn, licensePlateColumn, lineageColumn, colorColumn, carBrandColumn, ownerNameColumn));

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_NEXT_COLUMN);

        System.out.println(cars);

    }
}
