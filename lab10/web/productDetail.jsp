<%--  Created by IntelliJ IDEA.
  User: Tran
  Date: 11/27/2019
  Time: 12:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<jsp:useBean id="selectedProduct" scope="request" type="main.java.hust.entity.ProductEntity"/>
<c:set var="selectedProduct" value="${selectedProduct}"/>
<jsp:useBean id="selectedProductDetail" scope="request" type="main.java.hust.entity.ProductDetailEntity"/>
<c:set var="selectedProductDetail" value="${selectedProductDetail}"/>

<div id="container" class="bg-white">
    <div class="bg-white">
        <div class="heading_bg">
            <h2>
                <%=selectedProduct.getName()%>
            </h2>
        </div>
    </div>
    <div class="row">
        <div class="col-6">
            <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
                <div class="carousel-inner">
                    <%
                        List<String> links = selectedProductDetail.getAllImages();
                        String imageLink = links.get((int) (links.size() * Math.random()));
                    %>
                    <div class="carousel-item active">
                        <img class="d-block w-100" src="images/<%=imageLink%>" alt="First slide">
                    </div>
                    <%
                        for (String img : links) {
                    %>
                    <div class="carousel-item">
                        <img class="d-block w-100" src="images/<%=img%>" alt="Second slide">
                    </div>
                    <%
                        }
                    %>
                </div>
                <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
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
                    <a href="addToCart?productId=${selectedProduct.productId}">
                        <button type="button" class="btn btn-primary">Add to cart</button>
                    </a>
                    <c:if test="${role == 'admin'}">
                        <a href="deleteProduct?productId=${selectedProduct.productId}">
                            <button type="button" class="btn btn-danger">Delete product</button>
                        </a>
                    </c:if>
                </p>
            </div>
        </div>
    </div>
    <div style="clear:both; height: 40px"></div>
</div>