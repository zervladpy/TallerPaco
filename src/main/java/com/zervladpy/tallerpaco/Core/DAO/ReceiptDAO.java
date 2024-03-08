package com.zervladpy.tallerpaco.Core.DAO;

import com.zervladpy.tallerpaco.Core.Entities.Customer.Customer;
import com.zervladpy.tallerpaco.Core.Entities.Reciep.Receipt;
import com.zervladpy.tallerpaco.Core.Entities.Reciep.ReceiptTableViewDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.ArrayList;
import java.util.List;

public class ReceiptDAO extends DAO<Receipt> {
    public ReceiptDAO(EntityManagerFactory emf) {
        super(emf, Receipt.class);
    }

    public List<ReceiptTableViewDTO> getTableViewDTOs() {

        List<ReceiptTableViewDTO> result = new ArrayList<>();
        Class<ReceiptTableViewDTO> cls = ReceiptTableViewDTO.class;

        try (var session = super.getEmf().createEntityManager()) {

            String query = "SELECT new " + cls.getTypeName() + "(" +
                    "r.id, r.date, c.name, r.serviceType, COUNT(r.parts), r.laborPrice, (SUM(r.parts.price) + r.labor))" +
                    " FROM " + getEntityClass() + " r , " + Customer.class + " c" +
                    " WHERE r.customer = c.id";

            result = session.createQuery(query).getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
