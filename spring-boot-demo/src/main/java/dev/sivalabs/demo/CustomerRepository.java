package dev.sivalabs.demo;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepository {

    public List<Customer> findAll() {
        return List.of();
    }

    public Optional<Customer> findById() {
        return Optional.empty();
    }

}
