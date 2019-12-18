<%--
  Created by IntelliJ IDEA.
  User: Tran
  Date: 12/14/2019
  Time: 10:32 PM
  To change this template use File | Settings | File Templates.
--%>
<div id="container">
    <div class="heading_bg">
        <h2>Confirmation</h2>
    </div>
    <div class="alert alert-secondary" role="alert">
        <p id="confirmationText">
            <strong><fmt:message key="successMessage" bundle="${lang}"/></strong>
            <br/>
            <br/>
            <fmt:message key="confirmationNumberMessage" bundle="${lang}"/>
            <strong>${orderRecord.confirmationNumber}</strong>
            <br>
            <fmt:message key="contactMessage" bundle="${lang}"/>
            <br><br>
            <fmt:message key="thankYouMessage" bundle="${lang}"/>
        </p>
    </div>
    <div class="row">
        <div class="col-8">
            <div class="heading_bg">
                <h3><fmt:message key="orderSummary" bundle="${lang}"/></h3>
            </div>
            <table>
                <th><fmt:message key="product" bundle="${lang}"/></th>
                <th><fmt:message key="quantity" bundle="${lang}"/></th>
                <th><fmt:message key="price" bundle="${lang}"/></th>
                <c:forEach var="orderedProduct" items="${orderedProducts}" varStatus="iter">
                    <tr>
                        <td>
                                ${products[iter.index].name}
                        </td>
                        <td>
                                ${orderedProduct.quantity}
                        </td>
                        <td>
                            <fmt:formatNumber type="currency"
                                              currencySymbol="&dollar; "
                                              value="${products[iter.index].price* orderedProduct.quantity}"/>
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="2"><strong><fmt:message key="surcharge" bundle="${lang}"/>:</strong></td>
                    <td>
                        <fmt:formatNumber type="currency"
                                          currencySymbol="&euro; "
                                          value="${initParam.deliveryFee}"/></td>
                </tr>
                <tr>
                    <td colspan="2"><strong><fmt:message key="total" bundle="${lang}"/>:</strong></td>
                    <td>
                        <fmt:formatNumber type="currency"
                                          currencySymbol="&euro; "
                                          value="${orderRecord.amount}"/></td>
                </tr>
                <tr>
                    <td colspan="3"><strong><fmt:message key="dateProcessed" bundle="${lang}"/>:</strong>
                        ${orderRecord.dateCreated}
                </tr>
            </table>
        </div>
        <div class="col-4">
            <div class="heading_bg">
                <h2>
                    <fmt:message key="deliveryAddress" bundle="${lang}"/>
                </h2>
            </div>
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
    </div>
</div>
