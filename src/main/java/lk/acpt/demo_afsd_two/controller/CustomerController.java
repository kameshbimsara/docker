package lk.acpt.demo_afsd_two.controller;

import lk.acpt.demo_afsd_two.dto.CustomerDto;
import lk.acpt.demo_afsd_two.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RequestMapping("/api/v1/customers")
@RestController
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerDto> saveCustomer(@RequestBody CustomerDto customerDto) {
        CustomerDto response = customerService.saveCustomer(customerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCustomer(@PathVariable Integer id) {
        Boolean response = customerService.deleteCustomer(id);

        return new ResponseEntity<>(response, response? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomer() {
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCustomer(@PathVariable Integer id, @RequestBody CustomerDto customerDto) {
        customerDto.setId(id);
        CustomerDto updatedCustomer = customerService.updateCustomer(customerDto);
        if (updatedCustomer != null) {
            return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
        } else {
            HashMap<String, Object> response = new HashMap<>();
            response.put("message", "Customer not found");
            return  new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCustomerById(@PathVariable Integer id) {
        CustomerDto customer = customerService.getCustomerById(id);
        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            HashMap<String, Object> response = new HashMap<>();
            response.put("message", "Customer not found");
            return  new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get_by_email/{email}")
    public ResponseEntity<Object> getCustomerByEmail(@PathVariable String email) {
        CustomerDto customer = customerService.getCustomerByEmail(email);
        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            HashMap<String, Object> response = new HashMap<>();
            response.put("message", "Customer not found");
            return  new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
