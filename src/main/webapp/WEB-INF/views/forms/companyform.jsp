<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form:form method="post" modelAttribute="bindableCompany" id="courseForm">

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

    <div class="default-block">
        <form:label path="name"><spring:message code="company.form.name"/></form:label>
        <form:input path="name" cssClass="form-input" cssErrorClass="form-input-error" size="60" maxlength="60"/>
        <form:errors path="name" cssClass="form-error"/>
    </div>
    <div class="default-block logo">
        <form:label path="logoFileName"><spring:message code="company.form.logo"/></form:label>
        <a href="#" class="lightbox"><spring:message code="company.upload.logo"/></a>

        <div id="companyLogo"></div>
    </div>
    <div class="default-block">
        <form:label path="address"><spring:message code="company.form.address"/></form:label>
        <form:input path="address" cssClass="form-input" cssErrorClass="form-input-error" size="60" maxlength="60"/>
        <form:errors path="address" cssClass="form-error"/>
    </div>
    <div class="default-block">
        <form:label path="zipCode"><spring:message code="company.form.zipcode"/></form:label>
        <form:input path="zipCode" cssClass="form-input" cssErrorClass="form-input-error" size="6" maxlength="6"/>
        <form:errors path="zipCode" cssClass="form-error"/>
    </div>
    <div class="default-block">
        <form:label path="city"><spring:message code="company.form.city"/></form:label>
        <form:input path="city" cssClass="form-input" cssErrorClass="form-input-error" size="30" maxlength="30"/>
        <form:errors path="city" cssClass="form-error"/>
    </div>
    <div class="default-block">
        <form:label path="country"><spring:message code="company.form.country"/></form:label>
        <form:select path="country" cssErrorClass="form-input-error">
            <form:option value="-1" label=""/>
            <form:options items="${countries}" itemValue="code" itemLabel="name"/>
        </form:select>
        <form:errors path="country" cssClass="form-error"/>
    </div>
    <div class="default-block">
        <form:label path="email"><spring:message code="company.form.email"/></form:label>
        <form:input path="email" cssClass="form-input" cssErrorClass="form-input-error" size="50" maxlength="50"/>
        <form:errors path="email" cssClass="form-error"/>
    </div>
    <div class="default-block">
        <form:label path="phone"><spring:message code="company.form.phone"/></form:label>
        <form:input path="phone" cssClass="form-input" cssErrorClass="form-input-error" size="15" maxlength="15"/>
        <form:errors path="phone" cssClass="form-error"/>
    </div>
    <div class="default-block">
        <form:label path="vatNumber"><spring:message code="company.form.vatnumber"/></form:label>
        <form:input path="vatNumber" cssClass="form-input" cssErrorClass="form-input-error" size="15" maxlength="20"/>
        <form:errors path="vatNumber" cssClass="form-error"/>
    </div>
    <div class="default-block">
        <form:label path="chamberOfCommerceNumber"><spring:message code="company.form.chamberofcommercenumber"/></form:label>
        <form:input path="chamberOfCommerceNumber" cssClass="form-input" cssErrorClass="form-input-error" size="15" maxlength="15"/>
        <form:errors path="chamberOfCommerceNumber" cssClass="form-error"/>
    </div>
    <div class="default-block">
        <form:label path="description"><spring:message code="company.form.description"/></form:label>
        <form:textarea rows="12" path="description" cssErrorClass="form-input-error" cols="100"/>
        <form:errors path="description" cssClass="form-error"/>
    </div>


    <div class="default-block">
        <input type="submit" class="submitbutton" value="Verzenden"/>
    </div>

</form:form>

<div id="lightbox" style="display: none;"></div>

<div id="lightbox-panel">
    <form:form method="post" target="upload-result" id="uploadForm" enctype="multipart/form-data" action="/admin/logo/upload" modelAttribute="bindableFileUpload">
        <div class="default-block">
            <form:label path="fileData"><spring:message code="company.upload.logo"/></form:label>
            <form:input type="file" path="fileData"/>
            <form:errors path="fileData" cssClass="form-error"/>
            <br/><br/>
            <input type="submit" value="upload" class="subbutton" id="uploadbutton"/>
        </div>
    </form:form>

    <p align="center">
        <a id="close-panel" href="#">Sluiten</a>
    </p>
</div>

<iframe name="upload-result" id="upload-result-frame">

</iframe>

