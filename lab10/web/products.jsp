<%@ page import="main.java.hust.entity.ProductEntity" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Tran
  Date: 11/17/2019
  Time: 10:03 PM
  To change this template use File | Settings | File Templates.
--%>

<jsp:useBean id="filteredProducts" scope="request" type="java.util.List"/>
<c:set var="filteredProducts" scope="request" value="${filteredProducts}"/>
<jsp:useBean id="activePage" scope="request" type="java.lang.Integer"/>
<c:set var="activePage" scope="request" value="${activePage}"/>
<jsp:useBean id="numberOfPages" scope="request" type="java.lang.Integer"/>
<c:set var="numberOfPages" scope="request" value="${numberOfPages}"/>
<jsp:useBean id="filter" scope="request" type="java.lang.String"/>
<c:set var="filter" scope="request" value="${filter}"/>

<div id="container">
    <div class="bg-white">
        <div class="heading_bg">
            <h2>Products </h2>
        </div>
    </div>
    <c:if test="${role == 'admin'}">
        <a href="addProduct">
            <button type="button" class="btn btn-primary">Add a new product</button>
        </a>
    </c:if>
    <nav class="navbar navbar-light bg-light justify-content-between">
        <a class="navbar-brand">
            <c:choose>
                <c:when test="${not empty filter}">
                    Search: ${filter}
                </c:when>
                <c:otherwise>
                    All products
                </c:otherwise>
            </c:choose>
            <c:if test="${not empty filter}">

            </c:if>
        </a>
        <form class="form-inline">
            <input class="form-control mr-sm-2" type="search" placeholder="Search name ..." aria-label="Search"
                   name="filter">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
    </nav>
    <div id="portfolio" class="mt-2">
        <div>
            <div class="row">
                <%
                    for (ProductEntity product : (List<ProductEntity>) filteredProducts) {
                %>
                <div class="col-4 mb-2">
                    <p>
                        <a title="<%=product.getName()%>"
                           href="images/<%=product.getImage()%>"
                           class="portfolio-item-preview"
                           data- rel="prettyPhoto">
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
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">
            <li class="page-item <% if (activePage == 1) {%> disabled <%}%>">
                <a class="page-link" href="?filter=${filter}&page=${activePage - 1}" tabindex="-1">Previous</a>
            </li>
            <li class="page-item"><a class="page-link" href="#">${activePage}</a></li>
            <li class="page-item <% if (activePage.equals(numberOfPages)){ %> disabled <%}%>">
                <a class="page-link" href="?filter=${filter}&page=${activePage + 1}">Next</a>
            </li>
        </ul>
    </nav>
    <div style="clear:both; height: 40px"></div>
</div>
