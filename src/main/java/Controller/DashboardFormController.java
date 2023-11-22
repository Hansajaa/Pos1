package Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardFormController {

    public JFXButton btnCustomer;
    public JFXButton btnItems;
    public JFXButton btnOrders;
    public JFXButton btnPlaceOrder;
    public AnchorPane dashboardPane;


    public void customerButtonOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) dashboardPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/CustomerForm.fxml"))));
        stage.show();

    }

    public void itemButtonOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage=(Stage)dashboardPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/ItemForm.fxml"))));
        stage.show();
    }
}
