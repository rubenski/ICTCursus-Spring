<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<jsp:include page="../components/adminuserprofiletabs.jsp"/>

<form:form method="post" modelAttribute="passwordForm">

    <%-- error / success feedback --%>
    <jsp:include page="submitmessage.jsp"/>

    <div class="default-block">
        <form:label path="existingPassword"><spring:message code="password.form.label.existing"/></form:label>
        <form:password path="existingPassword" cssClass="form-input" cssErrorClass="form-input-error" size="50" maxlength="60"/>
        <form:errors path="existingPassword" cssClass="form-error"/>
    </div>

    <div class="default-block">
        <form:label path="newPassword1"><spring:message code="password.form.label.new"/></form:label>
        <form:password path="newPassword1" cssClass="form-input" cssErrorClass="form-input-error" size="50" maxlength="60"/>
        <form:errors path="newPassword1" cssClass="form-error"/>
    </div>

    <div class="default-block">
        <form:label path="newPassword2"><spring:message code="password.form.label.new.confirm"/></form:label>
        <form:password path="newPassword2" cssClass="form-input" cssErrorClass="form-input-error" size="50" maxlength="60"/>
        <form:errors path="newPassword2" cssClass="form-error"/>
    </div>

    <div class="default-block">
        <input type="submit" class="submitbutton" value="<spring:message code="generic.message.save"/>"/>
    </div>

</form:form>