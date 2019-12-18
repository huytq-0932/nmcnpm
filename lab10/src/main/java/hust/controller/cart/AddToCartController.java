package main.java.hust.controller.cart;

import main.java.hust.entity.ProductEntity;
import main.java.hust.entity.ShoppingCart;
import main.java.hust.session.bean.ProductSB;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/addToCart"})
public class AddToCartController extends HttpServlet {

    @EJB
    ProductSB productSB;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        ShoppingCart shoppingCart = (ShoppingCart) req.getSession().getAttribute("cart");
        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart();
            session.setAttribute("cart", shoppingCart);
        }

        String productId = req.getParameter("productId");
        if (productId != null && !productId.isEmpty()) {
            ProductEntity product = productSB.find(Integer.parseInt(productId));
            shoppingCart.addItem(product);
        }

        req.getRequestDispatcher("viewCart.jsp").forward(req, resp);
    }
}

