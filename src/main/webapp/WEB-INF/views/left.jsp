<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div id="left">
    <c:if test="${articleNav}">
        <jsp:include page="components/articleMenu.jsp"/>
    </c:if>
    <c:if test="${categoriesNav}">
        <jsp:include page="components/categoriesNavigation.jsp"/>
    </c:if>
    <c:if test="${facetedSearch}">
        <jsp:include page="components/facetedSearch.jsp"/>
    </c:if>
    <c:if test="${aboutIctCursusPage}">
        <jsp:include page="components/aboutMenu.jsp"/>
    </c:if>

    <c:if test="${isCourse}">
        <jsp:include page="components/companyDetails.jsp"/>
    </c:if>

</div>