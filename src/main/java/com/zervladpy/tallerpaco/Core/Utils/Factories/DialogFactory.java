package com.zervladpy.tallerpaco.Core.Utils.Factories;

import com.zervladpy.tallerpaco.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Dialog;
import javafx.stage.Window;

import java.io.IOException;

public class DialogFactory {

    /**
     * Private constructor for no instance creation
     * */
    private DialogFactory() {
    }

    public enum DialogType{
        CREATE_CAR_BRAND("/com/zervladpy/tallerpaco/carbrand/brand-dialog.fxml"),
        CREATE_CAR("/com/zervladpy/tallerpaco/car/car-dialog.fxml"),
        CREATE_CUSTOMER("/com/zervladpy/tallerpaco/customer/customer-dialog.fxml"),
        CREATE_PART("/com/zervladpy/tallerpaco/part/part-dialog.fxml"),
        CREATE_RECEIPT("/com/zervladpy/tallerpaco/receipt/receipt-dialog.fxml");

        final String path;

        DialogType(String path) {
            this.path = path;
        }
    }

    public static <R, C> Dialog<R> createDialog(DialogType type, Window root, C controller) {

        FXMLLoader loader = new FXMLLoader(Main.class.getResource(type.path));
        loader.setController(controller);
        Dialog<R> dialog = new Dialog<>();

        try {
            dialog.getDialogPane().setContent(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        dialog.initOwner(root);

        return dialog;
    }

}
