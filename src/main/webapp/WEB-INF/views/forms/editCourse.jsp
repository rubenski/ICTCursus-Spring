<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<form:form method="post" modelAttribute="bindableCourse" id="courseForm">

    <%-- Add hidden tag fields --%>
    <c:forEach items="${bindableCourse.tags}" var="tag">
        <input type="hidden" name="tags" value="${tag}"/>
    </c:forEach>

    <%-- Add hidden date fields --%>
    <c:forEach items="${bindableCourse.dates}" var="date">
        <input type="hidden" name="dates" value="${date}"/>
    </c:forEach>

    <%-- a hidden id --%>
    <form:hidden path="id"/>

    <%-- error / success feedback --%>
    <c:if test="${courseValid != ''}">
        <c:if test="${courseValid == 'true'}">
            <div id="validMessage" class="default-block"><spring:message code="course.form.valid"/></div>
        </c:if>
        <c:if test="${courseValid == 'false'}">
            <div id="invalidMessage" class="default-block"><spring:message code="course.form.invalid"/></div>
        </c:if>
    </c:if>

    <%-- name --%>
    <div class="default-block">
        <form:label path="name">
            <spring:message code="course.form.name"/>
        </form:label>
        <form:input path="name" cssErrorClass="form-input-error" size="78"/>
        <form:errors path="name" cssClass="form-error"/>
    </div>

    <%-- short description --%>
    <div class="default-block">
        <form:label path="shortDescription">
            <spring:message code="course.form.shortdescription"/>
            <span><spring:message code="course.form.shortdescription.helptext"/></span>
        </form:label>
        <form:textarea rows="3" path="shortDescription" cssErrorClass="form-input-error" cols="100"/>
        <form:errors path="shortDescription" cssClass="form-error"/>
    </div>

    <%-- long description --%>
    <div class="default-block">
        <form:label path="longDescription"><spring:message code="course.form.longdescription"/></form:label>
        <form:textarea rows="20" path="longDescription" cssErrorClass="form-input-error" cols="100" cssClass="richtext"/>
        <form:errors path="longDescription" cssClass="form-error"/>
    </div>

    <%-- duration --%>
    <div class="default-block">
        <form:label path="duration">
            <spring:message code="course.form.duration"/>
            <span><spring:message code="course.form.duration.helptext"/></span>
        </form:label>
        <form:textarea rows="2" path="duration" cssErrorClass="form-input-error" cols="100"/>
        <form:errors path="duration" cssClass="form-error"/>
    </div>

    <%-- regions --%>
    <div class="default-block">
        <form:label path="regions">
            <spring:message code="course.form.regions"/>
            <span><spring:message code="course.form.regions.helptext"/></span>
        </form:label>
        <c:forEach items="${allRegions}" var="region">
            <span class="inlineCheckBox"><form:checkbox path="regions" value="${region.id}" label="${region.name}"/></span>
        </c:forEach>
        <div><br><a href="#" id="selectAllRegions"><spring:message code="course.form.regions.selectall.linktext"/></a><br><br></div>
        <form:errors path="regions" cssClass="form-error"/>
    </div>

    <%-- price (formatted) --%>
    <div class="default-block">
        <form:label path="formattedPrice"><spring:message code="course.form.price"/></form:label>
        <c:set var="localeCode" value="${pageContext.response.locale}"/>
        <form:input path="formattedPrice" cssErrorClass="form-input-error" size="10"/>
        <spring:message code="course.form.currencysymbol"/> <spring:message code="course.form.price.perperson"/>
        <form:errors path="formattedPrice" cssClass="form-error"/>
    </div>

    <%-- category --%>
    <div class="default-block">
        <form:label path="category"><spring:message code="course.form.category"/></form:label>
        <form:select path="category" cssErrorClass="form-input-error">
            <form:option value="-1" label=""/>
            <form:options items="${categories}" itemValue="id" itemLabel="name"/>
        </form:select>
        <form:errors path="category" cssClass="form-error"/>
    </div>

    <%-- dates --%>
    <div class="default-block">
        <form:label path="dates"><spring:message code="course.form.dates"/></form:label>
        <input autocomplete="off" size="40" id="dateSelection"/>
        <a href="#" id="addDateButton"><spring:message code="course.form.buttontext.add"/></a>

        <div id="selectedDates" class="selectionContainer">
            <table></table>
        </div>
        <span id="dateError" class="hide"><spring:message code="course.form.dateError"/></span>
    </div>

    <%-- times --%>
    <div class="default-block">
        <form:label path="times"><spring:message code="course.form.times"/></form:label>
        <c:forEach items="${times}" var="time">
            <span class="inlineCheckBox"><form:checkbox path="times" value="${time.id}" label="${time.name}"/></span>
        </c:forEach>
        <form:errors path="times" cssClass="form-error"/>
    </div>

    <%-- tags --%>
    <div class="default-block">
        <form:label path="tags">
            <spring:message code="course.form.tags"/>
            <span><spring:message code="course.form.tags.helptext"/></span>
        </form:label>
        <input autocomplete="off" size="40" id="tagSelection"/>
        <a href="#" id="addTag"><spring:message code="course.form.buttontext.add"/></a>

        <div id="selectedTags" class="selectionContainer">
            <table></table>
        </div>
        <span id="tagError" class="hide"><spring:message code="course.form.tags.tagerror"/></span>
    </div>

    <%-- Options --%>
    <div class="default-block">
        <form:label path="options">
            <spring:message code="course.form.properties"/>
            <span><spring:message code="course.form.properties.helptext"/></span>
        </form:label>
        <c:forEach items="${optionCategories}" var="optionCategory">
            <div class="option-category">
                <strong>${optionCategory.name}</strong>
                <c:forEach items="${optionCategory.options}" var="option">
                    <div><form:checkbox path="options" value="${option.id}" label="${option.displayName}"/></div>
                </c:forEach>
            </div>
        </c:forEach>
    </div>

    <%-- Certificaat / diploma --%>
    <div class="default-block">
        <form:label path="certificate">
            <spring:message code="course.form.certificate"/>
        </form:label>
        <fmt:message key="course.form.certificate.label" var="certificatelabel"/>
        <span class="inlineCheckBox"><form:checkbox id="certificate-checkbox" path="certificate" label="${certificatelabel}"/></span>
        <div id="certificate-name">
            <span><spring:message code="course.form.certificatename.text"/></span>
            <form:input path="certificateName" size="20"/>
        </div>
    </div>

    <%-- certificate / diploma description --%>
    <div class="default-block">
        <form:label path="certificateText">
            <spring:message code="course.form.certificatetext"/>
            <span><spring:message code="course.form.certificatehelptext"/></span>
        </form:label>
        <form:textarea rows="3" path="certificateText" cssErrorClass="form-input-error" cols="100"/>
        <form:errors path="certificateText" cssClass="form-error"/>
    </div>

    <%-- submit button --%>
    <div class="default-block">
        <input type="submit" value="Opslaan"/>
    </div>



</form:form>
