<%--
  Created by IntelliJ IDEA.
  User: Tran
  Date: 12/15/2019
  Time: 4:03 PM
  To change this template use File | Settings | File Templates.
--%>
<jsp:useBean id="status" scope="request" type="java.lang.String"/>
<c:set var="status" scope="request" value="${status}"/>
<div id="container">
    <%
        if ("success".equals(status)) {
    %>
    <div class="alert alert-success" role="alert">
        Add product success
    </div>
    <%
    } else if ("failed".equals(status)) {
    %>
    <div class="alert alert-warning" role="alert">
        Add product failed!!
    </div>
    <%
        }
    %>
    <div class="row mb-3">
        <div class="col-12">
            <div class="heading_bg">
                <h2> Product's Information</h2>
            </div>

            <form id="addProductForm" action="addProduct" method="post" class="mt-3">
                <div class="form-group row">
                    <label for="inputName" class="col-sm-2 col-form-label">
                        Name <span class="required">*</span>
                    </label>
                    <div class="col-sm-10">
                        <input type="text"
                               class="form-control"
                               id="inputName"
                               placeholder="Mac Mini MGEM2"
                               name="name"
                               required>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="inputPrice" class="col-sm-2 col-form-label">
                        Price <span class="required">*</span>
                    </label>
                    <div class="col-sm-10">
                        <input type="number"
                               step="0.01"
                               class="form-control"
                               id="inputPrice"
                               placeholder="499.00 $"
                               name="price"
                               required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputCategory">Category</label>
                    <select class="form-control" id="inputCategory" name="category" required>
                        <option>Mac</option>
                        <option>iPad</option>
                        <option>iPhone</option>
                        <option>Accessories</option>
                    </select>
                </div>
                <%--                <div class="row">--%>
                <%--                    <div class="col-6">--%>
                <%--                        <form>--%>
                <%--                            <div class="form-group">--%>
                <%--                                <label for="exampleFormControlFile1">Upload primary image</label>--%>
                <%--                                <input type="file" class="form-control-file" id="exampleFormControlFile1" name="image">--%>
                <%--                            </div>--%>
                <%--                        </form>--%>
                <%--                    </div>--%>

                <%--                    <div class="col-6">--%>
                <%--                        <form>--%>
                <%--                            <div class="form-group">--%>
                <%--                                <label for="exampleFormControlFile2">Upload thumbnail image</label>--%>
                <%--                                <input type="file" class="form-control-file" id="exampleFormControlFile2"--%>
                <%--                                       name="thumbnailImage">--%>
                <%--                            </div>--%>
                <%--                        </form>--%>
                <%--                    </div>--%>
                <%--                </div>--%>
                <div class="form-group row">
                    <label for="inputDescription" class="col-sm-12 col-form-label">
                        Description <span class="required">*</span>
                    </label>
                    <div class="col-sm-12">
                        <textarea class="form-control"
                                  id="inputDescription"
                                  name="description"
                                  placeholder="Garanty 2 years. Full Box and accessories"
                                  rows="3"
                                  maxlength="200"
                                  required></textarea>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="inputDescriptionDetail" class="col-sm-12 col-form-label">
                        Detail Description
                    </label>
                    <div class="col-sm-12">
                        <textarea class="form-control"
                                  id="inputDescriptionDetail"
                                  name="descriptionDetail"
                                  placeholder="Mac mini is an affordable powerhouse that packs the entire Mac experience into a 7.7-inch-square frame. Just connect your own display, keyboard and mouse and you're ready to make big things happen."
                                  rows="3"
                                  maxlength="200"></textarea>
                    </div>
                </div>

                <button type="submit" class="btn btn-primary">Add product</button>
            </form>
        </div>
    </div>
</div>
