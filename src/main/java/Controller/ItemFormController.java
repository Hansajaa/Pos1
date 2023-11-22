package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class ItemFormController {

    @FXML
    private AnchorPane itemPane;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXTextField txtCode;

    @FXML
    private JFXTextField txtUnitPrice;

    @FXML
    private JFXButton btnBack;

    @FXML
    private JFXButton btnRefresh;

    @FXML
    private TableView tblCustomer;

    @FXML
    private TableColumn colCode;

    @FXML
    private TableColumn colDescription;

    @FXML
    private TableColumn colUnitPrice;

    @FXML
    private TableColumn colQuantityOnHand;

    @FXML
    private TableColumn colOption;

    @FXML
    private JFXTextArea txtDescription;

    @FXML
    private JFXTextField txtQuantityOnHand;

    @FXML
    void refreshButtonOnAction(ActionEvent event) {

    }

    public void saveButtonOnAction(javafx.event.ActionEvent actionEvent) {

    }

    public void backButtonOnAction(javafx.event.ActionEvent actionEvent) throws IOException {
        Stage stage=(Stage) itemPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/DashboardForm.fxml"))));
        stage.show();
    }

    public void refreshButtonOnAction(javafx.event.ActionEvent actionEvent) {

    }
}
