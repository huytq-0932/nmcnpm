<%--
  Created by IntelliJ IDEA.
  User: Tran
  Date: 12/16/2019
  Time: 9:45 AM
  To change this template use File | Settings | File Templates.
--%>

<jsp:useBean id="orders" scope="request" type="java.util.List"/>
<c:set var="orders" scope="request" value="${orders}"/>
<jsp:useBean id="deleteMessage" scope="request" type="java.lang.String"/>
<c:set var="deleteMessage" scope="request" value="${deleteMessage}"/>
<div id="container">
    <c:if test="${ not empty deleteMessage}">
        <div class="alert alert-info" role="alert"><%=deleteMessage%>
        </div>
    </c:if>

    <div class="heading_bg">
        <h2>
            <fmt:message key="order" bundle="${lang}"/>
        </h2>
    </div>
    <c:forEach var="order" items="${orders}">
        <c:set var="orderedProducts" scope="request" value="${order.orderedProducts}"/>
        <c:set var="orderRecord" scope="request" value="${order.orderRecord}"/>
        <c:set var="products" scope="request" value="${order.products}"/>
        <c:set var="customer" scope="request" value="${order.customer}"/>
        <div class="row">
            <div class="col-5">
                <div class="card" style="width: 100%">
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">
                            <strong>${customer.name}</strong>
                            <br>
                                ${customer.address}
                            <br>
                            <fmt:message key="city" bundle="${lang}"/> : ${customer.cityRegion}
                        </li>
                        <li class="list-group-item">
                            <strong><fmt:message key="email" bundle="${lang}"/></strong>
                            <br>
                                ${customer.email}
                        </li>
                        <li class="list-group-item">
                            <strong><fmt:message key="phone" bundle="${lang}"/></strong>
                            <br>
                                ${customer.phone}
                        </li>
                    </ul>
                </div>
            </div>
            <div class="col-7">
                <form action="deleteOrder" method="POST">
                    <label>
                        <input value="${orderRecord.orderId}" name="orderId" hidden>
                    </label>
                    <button type="submit" class="btn btn-danger">Delete order</button>
                </form>

                <table class="table table-bordered">
                    <thead>
                    <th scope="col"><fmt:message key="product" bundle="${lang}"/></th>
                    <th scope="col"><fmt:message key="quantity" bundle="${lang}"/></th>
                    <th scope="col"><fmt:message key="price" bundle="${lang}"/></th>
                    </thead>
                    <tbody>
                    <c:forEach var="orderedProduct" items="${orderedProducts}" varStatus="iter">
                        <tr>
                            <td> ${products[iter.index].name} </td>
                            <td> ${orderedProduct.quantity} </td>
                            <td>
                                <fmt:formatNumber type="currency"
                                                  currencySymbol="&dollar; "
                                                  value="${products[iter.index].price* orderedProduct.quantity}"/>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <th colspan="2" scope="row">
                            <fmt:message key="surcharge" bundle="${lang}"/>:
                        </th>
                        <td>
                            <fmt:formatNumber type="currency"
                                              currencySymbol="&euro; "
                                              value="${initParam.deliveryFee}"/></td>
                    </tr>
                    <tr>
                        <th colspan="2"><fmt:message key="total" bundle="${lang}"/>:</th>
                        <td>
                            <fmt:formatNumber type="currency"
                                              currencySymbol="&euro; "
                                              value="${orderRecord.amount}"/></td>
                    </tr>
                    <tr>
                        <th colspan="3"><fmt:message key="dateProcessed" bundle="${lang}"/>:
                                ${orderRecord.dateCreated}
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <hr>
    </c:forEach>

</div>

