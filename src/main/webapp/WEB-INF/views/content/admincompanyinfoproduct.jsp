<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../components/adminproductsettingstabs.jsp"/>
<div class="default-block">

    <h2><spring:message code="products.listingtype.title"/></h2>

    <form:form modelAttribute="company">

        <spring:message code="products.companyinfo.explanation"/>


        <spring:message code="products.companyinfo.explanation" var="companyInfoLabel" htmlEscape="false"/>
        <span class="inlineCheckBox">
        <form:checkbox path="companyInfoActive" label="${companyInfoLabel}" htmlEscape="false" cssClass="inlineCheckBox"/>
        </span>



    </form:form>
</div>
