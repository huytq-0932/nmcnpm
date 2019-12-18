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
<div id="container" class="bg-white">
    <div class="heading_bg">
        <h2><%=selectedCategory.getName()%>
        </h2>
    </div>
    <div class="bg-white">
        <div id="portfolio" class="mt-2">
            <div>
                <div class="row">
                    <%
                        for (ProductEntity product : (List<ProductEntity>) categoryProducts) {
                    %>
                    <div class="col-4 mb-2">
                        <p>
                            <a title="<%=product.getName()%>"
                               href="images/<%=product.getImage()%>"
                               class="portfolio-item-preview"
                               data- rel="prettyPhoto"
                            >
                                <img src="images/<%=product.getImage()%>"
                                     alt=""
                                     width="210"
                                     height="145"
                                     class="portfolio-img pretty-box">
                            </a>
                        </p>
                        <h4><a href="detail?productId=<%=product.getProductId()%>"><%=product.getName()%>
                        </a></h4>
                        <p><%=product.getDescription()%>
                        </p>
                        <p style="text-align: left">
                            <a href="detail?productId=<%=product.getProductId()%>" class="button_small white">
                                See Details &raquo;
                            </a>
                        </p>
                    </div>
                    <%
                        }
                    %>
                </div>
            </div>
        </div>
    </div>
</div>