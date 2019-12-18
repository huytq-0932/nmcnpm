package main.java.hust.session.bean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ProductSB extends BaseSessionBean<ProductEntity> {

    @PersistenceContext(unitName = "eMarketPU")
    private EntityManager entityManager;

    public ProductSB(EntityManager entityManager) {
        super(ProductEntity.class);
        this.entityManager = entityManager;
    }

    public ProductSB() {
        super(ProductEntity.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
