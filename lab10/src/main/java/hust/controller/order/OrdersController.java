package main.java.hust.controller.order;

import main.java.hust.entity.CustomerOrder;
import main.java.hust.session.bean.CustomerOrderSB;
import main.java.hust.session.bean.CustomerSB;
import main.java.hust.session.bean.OrderManager;
import main.java.hust.session.bean.OrderedProductSB;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/orders", "/deleteOrder"})
public class OrdersController extends HttpServlet {

    @EJB
    CustomerSB customerSB;
    @EJB
    CustomerOrderSB customerOrderSB;

    @EJB
    OrderedProductSB orderedProductSB;

    @EJB
    OrderManager orderManager;

    private String deleteMessage = "";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  SettingController.getInstance().configureRole(req).configureLanguage(req);
        String orderIdParameter = req.getParameter("orderId");

        List<CustomerOrder> customerOrders = customerOrderSB.findAll();
        List<Map> orders = customerOrders.stream()
                .map(customerOrder -> orderManager.getOrderDetails(customerOrder.getOrderId()))
                .collect(Collectors.toList());
        req.setAttribute("deleteMessage", deleteMessage);
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("orders.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //   SettingController.getInstance().configureRole(req).configureLanguage(req);
        String orderIdParameter = req.getParameter("orderId");

        try {
            int orderId = Integer.parseInt(orderIdParameter);
            orderManager.removeOrder(orderId);
            deleteMessage = "Delete order success!";
        } catch (Exception e) {
            deleteMessage = "Delete order failed!";
        }
        doGet(req, resp);
    }
}
