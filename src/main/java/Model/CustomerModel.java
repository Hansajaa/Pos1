package Model;

import Dto.CustomerDto;
import Dto.ItemDto;

import java.util.List;

public interface CustomerModel {
    boolean saveCustomer(CustomerDto dto);
    CustomerDto searchCustomer(String id);
    boolean updateCustomer(CustomerDto dto);
    boolean deleteCustomer(String id);

    List<CustomerDto> allCustomers();
}
