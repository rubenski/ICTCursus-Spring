<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<jsp:include page="../components/admincoursestabs.jsp"/>
<div class="default-block">

    <h2><spring:message code="advertised.courses"/></h2>

    <c:choose>
        <c:when test="${numberOfCourses > 0}">

            <table cellpadding="4" width="600">
                <tr>
                    <th><spring:message code="generic.userMessage.course"/></th>
                    <th><spring:message code="highlighted.course.startdate"/></th>
                    <th><spring:message code="highlighted.course.enddate"/></th>
                    <th><spring:message code="generic.message.category"/></th>
                </tr>
                <c:forEach items="${highlightedCourses}" var="highlightedCourse">
                    <tr>
                        <td>${highlightedCourse.course.name}</td>
                        <td><fmt:formatDate value="${highlightedCourse.startTime.time}" type="date" pattern="dd-MM-yyyy"/></td>
                        <td><fmt:formatDate value="${highlightedCourse.endTime.time}" type="date" pattern="dd-MM-yyyy"/></td>
                        <td>${highlightedCourse.category.name}</td>
                    </tr>
                </c:forEach>
            </table>

        </c:when>
        <c:otherwise>
            <spring:message code="admin.no.highlighted.courses"/>
        </c:otherwise>
    </c:choose>

</div>

