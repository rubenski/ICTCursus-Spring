<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<form:form method="post" commandName="course">
    <div id="data-entry-form">
        <form:hidden path="id" />
        <div class="form-entry manage">
            <form:label path="name"><spring:message code="course.form.name"/></form:label>
            <form:input path="name" cssClass="form-input" cssErrorClass="form-input-error"/>
            <form:errors path="name" cssClass="form-error" />
        </div>
        <div class="form-entry manage">
            <form:label path="shortDescription"><spring:message code="course.form.shortdescription"/></form:label>
            <form:textarea rows="3" path="shortDescription" cssClass="form-input" cssErrorClass="form-input-error"/>
            <form:errors path="shortDescription" cssClass="form-error" />
        </div>
        <div class="form-entry manage">
            <form:label path="longDescription"><spring:message code="course.form.longdescription"/></form:label>
            <form:textarea rows="20" path="longDescription" cssClass="form-input" cssErrorClass="form-input-error"/>
            <form:errors path="longDescription" cssClass="form-error" />
        </div>
        <div class="form-entry manage">
            <form:label path="duration"><spring:message code="course.form.duration"/></form:label>
            <form:textarea rows="2" path="duration" cssClass="form-input" cssErrorClass="form-input-error"/>
            <form:errors path="duration" cssClass="form-error" />
        </div>
        <div class="form-entry">
            <input type="submit" value="Verzenden"/>
        </div>
    </div>
</form:form>
