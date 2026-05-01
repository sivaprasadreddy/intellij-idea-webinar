package dev.sivalabs.demo;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    public List<Customer> findAllCustomers() {
        return null;
    }

    public Optional<Customer> findById() {
        return Optional.empty();
    }

}
