<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../components/productsettingstabs.jsp"/>
<div class="default-block">

    <h2><spring:message code="products.listingtype.title"/></h2>

    <form:form modelAttribute="productSettings">

        <spring:message code="products.listingtype.explanation"/>

        <form:select path="courseListingType">
            <form:options items="${productSettings.listingTypes}"/>
        </form:select>

        <br/>

        <spring:message code="products.companyinfo.explanation" var="companyInfoLabel" htmlEscape="false"/>
        <span class="inlineCheckBox">
        <form:checkbox path="companyInfoActive" label="${companyInfoLabel}" htmlEscape="false" cssClass="inlineCheckBox"/>
        </span>


        <br/>

        <spring:message code="products.directlinks.explanation" var="externalCourseLinksLabel" htmlEscape="false"/>
        <span class="inlineCheckBox">
            <form:checkbox path="externalCourseLinksActive" label="${externalCourseLinksLabel}" htmlEscape="false"/>
        </span>

    </form:form>
</div>
