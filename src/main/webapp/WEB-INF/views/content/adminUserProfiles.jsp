<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="default-block">

    <h2><spring:message code="admin.users.header"/></h2>
    <c:choose>
        <c:when test="${numberOfOtherUsers > 0}">

            <table cellpadding="4" width="600">
                <tr>
                    <th><spring:message code="user.genericlabel.name"/></th>
                    <th><spring:message code="user.accountstatus"/></th>
                </tr>
                <c:forEach items="${profiles}" var="profile">
                    <tr>
                        <td>
                            <a href="/admin/users/${profile.id}">${profile.fullNameFormal}</a>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${profile.enabled}">
                                    <spring:message code="userprofile.status.active"/>
                                </c:when>
                                <c:otherwise>
                                    <spring:message code="userprofile.status.inactive"/>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </table>

        </c:when>
        <c:otherwise>
            <spring:message code="management.home.nootherusers.message"/>
        </c:otherwise>
    </c:choose>

</div>

<div class="default-block">
    <h2><spring:message code="admin.yourinvitations.header"/></h2>
    <c:choose>
        <c:when test="${numberOfInvitations > 0}">

            <table cellpadding="4" width="600">
                <tr>
                    <th><spring:message code="user.genericlabel.name"/></th>
                    <th><spring:message code="user.accountstatus"/></th>
                </tr>
                <c:forEach items="${invitations}" var="invitation">
                    <tr>
                        <td>
                            <a href="/admin/invitations/${invitation.id}">${invitation.fullNameFormal}</a>
                        </td>
                        <td>

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
    <div><a href="/admin/users/invite"><spring:message code="user.invite.linktext"/></a></div>
</div>
