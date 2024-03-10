package com.zervladpy.tallerpaco;

import com.zervladpy.tallerpaco.Core.DAO.*;
import com.zervladpy.tallerpaco.Core.Entities.Brand.Brand;
import com.zervladpy.tallerpaco.Core.Entities.Car.Car;
import com.zervladpy.tallerpaco.Core.Entities.Car.CarDetails;
import com.zervladpy.tallerpaco.Core.Entities.Client.Client;
import com.zervladpy.tallerpaco.Core.Entities.Invoice.Invoice;
import com.zervladpy.tallerpaco.Core.Entities.Invoice.InvoicePK;
import com.zervladpy.tallerpaco.Core.Entities.Service.Service;
import com.zervladpy.tallerpaco.Core.Session.SessionManager;
import com.zervladpy.tallerpaco.Core.Utils.Managers.DependencyManager;
import jakarta.persistence.EntityManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        setupDependencyManager();
        // generateDummyData();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 530);
        stage.setTitle("Taller Paco");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        DependencyManager.getInstance().get(EntityManager.class).close();
        super.stop();
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
        ServiceDAO serviceDAO = new ServiceDAO(session);
        dependencies.set(ServiceDAO.class, serviceDAO);

        // --- ReceiptDAO --- //
        InvoiceDAO invoiceDAO = new InvoiceDAO(session);
        dependencies.set(InvoiceDAO.class, invoiceDAO);
    }

    private void generateDummyData() {
        // Clients
        Client client1 = new Client(0, "Carlos", "Gonzalez", "carlos.gonzalez@example.com", "555-123-456");
        Client client2 = new Client(0, "Maria", "Lopez", "maria.lopez@example.com", "555-123-457");
        Client client3 = new Client(0, "Juan", "Martinez", "juan.martinez@example.com", "555-123-458");
        Client client4 = new Client(0, "Ana", "Garcia", "ana.garcia@example.com", "555-123-459");
        Client client5 = new Client(0, "Jose", "Rodriguez", "jose.rodriguez@example.com", "555-123-460");
        Client client6 = new Client(0, "Marta", "Fernandez", "marta.fernandez@example.com", "555-123-461");
        Client client7 = new Client(0, "Francisco", "Torres", "francisco.torres@example.com", "555-123-462");
        Client client8 = new Client(0, "Carmen", "Sanchez", "carmen.sanchez@example.com", "555-123-463");
        Client client9 = new Client(0, "Ricardo", "Castro", "ricardo.castro@example.com", "555-123-464");
        Client client10 = new Client(0, "Isabel", "Gomez", "isabel.gomez@example.com", "555-123-465");
        Client client11 = new Client(0, "Antonio", "Ruiz", "antonio.ruiz@example.com", "555-123-466");
        Client client12 = new Client(0, "Patricia", "Morales", "patricia.morales@example.com", "555-123-467");
        Client client13 = new Client(0, "Manuel", "Ortega", "manuel.ortega@example.com", "555-123-468");
        Client client14 = new Client(0, "Laura", "Guerrero", "laura.guerrero@example.com", "555-123-469");
        Client client15 = new Client(0, "Luis", "Peña", "luis.peña@example.com", "555-123-470");
        Client client16 = new Client(0, "Sofia", "Aguilar", "sofia.aguilar@example.com", "555-123-471");
        Client client17 = new Client(0, "Alberto", "Moreno", "alberto.moreno@example.com", "555-123-472");
        Client client18 = new Client(0, "Teresa", "Mendoza", "teresa.mendoza@example.com", "555-123-473");
        Client client19 = new Client(0, "Javier", "Reyes", "javier.reyes@example.com", "555-123-474");
        Client client20 = new Client(0, "Susana", "Guerra", "susana.guerra@example.com", "555-123-475");

        List<Client> clients = List.of(client1, client2, client3, client4, client5, client6, client7, client8, client9, client10, client11, client12, client13, client14, client15, client16, client17, client18, client19, client20);

        // Brands
        Brand brand1 = new Brand(0, "Toyota", "Japón", null);
        Brand brand2 = new Brand(0, "Volkswagen", "Alemania", null);
        Brand brand3 = new Brand(0, "Ford", "Estados Unidos", null);
        Brand brand4 = new Brand(0, "Hyundai", "Corea del Sur", null);
        Brand brand5 = new Brand(0, "Chevrolet", "Estados Unidos", null);
        Brand brand6 = new Brand(0, "Nissan", "Japón", null);
        Brand brand7 = new Brand(0, "Honda", "Japón", null);
        Brand brand8 = new Brand(0, "Peugeot", "Francia", null);
        Brand brand9 = new Brand(0, "Renault", "Francia", null);
        Brand brand10 = new Brand(0, "Mercedes-Benz", "Alemania", null);

        List<Brand> brands = List.of(brand1, brand2, brand3, brand4, brand5, brand6, brand7, brand8, brand9, brand10);


        Service service1 = new Service(0, "Cambio de aceite", "Quitar el aceite antiguo y poner nuevo", 10, null);
        Service service2 = new Service(0, "Cambio de frenos", "Reemplazar las pastillas de freno", 20, null);
        Service service3 = new Service(0, "Cambio de neumáticos", "Reemplazar los neumáticos viejos por nuevos", 30, null);
        Service service4 = new Service(0, "Alineación", "Alinear las ruedas del vehículo", 15, null);
        Service service5 = new Service(0, "Inspección de seguridad", "Realizar una inspección completa de seguridad", 50, null);
        Service service6 = new Service(0, "Cambio de filtro de aire", "Reemplazar el filtro de aire del motor", 10, null);
        Service service7 = new Service(0, "Cambio de luces", "Reemplazar las luces quemadas", 5, null);
        Service service8 = new Service(0, "Cambio de batería", "Reemplazar la batería vieja por una nueva", 25, null);
        Service service9 = new Service(0, "Cambio de bujías", "Reemplazar las bujías viejas por nuevas", 15, null);
        Service service10 = new Service(0, "Cambio de líquido de frenos", "Reemplazar el líquido de frenos viejo por nuevo", 10, null);

        List<Service> services = List.of(service1, service2, service3, service4, service5, service6, service7, service8, service9, service10);
        // Save
        ClientDAO clientDAO = DependencyManager.getInstance().get(ClientDAO.class);
        BrandDAO brandDAO = DependencyManager.getInstance().get(BrandDAO.class);
        ServiceDAO serviceDAO = DependencyManager.getInstance().get(ServiceDAO.class);

        clientDAO.saveMany(clients);
        brandDAO.saveMany(brands);
        serviceDAO.saveMany(services);

    }

}