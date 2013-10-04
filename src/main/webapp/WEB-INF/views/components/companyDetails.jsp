<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="roundedbox columntext white">
    <span class="header">Aangeboden door</span>
    <a href="/company/${course.company.id}">${course.company.name}</a><br/>
    ${course.company.address}<br/>
    ${course.company.zipCode} ${course.company.city}
    <c:if test="${course.company.hasLogo()}">
        <img src="${course.company.getNormalLogoUrl()}" class="company_logo"/>
    </c:if>
</div>



<div class="roundedbox columntext">
    <span class="header">Andere cursussen van ${course.company.name}</span>
    <ul>
    <c:forEach items="${otherCourses.courses}" var="course">
        <li><a href="${course.url}">${course.name}</a></li>
    </c:forEach>
    </ul>
</div>