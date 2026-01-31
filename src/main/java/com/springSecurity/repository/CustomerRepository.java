package com.springSecurity.repository;

import com.springSecurity.model.Customers;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customers,Long> {
    Optional<Customers> findByEmail(String email);
}
