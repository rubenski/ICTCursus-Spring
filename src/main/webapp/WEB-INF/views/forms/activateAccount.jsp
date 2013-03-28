<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<spring:message code="account.activation.salutation"/>  ${invitation.firstName} ${invitation.lastName},

<spring:message code="account.activation.mainmessage"/>


<form:form method="post" modelAttribute="invitation" id="userActivationForm">

    <form:hidden path="invitationId"/>

    <div class="default-block">
        <form:label path="email"><spring:message code="user.genericlabel.email"/></form:label>
        <form:input path="email" cssClass="form-input" cssErrorClass="form-input-error" size="60" maxlength="60"/>
    </div>

    <div class="default-block">
        <form:label path="password"><spring:message code="user.genericlabel.password"/></form:label>
        <form:input path="password" cssClass="form-input" cssErrorClass="form-input-error" size="60" maxlength="60"/>
        <form:errors path="password" cssClass="form-error"/>
    </div>

    <div class="default-block">
        <input type="submit" class="submitbutton" value="Verzenden"/>
    </div>

</form:form>