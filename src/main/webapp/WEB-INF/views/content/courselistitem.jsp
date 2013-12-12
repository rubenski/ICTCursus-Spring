<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="nl.codebasesoftware.produx.domain.dto.entity.CourseEntityDTO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:if test="${highlightMe == '1'}">
    <c:set var="highlightClass" value="highlighted"/>
</c:if>


<c:if test="${course.company.productSettings.courseListingType eq 'Eenvoudig'}">
    <c:set var="simpleClass" value=" simple"/>
</c:if>

<div class="course-list-item ${lastClass}${highlightClass}${simpleClass}">
    <div class="description">
        <a href="${course.url}"><h2>${course.name}</h2></a>
        <p>${course.listDescription}</p>

        <c:if test="${course.company.productSettings.courseListingType != 'Eenvoudig'}">

            <table width="100%">
                <tr>
                    <td>
                        <b><spring:message code="generic.message.topics"/>:</b> ${course.tagList}<br/>
                        <b><spring:message code="generic.message.offeredby"/>:</b> ${course.company.name}
                    </td>
                    <td width="65">
                        <c:if test="${course.company.hasLogo()}">
                            <img src="${course.company.getSmallLogoUrl()}"
                                 title="<spring:message code='course.listing.courseby'/> ${course.company.name}"/>
                        </c:if>
                    </td>
                </tr>
            </table>

        </c:if>
    </div>
</div>