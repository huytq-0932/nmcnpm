package main.java.hust.controller.product;

import main.java.hust.entity.ProductEntity;
import main.java.hust.session.bean.ProductSB;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/products"})
public class ProductsController extends HttpServlet {

    private static final int PER_PAGE = 6;

    @EJB
    ProductSB productSB;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProductEntity> products = productSB.findAll();
        List<ProductEntity> filteredProducts;

        String filter = req.getParameter("filter");

        if (filter == null || filter.isEmpty()) {
            filter = "";
            filteredProducts = products;
        } else {
            final String finalFilter = filter;
            filteredProducts = products.stream()
                    .filter(product -> isPassFilter(product, finalFilter))
                    .collect(Collectors.toList());
        }
        String page = req.getParameter("page");
        int activePage = page == null || page.isEmpty() ? 1 : Integer.parseInt(page);
        int startIndex = PER_PAGE * (activePage - 1);
        int endIndex = Math.min(filteredProducts.size(), PER_PAGE * activePage);
        int numberPages = Math.round(filteredProducts.size() / (float) PER_PAGE);
        activePage = activePage < 1 ? 1 : Math.min(activePage, numberPages);

        req.setAttribute("filter", filter);
        req.setAttribute("numberOfPages", numberPages);
        req.setAttribute("activePage", activePage);
        req.setAttribute("filteredProducts", filteredProducts.subList(startIndex, endIndex));
        req.getRequestDispatcher("products.jsp").forward(req, resp);
    }

    private boolean isPassFilter(ProductEntity product, String filter) {
        return product.getName().toLowerCase().contains(filter.toLowerCase())
                || product.getDescription().toLowerCase().contains(filter.toLowerCase());
    }
}
