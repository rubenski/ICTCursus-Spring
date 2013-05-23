<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class="default-block">
    <h2><spring:message code="sysadmin.article.suggest.header"/></h2>
    <c:choose>
        <c:when test="${numberOfSuggestions > 0}">

            <table cellpadding="4" width="600">
                <tr>
                    <th>ID</th>
                    <th><spring:message code="generic.userMessage.title"/></th>
                    <th><spring:message code="generic.userMessage.name"/></th>
                    <th><spring:message code="generic.userMessage.company"/></th>
                    <th><spring:message code="generic.userMessage.date"/></th>
                </tr>
                <c:forEach items="${suggestions}" var="suggestion">
                    <tr>
                        <td>
                            <a href="/admin/sys/articlesuggestions/${suggestion.id}">${suggestion.id}</a>
                        </td>
                        <td>
                            ${suggestion.suggestedTitle}
                        </td>
                        <td>
                            <a href="/admin/sys/userprofile/${suggestion.suggester.id}">${suggestion.suggester.fullNameInformal}</a>
                        </td>
                        <td>
                            ${suggestion.suggester.company.name}
                        </td>
                        <td>
                            <fmt:formatDate value="${suggestion.created.time}" type="date" pattern="dd-MM-yyyy HH:mm:ss"/>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <spring:message code="sysadmin.articlesuggestion.no.sugesstions"/>
        </c:otherwise>
    </c:choose>
</div>
