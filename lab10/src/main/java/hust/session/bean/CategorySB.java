package main.java.hust.session.bean;

import main.java.hust.entity.CategoryEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CategorySB extends BaseSessionBean<CategoryEntity> {

    @PersistenceContext(unitName = "eMarketPU")
    private EntityManager entityManager;

    public CategorySB() {
        super(CategoryEntity.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
