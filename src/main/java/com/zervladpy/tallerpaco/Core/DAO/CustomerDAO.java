package com.zervladpy.tallerpaco.Core.DAO;

import com.zervladpy.tallerpaco.Core.Entities.Customer.Customer;
import com.zervladpy.tallerpaco.Core.Entities.Customer.CustomerTableViewDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class CustomerDAO extends DAO<Customer>{
    public CustomerDAO(EntityManagerFactory emf) {
        super(emf, Customer.class);
    }

    public List<CustomerTableViewDTO> getTableViewDTOs() {

        Class<CustomerTableViewDTO> cDto = CustomerTableViewDTO.class;

        String query = "SELECT new " +cDto.getTypeName() + " (c.id, c.name, c.lastName, c.email, c.phone, CAST(COUNT(cars) AS int ) , CAST(COUNT(receipts) AS int )) " +
                "FROM " + getEntityClass().getName() + " c " +
                "LEFT JOIN c.cars cars " +
                "LEFT JOIN c.receipts receipts " +
                "GROUP BY c.id";

        List<CustomerTableViewDTO> result;

        try(EntityManager session = getEmf().createEntityManager()) {
            result = session.createQuery(query, cDto).getResultList();
        }

        return result;

    }


}
