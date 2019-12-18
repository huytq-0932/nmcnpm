package main.java.hust.controller.cart;

import main.java.hust.entity.ShoppingCart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/viewCart"})
public class ViewCartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String isClear = req.getParameter("clear");

        if ("true".equals(isClear)) {
            ShoppingCart shoppingCart = (ShoppingCart) req.getSession().getAttribute("cart");
            shoppingCart.clear();
        }

        req.getRequestDispatcher("viewCart.jsp").forward(req, resp);
    }
}
