package com.zervladpy.tallerpaco.Core.DAO;

import com.zervladpy.tallerpaco.Core.Entities.Brand.BrandNameDTO;
import com.zervladpy.tallerpaco.Core.Entities.Car.Car;
import com.zervladpy.tallerpaco.Core.Entities.Brand.Brand;
import com.zervladpy.tallerpaco.Core.Entities.Car.CarTableViewDTO;
import com.zervladpy.tallerpaco.Core.Entities.Client.Client;
import com.zervladpy.tallerpaco.Core.Entities.Client.ClientNameDTO;
import jakarta.persistence.*;

import java.util.List;

public class CarDAO extends DAO<Car>{
    public CarDAO(EntityManager session) {
        super(session, Car.class);
    }


    public List<CarTableViewDTO> getTableViewDTOs() {

        Class<CarTableViewDTO> resultClass = CarTableViewDTO.class;


        String query = "SELECT new " + resultClass.getTypeName() + " (" +
                "car.id, car.details.plate, car.details.color, car.details.mileage, " +
                "new " + BrandNameDTO.class.getTypeName() + "(brand.id, brand.name), " +
                "new " + ClientNameDTO.class.getTypeName() + "(client.id, CONCAT(client.name, ' ', client.surname))) " +
                "FROM " + getEntityClass().getName() + " car " +
                "JOIN car.brand brand " +
                "JOIN car.client client";

        return getSession().createQuery(query, resultClass).getResultList();

    }

}
