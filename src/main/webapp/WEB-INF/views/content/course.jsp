<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>





<c:choose>
    <c:when test="${!isSuccessView}">

        <h1>${course.name}</h1>

        ${course.longDescription}


        <c:if test="${scrolldown}">
            <span id="scrollhere"/>
        </c:if>
        <h3><spring:message code="course.formheader"/></h3>

        <spring:message code="course.contactform.text" arguments="${course.company.name}"/>
        <form:form method="post" modelAttribute="courseRequest">

            <form:hidden path="courseId"/>
            <form:hidden path="courseName" value="${course.name}"/>

            <jsp:include page="../forms/submitmessage.jsp"/>

            <div class="default-block">
                <form:label path="requesterName"><spring:message code="user.genericlabel.name"/></form:label>
                <table cellpadding="0" cellspacing="0">
                    <tr>
                        <td>
                            <form:select path="prefix" cssStyle="width:65px">
                                <form:options items="${prefixes}" itemValue="id" itemLabel="prefix"/>
                            </form:select>
                        </td>
                        <td>
                            <form:input path="requesterName" cssClass="form-input" cssErrorClass="form-input-error" size="47" maxlength="60"/>
                        </td>
                    </tr>
                </table>
            </div>
            <form:errors path="requesterName" cssClass="form-error"/>

            <div class="default-block">
                <form:label path="company"><spring:message code="user.genericlabel.company"/></form:label>
                <form:input path="company" cssClass="form-input" cssErrorClass="form-input-error" maxlength="60" size="60"/>
                <form:errors path="company" cssClass="form-error"/>
            </div>

            <div class="default-block">
                <form:label path="email"><spring:message code="user.genericlabel.email"/></form:label>
                <form:input path="email" cssClass="form-input" cssErrorClass="form-input-error" maxlength="60" size="60"/>
                <form:errors path="email" cssClass="form-error"/>
            </div>

            <div class="default-block">
                <form:label path="phone"><spring:message code="user.genericlabel.phone"/></form:label>
                <form:input path="phone" cssClass="form-input" cssErrorClass="form-input-error" maxlength="60" size="60"/>
                <form:errors path="phone" cssClass="form-error"/>
            </div>

            <div class="default-block">
                <form:label path="numberOfParticipants"><spring:message code="courserequest.numberofparticipants"/></form:label>
                <form:select path="numberOfParticipants" cssClass="form-input" cssErrorClass="form-input-error">
                    <option value="-1"></option>
                    <form:options items="${numberOfParticipants}"/>
                </form:select>
                <form:errors path="numberOfParticipants" cssClass="form-error"/>
            </div>

            <div class="default-block">
                <form:label path="message"><spring:message code="courserequest.message"/></form:label>
                <form:textarea path="message" cssClass="form-input" cssErrorClass="form-input-error" cols="70" rows="8" maxlength="1000"/>
                <form:errors path="message" cssClass="form-error"/>
            </div>

            <div class="default-block">
                <input type="submit" class="submitbutton" value="<spring:message code="generic.message.send"/>"/>
            </div>
        </form:form>

    </c:when>
    <c:otherwise>
        <p><spring:message code="submit.request.success" arguments="${course.company.name}"/></p>
    </c:otherwise>
</c:choose>

