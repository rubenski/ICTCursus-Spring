<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="roundedbox text white">
    <span class="header">Aangeboden door</span>
    <a href="/company/${course.company.id}">${course.company.name}</a><br/>
    ${course.company.address}<br/>
    ${course.company.zipCode} ${course.company.city}
    <img src="${course.company.getNormalLogoUrl()}" class="company_logo"/>
</div>



<div class="roundedbox text">
    <span class="header">Andere cursussen van ${course.company.name}</span>
    <c:forEach items="${otherCourses.courses}" var="course">
        <a href="${course.url}">${course.name}</a><br>
    </c:forEach>
</div>