<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<form:form method="post" modelAttribute="articlePageFormData">

    <form:hidden path="articleId"/>
    <form:hidden path="id"/>

    <%-- error / success feedback --%>
    <jsp:include page="submitmessage.jsp"/>

    <div class="default-block">
        <form:label path="title"><spring:message code="generic.message.title"/></form:label>
        <form:input path="title" cssClass="form-input" cssErrorClass="form-input-error" size="60" maxlength="60"/>
        <form:errors path="title" cssClass="form-error"/>
    </div>

    <div class="default-block">
        <form:label path="text"><spring:message code="articlepage.form.text.label"/></form:label>
        <form:textarea cols="75" rows="30" path="text" cssClass="richtext" cssErrorClass="form-input-error" size="60" maxlength="6000"/>
        <form:errors path="text" cssClass="form-error"/>
    </div>

    <div class="default-block">
        <form:label path="metaKeywords">
            <spring:message code="articlepage.form.meta.keywords"/>
            <span><spring:message code="page.metakeywords.helptext"/></span>
        </form:label>
        <form:textarea cols="75" rows="1" path="metaKeywords" cssClass="form-input" cssErrorClass="form-input-error" size="60" maxlength="60"/>
        <form:errors path="metaKeywords" cssClass="form-error"/>
    </div>

    <div class="default-block">
        <form:label path="metaDescription">
            <spring:message code="articlepage.form.meta.description"/>
            <span><spring code="page.metadescription.helptext"/></span>
        </form:label>
        <form:textarea cols="75" rows="3" path="metaDescription" cssClass="form-input" cssErrorClass="form-input-error" size="60" maxlength="200"/>
        <form:errors path="metaDescription" cssClass="form-error"/>
    </div>

    <div class="default-block">
        <input type="submit" class="submitbutton" value="<spring:message code="generic.message.save"/>"/>
        &nbsp;&nbsp;
        <input type="submit" name="remove" class="submitbutton" value="<spring:message code="generic.message.remove"/>"/>
    </div>

</form:form>