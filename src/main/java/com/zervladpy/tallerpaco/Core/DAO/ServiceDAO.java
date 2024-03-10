package com.zervladpy.tallerpaco.Core.DAO;

import com.zervladpy.tallerpaco.Core.Entities.Invoice.Invoice;
import com.zervladpy.tallerpaco.Core.Entities.Service.Service;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ServiceDAO extends DAO<Service> {
    public ServiceDAO(EntityManager session) {
        super(session, Service.class);
    }

    public List<Service> getServicesByInvoice(Invoice invoice) {

        String query = "SELECT DISTINCT s FROM " + Invoice.class.getTypeName() + " i " +
                "JOIN i.service s " +
                "WHERE i = :invoice";

        return getSession().createQuery(query, Service.class)
                .setParameter("invoice", invoice)
                .getResultList();

    }

    public void deleteServiceFromInvoice(Invoice invoice, Service selectedService) {
        
        invoice.getService().remove(selectedService);
        getSession().getTransaction().begin();
        getSession().merge(invoice);
        getSession().getTransaction().commit();

    }

    public void addServiceToInvoice(Invoice invoice, Service selectedService) {

        var invoices = invoice.getService();
        if (invoices == null) {
            invoices = List.of(selectedService);
        } else {
            invoices.add(selectedService);
        }
        invoice.setService(invoices);
        getSession().getTransaction().begin();
        getSession().merge(invoice);
        getSession().getTransaction().commit();

    }
}
