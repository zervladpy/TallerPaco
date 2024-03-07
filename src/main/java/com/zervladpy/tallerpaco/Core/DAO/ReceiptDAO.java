package com.zervladpy.tallerpaco.Core.DAO;

import com.zervladpy.tallerpaco.Core.Entities.Reciep.Receipt;
import jakarta.persistence.EntityManagerFactory;

public class ReceiptDAO extends DAO<Receipt> {
    public ReceiptDAO(EntityManagerFactory emf) {
        super(emf, Receipt.class);
    }
}
