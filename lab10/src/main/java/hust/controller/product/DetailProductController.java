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

@WebServlet(urlPatterns = {"/productDetail", "/detail"})
public class DetailProductController extends HttpServlet {

    @EJB
    private ProductSB productSB;
    @EJB
    private ProductDetailSB productDetailSB;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productIdParameter = req.getParameter("productId");
        if (productIdParameter == null) return;

        int productId = Integer.parseInt(productIdParameter);
        ProductDetailEntity productDetailEntity = productDetailSB.find(productId);
        ProductEntity productEntity = productSB.find(productId);

        req.setAttribute("selectedProduct", productEntity);
        req.setAttribute("selectedProductDetail", productDetailEntity);
        req.getRequestDispatcher("productDetail.jsp").forward(req, resp);
    }
}
