package main.java.hust.di;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityModule {

    private static EntityModule instance;
    private EntityManager entityManager;

    private EntityModule() {
    }

    public static EntityModule getInstance() {
        if (instance == null) {
            instance = new EntityModule();
        }
        return instance;
    }

    public EntityManager getEntityManager(String persistenceUnitName) {
        if (entityManager == null) {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory(persistenceUnitName);
            entityManager = factory.createEntityManager();
        }
        return entityManager;
    }
}
