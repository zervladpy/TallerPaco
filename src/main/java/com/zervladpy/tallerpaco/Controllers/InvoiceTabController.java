package com.zervladpy.tallerpaco.Controllers;

import com.zervladpy.tallerpaco.Core.DAO.CarDAO;
import com.zervladpy.tallerpaco.Core.DAO.InvoiceDAO;
import com.zervladpy.tallerpaco.Core.DAO.ServiceDAO;
import com.zervladpy.tallerpaco.Core.Entities.Car.CarPlateDTO;
import com.zervladpy.tallerpaco.Core.Entities.Invoice.Invoice;
import com.zervladpy.tallerpaco.Core.Entities.Invoice.InvoicePK;
import com.zervladpy.tallerpaco.Core.Entities.Invoice.InvoiceTableDTO;
import com.zervladpy.tallerpaco.Core.Entities.Service.Service;
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

public class InvoiceTabController {

    private ObservableList<InvoiceTableDTO> items;
    private InvoiceTableDTO selected;
    private Service selectedService;
    private ObservableList<CarPlateDTO> carPlates;
    private ObservableList<Service> serviceItems;

    // --- Table --- //
    @FXML private TableView<InvoiceTableDTO> tableInvoices;
    @FXML private TableColumn<InvoiceTableDTO, String> plateCL;
    @FXML private TableColumn<InvoiceTableDTO, LocalDate> dateCL;
    @FXML private TableColumn<InvoiceTableDTO, String> clientCL;
    @FXML private TableColumn<InvoiceTableDTO, Double> priceCL;

    // --- Table Services --- //
    @FXML private TableView<Service> tableServices;
    @FXML private TableColumn<InvoiceTableDTO, String> nameCL;
    @FXML private TableColumn<InvoiceTableDTO, Double> priceSCL;

    // --- Form --- //
    @FXML private DatePicker dateDP;
    @FXML private ComboBox<Service> serviceCB;
    @FXML private ComboBox<CarPlateDTO> plateCB;
    @FXML private TextField laborPriceTF;
    @FXML private Button formButton;

    // --- Bottom Bar --- //
    @FXML private Button deleteBtn, emptyBtn, addServiceBtn, deleteServiceBtn;

    // --- Label --- //
    @FXML Label totalPriceLb;

    // --- DAOs --- //
    private final InvoiceDAO invoiceDAO;
    private final ServiceDAO serviceDAO;
    private final CarDAO carDAO;

    // --- Constructor --- //
    public InvoiceTabController() {
        invoiceDAO = DependencyManager.getInstance().get(InvoiceDAO.class);
        serviceDAO = DependencyManager.getInstance().get(ServiceDAO.class);
        carDAO = DependencyManager.getInstance().get(CarDAO.class);
        items = FXCollections.observableList(new ArrayList<>());
        carPlates = FXCollections.observableList(new ArrayList<>());
        serviceItems = FXCollections.observableList(new ArrayList<>());
    }

    // --- Setters --- //
    public void setInvoiceItems(ObservableList<InvoiceTableDTO> items) {
        this.items = items;
        tableInvoices.setItems(this.items);
    }

    public void setInvoiceItems(List<InvoiceTableDTO> items) {
        this.items = FXCollections.observableList(items);
        tableInvoices.setItems(this.items);
    }

    public void setServicesItems(List<Service> items) {
        this.serviceItems = FXCollections.observableList(items);
        tableServices.setItems(this.serviceItems);
        var price = 0.0;
        for (var service : serviceItems) {
            price += service.getPrice();
        }
        totalPriceLb.setText(String.valueOf(price));
    }

    // --- Initialization --- //
    public void initialize() {
        setupColumns();
        setup();
        setInvoiceItems(invoiceDAO.getTableViewDTOs());
    }

