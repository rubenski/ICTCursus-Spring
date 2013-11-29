<%@ page import="nl.codebasesoftware.produx.domain.dto.entity.CourseEntityDTO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:if test="${highlightMe == '1'}">
    <c:set var="highlightClass" value="highlighted"/>
</c:if>
<div class="course-list-item ${lastClass}${highlightClass}">
    <div class="description">
        <a href="${course.url}"><h2>${course.name}</h2></a>
        <p>${course.listDescription}</p>
        <table width="100%">
            <tr>
                <td>
                    <b><spring:message code="generic.message.topics"/>:</b> ${course.getTagList()}<br/>
                    <b><spring:message code="generic.message.offeredby"/>:</b> ${course.company.name}
                </td>
                <td>
                    <c:if test="${course.company.hasLogo()}">
                        <img src="${course.company.getSmallLogoUrl()}"
                             title="<spring:message code='course.listing.courseby'/> ${course.company.name}"/>
                    </c:if>
                </td>
            </tr>
        </table>
    </div>
</div>