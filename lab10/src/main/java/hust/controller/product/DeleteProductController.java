package main.java.hust.controller.product;

import main.java.hust.entity.ProductDetailEntity;
import main.java.hust.entity.ProductEntity;
import main.java.hust.session.bean.ProductDetailSB;
import main.java.hust.session.bean.ProductSB;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/deleteProduct"})
public class DeleteProductController extends HttpServlet {

    @EJB
    ProductSB productSB;

    @EJB
    ProductDetailSB productDetailSB;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int productId = Integer.parseInt(req.getParameter("productId"));
            ProductEntity product = productSB.find(productId);
            ProductDetailEntity productDetailEntity = productDetailSB.find(productId);
            productSB.remove(product);
            productDetailSB.remove(productDetailEntity);
        } catch (Exception ignored) {
        }

        req.getRequestDispatcher("home").forward(req, resp);

    }
}