    private void setupColumns() {
        plateCL.setCellValueFactory(cell -> cell.getValue().getPlateProperty());
        dateCL.setCellValueFactory(new PropertyValueFactory<>("date"));
        clientCL.setCellValueFactory(cell -> cell.getValue().getClientNameProperty());
        priceCL.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        // --- Service Table -- //
        nameCL.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceSCL.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    private void setup() {
        carPlates.addAll(carDAO.getPlates());
        plateCB.setItems(carPlates);
        serviceItems.addAll(serviceDAO.getAll());
        serviceCB.setItems(serviceItems);

    }

    // --- Reload --- //
    public void reloadInvoices() {
        setInvoiceItems(invoiceDAO.getTableViewDTOs());
        tableInvoices.refresh();
    }

    public void reloadServices() {
        if (selected == null) {
            return;
        }
        var car = carDAO.getById(selected.getPlate().getPlate());
        var invoice = invoiceDAO.getById(new InvoicePK(car, selected.getDate()));
        setServicesItems(serviceDAO.getServicesByInvoice(invoice));
    }

    public void reloadForm() {
        this.carPlates = FXCollections.observableList(carDAO.getPlates());
        serviceItems = FXCollections.observableList(serviceDAO.getAll());
        plateCB.setItems(carPlates);
        serviceCB.setItems(serviceItems);
    }

    // --- Listeners --- //
    @FXML private void onTableInvoiceClick() {
        selected = tableInvoices.getSelectionModel().getSelectedItem();

        if (selected != null) {
            deleteBtn.setDisable(false);
            reloadServices();
            dateDP.setValue(selected.getDate());
            plateCB.setValue(selected.getPlate());
        } else {
            deleteBtn.setDisable(true);

        }
    }

    @FXML private void onTableServicesClick() {
        selectedService = tableServices.getSelectionModel().getSelectedItem();
        System.out.println(selectedService);
        deleteServiceBtn.setDisable(selectedService == null);
    }

    @FXML private void onFormButtonClick(MouseEvent event) {

        LocalDate date = dateDP.getValue();
        CarPlateDTO plate = plateCB.getValue();
        boolean isValidDate = date != null && !date.isAfter(LocalDate.now());
        boolean isValidPlate = plate != null;

        if (!isValidDate || !isValidPlate ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("El formualrio no esta correcto");
            if(!isValidDate) {
                alert.setHeaderText("Asegurate de que la fecha esta correcta y no sea despues de hoy");
            }

            alert.setContentText("Porfavor revise que los campos estan adecuadamente rellenados.");
            alert.showAndWait();

            return;
        }

        if (selected == null) {
            var car = carDAO.getById(plateCB.getValue().getPlate());
            var invoicepk = new InvoicePK(car, dateDP.getValue());
            var invoice = new Invoice(invoicepk, null);
            invoiceDAO.save(invoice);
            reloadInvoices();
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se puede modificar la fecha de una factura ni la matricula");
            alert.setContentText("Por favor, elimine la factura y cree una nueva");
            alert.showAndWait();
        }
    }

    @FXML private void onAddServiceClick(MouseEvent event) {
        var cbSelection = serviceCB.getValue();

        if (cbSelection != null) {
            var car = carDAO.getById(selected.getPlate().getPlate());
            var invoice = invoiceDAO.getById(new InvoicePK(car, selected.getDate()));
            serviceDAO.addServiceToInvoice(invoice, cbSelection);
            reloadServices();
            reloadInvoices();
        }
    }

    @FXML private void onEmptyBtnClick(MouseEvent event) {
        selected = null;
        dateDP.setValue(null);
        plateCB.setValue(null);
        deleteServiceBtn.setDisable(true);
        deleteBtn.setDisable(true);
    }

    @FXML private void onDeleteInvoiceBtnClick(MouseEvent event) {

        if(selected != null) {
            var car = carDAO.getById(selected.getPlate().getPlate());
            var invoice = invoiceDAO.getById(new InvoicePK(car, selected.getDate()));
            invoiceDAO.delete(invoice);
            reloadInvoices();
            reloadServices();
            onEmptyBtnClick(null);
        }

    }

    @FXML private void onDeleteServiceBtnClick(MouseEvent event) {
        System.out.println("FAFSA");
        if (selectedService != null) {
            System.out.println("FAFSA");

            var car = carDAO.getById(selected.getPlate().getPlate());
            var invoice = invoiceDAO.getById(new InvoicePK(car, selected.getDate()));
            serviceDAO.deleteServiceFromInvoice(invoice, selectedService);
            reloadServices();
            reloadInvoices();
        }
    }

}
