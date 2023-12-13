package dao.Impl;

import DB.DBConnection;
import Dto.CustomerDto;
import dao.CustomerModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerModelImpl implements CustomerModel {
    @Override
    public boolean saveCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO customer VALUES(?,?,?,?)";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setString(1,dto.getId());
        pstm.setString(2,dto.getName());
        pstm.setString(3, dto.getAddress());
        pstm.setDouble(4,dto.getSalary());

        return pstm.executeUpdate()>0;
    }

    @Override
    public CustomerDto searchCustomer(String id) {
        return null;
    }

    @Override
    public boolean updateCustomer(CustomerDto dto) {
        return false;
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM customer WHERE id='"+id+"'";
        Statement stm = DBConnection.getInstance().getConnection().createStatement();

        return stm.executeUpdate(sql)>0;
    }

    @Override
    public List<CustomerDto> allCustomers() throws SQLException, ClassNotFoundException {
        List<CustomerDto> customerDtoList=new ArrayList<>();
        String sql="SELECT * FROM Customer";
        Statement stm = DBConnection.getInstance().getConnection().createStatement();
        ResultSet resultSet = stm.executeQuery(sql);

        while (resultSet.next()){
            CustomerDto dto=new CustomerDto(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3), resultSet.getDouble(4));
            customerDtoList.add(dto);
        }

        return customerDtoList;
    }
}
