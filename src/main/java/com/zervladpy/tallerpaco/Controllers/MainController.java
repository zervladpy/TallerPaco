package com.zervladpy.tallerpaco.Controllers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

public class MainController {

    public BorderPane carTabC;
    @FXML private TabPane tabs;

    @FXML private Tab brandTab, carTab, clientTab, receiptTab, partTab;
    @FXML private BrandTabController brandTabController;
    @FXML private CarTabController carTabController;
    @FXML private ClientTabController clientTabController;
    @FXML private ReceiptTabController receiptTabController;
    @FXML private PartTabController partTabController;

}