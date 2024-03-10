package com.zervladpy.tallerpaco.Controllers;

import com.zervladpy.tallerpaco.Core.DAO.BrandDAO;
import com.zervladpy.tallerpaco.Core.Entities.Brand.Brand;
import com.zervladpy.tallerpaco.Core.Utils.Managers.DependencyManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class BrandTabController {
    private ObservableList<Brand> items;
    private Brand selected;

    // --- Table --- //
    @FXML private TableView<Brand> table;
    @FXML private TableColumn<Brand, Integer> idCL;
    @FXML private TableColumn<Brand, String> nameCL;
    @FXML private TableColumn<Brand, String> originCL;

    // --- Form --- //
    @FXML private TextField nameTF, originTF, yearTF, pathTF;
    @FXML private Button formButton;

    @FXML private ImageView logoPreview;

    // --- Bottom Bar --- //
    @FXML private ButtonBar buttonBar;
    @FXML private Button emptyBtn, deleteBtn, openFileChooserBtn;

    // --- DAOs --- //
    private final BrandDAO brandDAO;

    // --- Constructor --- //
    public BrandTabController() {
        brandDAO = DependencyManager.getInstance().get(BrandDAO.class);
        items = FXCollections.observableList(new ArrayList<>());
    }

    // --- Setters --- //
    public void setItems(ObservableList<Brand> items) {
        this.items = items;
        table.setItems(this.items);
    }

    public void setItems(List<Brand> items) {
        this.items = FXCollections.observableList(items);
        table.setItems(this.items);
    }

    // --- Initialization --- //
    public void initialize() {
        setupColumns();
        setItems(brandDAO.getAll());
    }

    private void setupColumns() {
        idCL.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCL.setCellValueFactory(new PropertyValueFactory<>("name"));
        originCL.setCellValueFactory(new PropertyValueFactory<>("country"));
    }

    // --- Reload --- //
    public void reload() {
        setItems(brandDAO.getAll());
        table.refresh();
    }

    // --- Listeners --- //
    @FXML private void onTableClick() throws IOException {
        selected = table.getSelectionModel().getSelectedItem();
        boolean isNotNull = selected != null;
        nameTF.setText(isNotNull ? selected.getName() : "");
        originTF.setText(isNotNull ? selected.getCountry() : "");

        if (selected.getLogo() != null && (selected.getLogo().length != 0)) {
            ByteArrayInputStream stream = new ByteArrayInputStream(selected.getLogo());
            logoPreview.setImage(new Image(stream));
        } else {
            logoPreview.setImage(null);
        }
    }

    @FXML private void onOpenFileChooserButtonClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        fileChooser.setTitle("Abrir archivo");
        Window root = table.getScene().getWindow();
        var file = fileChooser.showOpenDialog(root);

        if (file != null) {
            pathTF.setText(file.getAbsolutePath());
            logoPreview.setImage(new Image(file.toURI().toString()));
        }
    }

    @FXML private void onFormButtonClick() {

        String name = nameTF.getText();
        String origin = originTF.getText();

        boolean isValidName = !name.isEmpty();
        boolean isValidOrigin = !origin.isEmpty();

        if (!isValidOrigin && !isValidName) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Formulario no rellenado correctamente");
            alert.setContentText("Porfavor revise que los campos esten llenos");

            alert.showAndWait();
            return;
        }


        if (selected == null) {
            Brand brand = new Brand();
            brand.setName(name);
            brand.setCountry(origin);
            try {
                File file = new File(pathTF.getText());
                byte[] stream = Files.readAllBytes(file.toPath());
                brand.setLogo(stream);
                pathTF.setText("");
            } catch (IOException e) {
                e.printStackTrace();
            }
            brandDAO.save(brand);

        } else {
            selected.setName(nameTF.getText());
            selected.setCountry(originTF.getText());
            try {
                File file = new File(pathTF.getText());
                byte[] stream = Files.readAllBytes(file.toPath());
                selected.setLogo(stream);
                pathTF.setText("");
            } catch (IOException e) {
                e.printStackTrace();
            }
            brandDAO.update(selected);
        }
        reload();

    }

    @FXML private void onEmptyButtonClick() {
        selected = null;
        nameTF.setText("");
        originTF.setText("");
        pathTF.setText("");
        logoPreview.setImage(null);
    }

    @FXML private void onDeleteButtonClick() {
        if (selected != null) {
            brandDAO.delete(selected);
            reload();
        }
    }

}
