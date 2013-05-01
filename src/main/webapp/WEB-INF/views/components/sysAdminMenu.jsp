<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="default-block">
    <ul>
        <li><a href="/admin/sys">Home</a></li>
    </ul>
    <br>
    <ul>
        <li><a href="<c:url value="/admin/myprofile" />">Mijn profiel</a></li>
        <li><a href="<c:url value="/admin/sys/courserequests" />"><spring:message code="sysadmin.sections.courserequests"/></a></li>
        <li><a href="<c:url value="/admin/sys/courses" />"><spring:message code="admin.sections.courses"/></a></li>
        <li><a href="<c:url value="/admin/sys/accountrequests" />"><spring:message code="sysadmin.sections.accountrequests"/></a></li>
        <li><a href="<c:url value="/admin/sys/articlesuggestions" />"><spring:message code="sysadmin.sections.articlesuggestions"/></a></li>
        <li><a href="<c:url value="/admin/articles" />"><spring:message code="admin.sections.articles"/></a></li>
    </ul>
    <br>
    <ul>
        <li><a href="<c:url value="/admin/logout" />">Uitloggen</a></li>
    </ul>
</div>