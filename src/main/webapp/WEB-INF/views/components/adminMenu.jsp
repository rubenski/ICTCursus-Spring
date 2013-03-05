<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="default-block">
    <ul>
        <li><a href="/admin/home"><spring:message code="management.home.linktext"/></a></li>
        <li><a href="/admin/company"><spring:message code="management.profile.linktext"/></a></li>
        <li><a href="/admin/requests"><spring:message code="management.requests.linktext"/></a></li>
        <li><a href="/admin/articles"><spring:message code="management.articles.linktext"/></a></li>
    </ul>
</div>

