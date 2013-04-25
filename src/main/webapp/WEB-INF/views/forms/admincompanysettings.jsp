
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<form:form method="post" modelAttribute="settings">

    <form:hidden path="companyId"/>

    <%-- error / success feedback --%>
    <jsp:include page="submitmessage.jsp"/>

    <div class="default-block">
        <form:label path="budgetTriggerAmount">
            <spring:message code="company.settings.budgetwarning.trigger"/>
            <span><spring:message code="company.settings.budgetwarning.trigger.helptext"/></span>
        </form:label>
        <form:input path="budgetTriggerAmount" cssClass="form-input" cssErrorClass="form-input-error" size="2" maxlength="4"/> &#0128;
        <form:errors path="budgetTriggerAmount" cssClass="form-error"/>
    </div>

    <div class="default-block">
        <form:label path="courseRequestEmailAddress">
            <spring:message code="company.settings.requests.email"/>
            <span>
                <spring:message code="company.settings.requests.email.helptext"/>
            </span>
        </form:label>
        <form:input path="courseRequestEmailAddress" cssClass="form-input" cssErrorClass="form-input-error" size="60" maxlength="60"/>
        <form:errors path="courseRequestEmailAddress" cssClass="form-error"/>
    </div>

    <div class="default-block">
        <form:label path="allCoursesDeactivated">
            <spring:message code="company.settings.allcoursesdeactivated"/>
            <span>
                <spring:message code="company.settings.allcoursesdeactivated.helptext"/>
            </span>
        </form:label>
        <form:checkbox path="allCoursesDeactivated" cssClass="form-input" cssErrorClass="form-input-error" size="60" maxlength="60"/><spring:message code="company.settings.allcoursesdeactivated"/>
        <form:errors path="allCoursesDeactivated" cssClass="form-error"/>
    </div>

    <div class="default-block">
        <input type="submit" class="submitbutton" value="<spring:message code="generic.message.save"/>"/>
    </div>

</form:form>