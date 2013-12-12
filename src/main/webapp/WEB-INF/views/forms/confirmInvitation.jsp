<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form method="post" modelAttribute="invitation" id="userActivationConformationForm">

    <form:hidden path="firstName"/>
    <form:hidden path="preposition"/>
    <form:hidden path="lastName"/>
    <form:hidden path="email"/>
    <form:hidden path="roles"/>

    <input type="hidden" name="confirmationScreen" value="1"/>

    <%-- first name --%>
    <div class="default-block">
        <form:label path="firstName">
            <spring:message code="user.genericlabel.firstname"/>
        </form:label>
            ${invitation.firstName}
    </div>

    <%-- preposition --%>
    <div class="default-block">
        <form:label path="preposition">
            <spring:message code="user.genericlabel.preposition"/>
        </form:label>
            ${invitation.preposition}
    </div>

    <%-- last name --%>
    <div class="default-block">
        <form:label path="lastName">
            <spring:message code="user.genericlabel.lastname"/>
        </form:label>
            ${invitation.lastName}
    </div>

    <%-- access rights --%>
    <div class="default-block">
        <form:label path="roles">
            <spring:message code="user.genericlabel.access"/>
        </form:label>
        <ul>
            <c:forEach items="${selectedRoles}" var="role">
                <li>${role.displayName}</li>
            </c:forEach>
        </ul>
    </div>

    <%-- email --%>
    <div class="default-block">
        <form:label path="email">
            <spring:message code="user.genericlabel.email"/>
        </form:label>
        <span style="color:red;font-weight:bold;font-size:14px">${invitation.email}</span>
    </div>


    <%-- submit --%>
    <div class="default-block">
        <span style="color:red;font-weight:bold">
                <spring:message code="invite.user.warning"/>
        </span>
        <br/><br/>
        <div class="form-entry">
            <input type="submit" name="goback" value="Terug"/>
            <input type="submit" name="send" value="Verzenden"/>
        </div>
    </div>

</form:form>