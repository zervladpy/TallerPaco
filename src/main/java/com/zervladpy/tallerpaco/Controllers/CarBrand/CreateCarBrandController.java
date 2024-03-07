package com.zervladpy.tallerpaco.Controllers.CarBrand;

import com.zervladpy.tallerpaco.Core.Entities.Car.CarBrand;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import lombok.Getter;
import org.hibernate.Hibernate;
import org.hibernate.engine.jdbc.LobCreator;
import org.hibernate.engine.jdbc.env.internal.BlobAndClobCreator;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Blob;

@Getter
public class CreateCarBrandController {

    private final CarBrand data;

    @FXML private TextField brandTextField, countryTextField, yearTextField, fileChooserLocation;
    @FXML private Button fileChooserButton;
    public CreateCarBrandController() {
        data = new CarBrand();
    }

    public void initialize() {

        fileChooserButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select a logo");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
            );

            File file = fileChooser.showOpenDialog(fileChooserButton.getScene().getWindow());

            if (file != null) {
                String absolutePath = file.getAbsolutePath();
                fileChooserLocation.setText(absolutePath);
                data.setLogo(getByteArrayFromPath(absolutePath));
            } else {
                data.setLogo(null);
            }
        });

        yearTextField.addEventFilter(KeyEvent.KEY_TYPED, this::filterNumericInput);

        // --- Brand Name Change Listener --- //
        brandTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            data.setBrand(newValue);
        });

        // --- Country Name Change Listener --- //
        countryTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            data.setCountry(newValue);
        });

        // --- Date Change Listener --- //
        yearTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            data.setFoundationYear(Integer.parseInt(newValue));
        });

    }

    private void filterNumericInput(KeyEvent event) {
        String character = event.getCharacter();
        if (!character.matches("[0-9]")|| yearTextField.getText().length() > 3) {
            event.consume();
        }
    }

    public boolean isDataValid() {

        boolean isYearValid = yearTextField.getText().length() == 4 && Integer.parseInt(yearTextField.getText()) <= 2021 && Integer.parseInt(yearTextField.getText()) >= 1886;
        boolean isBrandValid = !brandTextField.getText().isEmpty();
        boolean isCountryValid = !countryTextField.getText().isEmpty();

        return isBrandValid && isCountryValid && isYearValid;
    }

    private byte[] getByteArrayFromPath(String path) {

        try (FileInputStream fileInputStream = new FileInputStream(path)) {
            byte[] buffer = new byte[fileInputStream.available()];
            var r  = fileInputStream.read(buffer);
            return buffer;
        } catch (Exception ignored) {


        }

        return new byte[0];
    }

}
