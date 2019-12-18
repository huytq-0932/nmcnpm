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

@WebServlet(urlPatterns = {"/addProduct"})
public class AddProductController extends HttpServlet {

    @EJB
    ProductSB productSB;

    @EJB
    ProductDetailSB productDetailSB;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("status", "");
        req.getRequestDispatcher("addProduct.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ProductEntity product = productSB.create(new ProductEntity(req));
            productDetailSB.create(new ProductDetailEntity(product));
            req.setAttribute("status", "success");
        } catch (Exception e) {
            req.setAttribute("status", "failed");
        }

        req.getRequestDispatcher("addProduct.jsp").forward(req, resp);
    }
}
