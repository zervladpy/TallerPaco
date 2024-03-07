package com.zervladpy.tallerpaco.Controllers.CarBrand;

import com.zervladpy.tallerpaco.Core.DAO.CarBrandDAO;
import com.zervladpy.tallerpaco.Core.DAO.IDAO;
import com.zervladpy.tallerpaco.Core.Entities.Car.CarBrand;
import com.zervladpy.tallerpaco.Core.Session.SessionManager;
import jakarta.persistence.EntityManagerFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class CarBrandController {

    private final EntityManagerFactory emf;
    private final IDAO<CarBrand> dao;

    @FXML private Button addButton;
    @FXML private TextField searchField;
    @FXML private ListView<CarBrand> listView;

    public CarBrandController() {
        this.emf = SessionManager.getInstance();
        this.dao = new CarBrandDAO(emf);
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
    public void create() {

    }

    /**
     * Filter car brands
     * */
    public void filter() {

    }
}
