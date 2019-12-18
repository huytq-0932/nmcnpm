package main.java.hust.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

@Entity
@Table(name = "customer_order", schema = "emarket", catalog = "")
public class CustomerOrder {
    private int orderId;
    private BigDecimal amount;
    private String dateCreated;
    private int confirmationNumber;
    private int customerId;

    public CustomerOrder() {
    }

    public CustomerOrder(Customer customer, ShoppingCart cart) {
        this.customerId = customer.getCustomerId();
        this.amount = BigDecimal.valueOf(cart.getTotal());
        this.confirmationNumber = new Random().nextInt(999999999);
        this.dateCreated = new SimpleDateFormat("dd/MM/yy HH:mm:ss").format(new Date());
    }

    @Id
    @Column(name = "order_id", nullable = false)
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "amount", nullable = false, precision = 2)
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "date_created", nullable = false, length = 45)
    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Basic
    @Column(name = "confirmation_number", nullable = false)
    public int getConfirmationNumber() {
        return confirmationNumber;
    }

    public void setConfirmationNumber(int confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
    }

    @Basic
    @Column(name = "customer_id", nullable = false)
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerOrder that = (CustomerOrder) o;
        return orderId == that.orderId &&
                confirmationNumber == that.confirmationNumber &&
                customerId == that.customerId &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(dateCreated, that.dateCreated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, amount, dateCreated, confirmationNumber, customerId);
    }
}
