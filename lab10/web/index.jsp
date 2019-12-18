<%--
  Created by IntelliJ IDEA.
  User: Tran
  Date: 11/20/2019
  Time: 11:18 AM
  To change this template use File | Settings | File Templates.
--%>

<div id="container">
    <div class="heading_bg">
        <h2><fmt:message key="bestSeller" bundle="${lang}"/></h2>
    </div>
    <!-- tab panes -->
    <div id="prod_wrapper">
        <div id="" class="mb-3">
            <%--@elvariable id="newProducts" type="java.util.List"--%>
            <jsp:useBean id="currentProduct" scope="request" type="main.java.hust.entity.ProductEntity"/>
            <c:set var="currentProduct" scope="request" value="${currentProduct}"/>
            <div class="row d-flex justify-content-center">
                <div class="col-6" style="text-align: center; position: relative">
                    <img class="mx-auto"
                         style="display: inline-block"
                         src="img/demo/${currentProduct.image}"
                         alt=""
                         height="200px"/>
                </div>

                <div class="col-6">
                    <h5>${currentProduct.name}</h5>
                    <p>${currentProduct.description}</p>
                    <p style="text-align: left">
                        <a href="detail?productId=${currentProduct.productId}" class="button">
                            <fmt:message key="moreInfo" bundle="${lang}"/>
                        </a>
                        <a href="addToCart?productId=${currentProduct.productId}" class="button">
                            <fmt:message key="buyNow" bundle="${lang}"/>
                        </a>
                    </p>
                </div>
            </div>
        </div>
        <!-- END tab panes -->
        <!-- navigator -->
        <div id="prod_nav" class="pb-2">
            <ul>
                <jsp:useBean id="bestSeller" scope="request" type="java.util.List"/>
                <c:forEach var="product" items="${bestSeller}" varStatus="loop">
                    <li class="d-flex justify-content-center">
                        <a href="?index=${loop.index}">
                            <img src="img/demo/${product.getImage()}" width="85" height="85" alt="">
                            <strong>${product.getName()}</strong>
                                ${product.getPrice()}
                        </a>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <!-- close navigator -->
    </div>
    <!-- END prod wrapper -->

    <div class="row">
        <!-- First Column -->
        <div class="col-3">
            <div class="heading_bg">
                <h2>Mac</h2>
            </div>
            <a href="category?id=1">
                <img src="img/demo/2.jpg" width="217" alt="">
            </a>
        </div>
        <!-- Second Column -->
        <div class="col-3">
            <div class="heading_bg">
                <h2>iPad</h2>
            </div>
            <a href="category?id=2"><img src="img/demo/4.jpg" width="217" alt=""></a>
        </div>
        <!-- Third Column -->
        <div class="col-3">
            <div class="heading_bg">
                <h2>iPhone</h2>
            </div>
            <a href="category?id=3"><img src="img/demo/3.jpg" width="217" alt=""></a>
        </div>
        <!-- Fourth Column -->
        <div class="col-3">
            <div class="heading_bg">
                <h2>Accessories</h2>
            </div>
            <a href="category?id=4"><img src="img/demo/1.jpg" width="217" alt=""></a>
        </div>
    </div>

</div>
