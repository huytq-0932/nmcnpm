package main.java.hust.controller;

import main.java.hust.controller.setting.SettingController;
import main.java.hust.entity.ProductEntity;
import main.java.hust.session.bean.ProductSB;

import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "ControllerServlet", urlPatterns = {"/", "/home", "/admin/home", "/admin"})
public class MainController extends HttpServlet {
    @EJB
    ProductSB productSB;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SettingController.getInstance().configureRole(req).configureLanguage(req);
        List<ProductEntity> products = productSB.findAll();
        String indexParameter = req.getParameter("index");
        int position;
        try {
            position = Integer.parseInt(indexParameter);
        } catch (NumberFormatException e) {
            position = 0;
        }
        ProductEntity currentProduct = products.get(position);
        System.out.println(products.stream().map(ProductEntity::getName).collect(Collectors.toList()));

        req.setAttribute("bestSeller", products.subList(0, 5));
        req.setAttribute("currentProduct", currentProduct);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
