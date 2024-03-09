package com.zervladpy.tallerpaco.Controllers;

import com.zervladpy.tallerpaco.Core.DAO.ClientDAO;
import com.zervladpy.tallerpaco.Core.DAO.PartDAO;
import com.zervladpy.tallerpaco.Core.DAO.ReceiptDAO;
import com.zervladpy.tallerpaco.Core.Entities.Client.ClientNameDTO;
import com.zervladpy.tallerpaco.Core.Entities.Reciep.ReceiptTableViewDTO;
import com.zervladpy.tallerpaco.Core.Utils.Enums.ServiceType;
import com.zervladpy.tallerpaco.Core.Utils.Managers.DependencyManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReceiptTabController {

    private ObservableList<ReceiptTableViewDTO> items;
    private ReceiptTableViewDTO selected;

    // --- Table --- //
    @FXML private TableView<ReceiptTableViewDTO> table;
    @FXML private TableColumn<ReceiptTableViewDTO, Integer> idCL;
    @FXML private TableColumn<ReceiptTableViewDTO, LocalDate> dateCL;
    @FXML private TableColumn<ReceiptTableViewDTO, String> clientCL;
    @FXML private TableColumn<ReceiptTableViewDTO, ServiceType> serviceCL;
    @FXML private TableColumn<ReceiptTableViewDTO, Integer> partCL;
    @FXML private TableColumn<ReceiptTableViewDTO, Double> laborPriceCL;
    @FXML private TableColumn<ReceiptTableViewDTO, Number> totalPriceCL;

    // --- Form --- //
    @FXML private DatePicker dateDP;
    @FXML private ComboBox<ClientNameDTO> clientCB;
    @FXML private ComboBox<ServiceType> serviceCB;
    @FXML private TextField laborPriceTF;
    @FXML private Button formButton;

    // --- Bottom Bar --- //
    @FXML private Button deleteBtn, emptyBtn;

    // --- DAOs --- //
    private final ReceiptDAO receiptDAO;
    private final PartDAO partDAO;
    private final ClientDAO clientDAO;

    // --- Constructor --- //
    public ReceiptTabController() {
        receiptDAO = DependencyManager.getInstance().get(ReceiptDAO.class);
        partDAO = DependencyManager.getInstance().get(PartDAO.class);
        clientDAO = DependencyManager.getInstance().get(ClientDAO.class);
        items = FXCollections.observableList(new ArrayList<>());
    }

    // --- Setters --- //
    public void setItems(ObservableList<ReceiptTableViewDTO> items) {
        this.items = items;
        table.setItems(this.items);
    }

    public void setItems(List<ReceiptTableViewDTO> items) {
        this.items = FXCollections.observableList(items);
        table.setItems(this.items);
    }

    // --- Initialization --- //
    public void initialize() {
        setupColumns();
        setItems(receiptDAO.getTableViewDTOs());
    }

    private void setupColumns() {
        idCL.setCellValueFactory(new PropertyValueFactory<>("id"));
        dateCL.setCellValueFactory(new PropertyValueFactory<>("date"));
        clientCL.setCellValueFactory(cellData -> cellData.getValue().clientProperty());
        serviceCL.setCellValueFactory(new PropertyValueFactory<>("serviceType"));
        partCL.setCellValueFactory( new PropertyValueFactory<>("parts"));
        laborPriceCL.setCellValueFactory(new PropertyValueFactory<>("laborPrice"));
        totalPriceCL.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
    }

    // --- Reload --- //
    public void reload() {
        setItems(receiptDAO.getTableViewDTOs());
        table.refresh();
    }

    // --- Listeners --- //
    @FXML private void onTableClick() {
        selected = table.getSelectionModel().getSelectedItem();

        if (selected != null) {

        } else {

        }

    }

    @FXML private void onFormButtonClick(MouseEvent event) {

    }

    @FXML private void onEmptyBtnClick(MouseEvent event) {
        selected = null;
    }

    @FXML private void onDeleteBtnClick(MouseEvent event) {

    }

}
