package dao;

import Dto.OrderDto;

import java.sql.SQLException;

public interface OrderModel {
    public boolean orderSave(OrderDto dto) throws SQLException, ClassNotFoundException;

    public OrderDto getLastId() throws SQLException, ClassNotFoundException;
}
