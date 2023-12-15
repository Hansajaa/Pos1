package Controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DashboardFormController {

    public JFXButton btnCustomer;
    public JFXButton btnItems;
    public JFXButton btnOrders;
    public JFXButton btnPlaceOrder;
    public AnchorPane dashboardPane;
    public Label lblTime;
    public Text lblDate;


    public void initialize(){
        runTime();
        runDate();
    }

    private void runTime() {
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.ZERO,
                actionEvent -> lblTime.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")))
        ), new KeyFrame(Duration.seconds(1)));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void runDate() {
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.ZERO,
                actionEvent -> lblDate.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd")))
        ), new KeyFrame(Duration.hours(24)));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void customerButtonOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) dashboardPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/CustomerForm.fxml"))));
        stage.setTitle("Customer Form");
        stage.show();

    }

    public void itemButtonOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) dashboardPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/ItemForm.fxml"))));
        stage.setTitle("Item Form");
        stage.show();
    }

    public void placeOrderButtonOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) dashboardPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/PlaceOrderForm.fxml"))));
        stage.setTitle("Place Order Form");
        stage.show();
    }

    public void ordersButtonOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) dashboardPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/OrdersForm.fxml"))));
        stage.show();
    }
}
