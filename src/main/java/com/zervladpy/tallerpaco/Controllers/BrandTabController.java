package com.zervladpy.tallerpaco.Controllers;

import com.zervladpy.tallerpaco.Core.DAO.BrandDAO;
import com.zervladpy.tallerpaco.Core.Entities.Brand.Brand;
import com.zervladpy.tallerpaco.Core.Utils.Managers.DependencyManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class BrandTabController {
    private ObservableList<Brand> items;
    private Brand selected;

    // --- Table --- //
    @FXML private TableView<Brand> table;
    @FXML private TableColumn<Brand, Integer> idCL;
    @FXML private TableColumn<Brand, String> nameCL;
    @FXML private TableColumn<Brand, String> originCL;
    @FXML private TableColumn<Brand, Integer> yearCL;

    // --- Form --- //
    @FXML private TextField nameTF, originTF, yearTF;
    @FXML private Button formButton;

    // --- Bottom Bar --- //
    @FXML private ButtonBar buttonBar;
    @FXML private Button emptyBtn, deleteBtn;

    // --- DAOs --- //
    private final BrandDAO brandDAO;

    // --- Constructor --- //
    public BrandTabController() {
        brandDAO = DependencyManager.getInstance().get(BrandDAO.class);
        items = FXCollections.observableList(new ArrayList<>());
    }

    // --- Setters --- //
    public void setItems(ObservableList<Brand> items) {
        this.items = items;
        table.setItems(this.items);
    }

    public void setItems(List<Brand> items) {
        this.items = FXCollections.observableList(items);
        table.setItems(this.items);
    }

    // --- Initialization --- //
    public void initialize() {
        setupColumns();
        setItems(brandDAO.getAll());
    }

    private void setupColumns() {
        idCL.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCL.setCellValueFactory(new PropertyValueFactory<>("name"));
        originCL.setCellValueFactory(new PropertyValueFactory<>("country"));
        yearCL.setCellValueFactory(new PropertyValueFactory<>("foundationYear"));
    }

    // --- Reload --- //
    public void reload() {
        setItems(brandDAO.getAll());
        table.refresh();
    }

    // --- Listeners --- //
    @FXML private void onTableClick(MouseEvent event) {
        selected = table.getSelectionModel().getSelectedItem();
        boolean isNotNull = selected != null;
        nameTF.setText(isNotNull ? selected.getName() : "");
        originTF.setText(isNotNull ? selected.getCountry() : "");
        yearTF.setText(isNotNull ? String.valueOf(selected.getFoundationYear()) : "");
    }

    @FXML private void onFormButtonClick(MouseEvent event) {

        String name = nameTF.getText();
        String origin = originTF.getText();
        String year = yearTF.getText();

        if (name.isEmpty() || name.isBlank()) {
            return;
        }

        if (origin.isEmpty() || origin.isBlank()) {
            return;
        }

        if (year.isEmpty() || year.isBlank()) {
            return;
        } else {
            try {
                Integer.parseInt(year);
            } catch (Exception ignore) {
                return;
            }
        }

        if (selected != null) {
            selected.setName(name);
            selected.setCountry(origin);
            selected.setFoundationYear(Integer.parseInt(year));

            brandDAO.update(selected);

        } else {
            Brand newBrand = new Brand();
            newBrand.setName(name);
            newBrand.setCountry(origin);
            newBrand.setFoundationYear(Integer.parseInt(year));

            brandDAO.save(newBrand);
        }

        reload();

    }

    @FXML private void onEmptyButtonClick(MouseEvent event) {
        selected = null;
        nameTF.setText("");
        yearTF.setText("");
        originTF.setText("");
    }

    @FXML private void onDeleteButtonClick(MouseEvent event) {
        if (selected != null) {
            brandDAO.delete(selected);
            reload();
        }
    }

}
