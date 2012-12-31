<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<form:form method="post" modelAttribute="bindableCourse" id="courseForm">

    <%-- tags --%>
    <c:forEach items="${bindableCourse.tags}" var="tag">
        <input type="hidden" name="tags" value="${tag}"/>
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
        <form:textarea rows="20" path="longDescription" cssErrorClass="form-input-error" cols="100"/>
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
    <div class="default-block checkboxlist">
        <form:label path="regions">
            <spring:message code="course.form.regions"/>
            <span><spring:message code="course.form.regions.helptext"/></span>
        </form:label>
        <c:forEach items="${allRegions}" var="region">
            <span class="regionCheckBox"><form:checkbox path="regions" value="${region.id}" label="${region.name}"/></span>
        </c:forEach>
        <div><br></rb><a href="#" id="selectAllRegions"><spring:message code="course.form.regions.selectall.linktext"/></a><br><br></div>
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

    <%-- tags --%>
    <div class="default-block">
        <form:label path="tags">
            <spring:message code="course.form.tags"/>
            <span><spring:message code="course.form.tags.helptext"/></span>
        </form:label>
        <input autocomplete="off" size="40" id="tagSelection"/>
        &nbsp;
        <a href="#" id="addTag"><spring:message code="course.form.tags.add"/></a>

        <div id="selectedTags"></div>
        <span id="tagLengthError"><spring:message code="course.form.tags.taglengtherror"/></span>
        <span id="tagCharactersError"><spring:message code="course.form.tags.tagCharactersError"/></span>
    </div>

    <%-- submit button --%>
    <div class="default-block">
        <input type="submit" value="Verzenden"/>
    </div>

</form:form>
