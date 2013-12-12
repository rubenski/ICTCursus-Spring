<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="tabs">
    <ul>
        <li><a href="/admin/courses"><spring:message code="admin.sections.courses" /></a></li>
        <li><a href="/admin/courses/highlighted"><spring:message code="advertised.courses" /></a></li>
    </ul>
</div>