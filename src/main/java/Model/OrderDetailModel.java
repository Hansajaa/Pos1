package Model;

import Dto.OrderDetailDto;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailModel {
    public boolean orderDetailSave(List<OrderDetailDto> list) throws SQLException, ClassNotFoundException;
}
