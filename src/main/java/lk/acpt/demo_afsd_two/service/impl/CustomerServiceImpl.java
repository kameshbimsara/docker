package lk.acpt.demo_afsd_two.service.impl;

import lk.acpt.demo_afsd_two.dto.CustomerDto;
import lk.acpt.demo_afsd_two.entity.Customer;
import lk.acpt.demo_afsd_two.repo.CustomerRepo;
import lk.acpt.demo_afsd_two.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;

    @Autowired
    public CustomerServiceImpl(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    public CustomerDto saveCustomer(CustomerDto customerDto) {
        Customer customer = customerRepo.save(new Customer(null, customerDto.getName(), customerDto.getSalary(),
                customerDto.getEmail()));
        return new CustomerDto(customer.getId(), customer.getName(), customer.getSalary(), customer.getEmail());
    }

    @Override
    public Boolean deleteCustomer(Integer id) {
        if (customerRepo.existsById(id)) {
            customerRepo.deleteById(id);
        } else {
            return false;
        }
        return !customerRepo.existsById(id);
    }

    @Override
    public CustomerDto getCustomerById(Integer id) {
        Optional<Customer> customer = customerRepo.findById(id);
        if (customer.isPresent()) {
            Customer cust = customer.get();
            return new CustomerDto(
                    cust.getId(),
                    cust.getName(),
                    cust.getSalary(),
                    cust.getEmail());
        }
        return null;
    }

    @Override
    public CustomerDto getCustomerByEmail(String email) {
        Optional<Customer> customer = customerRepo.findByEmail(email);
        if (customer.isPresent()) {
            Customer cust = customer.get();
            return new CustomerDto(
                    cust.getId(),
                    cust.getName(),
                    cust.getSalary(),
                    cust.getEmail());
        }
        return null;
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto) {
        Optional<Customer> byId = customerRepo.findById(customerDto.getId());
        if (byId.isPresent()) {
            Customer customer = byId.get();
            customer.setName(customerDto.getName());
            customer.setSalary(customerDto.getSalary());
            customer.setEmail(customerDto.getEmail());
            Customer saved = customerRepo.save(customer);
            return new CustomerDto(
                    saved.getId(),
                    saved.getName(),
                    saved.getSalary(),
                    saved.getEmail());
        }
        return null;
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        return customerRepo.findAll()
                .stream()
                .map(customer -> new CustomerDto(
                        customer.getId(),
                        customer.getName(),
                        customer.getSalary(),
                        customer.getEmail()))
                .toList();
    }
}
