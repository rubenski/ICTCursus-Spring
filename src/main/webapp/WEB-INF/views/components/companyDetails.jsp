<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="roundedbox columntext white">
    <span class="header">Aangeboden door</span>
    <c:choose>
        <c:when test="${course.company.productSettings.companyInfoActive}">
            <a href="/company/${course.company.id}"><b>${course.company.name}</b></a><br/>
            ${course.company.address}<br/>
            ${course.company.zipCode} ${course.company.city}
            <c:if test="${course.company.hasLogo()}">
                <img src="${course.company.getNormalLogoUrl()}" class="company_logo"/>
            </c:if>
        </c:when>
        <c:otherwise>
            ${course.company.name}<br/>
        </c:otherwise>
    </c:choose>
</div>

<c:if test="${course.company.productSettings.externalCourseLinksActive && course.hasLinkToSite()}">
    <div class="roundedbox columntext white">
        <a href="${course.linkToSite}" target="_blank" id="linktosite">Bekijk deze cursus bij ${course.company.name}</a>
    </div>
</c:if>


<c:if test="${hasOtherCourses && course.company.productSettings.companyInfoActive}">
    <div class="roundedbox columntext">
        <span class="header">Andere cursussen van ${course.company.name}</span>
        <ul>
            <c:forEach items="${otherCourses.courses}" var="course">
                <li><a href="${course.url}">${course.name}</a></li>
            </c:forEach>
        </ul>
    </div>
</c:if>
