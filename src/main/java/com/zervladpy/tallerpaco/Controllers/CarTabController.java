package com.zervladpy.tallerpaco.Controllers;

import com.zervladpy.tallerpaco.Core.DAO.BrandDAO;
import com.zervladpy.tallerpaco.Core.DAO.CarDAO;
import com.zervladpy.tallerpaco.Core.DAO.ClientDAO;
import com.zervladpy.tallerpaco.Core.Entities.Brand.Brand;
import com.zervladpy.tallerpaco.Core.Entities.Brand.BrandNameDTO;
import com.zervladpy.tallerpaco.Core.Entities.Car.Car;
import com.zervladpy.tallerpaco.Core.Entities.Car.CarDetails;
import com.zervladpy.tallerpaco.Core.Entities.Car.CarTableViewDTO;
import com.zervladpy.tallerpaco.Core.Entities.Client.Client;
import com.zervladpy.tallerpaco.Core.Entities.Client.ClientNameDTO;
import com.zervladpy.tallerpaco.Core.Utils.Managers.DependencyManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.w3c.dom.Text;

import java.util.List;

public class CarTabController  {
    private ObservableList<CarTableViewDTO> items;
    private CarTableViewDTO selected;

    private final ClientNameDTO emptyClientNameDTO;
    private final BrandNameDTO emptyBrandNameDTO;

    // --- Table --- //
    @FXML private TableView<CarTableViewDTO> table;
    @FXML private TableColumn<CarTableViewDTO, String> plateCL;
    @FXML private TableColumn<CarTableViewDTO, String> mileageCL;
    @FXML private TableColumn<CarTableViewDTO, String> colorCL;
    @FXML private TableColumn<CarTableViewDTO, String> clientCL;
    @FXML private TableColumn<CarTableViewDTO, String> brandCL;

    // --- Form --- //
    @FXML private TextField plateTF, mileageTF;
    @FXML private ColorPicker colorCP;
    @FXML private ComboBox<ClientNameDTO> clientCB;
    @FXML private ComboBox<BrandNameDTO> brandCB;
    @FXML private Button formBtn;

    // --- Button Bar --- //
    @FXML private Button emptyBtn, deleteBtn;

    // --- DAOs --- //
    private final CarDAO carDAO;
    private final ClientDAO clientDAO;
    private final BrandDAO brandDAO;

    // --- Constructor --- //
    public CarTabController() {
        carDAO = DependencyManager.getInstance().get(CarDAO.class);
        clientDAO = DependencyManager.getInstance().get(ClientDAO.class);
        brandDAO = DependencyManager.getInstance().get(BrandDAO.class);
        emptyBrandNameDTO = new BrandNameDTO();
        emptyClientNameDTO = new ClientNameDTO();
    }

    // --- Setters --- //
    public void setItems(ObservableList<CarTableViewDTO> items) {
        this.items = items;
        table.setItems(this.items);
    }

    public void setItems(List<CarTableViewDTO> items) {
        this.items = FXCollections.observableList(items);
        table.setItems(this.items);
    }

    // --- Initialization --- //
    public void initialize() {
        setupColumns();
        setupForm();
        System.out.println(carDAO.getTableViewDTOs());
        setItems(carDAO.getTableViewDTOs());
    }

    private void setupColumns() {
        plateCL.setCellValueFactory(new PropertyValueFactory<>("plate"));
        mileageCL.setCellValueFactory(new PropertyValueFactory<>("mileage"));
        colorCL.setCellValueFactory(new PropertyValueFactory<>("color"));
        clientCL.setCellValueFactory(cellData -> cellData.getValue().clientNameProperty());
        brandCL.setCellValueFactory(cellData -> cellData.getValue().brandNameProperty());
    }

    public void setupForm() {
        clientCB.setItems(FXCollections.observableList(clientDAO.getClientNameDTOs()));
        brandCB.setItems(FXCollections.observableList(brandDAO.getBrandNameDTOs()));

        clientCB.setOnInputMethodTextChanged(event -> {
            System.out.println("clientCB");
        });



        clientCB.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(ClientNameDTO item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || item.getFullName() == null) {
                    setText("");
                } else {
                    setText(item.getFullName());
                }
            }


        });

        brandCB.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(BrandNameDTO item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || item.getName() == null) {
                    setText("");
                } else {
                    setText(item.getName());
                }
            }
        });
    }

    // --- Reload --- //
    public void reload() {
        setItems(carDAO.getTableViewDTOs());
        table.refresh();
    }

    public void reloadForm() {
        clientCB.setItems(FXCollections.observableList(clientDAO.getClientNameDTOs()));
        brandCB.setItems(FXCollections.observableList(brandDAO.getBrandNameDTOs()));
    }

    // --- Listeners --- //
    @FXML private void onTableClick(MouseEvent event) {
        selected = table.getSelectionModel().getSelectedItem();
        boolean isNotNull = selected != null;
        if (isNotNull) {
            plateTF.setText(selected.getPlate());
            mileageTF.setText(String.valueOf(selected.getMileage()));
            colorCP.setValue(Color.valueOf(selected.getColor()));
            clientCB.setValue(selected.getClient());
            brandCB.setValue(selected.getBrand());
        } else {
            plateTF.setText("");
            mileageTF.setText("");
            colorCP.setValue(Color.WHITE);
            clientCB.setValue(emptyClientNameDTO);
            brandCB.setValue(emptyBrandNameDTO);
        }
    }

    @FXML private void onFormButtonClick(MouseEvent event) {
        String plate = plateTF.getText();
        String mileage = mileageTF.getText();
        String color = colorCP.getValue().toString().toUpperCase();
        System.out.println(clientCB.getValue());
        System.out.println(brandCB.getValue());
        ClientNameDTO clientDTO = clientCB.getValue();
        BrandNameDTO brandDTO = brandCB.getValue();

        boolean isPlateValid = plate.isEmpty();
        boolean isMileageValid = false;
        boolean isNameValid = clientCB.getValue() != null;
        boolean isBrandValid = brandCB.getValue() != null;

        if (!mileage.isEmpty()) {
            try {
                Integer.parseInt(mileage);
                isMileageValid = true;
            } catch (NumberFormatException ignored) {
            }
        }

        if (!isBrandValid || !isMileageValid || !isPlateValid || !isNameValid) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("El formualrio no esta correcto");

            alert.setContentText("Porfavor revise que los campos estan rellenados.");
            alert.showAndWait();

            return;
        }

        if (color.isBlank() || color.isEmpty()) {
            color = Color.WHITE.toString().toUpperCase();
        }

        System.out.println("DSADA");

        Car car = new Car();
        car.setDetails(new CarDetails());
        car.setPlate(plate);
        car.setMileage(Integer.parseInt(mileage));
        car.getDetails().setColor(color);
        Client client = clientDAO.getById(clientDTO.getId());
        car.setClient(client);
        Brand brand = brandDAO.getById(brandDTO.getId());
        car.getDetails().setBrand(brand);
        if (selected != null) {
            carDAO.update(car);
        } else {
            carDAO.save(car);
        }
        onEmptyButtonClick();
        reload();

    }

    @FXML private void onEmptyButtonClick() {
        selected = null;
        plateTF.setText("");
        mileageTF.setText("");
        colorCP.setValue(Color.WHITE);
        clientCB.setValue(emptyClientNameDTO);
        brandCB.setValue(emptyBrandNameDTO);
    }

    @FXML private void onDeleteButtonClick() {
        if (selected != null) {
            Car car = carDAO.getById(selected.getPlate());
            System.out.println(car);
            carDAO.delete(car);
            reload();
        }
    }
}
