<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<jsp:include page="../components/breadcrumb.jsp"/>
<h1><spring:message code="generic.message.courses"/> ${category.name}</h1>
<%--
<sec:authorize access="hasRole('ROLE_PERM_edit_company_advertisements')">
    <c:if test="${showLightboxLink}">
        <div id="advertising-overlay-link-container">
            <a href="#" id="${category.id}" class="lightbox-trigger"><spring:message code="category.yourcourse.here"/></a>
        </div>
    </c:if>
</sec:authorize>
--%>
<c:forEach items="${highlighted}" var="course">
    <c:set var="course" scope="request" value="${course}" />
    <c:set var="highlightMe" scope="request" value="1" />
    <jsp:include page="courselistitem.jsp"/>
</c:forEach>

<jsp:include page="courselisting.jsp">
    <jsp:param name="courses" value="${searchResult}"/>
</jsp:include>





