<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>


<head>
    <script type="text/javascript" src="/static/javascript/jquery-1.8.3.min.js"></script>
    <link rel="stylesheet" href="/static/libs/jquery-ui-1.10.1.custom/css/smoothness/jquery-ui-1.10.1.custom.min.css"/>
    <script type="text/javascript" src="/static/libs/jquery-ui-1.10.1.custom/js/jquery-ui-1.10.1.custom.min.js"></script>
    <script type="text/javascript" src="/static/libs/jquery-ui-1.10.1.custom/js/datepicker-lang/jquery.ui.datepicker-nl.js"></script>
    <link href="/static/style/shared.css" rel="stylesheet">
    <link href="/static/style/style.css" rel="stylesheet">

    <script language="JavaScript">
        $(function() {
            $("#startDate").datepicker({ minDate: new Date(${startYear}, ${startMonth}, ${startDay}), maxDate: new Date(${endYear}, ${endMonth}, ${endDay})});
        });
    </script>
</head>
<body>

<c:if test="${hasHighlightedCourses}">
    <div class="pretty">
        <h2><spring:message code="course.highlight.yourhighlightedcourses" arguments="${category.name}"/></h2>
        <spring:message code="currently.highlighted.for.company.text" arguments="${category.name}"/>
        <table width="100%">
            <tr>
                <th><spring:message code="generic.userMessage.course"/></th>
                <th><spring:message code="highlighted.course.startdate"/></th>
                <th><spring:message code="highlighted.course.enddate"/></th>
            </tr>
            <c:forEach items="${highlightedCoursesForCompany}" var="highlightedCourse">
                <tr>
                    <td><a href="${highlightedCourse.course.adminUrl}" target="_top">${highlightedCourse.course.name}</a></td>
                    <td><fmt:formatDate value="${highlightedCourse.startTime.time}" type="date" pattern="dd-MM-yyyy"/></td>
                    <td><fmt:formatDate value="${highlightedCourse.endTime.time}" type="date" pattern="dd-MM-yyyy"/></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</c:if>


<div class="pretty">
    <form:form action="/admin/highlightcourses/${category.id}" method="post" class="pretty" modelAttribute="highlightCourseFormData">
        <c:choose>
            <c:when test="${hasEligibleCourses}">

                <form:hidden path="category" value="${category.id}"/>

                <c:if test="${!advertiseToday}">
                    <div class="default-block warn">${helpMessageDate}</div>
                </c:if>

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
            </c:when>
            <c:otherwise>
                <spring:message code="highlight.courses.no.eligible.courses" arguments="${category.name}"/>
            </c:otherwise>
        </c:choose>
    </form:form>
</div>
</body>
</html>