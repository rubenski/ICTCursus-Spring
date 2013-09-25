<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- listing --%>
<c:forEach items="${resultListing.courses}" var="course" varStatus="loop">

    <c:if test="${loop.last}">
        <c:set var="lastClass" value="last"/>
    </c:if>

    <div class="course-list-item ${lastClass}">
        <div class="description">
            <a href="${course.url}"><h2>${course.name}</h2></a>
            <p>
                ${course.listDescription}  <br>
                <b><spring:message key="generic.message.topics"/>: ${course.getTagList()}</b>
            </p>
        </div>
        <div class="logo">
            <c:if test="${course.company.hasLogo()}">
                <img src="${course.company.getSmallLogoUrl()}"
                     title="<spring:message key='course.listing.courseby'/> ${course.company.name}"/>
            </c:if>
        </div>
    </div>
</c:forEach>

<%-- paging --%>
<c:if test="${resultListing.hasPaging()}">
    <ul class="horizontal-list">
        <c:forEach items="${resultListing.getPagingUrls()}" var="pagingUrl" varStatus="loop">
            <li><a href="${pagingUrl}">${loop.count}</a></li>
        </c:forEach>
    </ul>
</c:if>


