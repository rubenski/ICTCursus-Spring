<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


Updated ${numberOfCourses} courses
<table>
<c:forEach items="${updatedCourses}" var="course">
    <tr>
        <td>${course.id}<br><br></td>
    </tr>
</c:forEach>

</table>