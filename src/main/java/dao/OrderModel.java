package dao;

import Dto.OrderDto;

import java.sql.SQLException;
import java.util.List;

public interface OrderModel {
    public boolean orderSave(OrderDto dto) throws SQLException, ClassNotFoundException;

    public OrderDto getLastId() throws SQLException, ClassNotFoundException;

    public List<OrderDto> allOrders() throws SQLException, ClassNotFoundException;
}
