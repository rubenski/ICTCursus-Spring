<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<nav class="roundedbox">
    <span class="header"><spring:message code="mainsite.label.topics"/></span>
    <ul>
        <c:forEach items="${categories}" var="category">
            <li><a href="/${category.urlTitle}">${category.name}</a></li>
        </c:forEach>
    </ul>
</nav>






