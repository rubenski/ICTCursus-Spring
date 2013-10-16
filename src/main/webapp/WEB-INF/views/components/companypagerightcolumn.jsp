<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="right" class="roundedbox">

    <c:if test="${hasCourses}">

        <span class="header">Cursussen van ${company.name}</span>
        <ul>
            <c:forEach items="${courses}" var="course">
                <li><a href="${course.url}">${course.name}</a></li>
            </c:forEach>
        </ul>

    </c:if>

</div>