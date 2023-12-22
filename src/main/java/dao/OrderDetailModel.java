package dao;

import Dto.OrderDetailDto;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailModel {
    boolean orderDetailSave(List<OrderDetailDto> list) throws SQLException, ClassNotFoundException;

    List<OrderDetailDto> getItems(String value) throws SQLException, ClassNotFoundException;
}
