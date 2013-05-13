<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="default-block">
    <form:label path="companyName"><spring:message code="company.form.name"/></form:label>
    <form:input path="companyName" cssClass="form-input" cssErrorClass="form-input-error" size="40"/>
    <form:errors path="companyName" cssClass="form-error"/>
</div>

<div class="default-block">
    <form:label path="companyAddress"><spring:message code="company.form.address"/></form:label>
    <form:input path="companyAddress" cssClass="form-input" cssErrorClass="form-input-error" size="40"/>
    <form:errors path="companyAddress" cssClass="form-error"/>
</div>

<div class="default-block">
    <form:label path="companyZipCode"><spring:message code="company.form.zipcode"/></form:label>
    <form:input path="companyZipCode" cssClass="form-input" cssErrorClass="form-input-error" size="10" maxlength="6"/>
    <form:errors path="companyZipCode" cssClass="form-error"/>
</div>

<div class="default-block">
    <form:label path="companyCity"><spring:message code="company.form.city"/></form:label>
    <form:input path="companyCity" cssClass="form-input" cssErrorClass="form-input-error" size="40"/>
    <form:errors path="companyCity" cssClass="form-error"/>
</div>

<div class="default-block">
    <form:label path="country"><spring:message code="company.form.country"/></form:label>
    <form:select path="country" cssErrorClass="form-input-error">
        <form:option value="-1" label=""/>
        <form:options items="${countries}" itemValue="code" itemLabel="name"/>
    </form:select>
    <form:errors path="country" cssClass="form-error"/>
</div>

<div class="default-block">
    <form:label path="companyEmail"><spring:message code="company.form.email"/></form:label>
    <form:input path="companyEmail" cssClass="form-input" cssErrorClass="form-input-error" size="40"/>
    <form:errors path="companyEmail" cssClass="form-error"/>
</div>

<div class="default-block">
    <form:label path="companyPhone"><spring:message code="company.form.phone"/></form:label>
    <form:input path="companyPhone" cssClass="form-input" cssErrorClass="form-input-error" size="40"/>
    <form:errors path="companyPhone" cssClass="form-error"/>
</div>

<div class="default-block">
    <form:label path="tradeNumber"><spring:message code="company.form.chamberofcommercenumber"/></form:label>
    <form:input path="tradeNumber" cssClass="form-input" cssErrorClass="form-input-error" size="40"/>
    <form:errors path="tradeNumber" cssClass="form-error"/>
</div>

<div class="default-block">
    <form:label path="vatNumber"><spring:message code="company.form.vatnumber"/></form:label>
    <form:input path="vatNumber" cssClass="form-input" cssErrorClass="form-input-error" size="40"/>
    <form:errors path="vatNumber" cssClass="form-error"/>
</div>