package main.java.hust.session.bean;

import main.java.hust.entity.Customer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CustomerSB extends BaseSessionBean<Customer> {

    @PersistenceContext(unitName = "eMarketPU")
    private EntityManager entityManager;

    public CustomerSB() {
        super(Customer.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
