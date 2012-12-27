<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<form:form method="post" modelAttribute="bindableCourse" id="courseForm">
    <c:forEach items="${bindableCourse.tags}" var="tag">
        <input type="hidden" name="tags" value="${tag}"/>
    </c:forEach>
    <div id="data-entry-form">
        <form:hidden path="id"/>
        <div class="form-entry">
            <form:label  path="name">
                <spring:message code="course.form.name"/>
            </form:label>
            <form:input path="name" cssErrorClass="form-input-error" size="78"/>
            <form:errors path="name" cssClass="form-error"/>
        </div>
        <div class="form-entry">
            <form:label  path="shortDescription">
                <spring:message code="course.form.shortdescription"/>
                <span><spring:message code="course.form.shortdescription.helptext"/></span>
            </form:label>
            <form:textarea rows="3" path="shortDescription" cssErrorClass="form-input-error" cols="60"/>
            <form:errors path="shortDescription" cssClass="form-error"/>
        </div>
        <div class="form-entry">
            <form:label  path="longDescription"><spring:message code="course.form.longdescription"/></form:label>
            <form:textarea rows="20" path="longDescription" cssErrorClass="form-input-error" cols="60"/>
            <form:errors path="longDescription" cssClass="form-error"/>
        </div>
        <div class="form-entry">
            <form:label  path="duration">
                <spring:message code="course.form.duration"/>
                <span><spring:message code="course.form.duration.helptext"/></span>
            </form:label>
            <form:textarea rows="2" path="duration" cssErrorClass="form-input-error" cols="60"/>
            <form:errors path="duration" cssClass="form-error"/>
        </div>
        <div class="form-entry checkboxlist">
            <form:label  path="regions">
                <spring:message code="course.form.regions"/>
                <span><spring:message code="course.form.regions.helptext"/></span>
            </form:label>
            <c:forEach items="${allRegions}" var="region">
                <span class="regionCheckBox"><form:checkbox path="regions" value="${region.id}" label="${region.name}" /></span>
            </c:forEach>
            <form:errors path="duration" cssClass="form-error"/>
        </div>
        <div class="form-entry">
            <form:label  path="price"><spring:message code="course.form.price"/></form:label>
            <form:input path="price" cssErrorClass="form-input-error" size="10"/>
            <form:errors path="price" cssClass="form-error"/>
        </div>
        <div class="form-entry">
            <form:label  path="category"><spring:message code="course.form.category"/></form:label>
            <form:select path="category">
                <form:option value="-1" label=""/>
                <form:options items="${categories}" itemValue="id" itemLabel="name"/>
            </form:select>
            <form:errors path="category" cssClass="form-error"/>
        </div>
        <div class="form-entry">
            <form:label  path="tags">
                <spring:message code="course.form.tags"/>
                <span><spring:message code="course.form.tags.helptext"/></span>
            </form:label>
            <input autocomplete="off" size="40" id="tagSelection"/>
            &nbsp;
            <a href="#" id="addTag"><spring:message code="course.form.tags.add"/></a>
            <div id="selectedTags"></div>
            <span id="tagLengthError"><spring:message code="course.form.tags.taglengtherror"/></span>
            <span id="tagCharactersError"><spring:message code="course.form.tags.tagCharactersError"/></span>
            <form:errors path="tags" cssClass="form-error"/>
        </div>
        <div class="form-entry">
            <input type="submit" value="Verzenden"/>
        </div>
    </div>
</form:form>
