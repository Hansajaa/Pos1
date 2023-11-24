package Model;

import Dto.ItemDto;

import java.util.List;

public interface ItemModel {
    boolean saveItem(ItemDto dto);
    ItemDto searchItem(String code);
    boolean updateItem(ItemDto dto);
    boolean deleteItem(String code);

    List<ItemDto> allItems();
}
