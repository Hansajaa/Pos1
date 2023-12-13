package Controller;

import Dto.CustomerDto;
import Dto.Tm.CustomerTm;
import dao.CustomerModel;
import dao.Impl.CustomerModelImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
import java.util.List;

public class CustomerFormController {
    public AnchorPane customerPane;
    public JFXButton btnSave;
    public JFXButton btnUpdate;
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
    public JFXTextField txtSerach;
    ObservableList<CustomerTm> ctm = FXCollections.observableArrayList();
    CustomerModel customerModel=new CustomerModelImpl();
    public void initialize(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
        loadCustomerTable();
    }
    private void loadCustomerTable() {
        try {
            List<CustomerDto> dtoList = customerModel.allCustomers();
            for(CustomerDto dto:dtoList){
                Button btn =new Button("Delete");
                CustomerTm c = new CustomerTm(
                        dto.getId(),
                        dto.getName(),
                        dto.getAddress(),
                        dto.getSalary(),
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
        try {
            boolean isDelete = customerModel.deleteCustomer(id);
            if (isDelete){
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

        try{
            CustomerDto c = new CustomerDto(txtId.getText(), txtName.getText(), txtAddress.getText(), Double.parseDouble(txtSalary.getText()));

            try {
                boolean isSaved = customerModel.saveCustomer(c);

                if (isSaved){
                    loadCustomerTable();
                    new Alert(Alert.AlertType.INFORMATION,"Customer Saved").show();
                    txtId.clear();
                    txtName.clear();
                    txtAddress.clear();
                    txtSalary.clear();
                }

            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }catch (RuntimeException e){
            new Alert(Alert.AlertType.ERROR,"Enter All Details").show();
        }

    }

    public void backButtonOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage=(Stage) customerPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/DashboardForm.fxml"))));
        stage.show();
    }
}
