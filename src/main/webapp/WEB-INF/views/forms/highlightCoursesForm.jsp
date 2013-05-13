<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script type="text/javascript" src="/static/javascript/jquery-1.8.3.min.js"></script>
<link rel="stylesheet" href="/static/libs/jquery-ui-1.10.1.custom/css/smoothness/jquery-ui-1.10.1.custom.min.css"/>
<script type="text/javascript" src="/static/libs/jquery-ui-1.10.1.custom/js/jquery-ui-1.10.1.custom.min.js"></script>
<script type="text/javascript" src="/static/libs/jquery-ui-1.10.1.custom/js/datepicker-lang/jquery.ui.datepicker-nl.js"></script>
<link href="/static/style/shared.css" rel="stylesheet">
<link href="/static/style/lightboxform.css" rel="stylesheet">

<fmt:formatDate var="formattedStartDate" value="${dateRange.startDate.time}" type="date" pattern="yyyy,M,d"/>
<fmt:formatDate var="formattedEndDate" value="${dateRange.endDate.time}" type="date" pattern="yyyy,M,d"/>


<script language="JavaScript">
    $(function() {
        var startDate = new Date(${formattedStartDate});
        $("#startDate").datepicker({ minDate: new Date(${startYear}, ${startMonth}, ${startDay}), maxDate: new Date(${endYear}, ${endMonth}, ${endDay})});
    });
</script>


<c:choose>
    <c:when test="${hasEligibleCourses}">
        <form:form action="/admin/highlightcourses/${category.id}" method="post" class="pretty" modelAttribute="highlightCourseFormData">
            <form:hidden path="category" value="${category.id}"/>

            <div class="default-block">
                <label><spring:message code="course.highlight.select.course" arguments="${category.name}"/></label>
                <span class="helptext">${helpMessageCourses}</span>
                <form:select path="course">
                    <c:forEach items="${courses}" var="course">
                        <form:option value="${course.id}" label="${course.name}"/>
                    </c:forEach>
                </form:select>
            </div>
            <div class="default-block">
                <label>
                    <spring:message code="course.highlight.fromdate"/>
                </label>
                <c:if test="${!advertiseToday}">
                    <span class="helptext">${helpMessageDate}</span>
                </c:if>
                <form:input type="text" path="startDate" id="startDate"/>
                <form:errors path="startDate" cssClass="form-error"/>
            </div>
            <div class="default-block">
                <label><spring:message code="course.highlight.numberofmonths"/></label>
                <select name="numberOfMonths">
                    <c:forEach items="${monthsOptions}" var="option">
                        <option value="${option}">${option}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="default-block">
                <input type="submit" id="formsubmit" value="<spring:message code='generic.message.next'/>"/>
            </div>
        </form:form>
    </c:when>
    <c:otherwise>
        <spring:message code="highlight.courses.no.eligible.courses" arguments="${category.name}"/>
    </c:otherwise>
</c:choose>