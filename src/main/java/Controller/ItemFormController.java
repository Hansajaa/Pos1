package Controller;

import DB.DBConnection;
import Dto.ItemDto;
import Dto.Tm.ItemTm;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ItemFormController {

    public JFXTreeTableView<ItemTm> tblItem;
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

    public void initialize() {
        colCode.setCellValueFactory(new TreeItemPropertyValueFactory<>("code"));
        colDesc.setCellValueFactory(new TreeItemPropertyValueFactory<>("description"));
        colPrice.setCellValueFactory(new TreeItemPropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new TreeItemPropertyValueFactory<>("quantityOnHand"));
        colOption.setCellValueFactory(new TreeItemPropertyValueFactory<>("btn"));
        loadItemTable();
    }

    private void loadItemTable() {

        ObservableList<ItemTm> itm = FXCollections.observableArrayList();
        String sql = "SELECT * FROM item";
        try {
            Statement stm = DBConnection.getInstance().getConnection().createStatement();
            ResultSet result = stm.executeQuery(sql);
            while(result.next()){
                JFXButton btn =new JFXButton("Delete");
                ItemTm i = new ItemTm(
                        result.getString(1),
                        result.getString(2),
                        result.getDouble(3),
                        result.getInt(4),
                        btn
                );
                btn.setOnAction(ActionEvent ->{
                    deleteItem(i.getCode());
                });
                itm.add(i);
            }
            RecursiveTreeItem treeItem = new RecursiveTreeItem<>(itm, RecursiveTreeObject::getChildren);
            tblItem.setRoot(treeItem);
            tblItem.setShowRoot(false);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteItem(String code) {
        String sql = "DELETE FROM item WHERE code = '"+code+"'";
        try {
            Statement stm = DBConnection.getInstance().getConnection().createStatement();
            int result = stm.executeUpdate(sql);
            if (result>0){
                new Alert(Alert.AlertType.INFORMATION,"Delete Successfully").show();
                loadItemTable();
            }else{
                new Alert(Alert.AlertType.ERROR,"Something went wrong !").show();
            }
        } catch (SQLException  | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void refreshButtonOnAction(javafx.event.ActionEvent actionEvent) {
        loadItemTable();
    }

    public void updateButtonOnAction(ActionEvent actionEvent) {

    }

    public void saveButtonOnAction(ActionEvent actionEvent) {
        ItemDto item=new ItemDto(
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
        loadItemTable();
    }

    public void backButtonOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage=(Stage) itemPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/DashboardForm.fxml"))));
        stage.show();
    }
}
