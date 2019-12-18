package main.java.hust.session.bean;

import main.java.hust.entity.ProductDetailEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ProductDetailSB extends BaseSessionBean<ProductDetailEntity> {

    @PersistenceContext(unitName = "eMarketPU")
    private EntityManager entityManager;

    public ProductDetailSB() {
        super(ProductDetailEntity.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
