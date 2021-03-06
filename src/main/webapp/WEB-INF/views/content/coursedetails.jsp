<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%--@elvariable id="course" type="nl.codebasesoftware.produx.domain.dto.entity.CourseEntityDTO"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="right" class="roundedbox">
    <span class="header">Regio's</span>
    <c:forEach items="${course.regionNames}" var="region">
        ${region}<br>
    </c:forEach>

    <br/>
    <span class="header">Prijs</span>
    ${course.getEuroPrice()}
    <br/>
    <br/>
    <span class="header">Onderwerpen</span>
    <c:forEach items="${course.tagNames}" var="tag">
        ${tag}<br/>
    </c:forEach>

    <c:forEach items="${course.optionCategories}" var="category">
        <br/>
        <span class="header">${category.name}</span>
        <c:forEach items="${category.options}" var="option">
            ${option.displayName} <br/>
        </c:forEach>
    </c:forEach>

</div>
