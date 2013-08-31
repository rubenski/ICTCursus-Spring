<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<h1><spring:message code="generic.message.courses"/> ${category.name}</h1>

<sec:authorize access="hasRole('ROLE_PERM_edit_company_advertisements')">
    <c:if test="${showLightboxLink}">
        <div id="advertising-overlay-link-container">
            <a href="#" id="${category.id}" class="lightbox-trigger"><spring:message code="category.yourcourse.here"/></a>
        </div>
    </c:if>
</sec:authorize>

<c:forEach items="${highlighted}" var="course">
    <div class="course-list-item highlighted">
        <div class="description">
            <a href="${course.url}" class="courselink"><h2>${course.name}</h2></a>
            <p>
                    ${course.listDescription}
            </p>
        </div>
        <div class="logo">
            <c:if test="${course.company.hasLogo()}">
                <img src="${course.company.getNormalLogoUrl()}"
                     title="<spring:message code='course.listing.courseby'/> ${course.company.name}"/>
            </c:if>
        </div>
    </div>
</c:forEach>

<c:forEach items="${searchResult.courses}" var="course" varStatus="loop">

    <c:if test="${loop.last}">
        <c:set var="lastClass" value="last"/>
    </c:if>

    <div class="course-list-item ${lastClass}">
        <div class="description">
            <a href="${course.url}" class="courselink"><h2>${course.name}</h2></a>
            <p>
                    ${course.listDescription}
            </p>
        </div>
        <div class="logo">
            <c:if test="${course.company.hasLogo()}">
                <img src="${course.company.getNormalLogoUrl()}"
                     title="<spring:message code='course.listing.courseby'/> ${course.company.name}"/>
            </c:if>
        </div>
    </div>
</c:forEach>

<c:if test="${searchResult.getNumberOfResultPages() > 1}">
    <ul class="horizontal-list">
        <li><a href="/${category.urlTitle}">1</a></li>
    <c:forEach begin="1" end="${searchResult.getNumberOfResultPages()}" varStatus="loop">
        <li><a href="/${category.urlTitle}/${loop.count}">${loop.count + 1}</a></li>
    </c:forEach>
    </ul>
</c:if>



