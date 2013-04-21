<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="default-block">
    <h2><spring:message code="sysadmin.sections.accountrequests"/></h2>
    <c:choose>
        <c:when test="${numberOfRequests > 0}">

            <table cellpadding="4" width="600">
                <tr>
                    <th><spring:message code="generic.message.id"/></th>
                    <th><spring:message code="sysadmin.account.request.date"/></th>
                    <th><spring:message code="generic.message.name"/></th>
                    <th><spring:message code="company.form.name"/></th>
                    <th><spring:message code="company.form.chamberofcommercenumber"/></th>

                </tr>
                <c:forEach items="${requests}" var="request">
                    <tr>
                        <td>
                            <a href="/admin/sys/accountrequests/${request.id}">${request.id}</a>
                        </td>
                        <td>
                            <fmt:formatDate value="${request.requestDate.time}" type="date" pattern="dd-MM-yyyy"/>
                        </td>
                        <td>
                            ${request.fullNameFormal}
                        </td>
                        <td>
                              ${request.companyName}
                        </td>

                        <td>
                                ${request.tradeNumber}
                        </td>
                    </tr>
                </c:forEach>
            </table>

        </c:when>
        <c:otherwise>
            <spring:message code="sysadmin.norequests.message"/>
        </c:otherwise>
    </c:choose>
</div>
