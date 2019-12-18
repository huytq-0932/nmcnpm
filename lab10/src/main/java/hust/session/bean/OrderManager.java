package main.java.hust.session.bean;

import main.java.hust.entity.*;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class OrderManager {
    @EJB
    private CustomerOrderSB customerOrderSB;
    @EJB
    private ProductSB productSB;
    @EJB
    private OrderedProductSB orderedProductSB;
    @EJB
    private CustomerSB customerSB;

    @PersistenceContext(unitName = "eMarketPU")
    private EntityManager em;
    @Resource
    private SessionContext context;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public int placeOrder(String name, String email, String phone, String
            address, String cityRegion, String ccNumber, ShoppingCart cart) {
        try {
            Customer customer = customerSB.create(new Customer(name, email, phone, address, cityRegion, ccNumber));
            CustomerOrder customerOrder = customerOrderSB.create(new CustomerOrder(customer, cart));
            addOrderedItems(customerOrder, cart);
            return customerOrder.getOrderId();
        } catch (Exception e) {
            context.setRollbackOnly();
            e.printStackTrace();
            return 0;
        }
    }

    private void addOrderedItems(CustomerOrder order, ShoppingCart cart) {
        List<ShoppingCartItem> items = cart.getItems();
        // iterate through shopping cart and create OrderedProducts
        for (ShoppingCartItem scItem : items) {
            int productId = scItem.getProduct().getProductId();
            // set up primary key object
            OrderedProductPK orderedProductPK = new OrderedProductPK();
            orderedProductPK.setOrderId(order.getOrderId());
            orderedProductPK.setProductId(productId);
            // create ordered item using PK object
            OrderedProduct orderedItem = new OrderedProduct(orderedProductPK);
            // set quantity
            orderedItem.setQuantity(scItem.getQuantity());
            orderedProductSB.create(orderedItem);
        }
    }

    public void removeOrder(int orderId) throws Exception {
        CustomerOrder order = customerOrderSB.find(orderId);
        List<OrderedProduct> orderedProducts = orderedProductSB.findByOrderId(orderId);
        Customer customer = customerSB.find(order.getCustomerId());

        for (OrderedProduct orderedProduct : orderedProducts) {
            orderedProductSB.remove(orderedProduct);
        }
        customerOrderSB.remove(order);
        customerSB.remove(customer);
    }

    public Map getOrderDetails(int orderId) {
        Map orderMap = new HashMap();
        // get order
        CustomerOrder order = customerOrderSB.find(orderId);
        // get customer
        Customer customer = customerSB.find(order.getCustomerId());
        // get all ordered products
        List<OrderedProduct> orderedProducts = orderedProductSB.findByOrderId(orderId);
        // get product details for ordered items
        List<ProductEntity> products = orderedProducts.stream()
                .map(orderedProduct -> productSB.find(orderedProduct.getProductId()))
                .collect(Collectors.toList());

        // add each item to orderMap
        orderMap.put("orderRecord", order);
        orderMap.put("customer", customer);
        orderMap.put("orderedProducts", orderedProducts);
        orderMap.put("products", products);
        return orderMap;
    }
}
