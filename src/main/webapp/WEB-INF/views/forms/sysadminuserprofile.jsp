<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<form:form method="post" modelAttribute="userProfile">

    <jsp:include page="submitmessage.jsp"/>

    <form:hidden path="companyId"/>
    <form:hidden path="id"/>
    <input id="remove" type="hidden" name="remove" value="0"/>

    <div class="default-block">
        <form:label path="email"><spring:message code="user.genericlabel.email"/></form:label>
        <form:input cssClass="form-input" path="email"/>
        <form:errors path="email" cssClass="form-error"/>
    </div>
    <div class="default-block">
        <form:label path="enabled"><spring:message code="generic.message.status"/></form:label>
        <form:select path="enabled" cssClass="form-input" cssErrorClass="form-input-error">
            <form:options items="${statuses}" itemValue="enabled" itemLabel="name"/>
        </form:select>
    </div>

    <div class="default-block">
        <input type="submit" class="submitbutton" value="<spring:message code="generic.message.save"/>"/>
        <input type="submit" name="removeProfile" class="submitbutton" value="<spring:message code="generic.message.remove"/>"/>
    </div>

</form:form>


