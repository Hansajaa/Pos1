package Model.Impl;

import Dto.ItemDto;
import Model.ItemModel;

import java.util.List;

public class ItemModelImpl implements ItemModel {
    @Override
    public boolean saveItem(ItemDto dto) {
        return false;
    }

    @Override
    public ItemDto searchItem(String code) {
        return null;
    }

    @Override
    public boolean updateItem(ItemDto dto) {
        return false;
    }

    @Override
    public boolean deleteItem(String code) {
        return false;
    }

    @Override
    public List<ItemDto> allItems() {
        return null;
    }
}
