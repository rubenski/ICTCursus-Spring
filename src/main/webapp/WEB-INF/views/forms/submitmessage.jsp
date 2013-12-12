<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:if test="${valid != ''}">
    <c:if test="${valid == 'true'}">
        <div id="validMessage" class="default-block"><spring:message code="course.form.valid"/></div>
    </c:if>
    <c:if test="${valid == 'false'}">
        <div id="invalidMessage" class="default-block"><spring:message code="course.form.invalid"/></div>
    </c:if>
</c:if>