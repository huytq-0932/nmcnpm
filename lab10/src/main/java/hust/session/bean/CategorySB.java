package main.java.hust.session.bean;

import main.java.hust.entity.CategoryEntity;

import javax.persistence.EntityManager;

public class CategorySB extends BaseSessionBean<CategoryEntity> {

    private EntityManager entityManager;

    public CategorySB(EntityManager entityManager) {
        super(CategoryEntity.class);
        this.entityManager = entityManager;
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
