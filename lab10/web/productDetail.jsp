<%@ page import="java.util.List" %><%--  Created by IntelliJ IDEA.
  User: Tran
  Date: 11/27/2019
  Time: 12:34 PM
  To change this template use File | Settings | File Templates.
--%>
<jsp:useBean id="selectedProduct" scope="request" type="main.java.hust.entity.ProductEntity"/>
<c:set var="selectedProduct" value="${selectedProduct}"/>
<jsp:useBean id="selectedProductDetail" scope="request" type="main.java.hust.entity.ProductDetailEntity"/>
<c:set var="selectedProductDetail" value="${selectedProductDetail}"/>
<div id="container">
    <div class="one">
        <div class="heading_bg">
            <h2>
                <%=selectedProduct.getName()%>
            </h2>
        </div>
    </div>
    <div class="row">
        <div class="col-6">
            <%
                List<String> links = selectedProductDetail.getAllImages();
                String imageLink = links.get((int) (links.size() * Math.random()));
            %>
            <img class="w-100" src="images/<%=imageLink%>" alt="Second slide">
            <div class="d-flex justify-content-wrap">
                <%
                    for (String img : links) {
                %>
                <img src="images/tn-<%=img%>" alt="" title="" width="100"/>
                <%
                    }
                %>
            </div>
        </div>
        <div class="col-6">
            <ul id="tabify_menu" class="menu_tab" style="margin: 0;">
                <li><a href="#fane1">Product Details</a></li>
            </ul>
            <div id="fane1" class="tab_content">
                <h3>Technical Details</h3>
                <p>
                    <%=selectedProductDetail.getInformation()%>
                </p>
                <h3>Accessories</h3>
                <p><%=selectedProductDetail.getAccessories()%>
                </p>

                <h3>Warranty Strategy</h3>
                <p><%=selectedProductDetail.getGuaranty()%>
                </p>
                <h3>Price</h3>
                <p><%=selectedProduct.getPrice()%> $</p>
                <p style="text-align:left; margin-right: 16px">
                    <a href="#" class="button">Add to cart</a>
                </p>
            </div>
        </div>
    </div>
    <div style="clear:both; height: 40px"></div>
</div>

