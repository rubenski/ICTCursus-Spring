<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<h1>${course.name}</h1>


<c:choose>
    <c:when test="${!courseRequestSubmitSuccess}">
        <div class="prettymain">
                ${course.longDescription}
        </div>

        <div class="prettymain">
            <h3><spring:message code="course.field.duration"/></h3>
            <p>${course.duration}</p>
        </div>

        <div class="prettymain">
            <h3><spring:message code="course.field.price"/></h3>
            <p>&#0128; <fmt:formatNumber value="${course.price / 100}" pattern="#####.##"/></p>
        </div>

        <div class="prettymain">
            <h3><spring:message code="course.field.regions.header"/></h3>
            <p>
                <c:forEach items="${course.regions}" var="region" varStatus="status" >
                    ${region}<c:if test="${!status.last}">,</c:if>
                </c:forEach>
            </p>
        </div>

        <div class="prettymain">
            <c:if test="${scrolldown}">
                <span id="scrollhere"/>
            </c:if>
            <h3><spring:message code="course.formheader"/></h3>

            <spring:message code="course.contactform.text" arguments="${course.company.name}"/>
            <form:form method="post" modelAttribute="courseRequest">

                <form:hidden path="courseId"/>
                <form:hidden path="courseName" value="${course.name}"/>

                <%-- error / success feedback --%>
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
                                <form:input path="requesterName" cssClass="form-input" cssErrorClass="form-input-error" cssStyle="width:229px" maxlength="60"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                &nbsp;
                            </td>
                            <td>
                                <form:errors path="requesterName" cssClass="form-error"/>
                            </td>
                        </tr>
                    </table>
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
                        <option value="-1"></option>
                        <form:options items="${numberOfParticipants}"/>
                    </form:select>
                    <form:errors path="numberOfParticipants" cssClass="form-error"/>
                </div>

                <div class="default-block">
                    <input type="submit" class="submitbutton" value="<spring:message code="generic.message.save"/>"/>
                </div>
            </form:form>
        </div>
    </c:when>
    <c:otherwise>

        <div class="prettymain">
            <p><spring:message code="submit.request.success" arguments="${course.company.name},${courseRequest.email}"/></p>
        </div>

    </c:otherwise>
</c:choose>

