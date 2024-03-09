package com.zervladpy.tallerpaco;

import com.zervladpy.tallerpaco.Core.DAO.*;
import com.zervladpy.tallerpaco.Core.Entities.Car.Car;
import com.zervladpy.tallerpaco.Core.Entities.Brand.Brand;
import com.zervladpy.tallerpaco.Core.Entities.Car.CarDetails;
import com.zervladpy.tallerpaco.Core.Entities.Client.Client;
import com.zervladpy.tallerpaco.Core.Entities.Parts.Part;
import com.zervladpy.tallerpaco.Core.Entities.Reciep.Receipt;
import com.zervladpy.tallerpaco.Core.Session.SessionManager;
import com.zervladpy.tallerpaco.Core.Utils.Enums.ServiceType;
import com.zervladpy.tallerpaco.Core.Utils.Managers.DependencyManager;
import jakarta.persistence.EntityManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        setupDependencyManager();
        createDummyData();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 530);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private void setupDependencyManager() {
        var dependencies = DependencyManager.getInstance();

        // EntityManager
        EntityManager session = SessionManager.getInstance();
        dependencies.set(EntityManager.class, session);

        // --- CarBrandDAO --- //
        BrandDAO brandDAO = new BrandDAO(session);
        dependencies.set(BrandDAO.class, brandDAO);

        // --- CarDAO --- //
        CarDAO carDAO = new CarDAO(session);
        dependencies.set(CarDAO.class, carDAO);

        // --- CustomerDAO --- //
        ClientDAO clientDAO = new ClientDAO(session);
        dependencies.set(ClientDAO.class, clientDAO);

        // --- PartDAO --- //
        PartDAO partDAO = new PartDAO(session);
        dependencies.set(PartDAO.class, partDAO);

        // --- ReceiptDAO --- //
        ReceiptDAO receiptDAO = new ReceiptDAO(session);
        dependencies.set(ReceiptDAO.class, receiptDAO);
    }

    private void createDummyData() {

        DependencyManager dependencies = DependencyManager.getInstance();

        Brand toyota = new Brand(0, "Toyota", "Japan", 1937);
        Brand ford = new Brand(0, "Ford", "USA", 1903);
        Brand chevrolet = new Brand(0, "Chevrolet", "USA", 1911);
        Brand volkswagen = new Brand(0, "Volkswagen", "Germany", 1937);
        Brand nissan = new Brand(0, "Nissan", "Japan", 1933);
        Brand honda = new Brand(0, "Honda", "Japan", 1948);

        List<Brand> cars = new ArrayList<>(List.of(toyota, ford, chevrolet, volkswagen, nissan, honda));

        // set color in HEX
        Car car1 = new Car(0, new CarDetails( "Red", "1111ABC", 1059684), null, toyota);
        Car car2 = new Car(0, new CarDetails("Blue", "2222DEF", 1059684), null, ford);
        Car car3 = new Car(0, new CarDetails("Green", "3333GHI", 1059684), null, chevrolet);
        Car car4 = new Car(0, new CarDetails("Yellow", "4444JKL", 1059684), null, volkswagen);
        Car car5 = new Car(0, new CarDetails("Black", "5555MNO", 1059684), null, nissan);
        Car car6 = new Car(0, new CarDetails("White", "6666PQR", 1059684), null, honda);

        List<Car> carsList = List.of(car1, car2, car3, car4, car5, car6);

        Client client1 = new Client(0, "Ramon", "Gimenez", "12345678A", "Calle Falsa 123", List.of(car1), null);
        Client client2 = new Client(0, "Paco", "Gimenez", "12345678B", "Calle Falsa 124", List.of(car2), null);
        Client client3 = new Client(0, "Pepe", "Gimenez", "12345678C", "Calle Falsa 125", List.of(car3), null);
        Client client4 = new Client(0, "Luis", "Gimenez", "12345678D", "Calle Falsa 126", List.of(car4), null);
        Client client5 = new Client(0, "Juan", "Gimenez", "12345678E", "Calle Falsa 127", List.of(car5), null);
        Client client6 = new Client(0, "Pedro", "Gimenez", "12345678F", "Calle Falsa 128", List.of(car6), null);

        List<Client> clients = List.of(client1, client2, client3, client4, client5, client6);

        // update cars
        car1.setClient(client1);
        car2.setClient(client2);
        car3.setClient(client3);
        car4.setClient(client4);
        car5.setClient(client5);
        car6.setClient(client6);

        Part p1 = new Part(0, "123456", "Volante", 10.25, 10);
        Part p2 = new Part(0, "105454", "Rueda", 200, 5);
        Part p3 = new Part(0, "164897", "Foco Delantero", 50.99, 1);
        Part p4 = new Part(0, "171798", "Intermitente", 10.25, 0);
        Part p5 = new Part(0, "564651", "Bombilla", 10.25, 20);

        List<Part> parts = List.of(p1, p2, p3, p4, p5);

        Receipt r1 = new Receipt(0, LocalDate.now(), client1, ServiceType.MAINTENANCE, null, 100.0);
        Receipt r2 = new Receipt(0, LocalDate.now(), client5, ServiceType.REPAIR, List.of(p1, p2), 100.0);

        List<Receipt> receipts = List.of(r1, r2);

        // --- Save --- //

        dependencies.get(BrandDAO.class).saveMany(cars);
        dependencies.get(CarDAO.class).saveMany(carsList);
        dependencies.get(ClientDAO.class).saveMany(clients);
        dependencies.get(PartDAO.class).saveMany(parts);
        dependencies.get(ReceiptDAO.class).saveMany(receipts);

    }



}