package main.java.hust.session.bean;

import main.java.hust.entity.ProductEntity;

import javax.persistence.EntityManager;
import java.util.List;

public class ProductSB extends BaseSessionBean<ProductEntity> {

    private EntityManager entityManager;

    public ProductSB(EntityManager entityManager) {
        super(ProductEntity.class);
        this.entityManager = entityManager;
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    public List<ProductEntity> getProductCollectionByCategoryId(int id) {
        String query = "SELECT * FROM product, category WHERE product.category_id = category.category_id";
        return entityManager.createQuery(query).getResultList();
    }
}
