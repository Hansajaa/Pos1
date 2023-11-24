package Model.Impl;

import Dto.CustomerDto;
import Model.CustomerModel;

import java.util.List;

public class CustomerModelImpl implements CustomerModel {
    @Override
    public boolean saveCustomer(CustomerDto dto) {
        return false;
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
    public boolean deleteCustomer(String id) {
        return false;
    }

    @Override
    public List<CustomerDto> allCustomers() {
        return null;
    }
}
