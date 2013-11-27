<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- Determine the linkText of the first link --%>

<c:set var="view" value="<%=request.getParameter("viewType") %>" />
<c:choose>
    <c:when test="${view == 'menu'}">
        <c:set var="linkText" value="${article.title}"/>
    </c:when>
    <c:otherwise>
        <c:set var="linkText" value="1"/>
    </c:otherwise>
</c:choose>

<ul>

    <c:choose>
        <c:when test="${firstPage}">
            <li><strong>${linkText}</strong></li>
        </c:when>
        <c:otherwise>
            <li><a href="${article.url}">${linkText}</a></li>
        </c:otherwise>
    </c:choose>

    <c:forEach items="${article.pages}" var="page">

        <c:choose>
            <c:when test="${view == 'menu'}">
                <c:set var="linkText" value="${page.title}"/>
            </c:when>
            <c:otherwise>
                <c:set var="linkText" value="${page.position + 1}"/>
            </c:otherwise>
        </c:choose>

        <c:choose>
            <c:when test="${page.id == currentArticlePage.id && !firstPage}">
                <li><strong>${linkText}</strong></li>
            </c:when>
            <c:otherwise>
                <li><a href="${page.url}">${linkText}</a></li>
            </c:otherwise>
        </c:choose>

    </c:forEach>
</ul>