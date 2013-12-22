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


<h2 class="mainsubheader">Online cursussen en artikelen over ${category.name}</h2>

<c:forEach items="${articles}" var="article">
    <div class="course-list-item">
        <c:if test="${article.hasPicture()}">
            <div class="picture">
                <img src="/article/picture/${article.id}.png"/>
            </div>
        </c:if>
        <div class="description">
            <a href="${article.url}"><h3>${article.title}</h3></a>
            <p>${article.teaser}</p>
        </div>
    </div>
</c:forEach>


<%-- paging --%>
<c:if test="${resultListing.hasPaging()}">
    <ul class="horizontal-list">
        <c:forEach items="${resultListing.getPagingUrls()}" var="pagingUrl" varStatus="loop">
            <li><a href="${pagingUrl}">${loop.count}</a></li>
        </c:forEach>
    </ul>
</c:if>








