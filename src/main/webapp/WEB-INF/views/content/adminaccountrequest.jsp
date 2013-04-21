<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<div id="data-entry-form">

    <div class="default-block">
        <label><spring:message code="user.genericlabel.firstname"/></label>
        ${request.firstName}
    </div>
    <div class="default-block">
        <label><spring:message code="user.genericlabel.preposition"/></label>
        ${request.preposition}
    </div>
    <div class="default-block">
        <label><spring:message code="user.genericlabel.lastname"/></label>
        ${request.lastName}
    </div>
    <div class="default-block">
        <label><spring:message code="user.genericlabel.email"/></label>
        ${request.email}
    </div>
    <div class="default-block">
        <label><spring:message code="user.genericlabel.phone"/></label>
        ${request.phone}
    </div>
    <div class="default-block">
        <label><spring:message code="company.form.name"/></label>
        ${request.companyName}
    </div>
    <div class="default-block">
        <label><spring:message code="company.form.chamberofcommercenumber"/></label>
        ${request.tradeNumber}
    </div>
    <div class="default-block">
        <label><spring:message code="generic.message.message"/></label>
        ${request.message}
    </div>
    <form:form modelAttribute="accountRequestDecision">
        <div class="default-block">

            <spring:message code='generic.message.reject' var="reject"/>
            <spring:message code='generic.message.grant' var="grant"/>
            <spring:message code='generic.message.choose.option' var="chooseOption"/>

            <form:hidden path="articleRequestId"/>

            <form:select path="decision">
                <form:option value="-1" label="${chooseOption}"/>
                <form:option value="0" label="${reject}"/>
                <form:option value="1" label="${grant}"/>
            </form:select>

            <form:errors path="decision" cssClass="form-error"/>
        </div>
        <div class="default-block">
            <input type="submit" class="submitbutton" value="<spring:message code="generic.message.save"/>"/>
        </div>
    </form:form>
</div>




