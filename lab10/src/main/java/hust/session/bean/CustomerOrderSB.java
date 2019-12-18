package main.java.hust.session.bean;

import main.java.hust.entity.CustomerOrder;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CustomerOrderSB extends BaseSessionBean<CustomerOrder> {

    @PersistenceContext(unitName = "eMarketPU")
    private EntityManager entityManager;

    public CustomerOrderSB() {
        super(CustomerOrder.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    public CustomerOrder find(Object id) {
        CustomerOrder order = entityManager.find(CustomerOrder.class, id);
        entityManager.refresh(order);
        return order;
    }

    public CustomerOrder findByCustomer(Object customer) {
        return (CustomerOrder)
                entityManager.createNamedQuery("CustomerOrder.findByCustomer")
                        .setParameter("customer", customer)
                        .getSingleResult();
    }
}

