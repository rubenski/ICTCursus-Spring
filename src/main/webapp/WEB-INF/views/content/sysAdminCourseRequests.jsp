<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="default-block">
    <h2><spring:message code="admin.sections.requests"/></h2>
    <c:choose>
        <c:when test="${numberOfRequests > 0}">

            <table cellpadding="4" width="600">
                <tr>
                    <th>ID</th>
                    <th><spring:message code="admin.courserequests.requester"/></th>
                    <th><spring:message code="generic.userMessage.course"/></th>
                    <th><spring:message code="generic.userMessage.company"/></th>
                    <th><spring:message code="generic.userMessage.date"/></th>
                </tr>
                <c:forEach items="${courseRequests}" var="courseRequest">
                    <tr>
                        <td>
                            <a href="/admin/sys/courserequests/${courseRequest.id}">${courseRequest.id}</a>
                        </td>
                        <td>
                            ${courseRequest.requesterName}
                        </td>
                        <td>
                            <a href="/admin/sys/course/${courseRequest.course.id}"> ${courseRequest.courseName}</a>
                        </td>
                        <td>
                            ${courseRequest.course.company.name}
                        </td>
                        <td>
                            <fmt:formatDate value="${courseRequest.created.time}" type="date" pattern="dd-MM-yyyy HH:mm:ss"/>
                        </td>
                    </tr>
                </c:forEach>
            </table>



        </c:when>
        <c:otherwise>
            <spring:message code="management.home.nocourserequests.message"/>
        </c:otherwise>
    </c:choose>
</div>
