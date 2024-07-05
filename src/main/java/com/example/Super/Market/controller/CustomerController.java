package com.example.Super.Market.controller;

import com.example.Super.Market.entity.Customer;
import com.example.Super.Market.entity.Product;
import com.example.Super.Market.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customerdetails")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("addcustomers")
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.add(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
        Customer updateCustomer = customerService.update(customer);
        return new ResponseEntity<>(updateCustomer, HttpStatus.OK);
    }

    @DeleteMapping("/deleteCustomer/{customerId}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("customerId") Integer customerId) {
        Customer deletecustomer = customerService.deleteById(customerId);
        return new ResponseEntity<>(deletecustomer, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Integer customerId) {
        Customer customer = customerService.getById(customerId);
        return ResponseEntity.ok(customer);

    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Customer>> getAllProducts() {
        List<Customer> customers = customerService.getAll();
        return ResponseEntity.ok(customers);

    }
}
