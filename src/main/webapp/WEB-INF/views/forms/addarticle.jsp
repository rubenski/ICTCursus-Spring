<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<form:form method="post" modelAttribute="articleFormData">

    <form:hidden path="id"/>
    <form:hidden path="suggestionId"/>

    <%-- error / success feedback --%>
    <jsp:include page="submitmessage.jsp"/>

    <div class="default-block">
        <form:label path="title"><spring:message code="article.form.title"/></form:label>
        <form:input path="title" cssClass="form-input" cssErrorClass="form-input-error" size="50" maxlength="60"/>
        <form:errors path="title" cssClass="form-error"/>
    </div>

    <div class="default-block">
        <form:label path="teaser"><spring:message code="article.form.teaser"/></form:label>
        <form:textarea cols="75" rows="4" path="teaser" cssClass="form-input" cssErrorClass="form-input-error" size="50" maxlength="500"/>
        <form:errors path="teaser" cssClass="form-error"/>
    </div>

    <div class="default-block">
        <input type="submit" class="submitbutton" value="<spring:message code="generic.message.save"/>"/>
    </div>

</form:form>