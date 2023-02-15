<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="Dev 등록결과" name="title" />
</jsp:include>

<table class="table w-50 mx-auto" >
	<tr>
		<th scope="col">이름</th>
		<td>${dev.name}</td>
	</tr>
	<tr>
		<th scope="col">경력</th>
		<td>${dev.career}</td>
	</tr>
	<tr>
		<th scope="col">이메일</th>
		<td>${dev.email}</td>
	</tr>
	<tr>
		<th scope="col">성별</th>
		<td><c:if test="${dev.gender eq  'M'}">남</c:if>
		    <c:if test="${dev.gender eq  'F'}">여</c:if></td>
	</tr>
	<tr>
		<th scope="col">개발언어</th>
		<td><c:forEach items="${dev.lang}" var="lang" varStatus="i" >
			   <c:if test="${ ! i.last}">
		          ${lang} ,
		       </c:if>
		       <c:if test="${i.last}">
		          ${lang}
		       </c:if>  
		    </c:forEach>
		</td>
	</tr>
	<tr>
		<th scope="col">등록일</th>
		<td>
		  <fmt:parseDate value="${dev.createdAt}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="pd" type="both" />
		  <fmt:formatDate value="${pd}" pattern="yyyy년 MM월 dd일 hh시 mm분 ss초" /></td>
	</tr>
</table>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
