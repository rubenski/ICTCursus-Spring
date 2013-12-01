<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<div class="default-block">
    <h2><spring:message code="admin.sections.courses"/></h2>
    <c:choose>
        <c:when test="${numberOfCourses > 0}">

            <table cellpadding="4" width="600">
                <tr>
                    <th><spring:message code="generic.userMessage.course"/></th>
                    <th><spring:message code="generic.userMessage.company"/></th>
                    <th><spring:message code="generic.message.category"/></th>
                </tr>
                <c:forEach items="${courses}" var="course">
                    <tr>
                        <td>
                            <a href="/admin/sys/course/${course.id}">${course.name}</a>
                        </td>
                        <td>
                            ${course.company.name}
                        </td>
                        <td>
                            ${course.category.name}
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

