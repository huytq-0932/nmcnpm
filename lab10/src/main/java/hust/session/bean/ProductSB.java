package main.java.hust.session.bean;

import main.java.hust.entity.ProductEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProductSB extends BaseSessionBean<ProductEntity> {

    @PersistenceContext(unitName = "eMarketPU")
    private EntityManager entityManager;

    public ProductSB() {
        super(ProductEntity.class);
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
