package main.java.hust.controller.order;

import main.java.hust.controller.setting.SettingController;
import main.java.hust.entity.ShoppingCart;
import main.java.hust.session.bean.OrderManager;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

@WebServlet(urlPatterns = {"/confirmation"})
public class ConfirmationController extends HttpServlet {

    @EJB
    OrderManager orderManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SettingController.getInstance().configureRole(req).configureLanguage(req);

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        String cityRegion = req.getParameter("cityRegion");
        String creditCard = req.getParameter("creditCard");

        if (!isValidateForm(name, email, phone, address, cityRegion, creditCard)) {
            req.setAttribute("errorForm", "Your submit is invalid, make sure that length of phone number need be longer than 9");
            req.getRequestDispatcher("checkout.jsp").forward(req, resp);
            return;
        }
        HttpSession session = req.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");

        if (cart == null) {
            req.getRequestDispatcher("home").forward(req, resp);
            return;
        }

        int orderId = orderManager.placeOrder(name, email, phone, address, cityRegion, creditCard, cart);
        if (orderId != 0) {
            Locale locale = (Locale) session.getAttribute("javax.servlet.jsp.jstl.fmt.locale.session");
            String language = "";
            if (locale != null) {
                language = locale.getLanguage();
            }
            // dissociate shopping cart from session
            session.setAttribute("cart", null);
            if (!language.isEmpty()) {
                //                if user changed language using the toggle,
                //                reset the language attribute -otherwise
                req.setAttribute("language", language); //
                //    language will be switched on confirmation page !
            }
            // get order details
            Map orderMap = orderManager.getOrderDetails(orderId);
            // place order details in request scope
            req.setAttribute("customer", orderMap.get("customer"));
            req.setAttribute("products", orderMap.get("products"));
            req.setAttribute("orderRecord", orderMap.get("orderRecord"));
            req.setAttribute("orderedProducts", orderMap.get("orderedProducts"));
            req.getRequestDispatcher("confirmation.jsp").forward(req, resp);
        }else {
            req.getRequestDispatcher("home").forward(req, resp);
        }
    }

    private boolean isValidateForm(String name, String email, String phone, String address, String cityRegion, String creditCard) {
        if (name == null || email == null || phone == null || address == null || cityRegion == null || creditCard == null) {
            return false;
        }
        return !name.isEmpty() && !address.isEmpty() && phone.length() >= 9
                && !cityRegion.isEmpty() && !creditCard.isEmpty() && !email.isEmpty();
    }
}
