<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:if test="${!userProfile.emailEditable}">
    <c:set var="disabled" value="disabled"/>
</c:if>

<form:form method="post" modelAttribute="myUserProfile">

    <%-- error / success feedback --%>
    <jsp:include page="submitmessage.jsp"/>

    <form:hidden path="companyId"/>
    <form:hidden path="email"/>
    <form:hidden path="id"/>

    <div class="default-block">
        <form:label path="email"><spring:message code="user.genericlabel.email"/></form:label>
        <input cssClass="form-input" cssErrorClass="form-input-error" size="50" maxlength="60" disabled="true" value="${myUserProfile.email}"/>
        <form:errors path="email" cssClass="form-error"/>
    </div>

    <div class="default-block">
        <form:label path="firstName"><spring:message code="user.genericlabel.firstname"/></form:label>
        <form:input path="firstName" cssClass="form-input" cssErrorClass="form-input-error" size="50" maxlength="60"/>
        <form:errors path="firstName" cssClass="form-error"/>
    </div>

    <div class="default-block">
        <form:label path="preposition"><spring:message code="user.genericlabel.preposition"/></form:label>
        <form:input path="preposition" cssClass="form-input" cssErrorClass="form-input-error" size="50" maxlength="60"/>
        <form:errors path="preposition" cssClass="form-error"/>
    </div>

    <div class="default-block">
        <form:label path="lastName"><spring:message code="user.genericlabel.lastname"/></form:label>
        <form:input path="lastName" cssClass="form-input" cssErrorClass="form-input-error" size="50" maxlength="60"/>
        <form:errors path="lastName" cssClass="form-error"/>
    </div>

    <div class="default-block">
        <form:label path="phone"><spring:message code="user.genericlabel.phone"/></form:label>
        <form:input path="phone" cssClass="form-input" cssErrorClass="form-input-error" size="50" maxlength="60"/>
        <form:errors path="phone" cssClass="form-error"/>
    </div>


    <div class="default-block">
        <input type="submit" class="submitbutton" value="<spring:message code="generic.message.save"/>"/>
    </div>

</form:form>