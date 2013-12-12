<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="tabs">
    <ul>
        <li><a href="/admin/company"><spring:message code="admin.sections.companyinfo" /></a></li>
        <li><a href="/admin/company/settings"><spring:message code="company.settings" /></a></li>
        <li><a href="/admin/users"><spring:message code="management.users.linktext" /></a></li>
        <li><a href="/admin/invitations"><spring:message code="admin.sections.invitations" /></a></li>
        <li><a href="/admin/invoices"><spring:message code="admin.sections.invoices" /></a></li>
    </ul>
</div>