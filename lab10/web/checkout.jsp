<%--
  Created by IntelliJ IDEA.
  User: Tran
  Date: 12/14/2019
  Time: 10:30 PM
  To change this template use File | Settings | File Templates.
--%>
<c:set var='view' value='/checkout' scope='session'/>

<!-- JS Files -->
<script src="js/jquery.min.js"></script>
<script src="js/custom.js"></script>
<script src="js/slides/slides.min.jquery.js"></script>
<script src="js/cycle-slider/cycle.js"></script>
<script src="js/nivo-slider/jquery.nivo.slider.js"></script>
<script src="js/tabify/jquery.tabify.js"></script>
<script src="js/prettyPhoto/jquery.prettyPhoto.js"></script>
<script src="js/twitter/jquery.tweet.js"></script>
<script src="js/scrolltop/scrolltopcontrol.js"></script>
<script src="js/portfolio/filterable.js"></script>
<script src="js/modernizr/modernizr-2.0.3.js"></script>
<script src="js/easing/jquery.easing.1.3.js"></script>
<script src="js/kwicks/jquery.kwicks-1.5.1.pack.js"></script>
<script src="js/swfobject/swfobject.js"></script>
<!-- FancyBox -->
<link rel="stylesheet" type="text/css" href="js/fancybox/jquery.fancybox.css" media="all">
<script src="js/fancybox/jquery.fancybox-1.2.1.js"></script>

<jsp:useBean id="errorForm" scope="request" type="java.lang.String"/>
<c:set var="errorForm" value="${errorForm}"/>

<div id="container">
    <c:if test="${not empty errorForm}">
        <div class="alert alert-warning" role="alert">
                ${errorForm}
        </div>
    </c:if>

    <div class="row mb-3">
        <div class="col-7">
            <div class="heading_bg">
                <h2>Checkout</h2>
            </div>
            <div>In order to purchase the items in your shopping cart, please provide us with the following information:
            </div>
            <form id="checkoutForm" action="confirmation" method="post" class="mt-3">
                <div class="form-group row">
                    <label for="inputName" class="col-sm-2 col-form-label">
                        Name <span class="required">*</span>
                    </label>
                    <div class="col-sm-10">
                        <input type="text"
                               class="form-control"
                               id="inputName"
                               placeholder="Enter your name"
                               name="name"
                               value="${param.name}"
                               required>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="inputEmail" class="col-sm-2 col-form-label">
                        Email <span class="required">*</span>
                    </label>
                    <div class="col-sm-10">
                        <input type="email"
                               class="form-control"
                               id="inputEmail"
                               aria-describedby="email"
                               placeholder="Enter your email"
                               name="email"
                               value="${param.email}"
                               required>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="inputPhone" class="col-sm-2 col-form-label">
                        Phone <span class="required">*</span>
                    </label>
                    <div class="col-sm-10">
                        <input type="number"
                               class="form-control"
                               id="inputPhone"
                               aria-describedby=""
                               placeholder="Enter your phone"
                               name="phone"
                               value="${param.phone}"
                               required>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="inputAddress" class="col-sm-12 col-form-label">
                        Address
                        <span class="required">*</span>
                    </label>
                    <div class="col-sm-12">
                        <input type="text"
                               class="form-control"
                               id="inputAddress"
                               placeholder="Enter your address"
                               name="address"
                               value="${param.address}"
                               required>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="inputCity" class="col-sm-12 col-form-label">
                        City Region <span class="required">*</span>
                    </label>
                    <div class="col-sm-12">
                        <input type="text"
                               class="form-control"
                               id="inputCity"
                               placeholder="Enter your city"
                               name="cityRegion"
                               value="${param.cityRegion}"
                               required>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="inputCreditCard" class="col-sm-12 col-form-label">
                        Credit Card Number <span class="required">*</span>
                    </label>
                    <div class="col-sm-12">
                        <input type="number" class="form-control"
                               id="inputCreditCard"
                               name="creditCard"
                               placeholder="Enter your credit card number" value="${param.creditCard}"
                               required>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary">Submit purchase</button>
            </form>
        </div>
        <!-- END First Column -->
        <div class="col-5">
            <div class="heading_bg">
                <h2>Order Information</h2>
            </div>
            <div>Next-working day delivery is guaranteed</div>
            <div>
                A
                <fmt:formatNumber type="currency" currencySymbol="&euro; " value="${initParam.deliveryFee}"/>
                delivery surcharge is applied to all purchase orders
            </div>
            <table>
                <th>Total</th>
                <th>Delivery Surcharge</th>
                <th>Credit Total</th>
                <tr>
                    <td><fmt:formatNumber type="currency" currencySymbol="&dollar; " value="${cart.subtotal}"/></td>
                    <td><fmt:formatNumber type="currency" currencySymbol="&dollar; "
                                          value="${initParam.deliveryFee}"/></td>
                    <td><fmt:formatNumber type="currency" currencySymbol="&dollar; " value="${cart.total}"/></td>
                </tr>
            </table>
            <!-- END Second Column -->
            <div style="clear:both; height: 40px"></div>
        </div>
        <!-- close container -->
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        $("#checkoutForm").validate({
            rules: {
                name: "required",
                email: {
                    required: true,
                    email: true
                },
                phone: {
                    required: true,
                    number: true,
                    minlength: 9
                },
                address: {
                    required: true
                },
                creditCard: {
                    required: true,
                    creditCard: true
                }
            }
        });
    });
</script>

