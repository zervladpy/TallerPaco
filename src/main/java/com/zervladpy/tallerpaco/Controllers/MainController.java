package com.zervladpy.tallerpaco.Controllers;

import com.zervladpy.tallerpaco.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainController {

    @FXML private TabPane tabs;

    @FXML private Tab brandTab, carTab, clientTab, receiptTab, serviceTab;
    private BrandTabController brandTabController;
    private CarTabController carTabController;
    private ClientTabController clientTabController;
    private InvoiceTabController invoiceTabController;
    private ServiceTabController serviceTabController;

    public void initialize() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("brand-view.fxml"));
        brandTab.setContent(loader.load());
        brandTabController = loader.getController();

        loader = new FXMLLoader(Main.class.getResource("car-view.fxml"));
        carTab.setContent(loader.load());
        carTabController = loader.getController();

        loader = new FXMLLoader(Main.class.getResource("client-view.fxml"));
        clientTab.setContent(loader.load());
        clientTabController = loader.getController();

        loader = new FXMLLoader(Main.class.getResource("invoice-view.fxml"));
        receiptTab.setContent(loader.load());
        invoiceTabController = loader.getController();

        loader = new FXMLLoader(Main.class.getResource("service-view.fxml"));
        serviceTab.setContent(loader.load());
        serviceTabController = loader.getController();


        tabs.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (brandTab.getText().equals(newValue.getText())) {
                System.out.println("Brand Tab");
                brandTabController.reload();
            }

            if (carTab.getText().equals(newValue.getText())) {
                System.out.println("Car Tab");
                carTabController.reload();
                carTabController.setupForm();
            }

            if (clientTab.getText().equals(newValue.getText())) {
                System.out.println("Client Tab");
                clientTabController.reload();
            }

            if (receiptTab.getText().equals(newValue.getText())) {
                System.out.println("Receipt Tab");
                invoiceTabController.reloadInvoices();
                invoiceTabController.reloadServices();
                invoiceTabController.reloadForm();
            }

            if (serviceTab.getText().equals(newValue.getText())) {
                System.out.println("Service Tab");
                serviceTabController.reload();
            }

        });
    }

}