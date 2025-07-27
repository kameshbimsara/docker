package lk.acpt.demo_afsd_two.service;

import lk.acpt.demo_afsd_two.dto.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    CustomerDto saveCustomer(CustomerDto customerDto);
    Boolean deleteCustomer(Integer id);
    CustomerDto getCustomerById(Integer id);
    CustomerDto getCustomerByEmail(String email);
    CustomerDto updateCustomer(CustomerDto customerDto);
    List<CustomerDto> getAllCustomers();
}
