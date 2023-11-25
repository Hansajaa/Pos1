package Model;

import Dto.ItemDto;

import java.sql.SQLException;
import java.util.List;

public interface ItemModel {
    boolean saveItem(ItemDto dto) throws SQLException, ClassNotFoundException;
    ItemDto searchItem(String code);
    boolean updateItem(ItemDto dto);
    boolean deleteItem(String code) throws SQLException, ClassNotFoundException;

    List<ItemDto> allItems() throws SQLException, ClassNotFoundException;
}
