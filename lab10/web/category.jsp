<%--
  Created by IntelliJ IDEA.
  User: Tran
  Date: 11/21/2019
  Time: 8:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@page import="main.java.hust.entity.ProductEntity" %>
<%@ page import="java.util.List" %>
<jsp:useBean id="selectedCategory" scope="request" type="main.java.hust.entity.CategoryEntity"/>
<c:set var="category" scope="request" value="${selectedCategory}}"/>
<jsp:useBean id="categoryProducts" scope="request" type="java.util.List"/>
<c:set var="categoryProducts" scope="request" value="${categoryProducts}"/>
<div id="container">
    <div class="one">
        <div class="heading_bg">
            <h2><%=selectedCategory.getName()%>
            </h2>
        </div>
        <div id="portfolio">
            <div class="portfolio-container" id="columns">
                <ul>
                    <%
                        //                        List<ProductEntity> categoryProducts =
//                                (List<ProductEntity>) session.getAttribute("categoryProducts");
                        for (ProductEntity product : (List<ProductEntity>)categoryProducts) {
                    %>
                    <li class="one-third">
                        <p>
                            <a title="<%=product.getName()%>"
                               href="img/demo/<%=product.getImage()%>"
                               class="portfolio-item-preview"
                               data- rel="prettyPhoto"
                            >
                                <img src="img/demo/<%=product.getImage()%>"
                                     alt=""
                                     width="210"
                                     height="145"
                                     class="portfolio-img pretty-box"
                                >
                            </a>
                        </p>
                        <h4><a href="productDetail?productId=<%=product.getProductId()%>"><%=product.getName()%>
                        </a></h4>
                        <p><%=product.getDescription()%>
                        </p>
                        <p style="text-align: left">
                            <a href="productDetail?productId=<%=product.getProductId()%>" class="button_small white">
                                See Details &raquo;
                            </a>
                        </p>
                    </li>
                    <%
                        }
                    %>
                </ul>
            </div>
        </div>
    </div>
    <div style="clear:both; height: 40px"></div>
</div>