package dao.Impl;

import DB.DBConnection;
import Dto.OrderDetailDto;
import dao.OrderDetailModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailModelImpl implements OrderDetailModel {
    @Override
    public boolean orderDetailSave(List<OrderDetailDto> list) throws SQLException, ClassNotFoundException {
        boolean orderDetailsSaved= true;

        Connection connection = DBConnection.getInstance().getConnection();

        for (OrderDetailDto detailDto:list) {
            String sql="INSERT INTO OrderDetail VALUES(?,?,?,?)";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1,detailDto.getOrderId());
            pstm.setString(2,detailDto.getItemCode());
            pstm.setInt(3,detailDto.getQty());
            pstm.setDouble(4,detailDto.getUnitPrice());

            if (!(pstm.executeUpdate()>0)){
                orderDetailsSaved=false;
            }
        }

        return orderDetailsSaved;
    }

    @Override
    public List<OrderDetailDto> getItems(String value) throws SQLException, ClassNotFoundException {
        List<OrderDetailDto> items=new ArrayList<>();

        String sql=" SELECT * FROM orderdetail WHERE orderId=? ";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        if (value!=null){
            pstm.setString(1,value);
            ResultSet resultSet = pstm.executeQuery();

            while (resultSet.next()){
                items.add(
                        new OrderDetailDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getInt(3),
                            resultSet.getDouble(4)
                        )
                );
            }
        }
        return items;
    }

}
