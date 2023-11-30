package Controller;


import Dto.CustomerDto;
import Dto.ItemDto;
import Model.CustomerModel;
import Model.Impl.CustomerModelImpl;
import Model.Impl.ItemModelImpl;
import Model.ItemModel;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderFormController {
    public AnchorPane placeOrderPane;
    public JFXComboBox cmbCustomerIds;
    public JFXComboBox cmbItemCodes;
    public JFXTextField txtCustName;
    public JFXTextField txtDescription;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQty;


    CustomerModel customerModel=new CustomerModelImpl();
    ItemModel itemModel=new ItemModelImpl();

    List<CustomerDto> custDto;
    List<ItemDto> itemDto;
    public void initialize(){
        loadCustomerIds();
        loadItemCodes();
        cmbCustomerIds.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, id) -> {
            for (CustomerDto dto:custDto) {
                if (dto.getId().equals(id)){
                    txtCustName.setText(dto.getName());
                }
            }
        });

        cmbItemCodes.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, code) ->{
            for (ItemDto dto:itemDto) {
                if (dto.getCode().equals(code)){
                    txtDescription.setText(dto.getDescription());
                    txtUnitPrice.setText(Double.toString(dto.getUnitPrice()));
                }
            }
        });
    }

    private void loadCustomerIds() {
        try {
            custDto=customerModel.allCustomers();
            ObservableList<String> idList= FXCollections.observableArrayList();

            for(CustomerDto dto : custDto){
                idList.add(dto.getId());
            }

            cmbCustomerIds.setItems(idList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadItemCodes() {
        try {
            itemDto=itemModel.allItems();
            ObservableList<String> list=FXCollections.observableArrayList();
            for (ItemDto dto:itemDto){
                list.add(dto.getCode());
            }

            cmbItemCodes.setItems(list);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void backButtonOnAction(javafx.event.ActionEvent actionEvent) throws IOException {
        Stage stage=(Stage) placeOrderPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/DashboardForm.fxml"))));
        stage.show();
    }
}

