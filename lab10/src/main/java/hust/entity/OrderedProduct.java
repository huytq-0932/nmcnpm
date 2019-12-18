package main.java.hust.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ordered_product", schema = "emarket", catalog = "")
@IdClass(OrderedProductPK.class)
public class OrderedProduct {
    private int orderId;
    private int productId;
    private int quantity;

    public OrderedProduct(OrderedProductPK orderedProductPK) {
        this.orderId = orderedProductPK.getOrderId();
        this.productId = orderedProductPK.getProductId();
        this.quantity = 0;
    }

    public OrderedProduct() {
    }

    @Id
    @Column(name = "order_id", nullable = false)
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Id
    @Column(name = "product_id", nullable = false)
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "quantity", nullable = false)
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderedProduct that = (OrderedProduct) o;
        return orderId == that.orderId &&
                productId == that.productId &&
                quantity == that.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, productId, quantity);
    }
}
