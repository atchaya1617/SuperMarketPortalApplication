package com.example.Super.Market.service;

import com.example.Super.Market.entity.Customer;
import com.example.Super.Market.entity.Product;
import com.example.Super.Market.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public Customer add(Customer customer){
        return customerRepository.save(customer);
    }

    public Customer update(Customer customer){
        return customerRepository.save(customer);
    }

    public Customer deleteById(int customerId) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);

        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            customerRepository.deleteById(customerId);
        } else {
            throw new RuntimeException("Customer not found with id " + customerId);
        }
        return null;
    }

    public Customer getById(int customerId){
        Optional<Customer> optionalCustomer  = customerRepository.findById(customerId);
        if (optionalCustomer.isPresent()){
            return optionalCustomer.get();
        }else{
            throw new EntityNotFoundException("not found");
        }
    }

    public List<Customer> getAll(){
        return customerRepository.findAll();
    }

}
