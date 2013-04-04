<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:if test="${!userProfile.emailEditable}">
    <c:set var="disabled" value="disabled"/>
</c:if>

<form:form method="post" modelAttribute="userProfile">

    <form:hidden path="companyId"/>
    <form:hidden path="email"/>

    <div class="default-block">
        <form:label path="email"><spring:message code="user.genericlabel.email"/></form:label>
        <input cssClass="form-input" cssErrorClass="form-input-error" size="60" maxlength="60" disabled="true" value="${userProfile.email}"/>
        <form:errors path="email" cssClass="form-error"/>
    </div>

    <div class="default-block">
        <form:label path="firstName"><spring:message code="user.genericlabel.firstname"/></form:label>
        <form:input path="firstName" cssClass="form-input" cssErrorClass="form-input-error" size="60" maxlength="60"/>
        <form:errors path="firstName" cssClass="form-error"/>
    </div>

    <div class="default-block">
        <form:label path="preposition"><spring:message code="user.genericlabel.preposition"/></form:label>
        <form:input path="preposition" cssClass="form-input" cssErrorClass="form-input-error" size="60" maxlength="60"/>
        <form:errors path="preposition" cssClass="form-error"/>
    </div>

    <div class="default-block">
        <form:label path="lastName"><spring:message code="user.genericlabel.lastname"/></form:label>
        <form:input path="lastName" cssClass="form-input" cssErrorClass="form-input-error" size="60" maxlength="60"/>
        <form:errors path="lastName" cssClass="form-error"/>
    </div>

    <div class="default-block">
        <form:label path="phone"><spring:message code="user.genericlabel.phone"/></form:label>
        <form:input path="phone" cssClass="form-input" cssErrorClass="form-input-error" size="60" maxlength="60"/>
        <form:errors path="phone" cssClass="form-error"/>
    </div>

    <c:if test="${rolesEditable == 1}">
        <div class="default-block">
            <form:label path="roles">
                <spring:message code="user.genericlabel.access"/>
                <span><spring:message code="user.invitation.roles.helptext"/></span>
            </form:label>
            <c:forEach items="${assignableRoles}" var="role">
                <span class="blockCheckBox"><form:checkbox path="roles" value="${role.id}" label="${role.displayName}" /></span><br/>
            </c:forEach>
            <form:errors path="roles" cssClass="form-error"/>
        </div>
    </c:if>

    <c:if test="${rolesEditable == 1}">
        <div class="default-block">
            <form:label path="enabled"><spring:message code="generic.message.status"/></form:label>
            <form:select path="enabled" cssClass="form-input" cssErrorClass="form-input-error">
                <form:options items="${statuses}" itemValue="enabled" itemLabel="name"/>
            </form:select>
        </div>
    </c:if>

    <div class="default-block">
        <input type="submit" class="submitbutton" value="Verzenden"/>
    </div>

</form:form>