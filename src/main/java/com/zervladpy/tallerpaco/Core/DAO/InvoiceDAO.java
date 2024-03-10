package com.zervladpy.tallerpaco.Core.DAO;

import com.zervladpy.tallerpaco.Core.Entities.Car.CarPlateDTO;
import com.zervladpy.tallerpaco.Core.Entities.Client.ClientNameDTO;
import com.zervladpy.tallerpaco.Core.Entities.Invoice.Invoice;
import com.zervladpy.tallerpaco.Core.Entities.Invoice.InvoicePK;
import com.zervladpy.tallerpaco.Core.Entities.Invoice.InvoiceTableDTO;
import jakarta.persistence.EntityManager;

import java.util.List;

public class InvoiceDAO extends DAO<Invoice> {
    public InvoiceDAO(EntityManager session) {
        super(session, Invoice.class);
    }

    public Invoice getById(InvoicePK id) {

        Invoice invoice = getSession().find(getEntityClass(), id);

        return invoice;

    }

    public List<InvoiceTableDTO> getTableViewDTOs() {

        String query = "select new com.zervladpy.tallerpaco.Core.Entities.Invoice.InvoiceTableDTO(" +
                "invoice.id.date, new " + CarPlateDTO.class.getTypeName() + " (car.plate), " +
                "new "+ ClientNameDTO.class.getTypeName() + "(client.id, concat(client.name, '', client.surname)), " +
                "sum(coalesce(service.price, 0))) " +
                "FROM " + getEntityClass().getName() + " invoice " +
                "JOIN invoice.id.car car " +
                "JOIN car.client client " +
                "LEFT JOIN invoice.service service " +
                "GROUP BY invoice.id.date, car.plate, client.id";


        return getSession().createQuery(query, InvoiceTableDTO.class).getResultList();
    }
}
