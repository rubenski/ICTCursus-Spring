<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form:form method="post" modelAttribute="adrticleSuggestionFormData">

    <form:hidden path="id"/>

    <%-- error / success feedback --%>
    <jsp:include page="submitmessage.jsp"/>

    <div class="default-block">
        <form:label path="title"><spring:message code="article.suggestion.title"/></form:label>
        <form:input path="title" cssClass="form-input" cssErrorClass="form-input-error" size="60" maxlength="60"/>
        <form:errors path="title" cssClass="form-error"/>
    </div>

    <div class="default-block">
        <form:label path="description">
            <spring:message code="article.suggest.fieldheader"/>
            <span><spring:message code="articlesuggestion.description.helptext"/></span>
        </form:label>
        <form:textarea rows="12" path="description" cssErrorClass="form-input-error" cols="100"/>
        <form:errors path="description" cssClass="form-error"/>
    </div>

    <div class="default-block">
        <input type="submit" class="submitbutton" value="<spring:message code="articlesuggestion.send"/>"/>
    </div>

</form:form>
