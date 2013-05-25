<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form:form method="post" modelAttribute="articleSuggestionFormData">

    <form:hidden path="id"/>

    <%-- error / success feedback --%>
    <jsp:include page="submitmessage.jsp"/>

    <div class="default-block">
        <form:label path="title"><spring:message code="article.suggestion.title"/></form:label>
        <form:input path="title" cssClass="form-input" cssErrorClass="form-input-error" size="50" maxlength="60" disabled="true"/>
        <form:errors path="title" cssClass="form-error"/>
    </div>

    <div class="default-block">
        <form:label path="email"><spring:message code="generic.userMessage.email"/></form:label>
        <form:input path="email" cssClass="form-input" cssErrorClass="form-input-error" size="50" maxlength="60" disabled="true"/>
        <form:errors path="email" cssClass="form-error"/>
    </div>

    <div class="default-block">
        <form:label path="description">
            <spring:message code="article.suggest.fieldheader"/>
            <span><spring:message code="articlesuggestion.description.helptext"/></span>
        </form:label>
        <form:textarea rows="12" path="description" cssErrorClass="form-input-error" cols="100" disabled="true"/>
        <form:errors path="description" cssClass="form-error"/>
    </div>

    <c:if test="${articleSuggestionFormData.used}">
        <c:set var="disable" value="true"/>
    </c:if>
    <c:if test="${!articleSuggestionFormData.used}">
        <c:set var="disable" value="false"/>
    </c:if>

    <div class="default-block">
        <form:label path="approved"><spring:message code="articlesuggestion.approved"/></form:label>
        <form:checkbox path="approved" cssClass="form-input" cssErrorClass="form-input-error" disabled="${disable}"/><spring:message
            code="articlesuggestion.approved"/>
        <form:errors path="approved" cssClass="form-error"/>
    </div>

    <c:if test="${!articleSuggestionFormData.used}">
        <div class="default-block">
            <input type="submit" class="submitbutton" value="<spring:message code="generic.message.save"/>"/>
        </div>
    </c:if>
</form:form>
