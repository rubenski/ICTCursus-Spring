<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="default-block">
    <h2><spring:message code="sysadmin.userprofiles"/></h2>

    <table cellpadding="4" width="600">
        <tr>
            <th><spring:message code="generic.message.name"/></th>
            <th><spring:message code="generic.userMessage.company"/></th>
            <th><spring:message code="generic.userMessage.email"/></th>
        </tr>
        <c:forEach items="${userProfiles}" var="userProfile">
            <tr>
                <td>
                    <a href="/admin/sys/userprofile/${userProfile.id}">${userProfile.fullNameFormal}</a>
                </td>
                <td>
                     ${userProfile.company.name}
                </td>
                <td>
                    ${userProfile.email}
                </td>
            </tr>
        </c:forEach>
    </table>
</div>


