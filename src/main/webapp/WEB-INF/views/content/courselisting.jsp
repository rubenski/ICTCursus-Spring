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




