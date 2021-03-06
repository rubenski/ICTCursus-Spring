<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- <jsp:include page="../components/admincoursestabs.jsp"/>  --%>
<div class="default-block">
    <h2><spring:message code="admin.your.courses"/></h2>
    <c:choose>
        <c:when test="${numberOfCourses > 0}">

            <table cellpadding="4" width="600">
                <tr>
                    <th><spring:message code="generic.userMessage.course"/></th>
                    <th><spring:message code="admin.articles.firstpublished"/></th>
                    <th><spring:message code="admin.articles.currentlypublished"/></th>
                </tr>
                <c:forEach items="${courses}" var="course">
                    <tr>
                        <td>
                            <a href="/admin/course/${course.id}">${course.name}</a>
                        </td>
                        <td>
                            <fmt:formatDate value="${course.firstPublished.time}" type="date" pattern="dd-MM-yyyy HH:mm:ss"/>
                        </td>
                         <td>
                            <c:if test="${course.published}">
                                <spring:message code="generic.message.yes"/>
                            </c:if>
                             <c:if test="${!course.published}">
                                <spring:message code="generic.message.no"/>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>

        </c:when>
        <c:otherwise>
            <spring:message code="management.home.nocourses.message"/>
        </c:otherwise>
    </c:choose>
</div>
<div class="default-block">
    <h2><spring:message code="admin.add.course"/></h2>
    <a href="/admin/course/add"><spring:message code="admin.add.course.linktext"/></a>
</div>

