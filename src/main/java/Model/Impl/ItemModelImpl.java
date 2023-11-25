package Model.Impl;

import DB.DBConnection;
import Dto.ItemDto;
import Model.ItemModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemModelImpl implements ItemModel {
    @Override
    public boolean saveItem(ItemDto dto) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO item VALUES(?,?,?,?)";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setString(1, dto.getCode());
        pstm.setString(2, dto.getDescription());
        pstm.setDouble(3,dto.getUnitPrice());
        pstm.setInt(4,dto.getQuantityOnHand());


        return pstm.executeUpdate()>0;
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
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM item WHERE code = '"+code+"'";
        Statement stm = DBConnection.getInstance().getConnection().createStatement();

        return stm.executeUpdate(sql)>0;

    }

    @Override
    public List<ItemDto> allItems() throws SQLException, ClassNotFoundException {
        List<ItemDto> itemList = new ArrayList<>();
        String sql="SELECT * FROM item";
        Statement stm = DBConnection.getInstance().getConnection().createStatement();
        ResultSet resultSet = stm.executeQuery(sql);
        while (resultSet.next()){
            ItemDto item=new ItemDto(resultSet.getString(1),resultSet.getString(2),resultSet.getDouble(3),resultSet.getInt(4));
            itemList.add(item);
        }

        return itemList;
    }
}
