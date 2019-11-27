package main.java.hust.controller;

import main.java.hust.di.EntityModule;
import main.java.hust.entity.CategoryEntity;
import main.java.hust.entity.ProductEntity;
import main.java.hust.session.bean.CategorySB;
import main.java.hust.session.bean.ProductSB;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/category"})
public class CategoryController extends HttpServlet {

    private CategorySB categorySB;
    private ProductSB productSB;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        intiSessionBean();
        String idParameter = req.getParameter("id");
        if (idParameter == null) return;
        int categoryId = Integer.parseInt(idParameter);
        CategoryEntity selectedCategory = categorySB.find(categoryId);
        List<ProductEntity> products = productSB.findAll();
        List<ProductEntity> productCollection = products.stream()
                .filter(productEntity -> productEntity.getCategoryId() == categoryId)
                .collect(Collectors.toList());

        req.setAttribute("categoryProducts", productCollection);
        req.setAttribute("selectedCategory", selectedCategory);
        req.getRequestDispatcher("category.jsp").forward(req, resp);
    }

    private void intiSessionBean() {
        EntityManager entityManager = EntityModule.getInstance().getEntityManager("eMarketPU");
        categorySB = new CategorySB(entityManager);
        productSB = new ProductSB(entityManager);
    }
}
