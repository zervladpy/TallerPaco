package com.zervladpy.tallerpaco.Core.Session;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class SessionManager {

    static private EntityManager instance;

    static public EntityManager getInstance() {

        if (instance == null) {
            synchronized (EntityManagerFactory.class) {
                if (instance == null) {
                    instance = Persistence.createEntityManagerFactory("tallerpaco").createEntityManager();
                }
            }
        }

        return instance;
    }

    static public void closeSession() {
        if (instance != null && instance.isOpen()) {
            instance.close();
        }
    }

}
