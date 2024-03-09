package com.zervladpy.tallerpaco.Controllers;

import com.zervladpy.tallerpaco.Core.DAO.ClientDAO;
import com.zervladpy.tallerpaco.Core.Entities.Client.Client;
import com.zervladpy.tallerpaco.Core.Entities.Client.ClientTableViewDTO;
import com.zervladpy.tallerpaco.Core.Utils.Managers.DependencyManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class ClientTabController {

    private ObservableList<ClientTableViewDTO> items;
    private ClientTableViewDTO selected;

    // --- Table --- //
    @FXML private TableView<ClientTableViewDTO> table;
    @FXML private TableColumn<ClientTableViewDTO, Integer> idCL;
    @FXML private TableColumn<ClientTableViewDTO, String> nameCL;
    @FXML private TableColumn<ClientTableViewDTO, String> surnameCL;
    @FXML private TableColumn<ClientTableViewDTO, String> emailCL;
    @FXML private TableColumn<ClientTableViewDTO, String> phoneCL;
    @FXML private TableColumn<ClientTableViewDTO, Integer> tCarCL;
    @FXML private TableColumn<ClientTableViewDTO, Integer> tRecipeCL;

    // --- Form --- //
    @FXML private TextField nameTF, surnameTF, emailTF, phoneTF;
    @FXML private Button formBtn;

    // --- Button Bar --- //
    @FXML private Button emptyBtn, deleteBtn;

    // --- DAOs --- //
    private final ClientDAO clientDAO;

    // --- Constructor --- //
    public ClientTabController() {
        clientDAO = DependencyManager.getInstance().get(ClientDAO.class);
        items = FXCollections.observableList(new ArrayList<>());
    }

    // --- Setters --- //
    public void setItems(ObservableList<ClientTableViewDTO> items) {
        this.items = items;
        table.setItems(this.items);
    }

    public void setItems(List<ClientTableViewDTO> items) {
        this.items = FXCollections.observableList(items);
        table.setItems(this.items);
    }

    // --- Initialization --- //
    public void initialize() {
        setupColumns();
        setItems(clientDAO.getTableViewDTOs());
    }

    private void setupColumns() {
        idCL.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCL.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameCL.setCellValueFactory(new PropertyValueFactory<>("surname"));
        emailCL.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneCL.setCellValueFactory(new PropertyValueFactory<>("phone"));
        tCarCL.setCellValueFactory(new PropertyValueFactory<>("totalCars"));
        tRecipeCL.setCellValueFactory(new PropertyValueFactory<>("totalReceipts"));
    }

    // --- Reload --- //
    public void reload() {
        setItems(clientDAO.getTableViewDTOs());
        table.refresh();
    }

    // --- Listeners --- //
    @FXML private void onTableClick(MouseEvent event) {
        selected = table.getSelectionModel().getSelectedItem();
        boolean isNotNull = selected != null;
        nameTF.setText(isNotNull ? selected.getName() : "");
        surnameTF.setText(isNotNull ? selected.getSurname() : "");
        emailTF.setText(isNotNull ? selected.getEmail() : "");
        phoneTF.setText(isNotNull ? selected.getPhone() : "");

    }

    @FXML private void onFormButtonClick(MouseEvent event) {

        String name = nameTF.getText();
        String surname = surnameTF.getText();
        String phone = phoneTF.getText();
        String email = emailTF.getText();

        if (name.isEmpty() || name.isBlank()) {
            return;
        }

        if (surname.isEmpty() || surname.isBlank()) {
            return;
        }

        if (phone.isEmpty() || phone.isBlank()) {
            return;
        }

        if (email.isEmpty() || email.isBlank()) {
            return;
        }

        if (selected != null) {
            Client client = new Client();
            client.setId(selected.getId());
            client.setName(name);
            client.setSurname(surname);
            client.setPhone(phone);
            client.setEmail(email);
            clientDAO.update(client);

        } else {
            Client newClient = new Client();
            newClient.setName(name);
            newClient.setName(name);
            newClient.setSurname(surname);
            newClient.setPhone(phone);
            newClient.setEmail(email);
            clientDAO.save(newClient);
        }

        reload();

    }

    @FXML private void onEmptyButtonClick(MouseEvent event) {
        selected = null;
        nameTF.setText("");
        surnameTF.setText("");
        emailTF.setText("");
        phoneTF.setText("");
    }

    @FXML private void onDeleteButtonClick(MouseEvent event) {
        if (selected != null) {
            clientDAO.deleteById(selected.getId());
            reload();
        }
    }
}
