package Controller;


import Dto.CustomerDto;
import Dto.ItemDto;
import Dto.Tm.OrderTm;
import Model.CustomerModel;
import Model.Impl.CustomerModelImpl;
import Model.Impl.ItemModelImpl;
import Model.ItemModel;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
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
    public JFXTreeTableView<OrderTm> tblOrder;
    public TreeTableColumn colCode;
    public TreeTableColumn colDesc;
    public TreeTableColumn colQty;
    public TreeTableColumn colAmount;
    public TreeTableColumn colOption;
    public Label lblTotal;
    private double tot;
    CustomerModel customerModel=new CustomerModelImpl();
    ItemModel itemModel=new ItemModelImpl();

    List<CustomerDto> custDto;
    List<ItemDto> itemDto;

    ObservableList<OrderTm> orderTmList=FXCollections.observableArrayList();

    public void initialize(){
        colCode.setCellValueFactory(new TreeItemPropertyValueFactory<>("code"));
        colDesc.setCellValueFactory(new TreeItemPropertyValueFactory<>("desc"));
        colQty.setCellValueFactory(new TreeItemPropertyValueFactory<>("qty"));
        colAmount.setCellValueFactory(new TreeItemPropertyValueFactory<>("amount"));
        colOption.setCellValueFactory(new TreeItemPropertyValueFactory<>("btn"));

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

    public void addToCartButtonOnAction(ActionEvent actionEvent) {
        double amount=(Integer.parseInt(txtQty.getText()))*(Double.parseDouble(txtUnitPrice.getText()));
        JFXButton btn=new JFXButton("Delete");
        OrderTm tm=new OrderTm(
                cmbItemCodes.getValue().toString(),
                txtDescription.getText(),
                Integer.parseInt(txtQty.getText()),
                amount,
                btn
        );

        btn.setOnAction(actionEvent1 -> {
            orderTmList.remove(tm);
            tot-=tm.getAmount();
            tblOrder.refresh();
            lblTotal.setText(String.format("%.2f",tot));
        });
        boolean isExist=false;
        for (OrderTm orderTm:orderTmList) {
            if (orderTm.getCode().equals(tm.getCode())){
                orderTm.setQty(orderTm.getQty()+tm.getQty());
                orderTm.setAmount(orderTm.getAmount()+tm.getAmount());
                tot+=tm.getAmount();
                isExist=true;
            }
        }

        if (!isExist){
            orderTmList.add(tm);
            tot+=tm.getAmount();
        }

        RecursiveTreeItem<OrderTm> treeItem=new RecursiveTreeItem<OrderTm>(orderTmList, RecursiveTreeObject::getChildren);
        tblOrder.setRoot(treeItem);
        tblOrder.setShowRoot(false);


        lblTotal.setText(String.format("%.2f",tot));
    }

}

