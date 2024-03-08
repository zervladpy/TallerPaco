package com.zervladpy.tallerpaco.Controllers.CarBrand;

import com.zervladpy.tallerpaco.Core.DAO.CarBrandDAO;
import com.zervladpy.tallerpaco.Core.Entities.Car.CarBrand;
import com.zervladpy.tallerpaco.Core.Utils.Factories.DialogFactory;
import com.zervladpy.tallerpaco.Core.Utils.Managers.DependencyManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Window;

import java.io.IOException;

import static com.zervladpy.tallerpaco.Core.Utils.Factories.DialogFactory.DialogType.CREATE_CAR_BRAND;

public class CarBrandController {

    private final CarBrandDAO dao;

    @FXML private Button addButton;
    @FXML private TextField searchField;
    @FXML private ListView<CarBrand> listView;

    public CarBrandController() {
        dao = DependencyManager.getInstance().get(CarBrandDAO.class);
    }

    /**
     * Initialize the controller
     * */
    public void initialize() {
        // --- set items --- //
        listView.getItems().addAll(dao.getAll());
    }

    /**
     * Add a new car brand dialog
     * */
    public void launchCreateBrandDialog() {
        Window window = addButton.getScene().getWindow();
        CreateCarBrandController controller = new CreateCarBrandController();
        Dialog<ButtonType> dialog = DialogFactory.createDialog(CREATE_CAR_BRAND, window, controller);

        dialog.setTitle("Create Car Brand");

        // --- Add Button --- //
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.APPLY, ButtonType.CANCEL);
        dialog.getDialogPane().lookupButton(ButtonType.APPLY).addEventFilter(ActionEvent.ACTION, event -> {
            if (!controller.isDataValid()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Data");
                alert.setContentText("Please check the data and try again");
                alert.showAndWait();
                event.consume();
            }
        });
        dialog.showAndWait();

        if (dialog.getResult() == ButtonType.APPLY){
            if (controller.isDataValid()) {
                dao.save(controller.getData());
                listView.getItems().add(controller.getData());
            }
        }
    }

    /**
     * Filter car brands
     * */
    public void filter() {

    }
}
