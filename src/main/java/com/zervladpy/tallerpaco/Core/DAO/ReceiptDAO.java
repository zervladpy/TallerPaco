package com.zervladpy.tallerpaco.Core.DAO;

import com.zervladpy.tallerpaco.Core.Entities.Client.ClientNameDTO;
import com.zervladpy.tallerpaco.Core.Entities.Parts.Part;
import com.zervladpy.tallerpaco.Core.Entities.Reciep.Receipt;
import com.zervladpy.tallerpaco.Core.Entities.Reciep.ReceiptTableViewDTO;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ReceiptDAO extends DAO<Receipt> {
    public ReceiptDAO(EntityManager session) {
        super(session, Receipt.class);
    }

    public List<ReceiptTableViewDTO> getTableViewDTOs() {

        String query = "SELECT new " + ReceiptTableViewDTO.class.getTypeName() + "(" +
                "receipt.id, receipt.receiptDate, " +
                "new " + ClientNameDTO.class.getTypeName() + "(client.id, CONCAT(client.name, ' ', client.surname)), " +
                "receipt.serviceType, " +
                "CAST(COALESCE(COUNT(rp.id),0) as int), " +
                "COALESCE(receipt.laborPrice, 0.0) , " +
                "SUM(COALESCE(rp.price, 0)) + receipt.laborPrice) " +
                "FROM " + getEntityClass().getName() + " receipt " +
                "JOIN receipt.client c ON receipt.client.id = c.id " +
                "LEFT JOIN receipt.parts rp " +
                "GROUP BY receipt.id, receipt.receiptDate, c.name, receipt.laborPrice";

        return getSession().createQuery(query, ReceiptTableViewDTO.class).getResultList();
    }
}
