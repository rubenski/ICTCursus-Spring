<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="default-block">
    <a href="/admin/users/invite"><spring:message code="user.invite"/></a>
</div>
<div class="default-block">
<c:choose>
    <c:when test="${numberOfUsers > 0}">

            <table cellpadding="4" width="600">
                <tr>
                    <th><spring:message code="management.home.coursetable.courseheader"/></th>
                    <th><spring:message code="management.home.coursetable.description"/></th>
                </tr>
                <c:forEach items="${courses}" var="course">
                    <tr>
                        <td>
                            <a href="/admin/course/${course.id}">${course.name}</a>
                        </td>
                        <td>
                                ${fn:substring(course.shortDescription, 0, 50)}...
                        </td>
                    </tr>
                </c:forEach>
            </table>

    </c:when>
    <c:otherwise>
        <spring:message code="management.home.nootherusers.message"/>
    </c:otherwise>
</c:choose>
</div>
