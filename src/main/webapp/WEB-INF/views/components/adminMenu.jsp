<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="default-block">
    <ul>
        <sec:authorize access="hasRole('ROLE_PERM_edit_company_courses') or hasRole('ROLE_PERM_edit_everything')">
            <li><a href="/admin/course"><spring:message code="admin.sections.courses"/></a></li>
        </sec:authorize>
        <sec:authorize access="hasRole('ROLE_PERM_edit_company_profile') or hasRole('ROLE_PERM_edit_everything')">
            <li><a href="/admin/company"><spring:message code="admin.sections.profile"/></a></li>
        </sec:authorize>
        <sec:authorize access="hasRole('ROLE_PERM_edit_company_requests') or hasRole('ROLE_PERM_edit_everything')">
            <li><a href="/admin/requests"><spring:message code="admin.sections.requests"/></a></li>
        </sec:authorize>
        <sec:authorize access="hasRole('ROLE_PERM_edit_company_articles') or hasRole('ROLE_PERM_edit_everything')">
            <li><a href="/admin/articles"><spring:message code="admin.sections.articles"/></a></li>
        </sec:authorize>
        <sec:authorize access="hasRole('ROLE_PERM_edit_company_users') or hasRole('ROLE_PERM_edit_everything')">
            <li><a href="/admin/users"><spring:message code="admin.sections.users"/></a></li>
        </sec:authorize>
    </ul>
    <br>
    <ul>
        <li><a href="<c:url value="/admin/logout" />">Uitloggen</a></li>
    </ul>
</div>
