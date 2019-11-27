package main.java.hust.session.bean;

import main.java.hust.entity.ProductDetailEntity;

import javax.persistence.EntityManager;

public class ProductDetailSB extends BaseSessionBean<ProductDetailEntity> {

    private EntityManager entityManager;

    public ProductDetailSB(EntityManager entityManager) {
        super(ProductDetailEntity.class);
        this.entityManager = entityManager;
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
