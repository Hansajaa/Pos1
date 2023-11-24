package Controller;

import DB.DBConnection;
import Dto.Item;
import Dto.Tm.ItemTm;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ItemFormController {

    public JFXTreeTableView tblItem;
    @FXML
    private BorderPane itemPane;

    @FXML
    private JFXTextField txtCode;

    @FXML
    private JFXTextField txtDesc;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private TreeTableColumn colCode;

    @FXML
    private TreeTableColumn colDesc;

    @FXML
    private TreeTableColumn colPrice;

    @FXML
    private TreeTableColumn colQty;

    @FXML
    private TreeTableColumn colOption;


    public void refreshButtonOnAction(javafx.event.ActionEvent actionEvent) {

    }

    public void updateButtonOnAction(ActionEvent actionEvent) {

    }

    public void saveButtonOnAction(ActionEvent actionEvent) {
        Item item=new Item(
                txtCode.getText(),
                txtDesc.getText(),
                Double.parseDouble(txtPrice.getText()),
                Integer.parseInt(txtQty.getText())
        );
        String sql = "INSERT INTO item VALUES(?,?,?,?)";
        try {
            PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
            pstm.setString(1,item.getCode());
            pstm.setString(2,item.getDescription());
            pstm.setDouble(3,item.getUnitPrice());
            pstm.setInt(4,item.getQuantityOnHand());
            int result = pstm.executeUpdate();
            if (result>0){
                new Alert(Alert.AlertType.INFORMATION,"Item Added Successfully").show();
                txtCode.setText("");
                txtDesc.setText("");
                txtPrice.setText("");
                txtQty.setText("");
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void backButtonOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage=(Stage) itemPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/DashboardForm.fxml"))));
        stage.show();
    }
}
