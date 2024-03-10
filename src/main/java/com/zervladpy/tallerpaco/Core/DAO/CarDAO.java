package com.zervladpy.tallerpaco.Core.DAO;

import com.zervladpy.tallerpaco.Core.Entities.Brand.BrandNameDTO;
import com.zervladpy.tallerpaco.Core.Entities.Car.Car;
import com.zervladpy.tallerpaco.Core.Entities.Brand.Brand;
import com.zervladpy.tallerpaco.Core.Entities.Car.CarPlateDTO;
import com.zervladpy.tallerpaco.Core.Entities.Car.CarTableViewDTO;
import com.zervladpy.tallerpaco.Core.Entities.Client.Client;
import com.zervladpy.tallerpaco.Core.Entities.Client.ClientNameDTO;
import jakarta.persistence.*;

import java.util.List;

public class CarDAO extends DAO<Car>{
    public CarDAO(EntityManager session) {
        super(session, Car.class);
    }

    @Override
    public Car getById(String id) {
        return getSession().find(getEntityClass(), id);
    }

    public List<CarTableViewDTO> getTableViewDTOs() {

        Class<CarTableViewDTO> resultClass = CarTableViewDTO.class;


        String query = "SELECT new " + resultClass.getTypeName() + " (" +
                "car.plate, car.details.color, car.mileage, " +
                "new " + BrandNameDTO.class.getTypeName() + "(brand.id, brand.name), " +
                "new " + ClientNameDTO.class.getTypeName() + "(client.id, CONCAT(client.name, ' ', client.surname))) " +
                "FROM " + getEntityClass().getName() + " car " +
                "JOIN car.details.brand brand " +
                "JOIN car.client client";

        return getSession().createQuery(query, resultClass).getResultList();

    }

    public List<CarPlateDTO> getPlates() {

        String query = "SELECT new " + CarPlateDTO.class.getTypeName() + "(car.plate) FROM " + getEntityClass().getName() + " car";

        return getSession().createQuery(query, CarPlateDTO.class).getResultList();
    }

}
