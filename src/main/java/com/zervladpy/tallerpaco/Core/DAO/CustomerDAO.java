package com.zervladpy.tallerpaco.Core.DAO;

import com.zervladpy.tallerpaco.Core.Entities.Customer.Customer;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class CustomerDAO extends DAO<Customer>{
    public CustomerDAO(EntityManagerFactory emf) {
        super(emf, Customer.class);
    }

}
