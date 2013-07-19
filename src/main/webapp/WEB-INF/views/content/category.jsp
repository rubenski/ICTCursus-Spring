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
            <c:if test="${course.company.normalLogo != null}">
                <img src="/logo/small/${course.company.id}-cursusbedrijf.png"
                     title="<spring:message code='course.listing.courseby'/> ${course.company.name}"/>
            </c:if>
        </div>
    </div>
</c:forEach>

<c:forEach items="${nonHighlighted}" var="course">
    <div class="course-list-item">
        <div class="description">
            <a href="${course.url}" class="courselink"><h2>${course.name}</h2></a>

            <p>
                    ${course.listDescription}
            </p>
        </div>
        <div class="logo">
            <c:if test="${course.company.normalLogo != null}">
                <img src="/logo/small/${course.company.id}-cursusbedrijf.png"
                     title="<spring:message code='course.listing.courseby'/> ${course.company.name}"/>
            </c:if>
        </div>
    </div>
</c:forEach>

<h2>Online cursussen en artikelen over ${category.name}</h2>

<c:forEach items="${articles}" var="article">
    <div class="course-list-item">
        <div class="description">
            <a href="${article.url}" class="courselink"><h2>${article.title}</h2></a>
            <p>
                ${article.teaser}
            </p>
        </div>
    </div>
</c:forEach>

