<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="pagination pagination-split">
	<c:forEach begin="1" end="${pageInfo.totalPages}" varStatus="loop">
		<c:choose>
			<c:when test="${pageInfo.indexPage== loop.index}">
				<li class="page-item active"><a class="page-link" href="javascript:void(0);">${loop.index}</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="javascript:void(0);" onclick="gotoPage(${loop.index});">${loop.index}</a></li>
			</c:otherwise>
		</c:choose>

		<li></li>
	</c:forEach>
</ul>