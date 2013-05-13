<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<h1><spring:message code="generic.message.courses"/> ${category.name}</h1>

<sec:authorize access="hasRole('ROLE_PERM_edit_company_advertisements')">
    <div id="advertising-overlay-link-container">
        <a href="#" id="${category.id}" class="lightbox-trigger"><spring:message code="category.yourcourse.here"/></a>
    </div>
</sec:authorize>

<c:forEach items="${courses}" var="course">
    <div class="course-list-item">
        <div class="description">
            <a href="${course.url}" class="courselink"><h2>${course.name}</h2></a>
            <p>
                    ${course.listDescription}
            </p>
        </div>
        <div class="logo">
            <c:if test="${course.company.hasLogo()}">
                <img src="/logo/small/${course.company.id}-cursusbedrijf.png" title="<spring:message code='course.listing.courseby'/> ${course.company.name}"/>
            </c:if>
        </div>
    </div>
</c:forEach>
