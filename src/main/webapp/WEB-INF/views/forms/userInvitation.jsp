<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<form:form method="post" modelAttribute="userInvitation" id="userInvitationForm">


    <%-- error / success feedback --%>
    <c:if test="${courseValid != ''}">
        <c:if test="${courseValid == 'true'}">
            <div id="validMessage" class="default-block"><spring:message code="course.form.valid"/></div>
        </c:if>
        <c:if test="${courseValid == 'false'}">
            <div id="invalidMessage" class="default-block"><spring:message code="course.form.invalid"/></div>
        </c:if>
    </c:if>

    <%-- first name --%>
    <div class="default-block">
        <form:label path="firstName">
            <spring:message code="user.genericlabel.firstname"/>
        </form:label>
        <form:input path="firstName" cssErrorClass="form-input-error"/>
        <form:errors path="firstName" cssClass="form-error"/>
    </div>

    <%-- last name --%>
    <div class="default-block">
        <form:label path="lastName">
            <spring:message code="user.genericlabel.lastname"/>
        </form:label>
        <form:input path="lastName" cssErrorClass="form-input-error"/>
        <form:errors path="lastName" cssClass="form-error"/>
    </div>

    <%-- email --%>
    <div class="default-block">
        <form:label path="email">
            <spring:message code="user.genericlabel.email"/>
        </form:label>
        <form:input path="email" cssErrorClass="form-input-error"/>
        <form:errors path="email" cssClass="form-error"/>
    </div>

    <%-- submit --%>
    <div class="default-block">
        <div class="form-entry">
            <input type="submit" value="Verzenden"/>
        </div>
    </div>

</form:form>