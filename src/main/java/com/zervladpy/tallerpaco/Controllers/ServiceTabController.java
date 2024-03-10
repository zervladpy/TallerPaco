package com.zervladpy.tallerpaco.Controllers;


import com.zervladpy.tallerpaco.Core.DAO.ServiceDAO;
import com.zervladpy.tallerpaco.Core.Entities.Service.Service;
import com.zervladpy.tallerpaco.Core.Utils.Managers.DependencyManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class ServiceTabController {

    private ObservableList<Service> items;
    private Service selected;

    // --- Table --- //
    @FXML private TableView<Service> table;
    @FXML private TableColumn<Service, Integer> idCL;
    @FXML private TableColumn<Service, String> nameCL;
    @FXML private TableColumn<Service, String> descriptionCL;
    @FXML private TableColumn<Service, Double> priceCL;

    // --- Form --- //
    @FXML private TextField nameTF, descriptionTF, priceTF;
    @FXML private Button formButton;

    // --- Button Bar --- //
    @FXML private Button emptyBtn, deleteBtn;

    // --- DAOs --- //
    private final ServiceDAO serviceDAO;

    // --- Constructor --- //
    public ServiceTabController() {
        serviceDAO = DependencyManager.getInstance().get(ServiceDAO.class);
        items = FXCollections.observableList(new ArrayList<>());
    }

    // --- Setters --- //
    public void setItems(ObservableList<Service> items) {
        this.items = items;
        table.setItems(this.items);
    }

    public void setItems(List<Service> items) {
        this.items = FXCollections.observableList(items);
        table.setItems(this.items);
    }

    // --- Initialization --- //
    public void initialize() {
        setupColumns();
        setItems(serviceDAO.getAll());
    }

    private void setupColumns() {
        idCL.setCellValueFactory(new PropertyValueFactory<>("idService"));
        nameCL.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionCL.setCellValueFactory(new PropertyValueFactory<>("description"));
        priceCL.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    // --- Reload --- //
    public void reload() {
        setItems(serviceDAO.getAll());
        table.refresh();
    }

    // --- Listeners --- //
    public void onTableClick(MouseEvent event) {
        selected = table.getSelectionModel().getSelectedItem();
        if (selected != null) {
            nameTF.setText(selected.getName());
            descriptionTF.setText(selected.getDescription());
            priceTF.setText(String.valueOf(selected.getPrice()));
        } else {
            nameTF.setText("");
            descriptionTF.setText("");
            priceTF.setText("");
        }
    }

    public void onFormButtonClick(MouseEvent event) {

        String name = nameTF.getText();
        String description = descriptionTF.getText();
        String priceSTR = priceTF.getText();

        boolean isNameValid = !name.isEmpty();
        boolean isPriceValid = !description.isEmpty();

        var exists = serviceDAO.getById(name);

        if (exists != null) {
            isNameValid = false;
        }

        try {
            Double.parseDouble(priceSTR);
            isPriceValid = true;
        } catch (NumberFormatException ignore){
        }

        if (!isNameValid || !isPriceValid) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("El formualrio no esta correcto");
            if(!isNameValid) {
                alert.setHeaderText("Asegurate de que no haya ya un registro con el mismo nombre");
            }

            alert.setContentText("Porfavor revise que los campos estan adecuadamente rellenados.");
            alert.showAndWait();

            return;
        }



        if (selected == null) {
            Service service = new Service();
            service.setName(nameTF.getText());
            service.setDescription(descriptionTF.getText());
            service.setPrice(Double.parseDouble(priceTF.getText()));
            serviceDAO.save(service);
        } else {
            selected.setName(nameTF.getText());
            selected.setDescription(descriptionTF.getText());
            selected.setPrice(Double.parseDouble(priceTF.getText()));
            serviceDAO.update(selected);
        }

        reload();

    }

    public void onEmptyButtonClick(MouseEvent event) {
        selected = null;
        nameTF.setText("");
        descriptionTF.setText("");
        priceTF.setText("");
    }

    public void onDeleteButtonClick(MouseEvent event) {
        if (selected != null) {
            serviceDAO.delete(selected);
            reload();
        }
    }

}
