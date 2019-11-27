package main.java.hust.controller;

import main.java.hust.di.EntityModule;
import main.java.hust.entity.ProductEntity;
import main.java.hust.session.bean.ProductSB;

import javax.persistence.EntityManager;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "ControllerServlet", loadOnStartup = 1, urlPatterns = {"/products"})
public class ControllerServlet extends HttpServlet {

    private ProductSB productSB;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        EntityManager entityManager = EntityModule.getInstance().getEntityManager("eMarketPU");
        productSB = new ProductSB(entityManager);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getProducts(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    private void getProducts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProductEntity> products = productSB.findAll();
        String indexParamerter = req.getParameter("index");
        int position;
        try {
            position = Integer.parseInt(indexParamerter);
        } catch (NumberFormatException e) {
            position = 0;
        }
        ProductEntity currentProduct = products.get(position);
        System.out.println(products.stream().map(ProductEntity::getName).collect(Collectors.toList()));
        req.setAttribute("newProducts", products);
        req.setAttribute("currentProduct", currentProduct);
        req.getRequestDispatcher("products.jsp").forward(req, resp);
    }
}
