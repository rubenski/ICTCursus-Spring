<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- listing --%>
<c:forEach items="${resultListing.courses}" var="course" varStatus="loop">

    <c:if test="${loop.last}">
        <c:set var="lastClass" value="last" scope="request"/>
    </c:if>
    <c:set var="course" scope="request" value="${course}" />
    <jsp:include page="courselistitem.jsp"/>

</c:forEach>

<%-- paging --%>
<c:if test="${resultListing.hasPaging()}">
    <ul class="horizontal-list">
        <c:forEach items="${resultListing.getPagingUrls()}" var="pagingUrl" varStatus="loop">
            <li><a href="${pagingUrl}">${loop.count}</a></li>
        </c:forEach>
    </ul>
</c:if>


