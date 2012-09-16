<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<ul>
    <c:forEach items="${categories}" var="category">
        <li>${category.name}
            <ul>
                <c:forEach items="${category.courses}" var="course">
                    <li>${course.name}</li>
                </c:forEach>
            </ul>
        </li>
    </c:forEach>
</ul>