package Controller;

import DB.DBConnection;
import Model.Customer;
import Model.CustomerTm;
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

    public JFXButton btnBack;
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
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade","root","Hansaja@1234");
            Statement stm = connection.createStatement();
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
            connection.close();
            tblCustomer.setItems(ctm);

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteCustomer(String id) {
        String sql = "DELETE FROM customer WHERE id='"+id+"'";
        try {
            Statement stm = DBConnection.getInstance().getConnection().getStatement();
            int result = stm.executeUpdate(sql);
            if (result>0){
                new Alert(Alert.AlertType.INFORMATION,"Delete Successfully").show();
            }else{
                new Alert(Alert.AlertType.ERROR,"Something went wrong !").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        loadCustomerTable();
        /*try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade", "root", "Hansaja@1234");
            Statement stm = connection.createStatement();
            int result = stm.executeUpdate(sql);
            if (result > 0) {
                new Alert(Alert.AlertType.INFORMATION, "Successfully Deleted !").show();
                loadCustomerTable();
            }else {
                new Alert(Alert.AlertType.ERROR,"Something went wrong !");
            }
            loadCustomerTable();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }*/
    }

    public void backButtonOnAction(ActionEvent actionEvent) {
        Stage stage=(Stage) customerPane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/DashboardForm.fxml"))));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveButtonOnAction(ActionEvent actionEvent) {
        if (!(txtId.getText()==null || txtName.getText()==null || txtAddress.getText()==null || txtSalary.getText()==null)) {
            Customer c = new Customer(txtId.getText(), txtName.getText(), txtAddress.getText(), Double.parseDouble(txtSalary.getText()));
            String sql = "INSERT INTO customer VALUES('" + c.getId() + "','" + c.getName() + "','" + c.getAddress() + "','" + c.getSalary() + "')";
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade", "root", "Hansaja@1234");
                Statement stm = connection.createStatement();
                int result = stm.executeUpdate(sql);
                if (result > 0) {
                    new Alert(Alert.AlertType.INFORMATION, "Successfully Added !").show();
                    txtId.setText("");
                    txtName.setText("");
                    txtAddress.setText("");
                    txtSalary.setText("");
                }
                loadCustomerTable();
                connection.close();
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void refreshButtonOnAction(ActionEvent actionEvent) {
        loadCustomerTable();
    }
}
