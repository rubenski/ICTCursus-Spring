<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:forEach items="${courses}" var="course">
    <a href="${course.url}" class="courselink">
        <div class="course-list-item">
            <div class="logo">
                <img src="/static/img/logo/${course.company.logo}"/>
            </div>

            <div class="description">
                <h2>${course.name}</h2>

                <p>
                    ${course.listDescription}
                </p>
            </div>
        </div>
    </a>
</c:forEach>
