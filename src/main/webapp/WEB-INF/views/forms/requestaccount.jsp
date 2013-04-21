<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<h1><spring:message code="user.request.account"/></h1>
<spring:message code="explanation.request.account"/>
<form:form method="post" modelAttribute="accountRequestFormData">

    <jsp:include page="submitmessage.jsp"/>


    <div id="data-entry-form">

        <form:hidden path="id"/>

        <h2><spring:message code="company.data"/></h2>

        <div class="default-block">
            <form:label path="companyName"><spring:message code="company.form.name"/></form:label>
            <form:input path="companyName" cssClass="form-input" cssErrorClass="form-input-error" size="60"/>
            <form:errors path="companyName" cssClass="form-error"/>
        </div>

        <div class="default-block">
            <form:label path="companyAddress"><spring:message code="company.form.address"/></form:label>
            <form:input path="companyAddress" cssClass="form-input" cssErrorClass="form-input-error" size="60"/>
            <form:errors path="companyAddress" cssClass="form-error"/>
        </div>

        <div class="default-block">
            <form:label path="companyCity"><spring:message code="company.form.city"/></form:label>
            <form:input path="companyCity" cssClass="form-input" cssErrorClass="form-input-error" size="60"/>
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
            <form:input path="companyEmail" cssClass="form-input" cssErrorClass="form-input-error" size="60"/>
            <form:errors path="companyEmail" cssClass="form-error"/>
        </div>

        <div class="default-block">
            <form:label path="companyPhone"><spring:message code="company.form.phone"/></form:label>
            <form:input path="companyPhone" cssClass="form-input" cssErrorClass="form-input-error" size="60"/>
            <form:errors path="companyPhone" cssClass="form-error"/>
        </div>

        <div class="default-block">
            <form:label path="tradeNumber"><spring:message code="company.form.chamberofcommercenumber"/></form:label>
            <form:input path="tradeNumber" cssClass="form-input" cssErrorClass="form-input-error" size="60"/>
            <form:errors path="tradeNumber" cssClass="form-error"/>
        </div>

        <h2><spring:message code="personal.data"/></h2>

        <div class="default-block">
            <form:label path="firstName"><spring:message code="user.genericlabel.firstname"/></form:label>
            <form:input path="firstName" cssClass="form-input" cssErrorClass="form-input-error" size="60"/>
            <form:errors path="firstName" cssClass="form-error"/>
        </div>
        <div class="default-block">
            <form:label path="preposition"><spring:message code="user.genericlabel.preposition"/></form:label>
            <form:input path="preposition" cssClass="form-input" cssErrorClass="form-input-error" size="60"/>
            <form:errors path="preposition" cssClass="form-error"/>
        </div>
        <div class="default-block">
            <form:label path="lastName"><spring:message code="user.genericlabel.lastname"/></form:label>
            <form:input path="lastName" cssClass="form-input" cssErrorClass="form-input-error" size="60"/>
            <form:errors path="lastName" cssClass="form-error"/>
        </div>
        <div class="default-block">
            <form:label path="email"><spring:message code="user.genericlabel.email"/></form:label>
            <form:input path="email" cssClass="form-input" cssErrorClass="form-input-error" size="60"/>
            <form:errors path="email" cssClass="form-error"/>
        </div>

        <div class="default-block">
            <form:label path="phone"><spring:message code="user.genericlabel.phone"/></form:label>
            <form:input path="phone" cssClass="form-input" cssErrorClass="form-input-error" size="60"/>
            <form:errors path="phone" cssClass="form-error"/>
        </div>


        <div class="default-block">
            <form:label path="message"><spring:message code="request.account.optionalmessage"/></form:label>
            <form:textarea path="message" cssClass="form-input" cssErrorClass="form-input-error" cols="62" rows="5"/>
            <form:errors path="message" cssClass="form-error"/>
        </div>
        <div class="default-block">
            <input type="submit" value="Verzenden"/>
        </div>
    </div>
</form:form>



