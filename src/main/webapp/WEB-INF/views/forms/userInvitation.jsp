<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<form:form method="post" modelAttribute="userInvitation" id="userInvitationForm">

    <%-- first name --%>
    <div class="default-block">
        <form:label path="firstName">
            <spring:message code="user.genericlabel.firstname"/>
        </form:label>
        <form:input path="firstName" cssErrorClass="form-input-error"/>
        <form:errors path="firstName" cssClass="form-error"/>
    </div>

    <%-- preposition --%>
    <div class="default-block">
        <form:label path="preposition">
            <spring:message code="user.genericlabel.preposition"/>
        </form:label>
        <form:input path="preposition" cssErrorClass="form-input-error"/>
        <form:errors path="preposition" cssClass="form-error"/>
    </div>

    <%-- last name --%>
    <div class="default-block">
        <form:label path="lastName">
            <spring:message code="user.genericlabel.lastname"/>
        </form:label>
        <form:input path="lastName" cssErrorClass="form-input-error"/>
        <form:errors path="lastName" cssClass="form-error"/>
    </div>

    <%-- access rights --%>
    <div class="default-block">
        <form:label path="roles">
            <spring:message code="user.genericlabel.access"/>
            <span><spring:message code="user.invitation.roles.helptext"/></span>
        </form:label>
        <c:forEach items="${companyRoles}" var="role">
            <span class="blockCheckBox"><form:checkbox path="roles" value="${role.id}" label="${role.displayName}"/></span><br/>
        </c:forEach>
        <form:errors path="roles" cssClass="form-error"/>
    </div>

    <%-- email --%>
    <div class="default-block">
        <form:label path="email">
            <spring:message code="user.genericlabel.email"/>
        </form:label>
        <form:input path="email" cssErrorClass="form-input-error"/>
        <form:errors path="email" cssClass="form-error"/>
    </div>

    <%-- submit --%>
    <div class="default-block">
        <div class="form-entry">
            <input type="submit" value="Volgende"/>
        </div>
    </div>

</form:form>