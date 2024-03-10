package com.zervladpy.tallerpaco.Core.DAO;

import com.zervladpy.tallerpaco.Core.Entities.Client.Client;
import com.zervladpy.tallerpaco.Core.Entities.Client.ClientNameDTO;
import com.zervladpy.tallerpaco.Core.Entities.Client.ClientTableViewDTO;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ClientDAO extends DAO<Client>{
    public ClientDAO(EntityManager session) {
        super(session, Client.class);
    }

    public List<ClientTableViewDTO> getTableViewDTOs() {

        Class<ClientTableViewDTO> cDto = ClientTableViewDTO.class;

        String query = "SELECT new " +cDto.getTypeName() + " (c.id, c.name, c.surname, c.email, c.phone, CAST(COUNT(cars) AS int ) , CAST(COUNT(receipts) AS int )) " +
                "FROM " + getEntityClass().getName() + " c " +
                "LEFT JOIN c.cars cars " +
                "LEFT JOIN c.receipts receipts " +
                "GROUP BY c.id";

        return getSession().createQuery(query, cDto).getResultList();

    }

    public List<ClientNameDTO> getClientNameDTOs() {

        String query = "SELECT new " + ClientNameDTO.class.getTypeName() + "(" +
                "c.id, CONCAT(c.name , ' ',  c.surname) ) FROM " + getEntityClass().getTypeName() + " c";

        return getSession().createQuery(query, ClientNameDTO.class).getResultList();
    }

}
