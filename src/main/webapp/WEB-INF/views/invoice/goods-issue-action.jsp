<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- page content -->
<div class="right_col" role="main">
	<div class="">
		<div class="page-title">
			<div class="title_left">
				<h2>${titlePage}</h2>
				>
			</div>
		</div>
		<div class="clearfix"></div>
		<div class="row">
			<div class="col-md-12 col-sm-12 ">
				<div class="x_panel">
					<div class="x_content">
						<br />
						<form:form modelAttribute="modelForm"
							cssClass="form-horizontal form-label-left"
							servletRelativeAction="/goods-issue/save" method="POST">
							<form:hidden path="id"/>
							<form:hidden path="createDate"/>
							<form:hidden path="activeFlag"/>

							<div class="item form-group">
								<label class="col-form-label col-md-3 col-sm-3 label-align"
									for="code">Code <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 ">
									<form:input path="code" cssClass="form-control" disabled="${viewOnly}"/>
									<div class="has-error">
										<form:errors path="code" cssClass="help-block"></form:errors>
									</div>
								</div>
							</div>
							
							<div class="item form-group">
								<label class="col-form-label col-md-3 col-sm-3 label-align"
									for="productId">Product <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 ">
									<c:choose>
										<c:when test="${!viewOnly}">
											<form:select path="productId" cssClass="form-control">
												<form:options items="${mapProduct}"/>
											</form:select>
											<div class="has-error">
												<form:errors path="productId" cssClass="help-block"></form:errors>
											</div>
										</c:when>
										<c:otherwise>
											<form:input path="productInfo.name" disabled="true" cssClass="form-control"/>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
							<div class="item form-group">
								<label for="qty"
									class="col-form-label col-md-3 col-sm-3 label-align">Quantity</label>
								<div class="col-md-6 col-sm-6 ">
									<form:input path="qty" cssClass="form-control" disabled="${viewOnly}"/>
									<div class="has-error">
										<form:errors path="qty" cssClass="help-block"></form:errors>
									</div>
								</div>
							</div>
							
							<div class="item form-group">
								<label for="price"
									class="col-form-label col-md-3 col-sm-3 label-align">Price</label>
								<div class="col-md-6 col-sm-6 ">
									<form:input path="price" cssClass="form-control" disabled="${viewOnly}"/>
									<div class="has-error">
										<form:errors path="price" cssClass="help-block"></form:errors>
									</div>
								</div>
							</div>

							<div class="ln_solid"></div>
							<div class="item form-group">
								<div class="col-md-6 col-sm-6 offset-md-3">
									<button class="btn btn-primary" type="button" onclick="cancel();">Cancel</button>
									<c:if test="${!viewOnly}">
										<button class="btn btn-primary" type="reset">Reset</button>
										<button type="submit" class="btn btn-success">Submit</button>
									</c:if>
								</div>
							</div>

						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- /page content -->
<script type="text/javascript">
$(document).ready(function(){
	$('#goods-issuelistId').addClass('current-page').siblings().removeClass('current-page');
	var parent = $('#goods-issuelistId').parent('li');
	parent.addClass('active').siblings().removeClass('active');
	$('#goods-issuelistId').parents().show();
});
function cancel(){
	window.location.href='<c:url value="/goods-issue/list"/>'
}
</script>