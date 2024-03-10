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

    private ObservableList<Client> items;
    private Client selected;

    // --- Table --- //
    @FXML private TableView<Client> table;
    @FXML private TableColumn<Client, Integer> idCL;
    @FXML private TableColumn<Client, String> nameCL;
    @FXML private TableColumn<Client, String> surnameCL;
    @FXML private TableColumn<Client, String> emailCL;
    @FXML private TableColumn<Client, String> phoneCL;

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
    public void setItems(ObservableList<Client> items) {
        this.items = items;
        table.setItems(this.items);
    }

    public void setItems(List<Client> items) {
        this.items = FXCollections.observableList(items);
        table.setItems(this.items);
    }

    // --- Initialization --- //
    public void initialize() {
        setupColumns();
        setItems(clientDAO.getAll());
    }

    private void setupColumns() {
        idCL.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCL.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameCL.setCellValueFactory(new PropertyValueFactory<>("surname"));
        emailCL.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneCL.setCellValueFactory(new PropertyValueFactory<>("phone"));
    }

    // --- Reload --- //
    public void reload() {
        setItems(clientDAO.getAll());
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

    @FXML private void onFormButtonClick() {

        String name = nameTF.getText();
        String surname = surnameTF.getText();
        String phone = phoneTF.getText();
        String email = emailTF.getText();

        boolean isNameValid = !name.isEmpty();
        boolean isSurnameValid = !surname.isEmpty();
        boolean isPhoneValid = !phone.isEmpty();
        boolean isEmailValid = !email.isEmpty();

        if (!isNameValid || !isSurnameValid || !isPhoneValid || !isEmailValid) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("El formualrio no esta correcto");

            alert.setContentText("Porfavor revise que los campos estan rellenados.");
            alert.showAndWait();

            return;
        }

        if (selected != null) {
            selected.setId(selected.getId());
            selected.setName(name);
            selected.setSurname(surname);
            selected.setPhone(phone);
            selected.setEmail(email);
            clientDAO.update(selected);

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
