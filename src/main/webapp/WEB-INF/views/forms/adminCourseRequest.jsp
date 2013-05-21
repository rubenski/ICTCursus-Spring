<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form modelAttribute="courseRequestFormData">
    <div class="default-block">
        <form:label path="requesterName"><spring:message code="user.genericlabel.name"/></form:label>
        <table cellpadding="0" cellspacing="0" class="invisible">
            <tr>
                <td>
                    <form:select path="prefix" cssStyle="width:65px" disabled="true">
                        <form:options items="${prefixes}" itemValue="id" itemLabel="prefix"/>
                    </form:select>
                </td>
                <td>
                    <form:input path="requesterName" cssClass="form-input" cssErrorClass="form-input-error" disabled="true" maxlength="60" size="46"/>
                </td>
            </tr>
        </table>
    </div>

    <div class="default-block">
        <form:label path="email"><spring:message code="user.genericlabel.email"/></form:label>
        <form:input path="email" cssClass="form-input" cssErrorClass="form-input-error" maxlength="60" disabled="true" size="60"/>
        <form:errors path="email" cssClass="form-error"/>
    </div>

    <div class="default-block">
        <form:label path="courseName"><spring:message code="courserequest.requestforcourse.header"/></form:label>
        <a href="${course.adminUrl}">${courseRequestFormData.courseName}</a>
    </div>

    <div class="default-block">
        <form:label path="message"><spring:message code="courserequest.message"/></form:label>
        <form:textarea path="message" cssClass="form-input" cssErrorClass="form-input-error" cols="100" rows="10" disabled="true"/>
        <form:errors path="message" cssClass="form-error"/>
    </div>

    <div class="default-block">
        <form:label path="numberOfParticipants"><spring:message code="courserequest.numberofparticipants"/></form:label>
        <form:select path="numberOfParticipants" cssClass="form-input" cssErrorClass="form-input-error" disabled="true">
            <option value="-1"></option>
            <form:options items="${numberOfParticipants}"/>
        </form:select>
        <form:errors path="numberOfParticipants" cssClass="form-error"/>
    </div>
</form:form>
