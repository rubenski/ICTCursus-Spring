<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div id="admin-menu">
    <ul>
        <li><a href="/admin">Home</a></li>
        <sec:authorize access="hasRole('ROLE_PERM_edit_company_courses') or hasRole('ROLE_PERM_edit_everything')">
            <li><a href="/admin/courses"><spring:message code="admin.sections.courses"/></a></li>
        </sec:authorize>
        <%--
        <sec:authorize access="hasRole('ROLE_PERM_edit_company_profile') or hasRole('ROLE_PERM_edit_everything')">
            <li><a href="/admin/company"><spring:message code="admin.sections.companyprofile"/></a></li>
        </sec:authorize>
        --%>
        <sec:authorize access="hasRole('ROLE_PERM_edit_company_requests') or hasRole('ROLE_PERM_edit_everything')">
            <li><a href="/admin/courserequests"><spring:message code="admin.sections.requests"/></a></li>
        </sec:authorize>
        <sec:authorize access="hasRole('ROLE_PERM_edit_company_articles') or hasRole('ROLE_PERM_edit_everything')">
            <li><a href="/admin/articles"><spring:message code="admin.sections.articles"/></a></li>
        </sec:authorize>
        <%--
        <sec:authorize access="hasRole('ROLE_PERM_edit_company_users') or hasRole('ROLE_PERM_edit_everything')">
            <li><a href="/admin/users"><spring:message code="admin.sections.users"/></a></li>
        </sec:authorize>

        <sec:authorize access="hasRole('ROLE_PERM_edit_company_users') or hasRole('ROLE_PERM_edit_everything')">
            <li><a href="/admin/invitations"><spring:message code="admin.sections.invitations"/></a></li>
        </sec:authorize>
        --%>

        <sec:authorize access="hasRole('ROLE_PERM_edit_company_settings') or hasRole('ROLE_PERM_edit_everything')">
            <li><a href="/admin/company"><spring:message code="admin.sections.yourcompany"/></a></li>
        </sec:authorize>
        <%--
        <sec:authorize access="hasRole('ROLE_PERM_edit_company_invoices') or hasRole('ROLE_PERM_edit_everything')">
            <li><a href="/admin/invoices"><spring:message code="admin.sections.invoices"/></a></li>
        </sec:authorize>
        --%>
        <sec:authorize access="hasRole('ROLE_PERM_edit_product_settings') or hasRole('ROLE_PERM_edit_everything')">
            <li><a href="/admin/productsettings"><spring:message code="product.settings.title"/></a></li>
        </sec:authorize>

        <li><a href="/admin/faq"><spring:message code="faq.title"/></a></li>

    </ul>
    <br>
    <ul>
        <li><a href="<c:url value="/admin/myprofile" />">Mijn profiel</a></li>
    </ul>
    <br>
    <ul>
        <li><a href="<c:url value="/admin/logout" />">Uitloggen</a></li>
    </ul>
</div>
