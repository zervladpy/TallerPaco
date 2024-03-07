package com.zervladpy.tallerpaco;

import com.zervladpy.tallerpaco.Core.DAO.CarBrandDAO;
import com.zervladpy.tallerpaco.Core.DAO.CarDAO;
import com.zervladpy.tallerpaco.Core.DAO.CustomerDAO;
import com.zervladpy.tallerpaco.Core.DAO.IDAO;
import com.zervladpy.tallerpaco.Core.Entities.Car.Car;
import com.zervladpy.tallerpaco.Core.Entities.Car.CarBrand;
import com.zervladpy.tallerpaco.Core.Entities.Car.CarDetails;
import com.zervladpy.tallerpaco.Core.Entities.Customer.Customer;
import com.zervladpy.tallerpaco.Core.Session.SessionManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        var emf = SessionManager.getInstance();

        IDAO<CarBrand> carBrandDAO = new CarBrandDAO(emf);

        // Generate 5 car brands
        CarBrand carBrand1 = new CarBrand(0, "Toyota", "Japan", 2000, null);
        CarBrand carBrand2 = new CarBrand(0, "Ford", "USA", 1900, null);
        CarBrand carBrand3 = new CarBrand(0, "Chevrolet", "USA", 1900, null);
        CarBrand carBrand4 = new CarBrand(0, "Nissan", "Japan", 1900, null);
        CarBrand carBrand5 = new CarBrand(0, "Mazda", "Japan", 1900, null);

        carBrandDAO.saveMany(List.of(carBrand1, carBrand2, carBrand3, carBrand4, carBrand5));

        // Get all car brands
        List<CarBrand> carBrands = carBrandDAO.getAll();
        System.out.println(carBrands);

        // Create 5 cars
        Car car1 = new Car(0, new CarDetails("Red", "1234ABC", 10254), null, carBrand1);
        Car car2 = new Car(0, new CarDetails("Blue", "5678DEF", 10254), null, carBrand2);
        Car car3 = new Car(0, new CarDetails("Green", "91011GHI", 10254), null, carBrand3);
        Car car4 = new Car(0, new CarDetails("Yellow", "121314JKL", 10254), null, carBrand4);
        Car car5 = new Car(0, new CarDetails("Black", "151617MNO", 10254), null, carBrand5);

        // Save the cars
        IDAO<Car> carDAO = new CarDAO(emf);
        carDAO.saveMany(List.of(car1, car2, car3, car4, car5));

        IDAO<Customer> customerDAO = new CustomerDAO(emf);
        Customer customer1 = new Customer(0, "Paco", "Ramirez", "pr@gmail.com", "123456789", List.of(car1), null);

        customerDAO.save(customer1);

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}