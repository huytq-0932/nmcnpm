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

@WebServlet(urlPatterns = {"/updateCart"})
public class UpdateCartController extends HttpServlet {

    @EJB
    ProductSB productSB;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("viewCart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        ShoppingCart shoppingCart = (ShoppingCart) req.getSession().getAttribute("cart");

        if (shoppingCart == null) {
            req.getRequestDispatcher("index.jsp").forward(req, resp);
            return;
        }

        String productIdParameter = req.getParameter("productId");
        String quantity = req.getParameter("quantity");
        try {
            if (productIdParameter != null && !productIdParameter.isEmpty()
                    && quantity != null && !quantity.isEmpty()) {
                int productId = Integer.parseInt(productIdParameter);

                ProductEntity product = productSB.find(productId);
                shoppingCart.update(product, quantity);
                session.setAttribute("cart", shoppingCart);
            }
        } finally {
            req.getRequestDispatcher("viewCart.jsp").forward(req, resp);
        }
    }
}
