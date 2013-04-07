<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:if test="${!userProfile.emailEditable}">
    <c:set var="disabled" value="disabled"/>
</c:if>

<form:form method="post" modelAttribute="otherUserProfile">

    <%-- error / success feedback --%>
    <jsp:include page="submitmessage.jsp"/>

    <form:hidden path="companyId"/>
    <form:hidden path="email"/>
    <form:hidden path="id"/>

    <div class="default-block">
        <form:label path="email"><spring:message code="user.genericlabel.email"/></form:label>
        <input cssClass="form-input" cssErrorClass="form-input-error" size="60" maxlength="60" disabled="true" value="${otherUserProfile.email}"/>
        <form:errors path="email" cssClass="form-error"/>
    </div>


    <div class="default-block">
        <form:label path="roles">
            <spring:message code="user.genericlabel.access"/>
            <span><spring:message code="user.invitation.roles.helptext"/></span>
        </form:label>
        <c:forEach items="${assignableRoles}" var="role">
            <span class="blockCheckBox"><form:checkbox path="roles" value="${role.id}" label="${role.displayName}"/></span><br/>
        </c:forEach>
        <form:errors path="roles" cssClass="form-error"/>
    </div>


    <div class="default-block">
        <form:label path="enabled"><spring:message code="generic.message.status"/></form:label>
        <form:select path="enabled" cssClass="form-input" cssErrorClass="form-input-error">
            <form:options items="${statuses}" itemValue="enabled" itemLabel="name"/>
        </form:select>
    </div>


    <div class="default-block">
        <input type="submit" class="submitbutton" value="Verzenden"/>
    </div>

</form:form>