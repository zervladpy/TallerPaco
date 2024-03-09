package com.zervladpy.tallerpaco.Controllers;


import com.zervladpy.tallerpaco.Core.DAO.PartDAO;
import com.zervladpy.tallerpaco.Core.Entities.Parts.Part;
import com.zervladpy.tallerpaco.Core.Utils.Managers.DependencyManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class PartTabController {

    private ObservableList<Part> items;
    private Part selected;

    // --- Table --- //
    @FXML private TableView<Part> table;
    @FXML private TableColumn<Part, Integer> idCL;
    @FXML private TableColumn<Part, String> referenceCL;
    @FXML private TableColumn<Part, String> descriptionCL;
    @FXML private TableColumn<Part, Integer> stockCL;
    @FXML private TableColumn<Part, Double> priceCL;

    // --- Form --- //
    @FXML private TextField referenceTF, descriptionTF, stockTF, priceTF;
    @FXML private Button formButton;

    // --- Button Bar --- //
    @FXML private Button emptyBtn, deleteBtn;

    // --- DAOs --- //
    private final PartDAO partDAO;

    // --- Constructor --- //
    public PartTabController() {
        partDAO = DependencyManager.getInstance().get(PartDAO.class);
        items = FXCollections.observableList(new ArrayList<>());
    }

    // --- Setters --- //
    public void setItems(ObservableList<Part> items) {
        this.items = items;
        table.setItems(this.items);
    }

    public void setItems(List<Part> items) {
        this.items = FXCollections.observableList(items);
        table.setItems(this.items);
    }

    // --- Initialization --- //
    public void initialize() {
        setupColumns();
        setItems(partDAO.getAll());
    }

    private void setupColumns() {
        idCL.setCellValueFactory(new PropertyValueFactory<>("id"));
        referenceCL.setCellValueFactory(new PropertyValueFactory<>("reference"));
        descriptionCL.setCellValueFactory(new PropertyValueFactory<>("description"));
        stockCL.setCellValueFactory(new PropertyValueFactory<>("stock"));
        priceCL.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    // --- Reload --- //
    public void reload() {
        setItems(partDAO.getAll());
        table.refresh();
    }

    // --- Listeners --- //
    public void onTableClick(MouseEvent event) {
        selected = table.getSelectionModel().getSelectedItem();
        if (selected != null) {
            referenceTF.setText(selected.getReference());
            descriptionTF.setText(selected.getDescription());
            stockTF.setText(String.valueOf(selected.getStock()));
            priceTF.setText(String.valueOf(selected.getPrice()));
        } else {
            referenceTF.setText("");
            descriptionTF.setText("");
            stockTF.setText("");
            priceTF.setText("");
        }
    }

    public void onFormButtonClick(MouseEvent event) {

            String reference = referenceTF.getText();
            String description = descriptionTF.getText();
            String stock = stockTF.getText();
            String price = priceTF.getText();

            if (reference.isEmpty() || reference.isBlank()) {
                return;
            }

            if (description.isEmpty() || description.isBlank()) {
                return;
            }

            if (stock.isEmpty() || stock.isBlank()) {
                stock = "0";
            }

            if (price.isEmpty() || price.isBlank()) {
                price = "0.0";
            }

            if (selected == null) {
                Part part = new Part();
                part.setReference(reference);
                part.setDescription(description);
                part.setStock(Integer.parseInt(stock));
                part.setPrice(Double.parseDouble(price));
                partDAO.save(part);
            } else {
                selected.setReference(reference);
                selected.setDescription(description);
                selected.setStock(Integer.parseInt(stock));
                selected.setPrice(Double.parseDouble(price));
                partDAO.update(selected);
            }

            onEmptyButtonClick(null);
            reload();
    }

    public void onEmptyButtonClick(MouseEvent event) {
        selected = null;
        referenceTF.setText("");
        descriptionTF.setText("");
        stockTF.setText("");
        priceTF.setText("");
    }

    public void onDeleteButtonClick(MouseEvent event) {
        if (selected != null) {
            partDAO.delete(selected);
            reload();
        }
    }

}
