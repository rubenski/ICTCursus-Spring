<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div itemscope itemtype ="http://schema.org/Movie>
    <h1>${course.name}</h1>

    <p>${course.longDescription}</p>


    <h2><spring:message code="course.field.duration"/></h2>

    <p>${course.duration}</p>


    <h2><spring:message code="course.field.price"/></h2>

    <p>&#0128; <fmt:formatNumber value="${course.price / 100}" pattern="#####.##"/></p>


    <form:form method="post" modelAttribute="courseRequest">

        <form:hidden path="id"/>

        <%-- error / success feedback --%>
        <jsp:include page="../forms/submitmessage.jsp"/>

        <div class="default-block">
            <form:label path="name"><spring:message code="user.genericlabel.name"/></form:label>
            <form:select path="prefix" cssStyle="width:65px">
                <form:options items="${prefixes}" itemValue="id" itemLabel="prefix"/>
            </form:select>

            <form:input path="name" cssClass="form-input" cssErrorClass="form-input-error" cssStyle="width:229px" maxlength="60"/>
            <form:errors path="name" cssClass="form-error"/>
        </div>

        <div class="default-block">
            <form:label path="email"><spring:message code="user.genericlabel.email"/></form:label>
            <form:input path="email" cssClass="form-input" cssErrorClass="form-input-error" maxlength="60" cssStyle="width:300px"/>
            <form:errors path="email" cssClass="form-error"/>
        </div>

        <div class="default-block">
            <form:label path="message"><spring:message code="courserequest.message"/></form:label>
            <form:textarea path="message" cssClass="form-input" cssErrorClass="form-input-error" cssStyle="width:300px" rows="5"/>
            <form:errors path="message" cssClass="form-error"/>
        </div>

        <div class="default-block">
            <form:label path="numberOfParticipants"><spring:message code="courserequest.numberofparticipants"/></form:label>
            <form:select path="numberOfParticipants" cssClass="form-input" cssErrorClass="form-input-error">
                <form:options items="${numberOfParticipants}"/>
            </form:select>
            <form:errors path="numberOfParticipants" cssClass="form-error"/>
        </div>

        <div class="default-block">
            <input type="submit" class="submitbutton" value="<spring:message code="generic.message.save"/>"/>
        </div>

    </form:form>
</div>
