<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="default-block">
    <h2><spring:message code="admin.your.articles"/></h2>
    <c:choose>
        <c:when test="${numberOfArticles > 0}">

            <table cellpadding="4" width="600">
                <tr>
                    <th><spring:message code="generic.message.title"/></th>
                    <th><spring:message code="generic.message.author"/></th>
                    <th><spring:message code="admin.articles.firstpublished"/></th>

                </tr>
                <c:forEach items="${articles}" var="article">
                    <tr>
                        <td>
                            <a href="/admin/articles/edit/${article.id}">${article.title}</a>
                        </td>
                        <td>
                             ${article.author.fullNameFormal}
                        </td>
                        <td>
                             <fmt:formatDate value="${article.firstPublicationDate}" type="date" pattern="dd-MM-yyyy"/>
                        </td>
                    </tr>
                </c:forEach>
            </table>

        </c:when>
        <c:otherwise>
            <spring:message code="management.home.noarticles.message"/>
        </c:otherwise>
    </c:choose>
</div>

<div class="default-block">
    <h2><spring:message code="admin.articles.suggest"/></h2>
    <a href="/admin/articles/suggest"><spring:message code="admin.articles.suggest"/></a>
</div>
