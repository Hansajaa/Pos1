package dao.Impl;

import DB.DBConnection;
import Dto.OrderDto;
import dao.Impl.OrderDetailModelImpl;
import dao.OrderDetailModel;
import dao.OrderModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderModelImpl implements OrderModel {


    @Override
    public boolean orderSave(OrderDto dto) throws SQLException {
        OrderDetailModel orderDetailModel=new OrderDetailModelImpl();

        Connection connection=null;
        try{
            String sql="INSERT INTO orders VALUES(?,?,?)";
            connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1,dto.getOrderId());
            pstm.setString(2,dto.getDate());
            pstm.setString(3, dto.getCustId());

            connection.setAutoCommit(false);

            if (pstm.executeUpdate()>0){
                boolean isDetailsSaved=orderDetailModel.orderDetailSave(dto.getDetailDtoList());
                if (isDetailsSaved){
                    connection.commit();
                    return true;
                }
            }
        }catch (SQLException | ClassNotFoundException ex){
            connection.rollback();
        }finally {
            connection.setAutoCommit(true);
        }
        return false;
    }

    @Override
    public OrderDto getLastId() throws SQLException, ClassNotFoundException {
        String sql="SELECT * FROM orders ORDER BY id DESC LIMIT 1";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            return new OrderDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    null
            );
        }else {
            return null;
        }

    }

    @Override
    public List<OrderDto> allOrders() throws SQLException, ClassNotFoundException {
        List<OrderDto> dtoList=new ArrayList<>();
        String sql="SELECT * FROM Orders";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()){
            dtoList.add(
              new OrderDto(
                resultSet.getString(1),
                resultSet.getString(2),
                resultSet.getString(3),
                null
              )
            );
        }

        return dtoList;
    }
}
