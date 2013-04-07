<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1><spring:message code="account.activation.header"/></h1>

<c:choose>
    <c:when test="${failureMessage != ''}">
        ${failureMessage}
    </c:when>
    <c:otherwise>
        <form:form modelAttribute="accountActivationFormData">

            <c:if test="${submitted}">
                <jsp:include page="formsubmitresult.jsp"/>
            </c:if>

            <form:hidden path="companyId"/>
            <form:hidden path="email"/>
            <form:hidden path="invitationId"/>

            <div class="default-block">
                <form:label path="email"><spring:message code="user.genericlabel.email"/></form:label>
                <input cssClass="form-input" cssErrorClass="form-input-error" size="60" maxlength="60" disabled="true"
                       value="${accountActivationFormData.email}"/>
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

            <div class="default-block">
                <form:label path="password1"><spring:message code="user.genericlabel.password"/></form:label>
                <form:password path="password1" cssClass="form-input" cssErrorClass="form-input-error" size="60" maxlength="60"/>
                <form:errors path="password1" cssClass="form-error"/>
            </div>

            <div class="default-block">
                <form:label path="password2"><spring:message code="user.genericlabel.password.repeat"/></form:label>
                <form:password path="password2" cssClass="form-input" cssErrorClass="form-input-error" size="60" maxlength="60"/>
                <form:errors path="password2" cssClass="form-error"/>
            </div>

            <div class="default-block">
                <input type="submit" class="submitbutton" value="<spring:message code="generic.message.save"/>"/>
            </div>

        </form:form>
    </c:otherwise>
</c:choose>

