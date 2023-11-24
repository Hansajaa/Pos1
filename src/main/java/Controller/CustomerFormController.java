package Controller;

import DB.DBConnection;
import Dto.CustomerDto;
import Dto.Tm.CustomerTm;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
public class CustomerFormController {
    public AnchorPane customerPane;
    public JFXButton btnSave;
    public JFXButton btnUpdate;
    public JFXButton btnRefresh;
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtSalary;
    public TableView<CustomerTm> tblCustomer;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colSalary;
    public TableColumn colOption;


    public void initialize(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
        loadCustomerTable();
    }
    private void loadCustomerTable() {
        ObservableList<CustomerTm> ctm = FXCollections.observableArrayList();
        String sql = "SELECT * FROM customer";
        try {
            Statement stm = DBConnection.getInstance().getConnection().createStatement();
            ResultSet result = stm.executeQuery(sql);
            while(result.next()){
                Button btn =new Button("Delete");
                CustomerTm c = new CustomerTm(
                        result.getString(1),
                        result.getString(2),
                        result.getString(3),
                        result.getDouble(4),
                        btn
                );
                btn.setOnAction(ActionEvent ->{
                    deleteCustomer(c.getId());
                });
                ctm.add(c);
            }
            tblCustomer.setItems(ctm);

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteCustomer(String id) {
        String sql = "DELETE FROM customer WHERE id='"+id+"'";
        try {
            Statement stm = DBConnection.getInstance().getConnection().createStatement();
            int result = stm.executeUpdate(sql);
            if (result>0){
                new Alert(Alert.AlertType.INFORMATION,"Delete Successfully").show();
            }else{
                new Alert(Alert.AlertType.ERROR,"Something went wrong !").show();
            }
        } catch (SQLException  | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        loadCustomerTable();
    }

    public void saveButtonOnAction(ActionEvent actionEvent) {
        if (!(txtId.getText()==null || txtName.getText()==null || txtAddress.getText()==null || txtSalary.getText()==null)) {

            CustomerDto c = new CustomerDto(txtId.getText(), txtName.getText(), txtAddress.getText(), Double.parseDouble(txtSalary.getText()));
            String sql = "INSERT INTO customer VALUES(?,?,?,?)";

            try {
                PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
                pstm.setString(1,c.getId());
                pstm.setString(2,c.getName());
                pstm.setString(3,c.getAddress());
                pstm.setDouble(4,c.getSalary());
                int result = pstm.executeUpdate();
                if (result > 0) {
                    new Alert(Alert.AlertType.INFORMATION, "Successfully Added !").show();
                    txtId.clear();
                    txtName.clear();
                    txtAddress.clear();
                    txtSalary.clear();
                }
                loadCustomerTable();
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void refreshButtonOnAction(ActionEvent actionEvent) {
        loadCustomerTable();
    }

    public void backButtonOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage=(Stage) customerPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/DashboardForm.fxml"))));
        stage.show();
    }
}
