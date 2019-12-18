package main.java.hust.session.bean;

import main.java.hust.entity.OrderedProduct;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class OrderedProductSB extends BaseSessionBean<OrderedProduct> {

    @PersistenceContext(unitName = "eMarketPU")
    private EntityManager entityManager;

    public OrderedProductSB() {
        super(OrderedProduct.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    public List<OrderedProduct> findByOrderId(int id) {
        return findAll().stream()
                .filter(orderedProduct -> orderedProduct.getOrderId() == id)
                .collect(Collectors.toList());
    }
}
