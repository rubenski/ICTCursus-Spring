<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="default-block">
    <h2><spring:message code="admin.yourinvitations.header"/></h2>
    <c:choose>
        <c:when test="${numberOfInvitations > 0}">

            <table cellpadding="4" width="600">
                <tr>
                    <th><spring:message code="user.genericlabel.name"/></th>
                    <th><spring:message code="user.genericlabel.email"/></th>
                    <th><spring:message code="invitation.used"/></th>
                    <th><spring:message code="generic.message.date"/></th>

                </tr>
                <c:forEach items="${invitations}" var="invitation">
                    <tr>
                        <td>
                                ${invitation.fullNameFormal}
                        </td>
                        <td>
                                ${invitation.email}
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${invitation.activated}">
                                    <spring:message code="generic.message.yes"/>
                                </c:when>
                                <c:otherwise>
                                    <spring:message code="generic.message.no"/>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <fmt:formatDate value="${invitation.creationDate}" type="date" pattern="dd-MM-yyyy"/>
                        </td>
                        <td>
                            <a href="/admin/invitations/revoke/${invitation.id}"><spring:message code="generic.message.remove"/></a>
                        </td>
                    </tr>
                </c:forEach>
            </table>

        </c:when>
        <c:otherwise>
            <spring:message code="management.message.noinvitations"/>
        </c:otherwise>
    </c:choose>
</div>
<div class="default-block">
    <h2><spring:message code="user.invite"/></h2>

    <div><a href="/admin/invitations/invite"><spring:message code="user.invite.linktext"/></a></div>
</div>
