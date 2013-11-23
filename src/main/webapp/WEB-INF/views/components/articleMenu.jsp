<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<nav class="roundedbox">
    <span class="header"><spring:message code="generic.message.paginas"/></span>
    <ul>
        <c:choose>
            <c:when test="${firstPage}">
                <li><strong>${article.title}</strong></li>
            </c:when>
            <c:otherwise>
                <li><a href="${article.url}">${article.title}</a></li>
            </c:otherwise>
        </c:choose>

        <c:forEach items="${article.pages}" var="page">
            <c:choose>
                <c:when test="${page.id == currentArticlePage.id && !firstPage}">
                    <li><strong>${page.title}</strong></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${page.url}">${page.title}</a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </ul>
</nav>
