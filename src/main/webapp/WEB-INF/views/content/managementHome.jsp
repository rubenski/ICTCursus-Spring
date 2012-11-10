<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:if test="${numberOfCourses > 0}">
    <table cellpadding="4" width="600">
        <c:forEach items="${courses}" var="course">
            <tr>
                <td>
                    <a href="/manage/course/${course.id}">${course.name}</a>
                </td>
                <td>
                    ${fn:substring(course.shortDescription, 0, 15)}
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
