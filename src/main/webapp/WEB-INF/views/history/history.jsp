<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- page content -->
<div class="right_col" role="main">
	<div class="">
		<div class="clearfix"></div>

		<div class="col-md-12 col-sm-12  ">
			<div class="x_panel">
				<div class="x_title">
					<h2>History</h2>

					<div class="clearfix"></div>
				</div>

				<div class="x_content">
					<div class="container" style="padding: 48px;">
						<form:form modelAttribute="searchForm"
							cssClass="form-horizontal form-label-left"
							servletRelativeAction="/product-in-stock/list/1" method="POST">

							<div class="item form-group">
								<label for="code"
									class="col-form-label col-md-3 col-sm-3 label-align">Code</label>
								<div class="col-md-6 col-sm-6 ">
									<form:input path="productInfo.code" cssClass="form-control" />
								</div>
							</div>
							
							<div class="item form-group">
								<label class="col-form-label col-md-3 col-sm-3 label-align"
									for="category">Category </label>
								<div class="col-md-6 col-sm-6 ">
									<form:input path="productInfo.category.name" cssClass="form-control" />
								</div>
							</div>
							
							<div class="item form-group">
								<label class="col-form-label col-md-3 col-sm-3 label-align"
									for="action">Action </label>
								<div class="col-md-6 col-sm-6 ">
									<form:input path="actionName" cssClass="form-control" />
								</div>
							</div>
							
							<div class="item form-group">
								<label class="col-form-label col-md-3 col-sm-3 label-align"
									for="type">Type </label>
								<div class="col-md-6 col-sm-6 ">
									<form:select path="type" cssClass="form-control">
										<form:options items="${mapType}"/>
									</form:select>
								</div>
							</div>

							<div class="item form-group">
								<div class="col-md-6 col-sm-6 offset-md-3">
									<button type="submit" class="btn btn-success">Search</button>
								</div>
							</div>

						</form:form>
					</div>

					<div class="table-responsive">
						<table class="table table-striped jambo_table bulk_action">
							<thead>
								<tr class="headings">
									<th class="column-title">#</th>
									<th class="column-title">Category</th>
									<th class="column-title">Code</th>
									<th class="column-title">Name</th>
									<th class="column-title">Quantity</th>
									<th class="column-title">Price</th>
									<th class="column-title">Type</th>
									<th class="column-title">Action</th>
								</tr>
							</thead>

							<tbody>
								<c:forEach items="${products}" var="product" varStatus="loop">

									<c:choose>
										<c:when test="${loop.index%2==0 }">
											<tr class="even pointer">
										</c:when>
										<c:otherwise>
											<tr class="odd pointer">
										</c:otherwise>
									</c:choose>
									<td class=" ">${pageInfo.getOffset() + loop.index+1}</td>
									<td class=" ">${history.productInfo.category.name}</td>
									<td class=" ">${history.productInfo.code}</td>
									<td class=" ">${history.productInfo.name}</td>
									<td class=" ">${history.qty}</td>
									<td class=" ">${history.price}</td>
									<c:choose>
										<c:when test="${history.type == 1}">
											<td>Nhập hàng</td>>
										</c:when>
										<c:otherwise>
											<td>Xuất hàng</td>
										</c:otherwise>
									</c:choose>
									<td class=" ">${history.actionName}</td>
									</tr>
								</c:forEach>

							</tbody>
						</table>
						<jsp:include page="../layout/paging.jsp"></jsp:include>
					</div>


				</div>
			</div>
		</div>
	</div>
</div>
<!-- /page content -->
<script type="text/javascript">
	function gotoPage(page) {
		$('#searchForm').attr('action',
				'<c:url value="/product-in-stock/list/"/>' + page);
		$('#searchForm').submit();
	}
	$(document).ready(function() {
		processMessage();
	});
	function processMessage() {
		var msgSuccess = '${msgSuccess}';
		var msgError = '${msgError}';
		if (msgSuccess) {
			new PNotify({
				title : ' Success',
				text : msgSuccess,
				type : 'success',
				styling : 'bootstrap3'
			});
		}
		if (msgError) {
			new PNotify({
				title : ' Error',
				text : msgError,
				type : 'error',
				styling : 'bootstrap3'
			});
		}
	}
</script>